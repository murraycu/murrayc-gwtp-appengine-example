package com.murrayc.murraycgwtpexample.client.application.userprofile;

import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.murrayc.murraycgwtpexample.client.LoginInfo;

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
public class UserProfileView extends ViewWithUiHandlers<UserProfileUserEditUiHandlers>
        implements UserProfilePresenter.MyView {
    private final Label usernameTitleLabel = new Label("Username: ");
    private final Label usernameLabel = new Label();
    private final Anchor logoutLabel = new Anchor("Log out");

    private final SimplePanel userHistoryRecentPanel = new SimplePanel();

    UserProfileView() {
        final FlowPanel mainPanel = new FlowPanel();
        mainPanel.addStyleName("content-panel");

        final Label titleLabel = new Label("Profile");
        titleLabel.addStyleName("page-title-label");
        mainPanel.add(titleLabel);

        mainPanel.add(usernameTitleLabel);
        usernameTitleLabel.addStyleName("username-title-label");
        mainPanel.add(usernameLabel);
        usernameLabel.addStyleName("username-label");

        mainPanel.add(logoutLabel);
        logoutLabel.addStyleName("logout-label");

        //TODO: Why doesn't this appear?
        bindSlot(UserProfilePresenter.SLOT_USER_HISTORY_RECENT, userHistoryRecentPanel);
        mainPanel.add(userHistoryRecentPanel);
        //userHistoryRecentPanel.addStyleName("debug-userhistory");

        initWidget(mainPanel);
    }


    @Override
    public void setUserStatusFailed() {

    }

    @Override
    public void setLoginInfo(final LoginInfo loginInfo) {
        String username = null;
        String logoutLink = null;
        if (loginInfo != null) {
            username = loginInfo.getNickname();
            logoutLink = loginInfo.getLogoutUrl();
        }

        usernameLabel.setText(username);
        logoutLabel.setHref(logoutLink);
    }
}
