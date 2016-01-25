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
