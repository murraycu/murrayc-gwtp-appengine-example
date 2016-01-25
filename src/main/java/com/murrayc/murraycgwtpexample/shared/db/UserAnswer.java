package com.murrayc.murraycgwtpexample.shared.db;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.murrayc.murraycgwtpexample.shared.Thing;

/**
 * Created by murrayc on 1/21/16.
 */
@Entity
public class UserAnswer implements IsSerializable {
    @Id
    private Long id;

    //TODO: I would rather use a Ref<UserProfile> here,
    //but that doesn't seem to GWT-compile for the client side.b
    @Index
    private String userId;

    private String thingId;

    //TODO: Internationalization.
    @Ignore
    private String thingTitle;

    private boolean result;

    @Index
    private String time;

    public UserAnswer() {
    }

    public UserAnswer(final String userId, final Thing thing, final boolean result, final String time) {
        this.userId = userId;
        this.thingId = thing.getId();
        this.thingTitle = thing.getText();
        this.result = result;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public String getThingId() {
        return thingId;
    }


    public boolean getResult() {
        return result;
    }

    public String getTime() {
        return time;
    }

    public String getThingTitle() {
        return thingTitle;
    }

    public void setThingTitle(final String thingTitle) {
        this.thingTitle = thingTitle;
    }
}
