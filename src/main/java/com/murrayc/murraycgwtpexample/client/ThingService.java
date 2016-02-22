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

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.murrayc.murraycgwtpexample.shared.Thing;
import com.murrayc.murraycgwtpexample.shared.db.UserProfile;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("thing-service")
public interface ThingService extends RemoteService {

    Thing getThing(final String thingId) throws IllegalArgumentException;

    Thing getNextThing() throws IllegalArgumentException;

    /**
     * submitAnswer() returns the correct correctAnswer (if the supplied correctAnswer was wrong) and the next thing.
     * This avoids the client needing to make multiple calls.
     *
     * @param thingId
     * @param answer
     * @return
     * @throws IllegalArgumentException
     */
    SubmissionResult submitAnswer(final String thingId, final String answer) throws IllegalArgumentException;

    UserProfile getUserProfile() throws IllegalArgumentException;

    UserRecentHistory getUserRecentHistory() throws IllegalArgumentException;

    class SubmissionResult implements IsSerializable {
        private boolean result;
        private String correctAnswer; //If result is false.
        private Thing nextThing;

        public SubmissionResult() {
        }

        public SubmissionResult(final boolean result, final String correctAnswer, final Thing nextThing) {
            this.result = result;
            this.correctAnswer = correctAnswer;
            this.nextThing = nextThing;
        }

        public boolean getResult() {
            return result;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public Thing getNextThing() {
            return nextThing;
        }
    }
}
