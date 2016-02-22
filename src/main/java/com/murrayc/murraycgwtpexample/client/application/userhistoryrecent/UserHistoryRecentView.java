package com.murrayc.murraycgwtpexample.client.application.userhistoryrecent;

import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.murrayc.murraycgwtpexample.client.UserRecentHistory;
import com.murrayc.murraycgwtpexample.client.application.PlaceUtils;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;

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
public class UserHistoryRecentView extends ViewWithUiHandlers<UserHistoryRecentUserEditUiHandlers>
        implements UserHistoryRecentPresenter.MyView {

    private final VerticalPanel answersPanel = new VerticalPanel();
    private final PlaceManager placeManager;

    @Inject
    UserHistoryRecentView(PlaceManager placeManager) {
        this.placeManager = placeManager;

        final FlowPanel mainPanel = new FlowPanel();
        mainPanel.addStyleName("user-history-recent-panel");
        //box.getElement().setAttribute("id", "titlebox");

        final Label labelTitle = new Label("Recent History");
        mainPanel.add(labelTitle);
        labelTitle.addStyleName("subsection-title");

        mainPanel.add(answersPanel);
        answersPanel.addStyleName("user-status-answers-panel");
        initWidget(mainPanel);
    }

    @Override
    public void setUserRecentHistory(final UserRecentHistory result) {
        answersPanel.clear();

        for (final UserAnswer userAnswer : result.getUserAnswers()) {
            final Hyperlink link = createUserAnswerHyperlink(userAnswer);
            answersPanel.add(link);
        }
    }

    @Override
    public void addUserAnswer(final UserAnswer userAnswer) {
        //We assume that this is the most recent activity:
        final Hyperlink link = createUserAnswerHyperlink(userAnswer);
        answersPanel.insert(link, 0);
    }

    private Hyperlink createUserAnswerHyperlink(final UserAnswer userAnswer) {
        //TODO: This will take the user to that thing,
        //and keep any subsequent things to that thing's section,
        //by specifying the nextSectionThingId to getPlaceRequestForThing().
        //Alternatively, we could specify no section (meaning it would use things from all sections).
        //Both alternatives lose whatever the user had set before clicking this link.
        final PlaceRequest placeRequest = PlaceUtils.getPlaceRequestForThing(userAnswer.getThingId());
        final String url = placeManager.buildHistoryToken(placeRequest);
        return new Hyperlink(userAnswer.getThingTitle(), url);
    }

    @Override
    public void setServerFailed() {
        //TODO: labelDebug.setText("Error: Connection to service failed.");

    }
}
