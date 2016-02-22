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

package com.murrayc.murraycgwtpexample.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by murrayc on 1/18/16.
 */
public class ThingAndAnswer implements IsSerializable {
    //Don't make these final, because gwt serialization doesn't support that.

    private /* final */ Thing thing;
    private /* final */ String answer;


    public ThingAndAnswer(final String thingId, final String thingText, final String answerText) {
        this.thing = new Thing(thingId, thingText);
        this.answer = answerText;
    }

    public ThingAndAnswer() {
    }

    public String getId() {
        return thing.getId();
    }

    public Thing getThing() {
        return thing;
    }

    public String getAnswer() {
        return answer;
    }
}
