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
