package com.murrayc.murraycgwtpexample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.murrayc.murraycgwtpexample.shared.Thing;
import com.murrayc.murraycgwtpexample.shared.db.UserProfile;

/**
 * The async counterpart of <code>ThingService</code>.
 */
public interface ThingServiceAsync {
    void getThing(String thingId, AsyncCallback<Thing> async)
            throws IllegalArgumentException;

    void getNextThing(AsyncCallback<Thing> async)
            throws IllegalArgumentException;

    void submitAnswer(final String thingId, final String answer, AsyncCallback<ThingService.SubmissionResult> async)
            throws IllegalArgumentException;

    void getUserProfile(AsyncCallback<UserProfile> async)
            throws IllegalArgumentException;

    void getUserRecentHistory(AsyncCallback<UserRecentHistory> async)
            throws IllegalArgumentException;

    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    final class Util {
        private static ThingServiceAsync instance;

        private Util() {
            // Utility class should not be instantiated
        }

        public static ThingServiceAsync getInstance() {
            if (instance == null) {
                instance = GWT.create(ThingService.class);
            }
            return instance;
        }
    }

}
