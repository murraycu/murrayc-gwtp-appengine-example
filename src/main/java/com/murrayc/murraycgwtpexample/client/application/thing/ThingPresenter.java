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

package com.murrayc.murraycgwtpexample.client.application.thing;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.client.NameTokens;
import com.murrayc.murraycgwtpexample.client.ThingService;
import com.murrayc.murraycgwtpexample.client.ThingServiceAsync;
import com.murrayc.murraycgwtpexample.client.application.PlaceUtils;
import com.murrayc.murraycgwtpexample.shared.StringUtils;
import com.murrayc.murraycgwtpexample.client.application.ApplicationPresenter;

import com.google.inject.Inject;
import com.murrayc.murraycgwtpexample.shared.Thing;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;

/**
 * Created by murrayc on 1/21/16.
 */
public class ThingPresenter extends Presenter<ThingPresenter.MyView, ThingPresenter.MyProxy>
        implements ThingUserEditUiHandlers {
    private final PlaceManager placeManager;

    interface MyView extends View, HasUiHandlers<ThingUserEditUiHandlers> {
        void setThing(final Thing thing);

        String getChoiceSelected();

        void setSubmissionResult(final ThingService.SubmissionResult submissionResult);
    }

    private Thing thing;
    private Thing nextThing;

    @ProxyStandard
    @NameToken(NameTokens.THING)
    interface MyProxy extends ProxyPlace<ThingPresenter> {
    }

    @Inject
    ThingPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    private
    String getThingId() {
        if (thing == null) {
            return null;
        }

        return thing.getId();
    }

    @Override
    public void prepareFromRequest(final PlaceRequest request) {
        super.prepareFromRequest(request);

        //Thing ID:
        final String thingId = request.getParameter(NameTokens.THING_PARAM_THING_ID, null);
        //log.error("prepareFromRequest(): thingId=" + thingId);
        if (!StringUtils.isEmpty(thingId)) {
            //If we have already cached this one, just show it:
            if (nextThing != null && StringUtils.equals(nextThing.getId(), thingId)) {
                //log.error("prepareFromRequest(): using nextThing.");
                final Thing thing = nextThing;
                nextThing = null;
                showThingInView(thing);
                return;
            }

            //log.error("prepareFromRequest(): getting from server.");

            //Otherwise, get it from the server and show it:
            getAndUseThing(thingId);
        } else {
            getAndUseNextThing();
        }
    }

    @Override
    public void onSubmitAnswer() {
        //Submit the answer to the server:
        final String answer = getView().getChoiceSelected();

        final AsyncCallback<ThingService.SubmissionResult> callback = new AsyncCallback<ThingService.SubmissionResult>() {
            @Override
            public void onFailure(final Throwable caught) {
                // TODO: create a way to notify users of asynchronous callback failures
                Log.error("AsyncCallback Failed: onSubmitAnswer(): " + caught.getMessage());
            }

            @Override
            public void onSuccess(final ThingService.SubmissionResult result) {
                //Store these in case they are needed soon:
                //correctAnswer = result.getCorrectAnswer();

                //Store the possible next thing, to avoid having to ask as a separate async request,
                //but ignore duplicates:
                nextThing = null;
                final Thing possibleNextThing = result.getNextThing();
                if ((possibleNextThing != null) &&
                        (!StringUtils.equals(possibleNextThing.getId(), getThingId()))) {
                    nextThing = possibleNextThing;
                    //log.error("Storing nextThing for later: " + nextThing.getId());
                }

                tellUserHistoryPresenterAboutNewUserAnswer(result.getResult());

                //Show the user:
                getView().setSubmissionResult(result);
            }

        };

        ThingServiceAsync.Util.getInstance().submitAnswer(getThingId(), answer, callback);
    }

    private void tellUserHistoryPresenterAboutNewUserAnswer(boolean answerIsCorrect) {
        //Tell the UserHistoryRecent presenter/view that there is a new history item.
        //Otherwise it will only update when the whole page refreshes.
        final UserAnswer userAnswer = new UserAnswer(null, thing, answerIsCorrect, null);
        ThingUserAnswerAddedEvent.fire(this, userAnswer);
    }

    @Override
    public void onGoToNextThing() {
        //log.error("onGoToNextThing: current=" + thingId);
        //This was for the previously-answered thing:

        //If we previously submitted an answer,
        //we would have received the next thing along with the result,
        //and it might still be what we want:
        if (nextThing != null) {
            //log.error("onGoToNextThing: nextThing != null");

            revealThing(nextThing);
        }

        //Otherwise, get it from the server:
        getAndUseNextThing();
    }

    /**
     * This will cause prepareFromRequest() be called,
     * where we can update the view with the specified thingId,
     * storing a history token for the "place" along the way,
     * so the browser's back button can take us to the previous thing.
     *
     * @param thing
     */
    private void revealThing(final Thing thing) {
        final PlaceRequest placeRequest = PlaceUtils.getPlaceRequestForThing(thing.getId());
        placeManager.revealPlace(placeRequest);
    }

    private void showThingInView(final Thing thing) {
        this.thing = thing;
        getView().setThing(thing);
    }

    /** Get and show a thing from the specified section.
     * or from any section if @a sectionId is null.
     * This ignores any cached nextThing,
     * so don't call this method if you want to possibly use
     * the cached nextThing
     *
     */
    private void getAndUseNextThing() {
        nextThing = null;

        final AsyncCallback<Thing> callback = new AsyncCallback<Thing>() {
            @Override
            public void onFailure(final Throwable caught) {
                // TODO: create a way to notify users of asynchronous callback failures
                Log.error("AsyncCallback Failed: getNextThing(): " + caught.getMessage());
            }

            @Override
            public void onSuccess(final Thing result) {
                revealThing(result);
            }

        };

        ThingServiceAsync.Util.getInstance().getNextThing(callback);
    }

    private void getAndUseThing(final String thingId) {
        //Log.error("getAndUseThing");
        nextThing = null;

        final AsyncCallback<Thing> callback = new AsyncCallback<Thing>() {
            @Override
            public void onFailure(final Throwable caught) {
                // TODO: create a way to notify users of asynchronous callback failures
                Log.error("AsyncCallback Failed: getNextThing(): " + caught.getMessage());
            }

            @Override
            public void onSuccess(final Thing result) {
                showThingInView(result);
            }

        };

        ThingServiceAsync.Util.getInstance().getThing(thingId, callback);
    }
}
