package de.lioncraft.arcadegames.games.delayedTasks;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class closeInvLater extends BukkitRunnable {
    Player p;
    @Override
    public void run() {
        p.closeInventory();
        p.playSound(p, Sound.BLOCK_BARREL_CLOSE, 1.0F, 1.0F);

    }
    public closeInvLater(Player player){
        p = player;
    }
}
