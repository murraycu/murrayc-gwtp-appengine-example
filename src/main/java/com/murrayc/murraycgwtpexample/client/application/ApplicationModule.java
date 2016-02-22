package com.murrayc.murraycgwtpexample.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.murrayc.murraycgwtpexample.client.application.about.AboutModule;
import com.murrayc.murraycgwtpexample.client.application.menu.MenuModule;
import com.murrayc.murraycgwtpexample.client.application.menu.MenuPresenter;
import com.murrayc.murraycgwtpexample.client.application.menu.MenuView;
import com.murrayc.murraycgwtpexample.client.application.thing.ThingModule;
import com.murrayc.murraycgwtpexample.client.application.userhistoryrecent.UserHistoryRecentModule;
import com.murrayc.murraycgwtpexample.client.application.userprofile.UserProfileModule;
import com.murrayc.murraycgwtpexample.client.application.userstatus.UserStatusModule;
import com.murrayc.murraycgwtpexample.client.application.userstatus.UserStatusPresenter;
import com.murrayc.murraycgwtpexample.client.application.userstatus.UserStatusView;

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
public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new ThingModule());
        install(new UserProfileModule());
        install(new AboutModule());

        install(new UserStatusModule());
        install(new MenuModule());
        install(new UserHistoryRecentModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        bindSingletonPresenterWidget(MenuPresenter.class, MenuPresenter.MyView.class,
                MenuView.class);
        bindSingletonPresenterWidget(UserStatusPresenter.class, UserStatusPresenter.MyView.class,
                UserStatusView.class);
        //bindSingletonPresenterWidget(UserHistoryRecentPresenter.class, UserHistoryRecentPresenter.MyView.class,
        //        UserHistoryRecentView.class);
    }
}
