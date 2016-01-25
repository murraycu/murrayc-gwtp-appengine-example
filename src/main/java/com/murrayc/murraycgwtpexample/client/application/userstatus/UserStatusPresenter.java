package com.murrayc.murraycgwtpexample.client.application.userstatus;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.client.LoginInfo;
import com.murrayc.murraycgwtpexample.client.LoginServiceAsync;
import com.murrayc.murraycgwtpexample.client.ThingServiceAsync;
import com.murrayc.murraycgwtpexample.shared.db.UserProfile;

/**
 * Created by murrayc on 1/21/16.
 */
public class UserStatusPresenter extends PresenterWidget<UserStatusPresenter.MyView>
        implements UserStatusUserEditUiHandlers {
    public interface MyView extends View, HasUiHandlers<UserStatusUserEditUiHandlers> {
        void setUserStatus(final UserProfile result);

        void setLoginInfo(final LoginInfo result);

        void setUserStatusFailed();
    }

    @Inject
    UserStatusPresenter(
            EventBus eventBus,
            MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);

        final AsyncCallback<UserProfile> callback = new AsyncCallback<UserProfile>() {
            @Override
            public void onFailure(final Throwable caught) {
                // TODO: create a way to notify users of asynchronous callback failures
                Log.error("AsyncCallback Failed: getUserProfile(): " + caught.getMessage());
                getView().setUserStatusFailed();
            }

            @Override
            public void onSuccess(final UserProfile result) {
                //TODO: Throw an exception instead of returning null?
                if(result == null) {
                    //getView().setServerFailed();
                } else {
                    getView().setUserStatus(result);
                }
            }
        };

        ThingServiceAsync.Util.getInstance().getUserProfile(callback);


        // Check login status using login service.
        LoginServiceAsync.Util.getInstance().login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
            public void onFailure(final Throwable error) {
                Log.error("AsyncCallback Failed: login(): " + error.getMessage());

                getView().setUserStatusFailed();
            }

            public void onSuccess(final LoginInfo result) {
                //TODO: Throw an exception instead of returning null?
                if(result == null) {
                    getView().setUserStatusFailed();
                } else {
                    getView().setLoginInfo(result);
                }
            }
        });
    }
}