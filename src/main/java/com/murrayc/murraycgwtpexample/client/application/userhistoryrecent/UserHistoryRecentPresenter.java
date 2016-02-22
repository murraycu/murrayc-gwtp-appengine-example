package com.murrayc.murraycgwtpexample.client.application.userhistoryrecent;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.client.ThingServiceAsync;
import com.murrayc.murraycgwtpexample.client.UserRecentHistory;
import com.murrayc.murraycgwtpexample.client.application.thing.ThingUserAnswerAddedEvent;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;

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
 * Created by murrayc on 1/21/16.
 */
public class UserHistoryRecentPresenter extends PresenterWidget<UserHistoryRecentPresenter.MyView>
        implements UserHistoryRecentUserEditUiHandlers, ThingUserAnswerAddedEvent.EventHandler {

    public interface MyView extends View, HasUiHandlers<UserHistoryRecentUserEditUiHandlers> {
        /** Set a whole set of history.
         */
        void setUserRecentHistory(final UserRecentHistory result);

        /** Add a single item of history.
         * For instance, to avoid retrieving the whole history from the server,
         * if the new item is know already.
         *
         * @param userAnswer
         */
        void addUserAnswer(final UserAnswer userAnswer);

        void setServerFailed();


    }

    @Inject
    UserHistoryRecentPresenter(
            EventBus eventBus,
            MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);

        addRegisteredHandler(ThingUserAnswerAddedEvent.TYPE, this);

        getAndShowHistory();
    }

    @ProxyEvent
    @Override
    public void onThingUserAnswerAdded(final ThingUserAnswerAddedEvent event) {
        getView().addUserAnswer(event.getUserAnswer());
    }

    private void getAndShowHistory() {
        final AsyncCallback<UserRecentHistory> callback = new AsyncCallback<UserRecentHistory>() {
            @Override
            public void onFailure(final Throwable caught) {
                // TODO: create a way to notify users of asynchronous callback failures
                Log.error("AsyncCallback Failed: getUserRecentHistory(): " + caught.getMessage());
                getView().setServerFailed();
            }

            @Override
            public void onSuccess(final UserRecentHistory result) {
                //TODO: Throw an exception instead of returning null?
                if(result == null) {
                    //getView().setServerFailed();
                } else {
                    getView().setUserRecentHistory(result);
                }
            }
        };

        ThingServiceAsync.Util.getInstance().getUserRecentHistory(callback);
    }

}