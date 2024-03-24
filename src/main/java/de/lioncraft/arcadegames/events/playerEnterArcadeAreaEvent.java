package de.lioncraft.arcadegames.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class playerEnterArcadeAreaEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    Player player;

    public boolean hasEntered() {
        return hasEntered;
    }

    boolean hasEntered;
    public Player getPlayer(){
        return this.player;
    }
    public playerEnterArcadeAreaEvent(Player p, boolean hasEntered){
        player = p;
        this.hasEntered = hasEntered;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
