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
