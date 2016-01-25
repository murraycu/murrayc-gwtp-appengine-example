package com.murrayc.murraycgwtpexample.client.application.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import com.murrayc.murraycgwtpexample.client.NameTokens;
import com.murrayc.murraycgwtpexample.client.application.ApplicationModule;

/**
 * Created by murrayc on 1/21/16.
 */
public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder()
                .tokenFormatter(RouteTokenFormatter.class)
                .defaultPlace(NameTokens.THING)
                .errorPlace(NameTokens.THING)
                .unauthorizedPlace(NameTokens.THING)
                .build());

        install(new ApplicationModule());
    }
}