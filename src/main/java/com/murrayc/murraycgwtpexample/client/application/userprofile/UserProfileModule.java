package com.murrayc.murraycgwtpexample.client.application.userprofile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.murrayc.murraycgwtpexample.client.application.userhistoryrecent.UserHistoryRecentPresenter;
import com.murrayc.murraycgwtpexample.client.application.userhistoryrecent.UserHistoryRecentView;

/**
 * Created by murrayc on 1/21/16.
 */
public class UserProfileModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(UserProfilePresenter.class, UserProfilePresenter.MyView.class, UserProfileView.class,
                UserProfilePresenter.MyProxy.class);

        bindSingletonPresenterWidget(UserHistoryRecentPresenter.class, UserHistoryRecentPresenter.MyView.class,
                UserHistoryRecentView.class);
    }
}
