package cn.nukkit.event.plugin;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.plugin.Plugin;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class PluginEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private final Plugin plugin;

    public PluginEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
