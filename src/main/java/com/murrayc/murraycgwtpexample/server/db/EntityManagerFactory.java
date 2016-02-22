package com.murrayc.murraycgwtpexample.server.db;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;
import com.murrayc.murraycgwtpexample.shared.db.UserProfile;

/**
 * Created by murrayc on 1/19/16.
 */
public class EntityManagerFactory {
    private static EntityManagerFactory singleton;

    static {
        //Register classes whose instances we want to store in the databse via Objectify:
        ObjectifyService.register(UserProfile.class);
        ObjectifyService.register(UserAnswer.class);
    }

    protected EntityManagerFactory() {
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static EntityManagerFactory get() {
        if (singleton == null) {
            singleton = new EntityManagerFactory();
        }
        return singleton;
    }
}
