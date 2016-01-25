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
