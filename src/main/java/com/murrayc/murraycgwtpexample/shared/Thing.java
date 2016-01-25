package com.murrayc.murraycgwtpexample.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by murrayc on 1/21/16.
 */
public class Thing implements IsSerializable {
    private /* final */ String id;
    private /* final */ String text;

    public Thing() {
    }

    public Thing(final String id, final String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
