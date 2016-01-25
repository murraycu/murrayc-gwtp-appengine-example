package com.murrayc.murraycgwtpexample.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.murrayc.murraycgwtpexample.shared.Thing;
import org.junit.Test;

/**
 * @author Murray Cumming <murrayc@murrayc.com.com>
 */
public class GwtTestThingService extends GWTTestCase {

    @Test
    public void test() {
        // Setup an asynchronous event handler.
        final AsyncCallback<Thing> callback = new AsyncCallback<Thing>() {
            @Override
            public void onFailure(final Throwable caught) {
                fail(caught.toString());
            }

            @Override
            public void onSuccess(final Thing thing) {
                finishTest();
            }
        };

        delayTestFinish(500);

        final ThingServiceAsync service = ThingServiceAsync.Util.getInstance();
        assertNotNull(service);
        service.getThing("1", callback);
    }

    @Override
    public String getModuleName() {
        return "com.murrayc.MurraycGwtpExample";
    }

}
