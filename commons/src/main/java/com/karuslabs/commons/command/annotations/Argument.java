/*
 * The MIT License
 *
 * Copyright 2019 Karus Labs.
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
package com.karuslabs.commons.command.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * An annotation from which an {@link com.karuslabs.commons.command.tree.nodes.Argument}
 * can be created.
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Repeatable(Arguments.class)
public @interface Argument {
    
    /**
     * The namespace in which the {@code Argument} is to be created.
     * 
     * @return the namespace
     */
    String[] namespace();
    
    /**
     * An optional name of the {@code ArgumentType} to which the {@code Argument} 
     * to be created is bound.
     * 
     * @return the name of the bound {@code ArgumentType}.
     */
    String type() default "";
    
    /**
     * An optional name of the {@code SuggestionProvider} to which the {@code Argument}
     * to be created is bound.
     * 
     * @return the name
     */
    String suggestions() default "";
    
}
