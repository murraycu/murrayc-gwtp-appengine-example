package com.murrayc.murraycgwtpexample.client.application.about;


import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.murrayc.murraycgwtpexample.client.NameTokens;
import com.murrayc.murraycgwtpexample.client.application.ApplicationPresenter;

/**
 * Created by murrayc on 1/21/16.
 */
public class AboutPresenter extends Presenter<AboutPresenter.MyView, AboutPresenter.MyProxy>
        implements AboutUserEditUiHandlers {
    public interface MyView extends View, HasUiHandlers<AboutUserEditUiHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.ABOUT)
    interface MyProxy extends ProxyPlace<AboutPresenter> {
    }

    @Inject
    AboutPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        getView().setUiHandlers(this);
    }
}