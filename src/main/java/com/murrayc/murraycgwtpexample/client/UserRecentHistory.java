package com.murrayc.murraycgwtpexample.client;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;

import java.util.List;

/**
 * Created by murrayc on 1/23/16.
 */
public class UserRecentHistory implements IsSerializable {
    private List<UserAnswer> userAnswers;

    UserRecentHistory() {

    }

    public UserRecentHistory(final List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }
}
