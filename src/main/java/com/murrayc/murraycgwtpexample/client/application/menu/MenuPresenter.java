package com.murrayc.murraycgwtpexample.client.application.menu;


import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

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
public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView>
        implements MenuUserEditUiHandlers {
    private final PlaceManager placeManager;

    public interface MyView extends View, HasUiHandlers<MenuUserEditUiHandlers> {
    }

    @Inject
    MenuPresenter(
            EventBus eventBus,
            MyView view,
            PlaceManager placeManager) {
        super(eventBus, view);
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void goTo(final String token) {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(token)
                /* .with(ParameterTokens.MODEL, model) */
                .build();

        placeManager.revealPlace(placeRequest);
    }
}