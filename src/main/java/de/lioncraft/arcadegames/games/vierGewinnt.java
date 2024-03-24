package de.lioncraft.arcadegames.games;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.data.buttons;
import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.games.enums.nearbySlotType;
import de.lioncraft.arcadegames.games.delayedTasks.vierGewinntDelayedTasks;
import de.lioncraft.arcadegames.games.enums.GameType;
import de.lioncraft.arcadegames.utils.baseMethods;
import de.lioncraft.arcadegames.utils.utilClass;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class vierGewinnt extends game{
    ItemStack p1Item, p2Item, p1Wait, p2Wait;

    
    public vierGewinnt(Player player1, Player player2, int rounds) {
        super(GameType.VierGewinnt, player1, player2, rounds);
        inv = Bukkit.createInventory(player1, 54, strings.vierGewinntGUITitle);
        defaultData.activeVierGewinnt.add(this);
        p1Item = buttons.player1Item;
        p2Item = buttons.player2Item;
        p2Wait = buttons.setdefaultItem(Component.text("Warte auf den Zug von " + player2.getName()), Material.CLOCK, "...");
        p1Wait = buttons.setdefaultItem(Component.text("Warte auf den Zug von " + player1.getName()), Material.CLOCK, "...");
        for(Player p : players){
            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
            p.openInventory(inv);
            p.getOpenInventory().getBottomInventory().setItem(9, new baseMethods().getPlayerHead(player1));
            p.getOpenInventory().getBottomInventory().setItem(17, new baseMethods().getPlayerHead(player2));
            p.getOpenInventory().getBottomInventory().setItem(10, p1Wait);
        }
    }
    public void performAction(int slot, Player clicker) throws InterruptedException {
        if(!isActive){
            return;
        }
        if(inv.getItem(slot) == null){
            if(isp1turn){
                if(clicker == player1) {
                    isActive = false;
                    boolean b = true;
                    do {
                        if (!(slot - 9 < 0)) {
                            inv.setItem(slot - 9, null);
                        }
                        inv.setItem(slot, p1Item);
                        slot += 9;
                        if (slot < 54) {
                            if(inv.getItem(slot) != null){
                                b = false;
                            }
                        }else{
                            b = false;
                        }
                    } while (b);
                    isp1turn = false;
                    for (Player p : players) {
                        p.getOpenInventory().getBottomInventory().setItem(10, null);
                        p.getOpenInventory().getBottomInventory().setItem(16, p2Wait);
                    }
                    checkForWin(slot, clicker, p1Item);
                    isActive = true;
                }
            }else{
                if(clicker == player2){
                    do {
                        if(!(slot - 9 < 0)){
                            inv.setItem(slot - 9, null);
                        }
                        inv.setItem(slot, p2Item);
                        slot += 9;
                    }while (inv.getItem(slot) == null && slot < 54);
                    isp1turn = true;
                    for(Player p : players){
                        p.getOpenInventory().getBottomInventory().setItem(16, null);
                        p.getOpenInventory().getBottomInventory().setItem(10, p1Wait);
                    }
                    checkForWin(slot, clicker, p2Item);
                }
            }
        }
    }
    public boolean checkForWin(int slot, Player clicker, ItemStack item){
        int horizontal = 1, vertikal = 1, diagonalRechtsOben = 1, diagonalLinksOben = 1;
        HashMap<Integer, nearbySlotType> hm = utilClass.getNearSlots(slot, slot, null);
        for(int i : hm.keySet()){
            if(item.isSimilar(inv.getItem(i))){
                if (hm.get(i).equals(nearbySlotType.horizontal)){
                    horizontal++;
                    Integer temp = utilClass.getNextInRow(i, slot, nearbySlotType.horizontal);
                    if (temp != null){
                        if(item.isSimilar(inv.getItem(temp))){
                            horizontal++;
                            Integer temp2 = utilClass.getNextInRow(temp, i, nearbySlotType.horizontal);
                            if(temp2 != null){
                                if(item.isSimilar(inv.getItem(temp2))){
                                    horizontal++;
                                }
                            }
                        }
                    }
                } else if (hm.get(i).equals(nearbySlotType.vertikal)) {
                    vertikal++;
                    Integer temp = utilClass.getNextInRow(i, slot, nearbySlotType.vertikal);
                    if (temp != null){
                        if(item.isSimilar(inv.getItem(temp))){
                            vertikal++;
                            Integer temp2 = utilClass.getNextInRow(temp, i, nearbySlotType.vertikal);
                            if(temp2 != null){
                                if(item.isSimilar(inv.getItem(temp2))){
                                    vertikal++;
                                }
                            }
                        }
                    }
                } else if (hm.get(i).equals(nearbySlotType.diagonalRechtsOben)) {
                    diagonalRechtsOben++;
                    Integer temp = utilClass.getNextInRow(i, slot, nearbySlotType.diagonalRechtsOben);
                    if (temp != null){
                        if(item.isSimilar(inv.getItem(temp))){
                            diagonalRechtsOben++;
                            Integer temp2 = utilClass.getNextInRow(temp, i, nearbySlotType.diagonalRechtsOben);
                            if(temp2 != null){
                                if(item.isSimilar(inv.getItem(temp2))){
                                    diagonalRechtsOben++;
                                }
                            }
                        }
                    }
                } else if (hm.get(i).equals(nearbySlotType.diagonalLinksOben)) {
                    diagonalLinksOben++;
                    Integer temp = utilClass.getNextInRow(i, slot, nearbySlotType.diagonalLinksOben);
                    if (temp != null){
                        if(item.isSimilar(inv.getItem(temp))){
                            diagonalLinksOben++;
                            Integer temp2 = utilClass.getNextInRow(temp, i, nearbySlotType.diagonalLinksOben);
                            if(temp2 != null){
                                if(item.isSimilar(inv.getItem(temp2))){
                                    diagonalLinksOben++;
                                }
                            }
                        }
                    }
                }
            }
        }
        if((diagonalLinksOben >= 4) || (diagonalRechtsOben >= 4) || (vertikal >= 4) || (horizontal >= 4)){
            win(clicker);
            return true;
        }
        return false;
    }
    public void win(@NotNull Player winner){
        isActive = false;
        if(winner.equals(player1)){
            super.win(player1, player2);
        }else{
            super.win(player2, player1);
        }
        List<Component> list = new ArrayList<>();
        List<Component> list2 = new ArrayList<>();
        list.add(player1.name().append(Component.text(" hat ").append(Component.text(p1wins).append(Component.text(" mal gewonnen.")))));
        list2.add(player2.name().append(Component.text(" hat ").append(Component.text(p2wins).append(Component.text(" mal gewonnen.")))));
        for(Player p : players){
            if(p1wins != 0){
                Objects.requireNonNull(p.getOpenInventory().getBottomInventory().getItem(9)).setAmount(p1wins);
            }
            if(p2wins != 0){
                Objects.requireNonNull(p.getOpenInventory().getBottomInventory().getItem(17)).setAmount(p2wins);
            }
            Objects.requireNonNull(p.getOpenInventory().getBottomInventory().getItem(9)).lore(list);
            Objects.requireNonNull(p.getOpenInventory().getBottomInventory().getItem(17)).lore(list2);
        }
        new vierGewinntDelayedTasks(this).runTaskLater(ArcadeGames.getPlugin(), 80);

    }
    public void startNewRound(){
        inv.clear();
        for(Player p : players){
            p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
            if(isp1turn){
                p.getOpenInventory().getBottomInventory().setItem(10, p1Wait);
            }else{
                p.getOpenInventory().getBottomInventory().setItem(16, p2Wait);
            }

        }
        isActive = true;
    }
    public void endGame(){
        defaultData.activeVierGewinnt.remove(this);
        super.endGame();
    }
}
