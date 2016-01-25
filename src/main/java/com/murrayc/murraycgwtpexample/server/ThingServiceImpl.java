package com.murrayc.murraycgwtpexample.server;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.cmd.Query;
import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.client.ThingService;
import com.murrayc.murraycgwtpexample.client.UserRecentHistory;
import com.murrayc.murraycgwtpexample.server.db.EntityManagerFactory;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;
import com.murrayc.murraycgwtpexample.shared.Thing;
import com.murrayc.murraycgwtpexample.shared.ThingAndAnswer;
import com.murrayc.murraycgwtpexample.shared.db.UserProfile;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ThingServiceImpl extends ServiceWithUser implements
        ThingService {
    private static final String LOADED_THINGS = "loaded-things";

    //How many items to return.
    private static final int HISTORY_LIMIT = 5;

    /*
    public String greetServer(String input) throws IllegalArgumentException {
      // Verify that the input is valid.
      if (!FieldVerifier.isValidName(input)) {
        // If the input is not valid, throw an IllegalArgumentException back to
        // the client.
        throw new IllegalArgumentException(
            "Name must be at least 4 characters long");
      }

      String serverInfo = getServletContext().getServerInfo();
      String userAgent = getThreadLocalRequest().getHeader("UserProfile-Agent");

      // Escape data from the client to avoid cross-site script vulnerabilities.
      input = escapeHtml(input);
      userAgent = escapeHtml(userAgent);

      return "Hello, " + input + "!<br><br>I am running " + serverInfo
          + ".<br><br>It looks like you are using:<br>" + userAgent;
    }
    */
    private Things things;

    private static Things loadThings() {
        final Things result = new Things();
        result.addThing(new ThingAndAnswer("thing1", "Should a foo boo?", "no")); //Well obviously.
        result.addThing(new ThingAndAnswer("thing2", "Does a cow moo?", "yes"));
        result.addThing(new ThingAndAnswer("thing3", "Does a kanga roo ?", "yes"));

        return result;

        /* You might load something from an XML file, for instance: */
        /*
        try(final InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("things.xml")) {
            if (is == null) {
                final String errorMessage = "things.xml not found.";
                Log.fatal(errorMessage);
                return null;
            }

            return ThingsLoader.loadThings(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
        */
    }

    @Override
    public Thing getThing(final String thingId) throws IllegalArgumentException {
        final Things things = getThings();
        return things.getThing(thingId);
    }

    @Override
    public Thing getNextThing() throws IllegalArgumentException {
        final Things things = getThings();
        return things.getRandomThing();
    }

    @Override
    public SubmissionResult submitAnswer(final String thingId, final String answer) throws IllegalArgumentException {
        final ThingAndAnswer thingAndAnswer = getThingAndAnswer(thingId);
        if (thingAndAnswer == null) {
            throw new IllegalArgumentException("Unknown ThingAndAnswer ID");
        }

        //Store whether we got the thing right or wrong:
        final boolean result = StringUtils.equalsIgnoreCase(thingAndAnswer.getAnswer(), answer);
        storeAnswer(result, thingAndAnswer.getThing());

        return createSubmissionResult(result, thingId);
    }

    private ThingAndAnswer getThingAndAnswer(final String thingId) {
        final Things things = getThings();
        return things.getThingAndAnswer(thingId);
    }

    @Override
    public UserProfile getUserProfile() throws IllegalArgumentException {
        return getUserProfileImpl();
    }

    //@Override
    public UserRecentHistory getUserRecentHistory() throws IllegalArgumentException {
        final User user = getUser();
        if (user == null) {
            return null;
        }

        final EntityManagerFactory emf = EntityManagerFactory.get();
        Query<UserAnswer> q = emf.ofy().load().type(UserAnswer.class);
        q = q.filter("userId", user.getUserId());
        q = q.order("-time"); //- means descending.
        q = q.limit(HISTORY_LIMIT);


        //Objectify's Query.list() method seems to return a list implementation that contains
        //some kind of (non-serializable) proxy, leading to gwt compilation errors such as this:
        //  com.google.gwt.user.client.rpc.SerializationException: Type 'com.sun.proxy.$Proxy10' was not included in the set of types which can be serialized by this SerializationPolicy or its Class object could not be loaded. For security purposes, this type will not be serialized.: instance = [com.murrayc.murraycgwtpexample.shared.db.UserAnswer@7a44340b]
        //so we copy the items into a new list.
        //Presumably the act of iterating over the list causes us to actually get the data for each item,
        //as the actual type.
        //
        //This also gives us the opportunity to fill in the thing title,
        //which we want to give to the client, but which we didn't want to store
        //along with each UserAnswer.
        final List<UserAnswer> listCopy = new ArrayList<>();
        for (final UserAnswer a : q.list()) {
            if (a == null) {
                continue;
            }

            a.setThingTitle(getThingTitle(a.getThingId()));

            listCopy.add(a);
        }
        return new UserRecentHistory(listCopy);
    }

    private String getThingTitle(final String thingId) {
        final Things things = getThings();
        final Thing thing = things.getThing(thingId);
        if (thing == null) {
            return null;
        }

        return thing.getText();
    }

    private UserProfile getUserProfileImpl() {
        final User user = getUser();
        if (user == null) {
            return null;
        }

        final EntityManagerFactory emf = EntityManagerFactory.get();
        UserProfile userProfile = emf.ofy().load().type(UserProfile.class).id(user.getUserId()).now();
        if (userProfile == null) {
            userProfile = new UserProfile(user.getUserId(), user.getNickname());
            emf.ofy().save().entity(userProfile).now();
        }
        return userProfile;
    }

    private Things getThings() {
        if (things != null) {
            return things;
        }

        final ServletConfig config = this.getServletConfig();
        if (config == null) {
            Log.error("getServletConfig() return null");
            return null;
        }

        final ServletContext context = config.getServletContext();
        if (context == null) {
            Log.error("getServletContext() return null");
            return null;
        }

        //Use the existing shared things if any:
        final Object object = context.getAttribute(LOADED_THINGS);
        if ((object != null) && !(object instanceof Things)) {
            Log.error("The loaded-things attribute is not of the expected type.");
            return null;
        }

        things = (Things) object;
        if (things != null) {
            return things;
        }

        //Load it for the first time:
        try {
            things = loadThings();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

        context.setAttribute(LOADED_THINGS, things);

        return things;
    }

    private SubmissionResult createSubmissionResult(boolean result, final String thingId) {
        //We only provide the correct answer if the supplied answer was wrong:
        String correctAnswer = null;
        if (!result) {
            final Things things = getThings();
            correctAnswer = things.getAnswer(thingId);
        }

        final Thing nextThing = getNextThing();
        return new SubmissionResult(result, correctAnswer, nextThing);
    }

    private void storeAnswer(boolean result, final Thing thing) {
        final UserProfile userProfile = getUserProfileImpl();
        if (userProfile == null) {
            //TODO: Keep a score in the session, without a user profile?
            return;
        }

        final EntityManagerFactory emf = EntityManagerFactory.get();

        if (result) {
            userProfile.setCountCorrectAnswers(userProfile.getCountCorrectAnswers() + 1);

            emf.ofy().save().entity(userProfile).now();
        }

        final String time = getCurrentTime();
        final UserAnswer userAnswer = new UserAnswer(userProfile.getId(), thing, result, time);
        emf.ofy().save().entity(userAnswer).now();
    }

    private static String getCurrentTime() {
        //TODO: Performance:
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}
