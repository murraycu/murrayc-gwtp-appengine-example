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
