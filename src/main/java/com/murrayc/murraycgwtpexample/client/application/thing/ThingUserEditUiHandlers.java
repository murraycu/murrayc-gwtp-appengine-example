package com.murrayc.murraycgwtpexample.client.application.thing;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * Created by murrayc on 1/21/16.
 */
interface ThingUserEditUiHandlers extends UiHandlers {
    void onSubmitAnswer();

    void onGoToNextThing();
}
