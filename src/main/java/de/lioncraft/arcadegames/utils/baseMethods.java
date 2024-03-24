package de.lioncraft.arcadegames.utils;

import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.games.enums.GameType;
import de.lioncraft.arcadegames.games.tiktaktoe;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class baseMethods {
    public boolean isInArcadeArea(Player p){
        if(defaultData.isAreaSet){
            if(defaultData.pos2.getX() <= p.getLocation().getX() && p.getLocation().getX() <= defaultData.pos1.getX()){
                if(defaultData.pos2.getY() <= p.getLocation().getY() && p.getLocation().getY() <= defaultData.pos1.getY()){
                    if (defaultData.pos2.getZ() <= p.getLocation().getZ() && p.getLocation().getZ() <= defaultData.pos1.getZ()) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public void startGame(Player p1, Player p2, GameType gameType, int rounds){
        if (gameType.equals(GameType.SSP)) {
            tiktaktoe tiktaktoe = new tiktaktoe(p1, p2, rounds);
        } else if (gameType.equals(GameType.TikTakToe)) {
            
        } else if (gameType.equals(GameType.UNO)) {
            
        }
    }
    public ItemStack getPlayerHead(Player player){
        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) is.getItemMeta();
        sm.setOwningPlayer(player);
        sm.displayName(player.displayName());
        sm.setUnbreakable(true);
        sm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        is.setItemMeta(sm);
        return is;
    }

}
