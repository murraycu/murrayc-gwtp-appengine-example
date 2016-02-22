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