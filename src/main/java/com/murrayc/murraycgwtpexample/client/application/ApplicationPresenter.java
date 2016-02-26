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
 */

package com.murrayc.murraycgwtpexample.client.application;

import com.google.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

import com.murrayc.murraycgwtpexample.client.application.ApplicationPresenter.MyView;
import com.murrayc.murraycgwtpexample.client.application.ApplicationPresenter.MyProxy;
import com.murrayc.murraycgwtpexample.client.application.menu.MenuPresenter;
import com.murrayc.murraycgwtpexample.client.application.userhistoryrecent.UserHistoryRecentPresenter;
import com.murrayc.murraycgwtpexample.client.application.userstatus.UserStatusPresenter;

/**
 * Created by murrayc on 1/21/16.
 */
public class ApplicationPresenter extends Presenter<MyView, MyProxy> {
    private final MenuPresenter menuPresenter;
    private final UserStatusPresenter userStatusPresenter;
    private final UserHistoryRecentPresenter userHistoryRecentPresenter;

    interface MyView extends View {
    }

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    //This will use some presenter that corresponds to a place (see NameTokens)
    //such as ThingPresenter, UserProfilePresenter, or AboutPresenter.
    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    //The MenuPresenter and UserStatusPresenter are on every page.
    public static final SingleSlot<MenuPresenter> SLOT_MENU = new SingleSlot<>();
    public static final SingleSlot<UserStatusPresenter> SLOT_USER_STATUS = new SingleSlot<>();
    public static final SingleSlot<UserHistoryRecentPresenter> SLOT_USER_HISTORY_RECENT = new SingleSlot<>();

    @Inject
    ApplicationPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            MenuPresenter menuPresenter,
            UserStatusPresenter userStatusPresenter,
            UserHistoryRecentPresenter userHistoryRecentPresenter) {
        super(eventBus, view, proxy, RevealType.Root);

        this.menuPresenter = menuPresenter;
        this.userStatusPresenter = userStatusPresenter;
        this.userHistoryRecentPresenter = userHistoryRecentPresenter;
    }

    @Override
    protected void onBind() {
        super.onBind();

        setInSlot(SLOT_MENU, menuPresenter);
        setInSlot(SLOT_USER_STATUS, userStatusPresenter);
        setInSlot(SLOT_USER_HISTORY_RECENT, userHistoryRecentPresenter);

    }
}
