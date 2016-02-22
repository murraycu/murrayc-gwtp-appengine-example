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
 */

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
