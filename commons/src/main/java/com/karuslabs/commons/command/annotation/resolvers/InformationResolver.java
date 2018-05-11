/*
 * The MIT License
 *
 * Copyright 2018 Karus Labs.
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
package com.karuslabs.commons.command.annotation.resolvers;

import com.karuslabs.commons.command.*;
import com.karuslabs.commons.command.annotation.Information;

import java.lang.reflect.AnnotatedElement;
import java.util.List;

import static java.util.Arrays.asList;


public class InformationResolver implements Resolver {

    @Override
    public void resolve(AnnotatedElement element, CommandExecutor executor, Command... commands) {
        Information annotation = element.getAnnotation(Information.class);
        List<String> aliases = asList(annotation.aliases());
        
        for (Command command : commands) {
            command.setAliases(aliases);
            command.setDescription(annotation.description());
            command.setPermission(annotation.permission());
            command.setPermissionMessage(annotation.message());
            command.setUsage(annotation.usage());
        }
    }

    @Override
    public boolean isResolvable(AnnotatedElement element) {
        return element.getAnnotation(Information.class) != null;
    }
    
}
