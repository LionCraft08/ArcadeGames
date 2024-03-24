package de.lioncraft.arcadegames.data;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class inventoryContent {
    public static ItemStack[] MainGUIContent;
    public static ItemStack[] SettingsGUIContent;
    public static ItemStack[] defaultGUIContent;
    public static ItemStack[] tiktaktoeGUIContent;
    public static ItemStack[] amountSelectionGUIContent;
    public static ItemStack[] sspGUIContent;

    public void setContent(){

        MainGUIContent = buttons.backgroundItem.clone();
        MainGUIContent[49] = buttons.closeButton;
        MainGUIContent[53] = buttons.settingsButton;
        MainGUIContent[45] = buttons.playerHeadItem;
        MainGUIContent[10] = buttons.tiktaktoeItem;
        MainGUIContent[12] = buttons.sspItem;
        MainGUIContent[14] = buttons.vierGewinntItem;

        SettingsGUIContent = buttons.backgroundItem.clone();
        SettingsGUIContent[49] = buttons.closeButton;
        SettingsGUIContent[10] = buttons.settingsButton1;
        SettingsGUIContent[13] = buttons.settingsButton2;
        SettingsGUIContent[16] = buttons.settingsButton3;

        defaultGUIContent = buttons.backgroundItem.clone();
        defaultGUIContent[49] = buttons.closeButton;

        tiktaktoeGUIContent = buttons.backgroundItem.clone();
        tiktaktoeGUIContent[49] = buttons.closeButton;
        tiktaktoeGUIContent[12] = null;
        tiktaktoeGUIContent[13] = null;
        tiktaktoeGUIContent[14] = null;
        tiktaktoeGUIContent[21] = null;
        tiktaktoeGUIContent[22] = null;
        tiktaktoeGUIContent[23] = null;
        tiktaktoeGUIContent[31] = null;
        tiktaktoeGUIContent[32] = null;
        tiktaktoeGUIContent[30] = null;
        amountSelectionGUIContent = buttons.backgroundItem.clone();
        amountSelectionGUIContent[10] = buttons.setdefaultItem(Component.text("1 Runde") , Material.CLOCK, "Ein Spiel, bis einer gewinnt...");
        amountSelectionGUIContent[12] = buttons.setdefaultItem(Component.text("3 Runden") , Material.CLOCK, "Best-of-3, gewinne mind. 2 mal.");
        amountSelectionGUIContent[12].setAmount(3);
        amountSelectionGUIContent[14] = buttons.setdefaultItem(Component.text("5 Runden") , Material.CLOCK, "Best-of-5, gewinne mind. 3 mal.");
        amountSelectionGUIContent[14].setAmount(5);
        amountSelectionGUIContent[16] = buttons.setdefaultItem(Component.text("7 Runden") , Material.CLOCK, "Best-of-7, gewinne mind. 4 mal.");
        amountSelectionGUIContent[16].setAmount(7);
        amountSelectionGUIContent[29] = buttons.setdefaultItem(Component.text("15 Runden") , Material.CLOCK, "Best-of-15, für den ultimativen Spielspaß.");
        amountSelectionGUIContent[29].setAmount(13);
        amountSelectionGUIContent[33] = buttons.setdefaultItem(Component.text("21 Runden") , Material.CLOCK, "Best-of-21, für ganz langen Spielspaß.");
        amountSelectionGUIContent[33].setAmount(21);
        amountSelectionGUIContent[45] = buttons.backButton;

        sspGUIContent = buttons.backgroundItem.clone();
        sspGUIContent[47] = buttons.sspItemSchere;
        sspGUIContent[49] = buttons.sspItemPapier;
        sspGUIContent[51] = buttons.sspItemStein;
    }
}
