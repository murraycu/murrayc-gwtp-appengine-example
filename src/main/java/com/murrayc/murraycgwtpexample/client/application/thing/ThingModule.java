package com.murrayc.murraycgwtpexample.client.application.thing;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Created by murrayc on 1/21/16.
 */
public class ThingModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ThingPresenter.class, ThingPresenter.MyView.class, ThingView.class,
                ThingPresenter.MyProxy.class);
    }
}
