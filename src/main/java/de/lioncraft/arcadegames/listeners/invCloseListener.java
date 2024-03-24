package de.lioncraft.arcadegames.listeners;

import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.games.ssp;
import de.lioncraft.arcadegames.games.tiktaktoe;
import de.lioncraft.arcadegames.games.vierGewinnt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ConcurrentModificationException;

public class invCloseListener implements Listener {
    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (e.getView().title().equals(strings.sspGUITitle)) {
            try {
                for (ssp ssp : defaultData.activessp) {
                    if (ssp.getInv().equals(e.getInventory())) {
                        ssp.cancel();
                    }
                }
            } catch (ConcurrentModificationException ignored) {}
        } else if (e.getView().title().equals(strings.tiktaktoeGUITitle)) {
            try {
                for (tiktaktoe ssp : defaultData.activeTiktaktoe) {
                    if (ssp.getPlayer1().equals((Player) e.getPlayer()) || ssp.getPlayer2().equals((Player) e.getPlayer())) {
                        ssp.getPlayer1().sendMessage(strings.chatPrefix + e.getPlayer().getName() + " hat das Spiel beendet.");
                        ssp.getPlayer2().sendMessage(strings.chatPrefix + e.getPlayer().getName() + " hat das Spiel beendet.");
                        ssp.endGame();
                    }
                }
            } catch (ConcurrentModificationException ignored) {}
        } else if (e.getView().title().equals(strings.vierGewinntGUITitle)) {
            try {

                for (vierGewinnt vg : defaultData.activeVierGewinnt) {
                    if (vg.getPlayer1().equals(e.getPlayer()) || vg.getPlayer2().equals(e.getPlayer())) {
                        vg.endGame();
                    }
                }
            } catch (ConcurrentModificationException ignored) {}
        }
    }
}
