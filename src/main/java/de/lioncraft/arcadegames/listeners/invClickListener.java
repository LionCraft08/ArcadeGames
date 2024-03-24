package de.lioncraft.arcadegames.listeners;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.games.*;
import de.lioncraft.arcadegames.games.enums.GameType;
import de.lioncraft.arcadegames.utils.inventories;
import de.lioncraft.arcadegames.data.buttons;
import de.lioncraft.arcadegames.utils.settingsChanger;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ConcurrentModificationException;
import java.util.Objects;

import de.lioncraft.arcadegames.data.inventoryContent;

public class invClickListener implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getView().title().equals(strings.MainGUITitle)) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().equals(buttons.settingsButton)) {
                    new inventories().openSettingsGUI((Player) e.getWhoClicked());
                    //maybe sounds
                } else if (e.getCurrentItem().equals(buttons.tiktaktoeItem) || e.getCurrentItem().equals(buttons.sspItem) || e.getCurrentItem().equals(buttons.vierGewinntItem)) {
                    new inventories().openGameAmountSelectionGUI((Player) e.getWhoClicked(), e.getCurrentItem());
                }
            }
        } else if (e.getView().title().equals(strings.SettingsGUITitle)) {
            //settings inv
            e.setCancelled(true);
            if (Objects.equals(e.getCurrentItem(), buttons.backButton)) {
                new inventories().openMainGUI((Player) e.getWhoClicked());
            } else if (e.getCurrentItem() != null) {
                new settingsChanger().onButtonClick(e.getCurrentItem(), (Player) e.getWhoClicked(), e.getSlot(), e.getClickedInventory());
            }
        } else if (e.getView().title().equals(strings.playerSelectionGUITitle)) {
            //player selection inv
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                    //hier noch permission checken
                    SkullMeta sm = (SkullMeta) e.getCurrentItem().getItemMeta();
                    GameType gt = GameType.valueOf(e.getClickedInventory().getItem(4).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(ArcadeGames.getPlugin(), "gametype"), PersistentDataType.STRING));
                    int i = e.getClickedInventory().getItem(4).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(ArcadeGames.getPlugin(), "amount"), PersistentDataType.INTEGER);
                    if (sm.getOwningPlayer() == null) {
                        e.getWhoClicked().sendMessage(strings.chatPrefix.append(Component.text("Dieser Spieler ist Offline!")));
                        return;
                    }
                    GameRequest gr = new GameRequest((Player) e.getWhoClicked(), sm.getOwningPlayer().getPlayer(), gt, System.currentTimeMillis(), i);
                    gr.getPlayer2().sendMessage(gr.getRequestMessage());
                    gr.getPlayer1().sendMessage(gr.getRequestFeedbackMessage());
                    defaultData.playerRequest.put((Player) e.getWhoClicked(), gr);
                    e.getWhoClicked().closeInventory();
                } else if (e.getCurrentItem().equals(buttons.backButton)) {
                    new inventories().openGameAmountSelectionGUI((Player) e.getWhoClicked(), e.getInventory().getItem(4));
                }
            }
        } else if (e.getView().title().equals(strings.tiktaktoeGUITitle)) {
            e.setCancelled(true);
            try {
                for (tiktaktoe tiktaktoe : defaultData.activeTiktaktoe) {
                    if (tiktaktoe.getInv().equals(e.getClickedInventory())) {
                        tiktaktoe.performClickAction((Player) e.getWhoClicked(), e.getSlot());
                    }
                }
            }catch (ConcurrentModificationException ignored){}
        } else if (e.getView().title().equals(strings.amountSelectionGUITitle)) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType().equals(inventoryContent.amountSelectionGUIContent[10].getType())) {
                    ItemMeta im = e.getClickedInventory().getItem(4).getItemMeta();
                    im.getPersistentDataContainer().set(new NamespacedKey(ArcadeGames.getPlugin(), "amount"), PersistentDataType.INTEGER, e.getCurrentItem().getAmount());
                    e.getClickedInventory().getItem(4).setItemMeta(im);
                    new inventories().openPlayerSelectionGUI((Player) e.getWhoClicked(), 0, e.getClickedInventory().getItem(4));
                } else if (e.getCurrentItem().equals(buttons.backButton)) {
                    new inventories().openMainGUI((Player) e.getWhoClicked());
                }
            }
        } else if (e.getView().title().equals(strings.sspGUITitle)) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                if (e.getCurrentItem() != buttons.singleBackgroundItem) {
                    for (ssp ssp : defaultData.activessp) {
                        if (ssp.getInv().equals(e.getClickedInventory())) {
                            ssp.performAction(e.getCurrentItem(), (Player) e.getWhoClicked());
                        }
                    }
                }
            }
        } else if (e.getView().title().equals(strings.vierGewinntGUITitle)) {
            e.setCancelled(true);
            if(e.getCurrentItem() != buttons.singleBackgroundItem){
                for(vierGewinnt vierGewinnt : defaultData.activeVierGewinnt){
                    if(vierGewinnt.getInv().equals(e.getClickedInventory())){
                        try {
                            vierGewinnt.performAction(e.getSlot(), (Player) e.getWhoClicked());
                        } catch (InterruptedException ignored) {}
                        break;
                    }
                }
            }
        }
        if (Objects.equals(e.getCurrentItem(), buttons.closeButton)) {
            e.getWhoClicked().closeInventory();
        }
    }
    @EventHandler
    public void onBookEvent(PlayerEditBookEvent e){
        BookMeta bm = e.getNewBookMeta();
        Bukkit.getLogger().info(e.getPlayer().getName()+" hat ein Buch bearbeitet");
        if(bm.getPageCount() > 1){
            e.getPlayer().closeInventory();
        }
    }
}
