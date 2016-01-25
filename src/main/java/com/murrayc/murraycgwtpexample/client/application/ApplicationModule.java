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
