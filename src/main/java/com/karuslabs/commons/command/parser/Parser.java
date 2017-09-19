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
package com.karuslabs.commons.command.parser;

import com.karuslabs.commons.command.Command;
import com.karuslabs.commons.command.completion.Completion;
import com.karuslabs.commons.locale.Translation;

import java.util.List;
import javax.annotation.Nullable;

import org.bukkit.configuration.ConfigurationSection;

import static java.util.stream.Collectors.toList;


public class Parser {
    
    private Element<Command> command;
    private Element<Completion> completion;
    private Element<Translation> translation;
    
    
    public Parser(Element<Command> command, Element<Completion> completion, Element<Translation> translation) {
        this.command = command;
        this.completion = completion;
        this.translation = translation;
    }
    
    
    public List<Command> parse(ConfigurationSection config) {
        parseDefinitions(config.getConfigurationSection("define"));
        
        ConfigurationSection aConfig = config.getConfigurationSection("commands");
        return aConfig.getKeys(false).stream().map(key -> command.parse(aConfig.get(key))).collect(toList());
    }
    
    protected void parseDefinitions(@Nullable ConfigurationSection config) {
        if (config != null) {
            parseDefinition(config.getConfigurationSection("completions"), completion);
            parseDefinition(config.getConfigurationSection("translations"), translation);
            parseDefinition(config.getConfigurationSection("commands"), command);
        }
    }
    
    protected void parseDefinition(@Nullable ConfigurationSection config, Element<?> element) {
        if (config != null) {
            config.getKeys(false).forEach(key -> element.define(key, config.get(key)));
        }
    }

    
    public Element<Command> getCommand() {
        return command;
    }

    public Element<Completion> getCompletion() {
        return completion;
    }

    public Element<Translation> getTranslation() {
        return translation;
    }
    
}
