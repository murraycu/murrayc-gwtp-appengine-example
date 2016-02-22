package com.murrayc.murraycgwtpexample.client.application;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.gwtplatform.mvp.client.ViewImpl;

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
public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    private final Panel main = new FlowPanel();
    private final SimplePanel mainPanel = new SimplePanel();
    private final SimplePanel menuPanel = new SimplePanel();
    private final SimplePanel userStatusPanel = new SimplePanel();
    private final SimplePanel userHistoryRecentPanel = new SimplePanel();

    ApplicationView() {
        main.add(menuPanel);
        main.add(userStatusPanel);

        //We use a CSS media query to only show this on wider screens:
        main.add(userHistoryRecentPanel);
        userHistoryRecentPanel.addStyleName("user-history-recent-as-sidebar");

        main.add(mainPanel);
        initWidget(main);

        bindSlot(ApplicationPresenter.SLOT_MENU, menuPanel);
        bindSlot(ApplicationPresenter.SLOT_USER_STATUS, userStatusPanel);
        bindSlot(ApplicationPresenter.SLOT_USER_HISTORY_RECENT, userHistoryRecentPanel);
        bindSlot(ApplicationPresenter.SLOT_MAIN, mainPanel);
    }
}