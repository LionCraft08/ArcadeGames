package de.lioncraft.arcadegames.utils;

import de.lioncraft.arcadegames.data.buttons;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class settingsChanger {
    public void onButtonClick(ItemStack clickedItem, Player whoClicked, int slot, Inventory inv){
        if(clickedItem.displayName().equals(buttons.settingsButton1.displayName())){
            //DB daten verändern!!!
            inv.setItem(slot, switchItem(clickedItem));
        } else if (clickedItem.displayName().equals(buttons.settingsButton2.displayName())) {
         inv.setItem(slot, switchItem(clickedItem));
        } else if (clickedItem.displayName().equals(buttons.settingsButton3.displayName())) {
            inv.setItem(slot, switchItem(clickedItem));
        }
    }
    /*
    sb1: anfragen von allen / freunden / keinem
    sb2: anfragen außerhalb der Arcade lobby von allen / freunden / keinem
    sb3: Ressourcenpaket?

     */
    public ItemStack switchItem(ItemStack previous){
        if(previous.getType().equals(Material.GRAY_DYE)){
            previous.setType(Material.ORANGE_DYE);
        } else if (previous.getType().equals(Material.LIME_DYE)) {
            previous.setType(Material.GRAY_DYE);
        }else{
            previous.setType(Material.LIME_DYE);
        }
        return previous;
    }
    public ItemStack switchItemtruefalse(ItemStack previous){
        if(previous.getType().equals(Material.GRAY_DYE)){
            previous.setType(Material.LIME_DYE);
        }else{
            previous.setType(Material.LIME_DYE);
        }
        return previous;
    }
}
