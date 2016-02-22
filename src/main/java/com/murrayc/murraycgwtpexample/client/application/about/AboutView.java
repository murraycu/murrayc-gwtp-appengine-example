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

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Created by murrayc on 1/21/16.
 */
public class AboutView extends ViewWithUiHandlers<AboutUserEditUiHandlers>
        implements AboutPresenter.MyView {

    private final Panel mainPanel = new FlowPanel();
    private final Label aboutLabel = new Label("Yadda yadda yadda yadda.");
    private final Label versionLabel = new Label();

    AboutView() {
        mainPanel.addStyleName("content-panel");

        final Label titleLabel = new Label("About");
        titleLabel.addStyleName("page-title-label");
        mainPanel.add(titleLabel);

        mainPanel.add(aboutLabel);
        aboutLabel.addStyleName("about-label");

        mainPanel.add(versionLabel);
        versionLabel.addStyleName("version-label");

        //TODO: Internationalization:
        //TODO: Get the number from pom.xml somehow.
        versionLabel.setText("Version: " + "0.1");

        initWidget(mainPanel);
    }
}
