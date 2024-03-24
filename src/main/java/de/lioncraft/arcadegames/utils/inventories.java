package de.lioncraft.arcadegames.utils;

import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.data.inventoryContent;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.data.buttons;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class inventories {
    public void openMainGUI(Player owner){
        Inventory inv = Bukkit.createInventory(owner, 54, strings.MainGUITitle);
        inv.setContents(inventoryContent.MainGUIContent);
        owner.openInventory(inv);
    }
    public void openSettingsGUI(Player owner){
        Inventory inv = Bukkit.createInventory(owner, 54, strings.SettingsGUITitle);
        inv.setContents(inventoryContent.SettingsGUIContent);
        //checken, welche settings der Player hat
        inv.setItem(19, new buttons().setdefaultItem(inv.getItem(10).displayName(), Material.GRAY_DYE, "Klicke zum umschalten"));
        inv.setItem(22, new buttons().setdefaultItem(inv.getItem(13).displayName(), Material.GRAY_DYE, "Klicke zum umschalten"));
        inv.setItem(25, new buttons().setdefaultItem(inv.getItem(16).displayName(), Material.GRAY_DYE, "Klicke zum umschalten"));
        inv.setItem(45, buttons.backButton);
        owner.openInventory(inv);
    }
    public void openPlayerSelectionGUI(Player owner, int page, ItemStack gameItem){
        Inventory inv = Bukkit.createInventory(owner, 54, strings.playerSelectionGUITitle);
        inv.setContents(inventoryContent.defaultGUIContent);
        inv.setItem(4, gameItem);
        inv.setItem(54, buttons.backButton);
        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) is.getItemMeta();
        sm.setUnbreakable(true);
        List<Component> list = new ArrayList<>();
        list.add(Component.text("Klicke, um diesem Spieler eine"));
        list.add(Component.text("Spielanfrage zu senden."));
        sm.lore(list);
        int i = 9;
        for(Player p : defaultData.playerInArcadeArea){
            if (i < (page * 36) + 9) {
                i++;
                continue;
            }
            if(p.equals(owner)){ continue; }
            sm.displayName(p.displayName());
            sm.setOwningPlayer(p);
            is.setItemMeta(sm);
            inv.setItem(i, is);
            i++;
            if(i > page * 36 + 44){
                inv.setItem(53, buttons.nextItem);
                break; }
        }
        owner.openInventory(inv);
    }
    public void openGameAmountSelectionGUI(Player owner, ItemStack gameItem){
        Inventory inv = Bukkit.createInventory(owner, 54, strings.amountSelectionGUITitle);
        inv.setContents(inventoryContent.amountSelectionGUIContent);
        inv.setItem(4, gameItem);
        owner.openInventory(inv);
    }
}
