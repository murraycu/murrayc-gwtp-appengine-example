package com.murrayc.murraycgwtpexample.client.application.userstatus;

import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.client.LoginInfo;
import com.murrayc.murraycgwtpexample.client.NameTokens;
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
 * Created by murrayc on 1/21/16.
 */
public class UserStatusView extends ViewWithUiHandlers<UserStatusUserEditUiHandlers>
        implements UserStatusPresenter.MyView {

    private final Anchor usernameLabel = new Anchor();
    private final Label scoreLabel = new Label();

    private final Panel loginPanel = new FlowPanel();
    private final Label loginLabel = new Label(
            "Please sign in to your Google Account to track your progress.");
    private final Label loginFailedLabel = new Label(
            "Error: Could not connect to the login server.");
    private final Anchor signInLink = new Anchor("Sign In");

    private LoginInfo loginInfo = null;
    private UserProfile userProfile = null;
    private boolean loginServerFailed = false;

    UserStatusView() {
        final FlowPanel statusPanel = new FlowPanel();
        statusPanel.addStyleName("status-panel");
        //box.getElement().setAttribute("id", "titlebox");
        statusPanel.add(usernameLabel);
        usernameLabel.addStyleName("user-name");

        statusPanel.add(scoreLabel);
        usernameLabel.addStyleName("score");


        loginPanel.add(loginLabel);
        loginPanel.addStyleName("login-panel");

        loginPanel.add(signInLink);
        signInLink.addStyleName("sign-in-link");

        loginPanel.add(loginFailedLabel);
        loginFailedLabel.addStyleName("login-failed");
        loginFailedLabel.setVisible(false);

        final FlowPanel mainPanel = new FlowPanel();
        mainPanel.addStyleName("user-status-panel");
        mainPanel.add(loginPanel);
        mainPanel.add(statusPanel);
        initWidget(mainPanel);
    }

    private void showLogin() {
        if (loginInfo == null) {
            Log.error("showLogin(): loginInfo was null.");
        } else if (!loginInfo.isLoggedIn()) {
            signInLink.setHref(loginInfo.getLoginUrl());
            loginPanel.setVisible(true);
        } else {
            loginPanel.setVisible(false);
        }

        loginFailedLabel.setVisible(loginServerFailed);
    }

    private void showStatus() {
        //TODO: Avoid duplication with UserProfile.
        String username = loginInfo == null ? "" : loginInfo.getNickname();
        if (userProfile != null) {
            username = userProfile.getName();
        }

        usernameLabel.setText(username);
        usernameLabel.setHref("#" + NameTokens.USER_PROFILE);
    }

    @Override
    public void setUserStatus(final UserProfile userProfile) {
        this.userProfile = userProfile;
        this.loginServerFailed = false;


        updateUi();
    }

    @Override
    public void setUserStatusFailed() {
        this.userProfile = null;
        this.loginServerFailed = true;

        updateUi();
    }

    @Override
    public void setLoginInfo(LoginInfo result) {
        loginInfo = result;
        updateUi();
    }

    private void updateUi() {
        showLogin();
        showStatus();
    }
}
