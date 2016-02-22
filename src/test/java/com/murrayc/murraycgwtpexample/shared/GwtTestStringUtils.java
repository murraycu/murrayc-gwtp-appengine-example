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

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import org.junit.Test;

import static org.junit.Assert.*;

@GwtModule("com.murrayc.MurraycGwtpExample")
public class GwtTestStringUtils extends GwtTest {

    public GwtTestStringUtils() {
    }

    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(""));
        assertTrue(StringUtils.isEmpty(null));
        //noinspection RedundantStringConstructorCall
        assertTrue(StringUtils.isEmpty(new String()));
        assertFalse(StringUtils.isEmpty("something"));
    }

    @Test
    public void testDefaultString() {
        assertEquals("", StringUtils.defaultString(""));
        assertEquals("", StringUtils.defaultString(null));

        assertEquals("something", StringUtils.defaultString("something"));
    }

    @SuppressWarnings("RedundantStringConstructorCall")
    @Test
    public void testEquals() {
        assertTrue(StringUtils.equals(null, null));
        assertTrue(StringUtils.equals("", ""));
        assertTrue(StringUtils.equals(new String(), new String()));
        assertTrue(StringUtils.equals(null, ""));
        assertTrue(StringUtils.equals("", null));
        assertTrue(StringUtils.equals(null, new String()));
        assertTrue(StringUtils.equals(new String(), null));
        assertTrue(StringUtils.equals(null, new String()));
        assertTrue(StringUtils.equals(new String(), null));

        assertFalse(StringUtils.equals("something", null));
        assertFalse(StringUtils.equals("something", ""));
        assertFalse(StringUtils.equals("something", new String()));
        assertFalse(StringUtils.equals(null, "something"));
        assertFalse(StringUtils.equals("", "something"));
        assertFalse(StringUtils.equals(new String(), "something"));
    }

}
