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
 
package com.murrayc.murraycgwtpexample.server;

import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.shared.Thing;
import com.murrayc.murraycgwtpexample.shared.ThingAndAnswer;

import java.util.*;

/**
 * Created by murrayc on 1/18/16.
 */
public class Things {
    //Map of thing IDs to thing:
    private Map<String, ThingAndAnswer> things = new HashMap<>();

    //An extra list, used only for getting a random thing,
    //regardless of what section it is in.
    private final List<ThingAndAnswer> listThings = new ArrayList<>();


    public Things() {
        things = new HashMap<>();
    }

    public void addThing(final ThingAndAnswer thingAndAnswer) {
        things.put(thingAndAnswer.getId(), thingAndAnswer);

        //Store it here too:
        listThings.add(thingAndAnswer);
    }

    public Thing getRandomThing() {
        return getRandomThingFromList(listThings);
    }

    private static Thing getRandomThingFromList(final List<ThingAndAnswer> listThings) {
        if (listThings == null) {
            return null;
        }

        if (listThings.isEmpty()) {
            return null;
        }

        final int index = new Random().nextInt(listThings.size());
        final ThingAndAnswer thingAndAnswer = listThings.get(index);
        if (thingAndAnswer == null) {
            Log.error("getRandomThingFromList(): ThingAndAnswer was null.");
            return null;
        }

        return thingAndAnswer.getThing();
    }

    public Thing getThing(final String thingId) {
        final ThingAndAnswer thingAndAnswer = getThingAndAnswer(thingId);
        if (thingAndAnswer != null) {
            return thingAndAnswer.getThing();
        }

        return null;
    }

    public String getAnswer(final String thingId) {
        final ThingAndAnswer thingAndAnswer = getThingAndAnswer(thingId);
        if (thingAndAnswer != null) {
            return thingAndAnswer.getAnswer();
        }

        return null;
    }

    ThingAndAnswer getThingAndAnswer(final String thingId) {
        //Look in every section:
        final ThingAndAnswer thingAndAnswer = things.get(thingId);
        if (thingAndAnswer != null) {
            return thingAndAnswer;
        }

        return null;
    }
}
