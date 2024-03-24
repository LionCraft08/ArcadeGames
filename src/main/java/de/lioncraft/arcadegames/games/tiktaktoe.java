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
import de.lioncraft.arcadegames.games.delayedTasks.closeInvLater;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class tiktaktoe extends game{

    public tiktaktoe(Player player1, Player player2, int rounds){
        super(GameType.TikTakToe, player1, player2, rounds);
        inv = Bukkit.createInventory(player1, 54, strings.tiktaktoeGUITitle);
        inv.setContents(inventoryContent.tiktaktoeGUIContent);
        inv.setItem(17, new baseMethods().getPlayerHead(player1));
        inv.setItem(44, new baseMethods().getPlayerHead(player2));
        player1.openInventory(inv);
        player2.openInventory(inv);
        defaultData.activeTiktaktoe.add(this);
        SkullMeta sm = (SkullMeta) inv.getItem(17).getItemMeta();
        List<Component> list = new ArrayList<>();
        list.add(Component.text("0 Siege!"));
        sm.lore(list);
        inv.getItem(17).setItemMeta(sm);
        SkullMeta sm2 = (SkullMeta) inv.getItem(44).getItemMeta();
        sm2.lore(list);
        inv.getItem(44).setItemMeta(sm2);
    }
    public void performClickAction(Player clicker, int slot){
        if (!isActive) {
            return;
        }
        if(isp1turn){
            if(clicker.equals(player1)){
                if(inv.getItem(slot) == null){
                    isp1turn = false;
                    setSlot(slot, clicker);
                }
            }
        }else{
            if(clicker.equals(player2)){
                if(inv.getItem(slot) == null){
                    isp1turn = true;
                    setSlot(slot, clicker);
                }
            }
        }
    }
    public void setSlot(int slot, Player player){
        if(player == player1){
            inv.setItem(slot, buttons.player1Item);
        } else if (player == player2) {
            inv.setItem(slot, buttons.player2Item);
        }
        checkWin(slot);
    }
    public boolean checkWin(int slot){
        ItemStack is = inv.getItem(slot);
        switch (slot){
            case 12, 13, 14:
                if (Objects.equals(is, inv.getItem(slot + 9))) {
                    if (Objects.equals(is, inv.getItem(slot + 18))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 21, 22, 23:
                if (Objects.equals(is, inv.getItem(slot + 9))) {
                    if (Objects.equals(is, inv.getItem(slot - 9))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 30, 31, 32:
                if (Objects.equals(is, inv.getItem(slot - 18))) {
                    if (Objects.equals(is, inv.getItem(slot - 9))) {
                        win(slot);
                        return true;
                    }
                }
                break;

        }
        switch (slot){
            case 12, 21, 30:
                if (Objects.equals(is, inv.getItem(slot + 1))) {
                    if (Objects.equals(is, inv.getItem(slot + 2))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 13, 22, 31:
                if (Objects.equals(is, inv.getItem(slot + 1))) {
                    if (Objects.equals(is, inv.getItem(slot - 1))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 14, 23, 32:
                if (Objects.equals(is, inv.getItem(slot - 1))) {
                    if (Objects.equals(is, inv.getItem(slot - 2))) {
                        win(slot);
                        return true;
                    }
                }
                break;

        }
        switch (slot){
            case 12:
                if (Objects.equals(is, inv.getItem(22))) {
                    if (Objects.equals(is, inv.getItem(32))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 14:
                if (Objects.equals(is, inv.getItem(22))) {
                    if (Objects.equals(is, inv.getItem(30))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 30:
                if (Objects.equals(is, inv.getItem(22))) {
                    if (Objects.equals(is, inv.getItem(14))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 32:
                if (Objects.equals(is, inv.getItem(22))) {
                    if (Objects.equals(is, inv.getItem(12))) {
                        win(slot);
                        return true;
                    }
                }
                break;
            case 22:
                if (Objects.equals(is, inv.getItem(12))) {
                    if (Objects.equals(is, inv.getItem(32))) {
                        win(slot);
                        return true;
                    }
                }
                if (Objects.equals(is, inv.getItem(30))) {
                    if (Objects.equals(is, inv.getItem(14))) {
                        win(slot);
                        return true;
                    }
                }
                break;

        }
        if(inv.getItem(12) != null && inv.getItem(13) != null && inv.getItem(14) != null){
            if(inv.getItem(22) != null && inv.getItem(21) != null && inv.getItem(23) != null){
                if(inv.getItem(30) != null && inv.getItem(31) != null && inv.getItem(32) != null){
                    player1.playSound(player1, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0F, 1.0F);
                    player2.playSound(player2, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0F, 1.0F);
                    p1winlist.add(winType.unentschieden);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException ignored) {}
                    startNewRound();
                }
            }
        }
        return true;
    }
    public void win(int slot){
        isActive = false;
        if(Objects.equals(inv.getItem(slot), buttons.player1Item)){
            super.win(player1, player2);
            ItemStack is = inv.getItem(17);
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.lore(new ArrayList<>(Collections.singleton(Component.text(p1wins + " Siege!"))));
            is.setItemMeta(sm);
            is.setAmount(p1wins);
            inv.setItem(17, is);
        } else {
            super.win(player2, player1);
            ItemStack is = inv.getItem(44);
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.lore(new ArrayList<>(Collections.singleton(Component.text(p2wins + " Siege!"))));
            is.setAmount(p2wins);
            is.setItemMeta(sm);
            inv.setItem(44, is);
        }
        rounds--;
        if(rounds <= 0){
            endGame();
        }else{
            new startRoundLater(this).runTaskLater(ArcadeGames.getPlugin(), 50);
        }
    }
    public void endGame(){
        super.endGame();
        defaultData.activeTiktaktoe.remove(this);
    }
    public void startNewRound(){
        inv.setItem(12, null);
        inv.setItem(13, null);
        inv.setItem(14, null);
        inv.setItem(21, null);
        inv.setItem(22, null);
        inv.setItem(23, null);
        inv.setItem(31, null);
        inv.setItem(32, null);
        inv.setItem(30, null);
        isActive = true;
    }
}
