package de.lioncraft.arcadegames.data;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.games.enums.GameType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class buttons {
    public static ItemStack closeButton;
    public static ItemStack settingsButton;
    public static ItemStack[] backgroundItem;
    public static ItemStack singleBackgroundItem;
    public static ItemStack settingsButton1;
    public static ItemStack settingsButton2;
    public static ItemStack settingsButton3;
    public static ItemStack backButton;
    public static ItemStack player1Item;
    public static ItemStack player2Item;
    public static ItemStack nextItem;
    public static ItemStack playerHeadItem;
    public static ItemStack sspItem;
    public static ItemStack sspItemSchere;
    public static ItemStack sspItemStein;
    public static ItemStack sspItemPapier;
    public static ItemStack tiktaktoeItem;
    public static ItemStack sspItemWait;
    public static ItemStack sspItemFinish;
    public static ItemStack vierGewinntItem;
    public static ItemStack UNOItem;

    /*
    sb1: anfragen von allen / freunden / keinem
    sb2: anfragen außerhalb der Arcade lobby von allen / freunden / keinem
    sb3: Ressourcenpaket?

     */



    public void setVariables(){
        setCloseButton();
        setSettingsButton();
        setSettingsButtons();
        setBackgroundItem();
        backButton = setdefaultItem(Component.text("ZURÜCK"), Material.SPECTRAL_ARROW, "");
        player1Item = setPlayer1Item();
        player2Item = setPlayer2Item();
        nextItem = setNextItem();
        playerHeadItem = setPlayerHeadItem();
        sspItem = setSSPItem();
        tiktaktoeItem = setTiktaktoeItem();
        sspItemPapier = setdefaultItem(Component.text("Schere"), Material.SHEARS, "Klicke um Schere zu setzen.").clone();
        sspItemStein = setdefaultItem(Component.text("Stein"), Material.STONE_BRICKS, "Klicke um Stein zu setzen.").clone();
        sspItemSchere = setdefaultItem(Component.text("Papier"), Material.PAPER, "Klicke um Papier zu setzen.").clone();
        sspItemWait = setdefaultItem(Component.text("Warten auf Eingabe"), Material.ORANGE_STAINED_GLASS_PANE, "Warte auf Eingabe dieses Spielers.").clone();
        sspItemFinish = setdefaultItem(Component.text("Fertig"), Material.LIME_STAINED_GLASS_PANE, "Warte auf Eingabe des gegnerischen Spielers.").clone();
        vierGewinntItem = setdefaultItem(Component.text("Vier Gewinnt"), Material.SNOWBALL, "Vier Gewinnt nach bekannten Regeln...");
        UNOItem = setDefaultItem(Component.text("UNO", TextColor.color(255, 0, 0)), Material.RED_BANNER, "UNO, bis zu 4 Spieler ","!-ENTWICKLUNGSPHASE-!");
        ItemMeta im = vierGewinntItem.getItemMeta();
        im.getPersistentDataContainer().set(new NamespacedKey(ArcadeGames.getPlugin(), "gametype"), PersistentDataType.STRING, GameType.VierGewinnt.toString());
        vierGewinntItem.setItemMeta(im);
    }
    /*CloseButton: Barrier, CMD 1
    * SettingsButton: CommandBlock, CMD 1
    *
    *
    *
    *
    * */
    public static void setCloseButton() {
        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeButtonMeta = closeButton.getItemMeta();
        closeButtonMeta.displayName(Component.text("Schließen").color(TextColor.color(255, 0, 0)));
        closeButtonMeta.setUnbreakable(true);
        closeButtonMeta.setCustomModelData(1);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Klicke hier um das aktuelle "));
        lore.add(Component.text("Fenster zu schließen."));
        closeButtonMeta.lore(lore);
        closeButton.setItemMeta(closeButtonMeta);
        buttons.closeButton = closeButton;
    }

    public static void setSettingsButton() {
        ItemStack Button = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("Einstellungen").color(TextColor.color(0, 255, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Klicke hier um die Einstellungen "));
        lore.add(Component.text("zu öffnen."));
        ButtonMeta.lore(lore);
        Button.setItemMeta(ButtonMeta);
        buttons.settingsButton = Button;
    }
    public static void setBackgroundItem(){
        ItemStack Button = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text(""));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        ButtonMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        Button.setItemMeta(ButtonMeta);
        singleBackgroundItem = Button;
        backgroundItem = new ItemStack[54];
        for(int i = 0; i < 54; i++){
            backgroundItem[i] = Button;
        }
    }
    public static ItemStack setSettingsButtons(){
        ItemStack Button = new ItemStack(Material.TORCHFLOWER);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("Spiel-Anfragen").color(TextColor.color(0, 255, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Legt fest, ob du im Arcade-Bereich"));
        lore.add(Component.text("Spielherausforderungen von allen, "));
        lore.add(Component.text("nur von Freunden oder von keinem "));
        lore.add(Component.text("erhältst."));
        ButtonMeta.lore(lore);
        Button.setItemMeta(ButtonMeta);
        buttons.settingsButton1 = Button.clone();
        //item/einstellung 2
        Button.setType(Material.GOAT_HORN);
        ButtonMeta.displayName(Component.text("Spiel-Anfragen außerhalb des Arcade-Bereichs").color(TextColor.color(0, 255, 0)));
        lore.set(0, Component.text("Legt fest, ob du in der gesamten Lobby"));
        ButtonMeta.lore(lore);
        Button.setItemMeta(ButtonMeta);
        buttons.settingsButton2 = Button.clone();
        //item/einstellung 3
        Button.setType(Material.PAINTING);
        ButtonMeta.displayName(Component.text("Ressourcenpaket").color(TextColor.color(0, 255, 0)));
        lore.set(0, Component.text("Legt fest, ob du beim Betreten"));
        lore.set(1, Component.text("des Arcade-Bereichs dazu aufgefordert"));
        lore.set(2, Component.text("wirst, ein Ressourcenpaket"));
        lore.set(3, Component.text("zu laden"));
        ButtonMeta.lore(lore);
        Button.setItemMeta(ButtonMeta);
        buttons.settingsButton3 = Button.clone();
        return Button;
    }
    public static ItemStack setdefaultItem(Component title, Material material, String lores){
        ItemStack tempItem = new ItemStack(material);
        ItemMeta ButtonMeta = tempItem.getItemMeta();
        ButtonMeta.displayName(title);
        ButtonMeta.setUnbreakable(true);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(lores));
        ButtonMeta.lore(lore);
        tempItem.setItemMeta(ButtonMeta);
        return tempItem;
    }
    public static ItemStack setDefaultItem(Component title, Material material, String... lores){
        ItemStack tempItem = new ItemStack(material);
        ItemMeta ButtonMeta = tempItem.getItemMeta();
        ButtonMeta.displayName(title);
        ButtonMeta.setUnbreakable(true);
        List<Component> lore = new ArrayList<>();
        for(String s : lores){
            lore.add(Component.text(s));
        }
        ButtonMeta.addItemFlags();
        ButtonMeta.lore(lore);
        tempItem.setItemMeta(ButtonMeta);
        return tempItem;
    }
    public static ItemStack setDefaultItem(Component title, Material material, TextColor loreColor, String... lores){
        ItemStack tempItem = new ItemStack(material);
        ItemMeta ButtonMeta = tempItem.getItemMeta();
        ButtonMeta.displayName(title);
        ButtonMeta.setUnbreakable(true);
        List<Component> lore = new ArrayList<>();
        for(String s : lores){
            lore.add(Component.text(s, loreColor));
        }
        ButtonMeta.addItemFlags();
        ButtonMeta.lore(lore);
        tempItem.setItemMeta(ButtonMeta);
        return tempItem;
    }
    public static ItemStack setDefaultItem(Component title, Material material, Component... lores){
        ItemStack tempItem = new ItemStack(material);
        ItemMeta ButtonMeta = tempItem.getItemMeta();
        ButtonMeta.displayName(title);
        ButtonMeta.setUnbreakable(true);
        List<Component> lore = new ArrayList<>(Arrays.asList(lores));
        ButtonMeta.addItemFlags();
        ButtonMeta.lore(lore);
        tempItem.setItemMeta(ButtonMeta);
        return tempItem;
    }
    public ItemStack setPlayer1Item(){
        ItemStack Button = new ItemStack(Material.ENDER_PEARL);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("X").color(TextColor.color(255, 0, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        ButtonMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        Button.setItemMeta(ButtonMeta);
        return Button;
    }
    public ItemStack setPlayer2Item(){
        ItemStack Button = new ItemStack(Material.SNOWBALL);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("O").color(TextColor.color(0, 255, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        ButtonMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        Button.setItemMeta(ButtonMeta);
        return Button;
    }
    public ItemStack setNextItem(){
        ItemStack Button = new ItemStack(Material.SPECTRAL_ARROW);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("Nächste Seite").color(TextColor.color(0, 255, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        ButtonMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        Button.setItemMeta(ButtonMeta);
        return Button;
    }
    public ItemStack setPlayerHeadItem(){
        ItemStack Button = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("Spieler-Auswahl").color(TextColor.color(0, 255, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        ButtonMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        Button.setItemMeta(ButtonMeta);
        return Button;
    }
    public ItemStack setSSPItem(){
        ItemStack Button = new ItemStack(Material.SHEARS);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("Schere Stein Papier").color(TextColor.color(0, 255, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        ButtonMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        ButtonMeta.getPersistentDataContainer().set(new NamespacedKey(ArcadeGames.getPlugin(), "gametype"), PersistentDataType.STRING, GameType.SSP.toString());
        Button.setItemMeta(ButtonMeta);
        return Button;
    }
    public ItemStack setTiktaktoeItem(){
        ItemStack Button = new ItemStack(Material.ENDER_PEARL);
        ItemMeta ButtonMeta = Button.getItemMeta();
        ButtonMeta.displayName(Component.text("Tik Tak Toe").color(TextColor.color(0, 255, 0)));
        ButtonMeta.setUnbreakable(true);
        ButtonMeta.setCustomModelData(1);
        ButtonMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        ButtonMeta.getPersistentDataContainer().set(new NamespacedKey(ArcadeGames.getPlugin(), "gametype"), PersistentDataType.STRING, GameType.TikTakToe.toString());
        Button.setItemMeta(ButtonMeta);
        return Button;
    }

}
