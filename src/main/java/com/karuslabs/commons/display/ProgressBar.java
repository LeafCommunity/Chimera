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
package com.karuslabs.commons.display;

import com.karuslabs.commons.locale.Translation;
import com.karuslabs.commons.util.concurrent.UncheckedFuture;

import java.util.*;
import java.util.function.BiConsumer;

import org.bukkit.Server;
import org.bukkit.boss.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static java.util.stream.Collectors.toList;


public class ProgressBar extends Bar<List<BossBar>> {
    
    private BiConsumer<BossBar, ProgressBarTask> consumer;
    private Server server;
    private BarColor color;
    private BarStyle style;
    private BarFlag[] flags;
    private double progress;

    
    public ProgressBar(Plugin plugin, Translation translation, long frames, long delay, long period) {
        super(plugin, translation, frames, delay, period);
    }
    
    
    @Override
    public UncheckedFuture<List<BossBar>> render(Collection<Player> players) {
        List<BossBar> bars = players.stream().map(player -> {
            BossBar bar = create();
            bar.addPlayer(player);
            return bar;
        }).collect(toList());
                
        ProgressBarTask task = new ProgressBarTask(frames, translation, bars, consumer);
        task.runTaskTimerAsynchronously(plugin, delay, period);
        
        return task;
    }
    
    public BossBar create() {
        BossBar bar = server.createBossBar("", color, style, flags);
        bar.setProgress(progress);
        
        return bar;
    }
    
    
    public BarColor getColor() {
        return color;
    }

    public BarStyle getStyle() {
        return style;
    }

    public BarFlag[] getFlags() {
        return flags;
    }
    
}
