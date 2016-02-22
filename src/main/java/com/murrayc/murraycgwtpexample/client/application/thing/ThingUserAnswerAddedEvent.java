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

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.murrayc.murraycgwtpexample.shared.db.UserAnswer;

/**
 * Created by murrayc on 1/25/16.
 */
public class ThingUserAnswerAddedEvent extends GwtEvent<ThingUserAnswerAddedEvent.EventHandler> {
    private final UserAnswer userAnswer;

    public ThingUserAnswerAddedEvent(final UserAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public UserAnswer getUserAnswer() {
        return userAnswer;
    }

    public interface EventHandler extends com.google.gwt.event.shared.EventHandler {
        void onThingUserAnswerAdded(ThingUserAnswerAddedEvent event);
    }

    public static final Type<EventHandler> TYPE = new Type<>();

    public static void fire(final HasHandlers source, final UserAnswer userAnswer) {
        if (TYPE != null) {
            source.fireEvent(new ThingUserAnswerAddedEvent(userAnswer));
        }
    }

    public static Type<EventHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<EventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final EventHandler handler) {
        handler.onThingUserAnswerAdded(this);
    }
}
