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