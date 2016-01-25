package com.murrayc.murraycgwtpexample.client.application;

import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.murrayc.murraycgwtpexample.client.NameTokens;

/**
 * Created by murrayc on 1/24/16.
 */
public class PlaceUtils {
    public static PlaceRequest getPlaceRequestForThing(final String thingId) {
        return new PlaceRequest.Builder()
                .nameToken(NameTokens.THING)
                .with(NameTokens.THING_PARAM_THING_ID, thingId)
                .build();
    }
}
