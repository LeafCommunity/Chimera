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
package com.karuslabs.commons.command.bridges;

import com.karuslabs.commons.command.Suggestor;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.*;

import java.util.concurrent.CompletableFuture;

import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

import org.checkerframework.checker.nullness.qual.Nullable;


public class RequiredBridge extends Bridge implements SuggestionProvider<CommandListenerWrapper> {
    
    protected @Nullable Suggestor suggestor;
    
    
    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandListenerWrapper> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        return suggestor.suggest(sender(context), context, builder);
    }
    
    
    public static <T> RequiredBuilder<T> argument(String name, ArgumentType<T> type) {
        return new RequiredBuilder(new RequiredBridge(), RequiredArgumentBuilder.argument(name, type));
    }
    
    
    public static class RequiredBuilder<T> extends Builder<RequiredBuilder<T>, RequiredBridge, RequiredArgumentBuilder<CommandListenerWrapper, T>> {
        
        private RequiredBuilder(RequiredBridge bridge, RequiredArgumentBuilder<CommandListenerWrapper, T> builder) {
            super(bridge, builder);
        }
        
        
        public RequiredBuilder<T> suggests(Suggestor suggestor) {
            bridge.suggestor = suggestor;
            builder.suggests(bridge);
            return self();
        }

        @Override
        protected RequiredBuilder<T> self() {
            return this;
        }
    }
}
