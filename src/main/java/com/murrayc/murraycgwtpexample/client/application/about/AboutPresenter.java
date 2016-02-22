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
