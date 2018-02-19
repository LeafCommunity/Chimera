/*
 * The MIT License
 *
 * Copyright 2017 Karus Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.karuslabs.commons.locale;

import java.util.*;

import org.junit.jupiter.api.*;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Locale.KOREA;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.*;


@TestInstance(PER_CLASS)
class CachedControlTest {
    
    CachedControl control;
    ResourceBundle bundle;
    
    
    CachedControlTest() {
        control = new CachedControl();
        control.getBundles().put(KOREA, bundle = mock(ResourceBundle.class));
    }
    
    
    @Test
    void none() {
        assertSame(CachedResourceBundle.NONE, CachedControl.NONE.newBundle(null, Locale.KOREA, null, null, true));
    }
    
    
    @Test
    void newBundle() {
        assertSame(bundle, control.newBundle(null, KOREA, null, null, true));
    } 
    
    
    @Test
    void getFormats() {
        assertSame(EMPTY_LIST, control.getFormats(null));
    }
    
}
