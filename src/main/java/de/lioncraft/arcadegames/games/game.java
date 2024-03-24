package de.lioncraft.arcadegames.games;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.games.enums.GameType;
import de.lioncraft.arcadegames.games.enums.winType;
import de.lioncraft.arcadegames.games.delayedTasks.closeInvLater;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class game {
    GameType gameType;
    Player player1, player2;
    List<Player> players;
    Inventory inv;
    boolean isActive, isp1turn;
    int rounds, p1wins, p2wins;
    List<winType> p1winlist;

    public game(GameType gameType, Player player1, Player player2, int rounds) {
        this.gameType = gameType;
        this.player1 = player1;
        this.player2 = player2;
        this.rounds = rounds;
        p1winlist = new ArrayList<>();
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        isActive = true;
        isp1turn = true;
    }

    public void endGame(){
        /*messages, Sound, InvClose(delayed), stats*/
        new closeInvLater(player1).runTaskLater(ArcadeGames.getPlugin(), 100);
        new closeInvLater(player2).runTaskLater(ArcadeGames.getPlugin(), 100);
        setActive(false);
        Player winner;
        Player looser;
        if (p1wins > p2wins) {
            winner = player1;
            looser = player2;
        } else if (p2wins > p1wins) {
            winner = player2;
            looser = player1;
        }else{
            player1.sendMessage(strings.unentschiedenMessage);
            player2.sendMessage(strings.unentschiedenMessage);
            player1.playSound(player1, Sound.BLOCK_BEEHIVE_SHEAR, 1.0F, 1.0F);
            player2.playSound(player2, Sound.BLOCK_BEEHIVE_SHEAR, 1.0F, 1.0F);
            sendStats(player1, TextColor.color(255, 128, 0));
            sendStats(player2, TextColor.color(255, 128, 0));
            return;
        }
        winner.sendMessage(strings.winMessage);
        winner.playSound(winner, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0F, 1.0F);
        looser.sendMessage(strings.looseMessage);
        looser.playSound(looser, Sound.BLOCK_BEACON_DEACTIVATE, 1.0F, 1.0F);
        sendStats(winner, TextColor.color(0, 255, 0));
        sendStats(looser, TextColor.color(255, 0, 0));

    }
    public void win(Player winner, Player looser){
        winner.playSound(winner, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        looser.playSound(looser, Sound.ENTITY_EGG_THROW, 1.0F, 1.0F);
        if(winner.equals(player1)){
            p1winlist.add(winType.player1);
            p1wins++;
        }else{
            p1winlist.add(winType.player2);
            p2wins++;
        }
    }
    public void sendStats(Player p, TextColor color){
        Component c = Component.text("|- ", color);
        p.sendMessage(Component.text("|---------------", color));
        int i = 1;
        for(winType b : p1winlist){
            if(b.equals(winType.player1)){

                if (p.equals(player1)) p.sendMessage(c.append(Component.text("Runde " + i + ": Gewonnen!", TextColor.color(0, 255, 0))));
                else p.sendMessage(c.append(Component.text("Runde " + i + ": Verloren...", TextColor.color(255, 0, 0))));

            } else if (b.equals(winType.player2)) {

                if(p.equals(player1)) p.sendMessage(c.append(Component.text("Runde " + i + ": Verloren...", TextColor.color(255, 0, 0))));
                else p.sendMessage(c.append(Component.text("Runde " + i + ": Gewonnen!", TextColor.color(0, 255, 0))));

            }else if(b.equals(winType.unentschieden)) {
                p.sendMessage(c.append(Component.text("Runde " + i + ": Unentschieden", TextColor.color(255, 128, 0))));
            }
            i++;
        }
        p.sendMessage(Component.text("|---------------", color));
        p.sendMessage(Component.text("|- " + player1.getName() + ": ", color).append(Component.text(p1wins)));
        p.sendMessage(Component.text("|- " + player2.getName() + ": ", color).append(Component.text(p2wins)));
        p.sendMessage(c.append(Component.text(gameType.getDisplayName(), color)));
        p.sendMessage(Component.text("|---------------", color));
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public void setIsp1turn(boolean isp1turn) {
        this.isp1turn = isp1turn;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setP1wins(int p1wins) {
        this.p1wins = p1wins;
    }

    public void setP2wins(int p2wins) {
        this.p2wins = p2wins;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Inventory getInv() {
        return inv;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isIsp1turn() {
        return isp1turn;
    }

    public int getRounds() {
        return rounds;
    }

    public int getP1wins() {
        return p1wins;
    }

    public int getP2wins() {
        return p2wins;
    }
}
