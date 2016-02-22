package com.murrayc.murraycgwtpexample.client.application.userprofile;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.client.LoginInfo;
import com.murrayc.murraycgwtpexample.client.LoginServiceAsync;
import com.murrayc.murraycgwtpexample.client.NameTokens;
import com.murrayc.murraycgwtpexample.client.application.ApplicationPresenter;
import com.murrayc.murraycgwtpexample.client.application.userhistoryrecent.UserHistoryRecentPresenter;

/**
 * Created by murrayc on 1/21/16.
 */
public class UserProfilePresenter extends Presenter<UserProfilePresenter.MyView, UserProfilePresenter.MyProxy>
        implements UserProfileUserEditUiHandlers {
    private final UserHistoryRecentPresenter userHistoryRecentPresenter;

    interface MyView extends View, HasUiHandlers<UserProfileUserEditUiHandlers> {
        void setUserStatusFailed();

        void setLoginInfo(final LoginInfo result);
    }

    @ProxyStandard
    @NameToken(NameTokens.USER_PROFILE)
    interface MyProxy extends ProxyPlace<UserProfilePresenter> {
    }

    public static final SingleSlot SLOT_USER_HISTORY_RECENT = new SingleSlot();

    @Inject
    UserProfilePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            UserHistoryRecentPresenter userHistoryRecentPresenter) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.userHistoryRecentPresenter = userHistoryRecentPresenter;

        getView().setUiHandlers(this);

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

    @Override
    protected void onBind() {
        super.onBind();

        setInSlot(SLOT_USER_HISTORY_RECENT, userHistoryRecentPresenter);
    }
}