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
package com.karuslabs.commons.command.parser;

import com.karuslabs.commons.command.Command;

import java.util.*;

import org.bukkit.configuration.ConfigurationSection;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;


public class CommandsToken extends Token<Map<String, Command>> {
    
    private Token<Command> command;
    
    
    public CommandsToken(Token<Command> command) {
        this.command = command;
    }

    
    @Override
    protected Map<String, Command> getNull(ConfigurationSection config, String key) {
        return new HashMap<>(0);
    }
    
    @Override
    public boolean isAssignable(ConfigurationSection config, String key) {
        return config.isConfigurationSection(key);
    }

    @Override
    protected Map<String, Command> get(ConfigurationSection config, String key) {
        ConfigurationSection subcommands = config.getConfigurationSection(key);
        return subcommands.getKeys(false).stream().collect(toMap(identity(), aKey -> command.from(subcommands, aKey)));
    }
    
}
