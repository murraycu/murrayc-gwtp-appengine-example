package com.murrayc.murraycgwtpexample.server.db;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;
import com.murrayc.murraycgwtpexample.shared.db.UserProfile;

/**
 * Copyright (c) 2016 Murray Cumming
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
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
