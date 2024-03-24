package de.lioncraft.arcadegames.games;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.data.buttons;
import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.data.inventoryContent;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.games.delayedTasks.startRoundLater;
import de.lioncraft.arcadegames.games.enums.GameType;
import de.lioncraft.arcadegames.games.enums.winType;
import de.lioncraft.arcadegames.utils.baseMethods;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ssp extends game{

    ItemStack p1item, p2item;

    ssp(Player player1, Player player2, int rounds){
        super(GameType.SSP, player1, player2, rounds);
        inv = Bukkit.createInventory(player1, 54, strings.sspGUITitle);
        inv.setContents(inventoryContent.sspGUIContent);
        inv.setItem(18, new baseMethods().getPlayerHead(player1));
        inv.setItem(26, new baseMethods().getPlayerHead(player2));
        inv.setItem(21, buttons.sspItemWait);
        inv.setItem(23, buttons.sspItemWait);
        SkullMeta sm = (SkullMeta) inv.getItem(18).getItemMeta();
        List<Component> list = new ArrayList<>();
        list.add(Component.text("0 Siege!"));
        sm.lore(list);
        inv.getItem(18).setItemMeta(sm);
        SkullMeta sm2 = (SkullMeta) inv.getItem(26).getItemMeta();
        sm2.lore(list);
        inv.getItem(26).setItemMeta(sm2);
        player1.openInventory(inv);
        player2.openInventory(inv);
        defaultData.activessp.add(this);
    }
    public void performAction(ItemStack clickedItem, Player player){
        if(!isActive){
           return;
        }
        if(player == player1){
            if(clickedItem.equals(buttons.sspItemPapier) || clickedItem.equals(buttons.sspItemSchere) || clickedItem.equals(buttons.sspItemStein)){
                inv.setItem(21, buttons.sspItemFinish);
                p1item = clickedItem;
                if (p2item != null) {
                    checkForWin();
                }
            }

        } else if (player == player2) {
            if(clickedItem.equals(buttons.sspItemPapier) || clickedItem.equals(buttons.sspItemSchere) || clickedItem.equals(buttons.sspItemStein)){
                inv.setItem(23, buttons.sspItemFinish);
                p2item = clickedItem;
                if (p1item != null) {
                    checkForWin();
                }
            }
        }
    }
    public void checkForWin(){
        isActive = false;
        rounds--;
        if(p1item.equals(p2item)){
            unentschieden();
        }
        if(p1item.equals(buttons.sspItemSchere)){
            if(p2item.equals(buttons.sspItemStein)){
                win(player1);
            } else if (p2item.equals(buttons.sspItemPapier)) {
                win(player2);
            }
        }
        if(p1item.equals(buttons.sspItemStein)){
            if(p2item.equals(buttons.sspItemSchere)){
                win(player2);
            } else if (p2item.equals(buttons.sspItemPapier)) {
                win(player1);
            }
        }
        if(p1item.equals(buttons.sspItemPapier)){
            if(p2item.equals(buttons.sspItemStein)){
                win(player2);
            } else if (p2item.equals(buttons.sspItemSchere)) {
                win(player1);
            }
        }
        p1item = null;
        p2item = null;
    }
    public void unentschieden(){
        p1winlist.add(winType.unentschieden);
        //wenn unentschieden: kein Abzug bei Runden
        rounds++;
        inv.setItem(21, p1item);
        inv.setItem(23, p2item);
        if(getRounds() == 0){
        super.endGame();
        }
    }
    public void win(Player winner){
        inv.setItem(21, p1item);
        inv.setItem(23, p2item);
        if (winner.equals(player1)) {
            super.win(winner, player2);
            ItemStack is = inv.getItem(18);
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.lore(new ArrayList<>(Collections.singleton(Component.text(p1wins + " Siege!"))));
            is.setItemMeta(sm);
            is.setAmount(p1wins);
            inv.setItem(18, is);
        }else{
            super.win(winner, player1);
            ItemStack is = inv.getItem(26);
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.lore(new ArrayList<>(Collections.singleton(Component.text(p2wins + " Siege!"))));
            is.setItemMeta(sm);
            is.setAmount(p2wins);
            inv.setItem(26, is);
        }
        if(getRounds() == 0){
            defaultData.activessp.remove(this);
            super.endGame();
        }else{
            new startRoundLater(this).runTaskLater(ArcadeGames.getPlugin(), 50);
        }
    }
    public void cancel(){
        defaultData.activessp.remove(this);
        endGame();
        getPlayer1().sendMessage(strings.chatPrefix.append(Component.text("Das Spiel wurde abgebrochen!")));
        getPlayer2().sendMessage(strings.chatPrefix.append(Component.text("Das Spiel wurde abgebrochen!")));
    }
    public void startNewRound(){
        inv.setItem(21, buttons.sspItemWait);
        inv.setItem(23, buttons.sspItemWait);
        isActive = true;
    }
}
