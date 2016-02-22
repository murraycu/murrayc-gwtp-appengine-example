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

package com.murrayc.murraycgwtpexample.client.application.menu;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.murrayc.murraycgwtpexample.client.NameTokens;

/**
 * Created by murrayc on 1/21/16.
 */
public class MenuView extends ViewWithUiHandlers<MenuUserEditUiHandlers>
        implements MenuPresenter.MyView {

    private final Anchor titleLabel = new Anchor("murrayc GWTP AppEngine Example");
    private final MenuBar menuBar = new MenuBar();

    MenuView() {
        final Panel mainPanel = new FlowPanel();
        mainPanel.addStyleName("menu-panel");
        mainPanel.add(titleLabel);
        titleLabel.addStyleName("menu-title");
        titleLabel.setHref("#" + NameTokens.THING); //TODO: Or just / ?

        mainPanel.add(menuBar);
        menuBar.addStyleName("menu-bar");

        //Instead, the title is a clickable link.
        /*
        menuBar.addItem("Home", new Command() {
            @Override
            public void execute() {
                goTo(NameTokens.THING);
            }
        });
        */

        //Instead, the user name is a clickable link:
        /*
        menuBar.addItem("Profile", new Command() {
            @Override
            public void execute() {
                goTo(NameTokens.USER_PROFILE);
            }
        });
        */

        menuBar.addItem("About", new Command() {
            @Override
            public void execute() {
                goTo(NameTokens.ABOUT);
            }
        });

        initWidget(mainPanel);
    }

    private void goTo(final String token) {
        getUiHandlers().goTo(token);
    }
}
