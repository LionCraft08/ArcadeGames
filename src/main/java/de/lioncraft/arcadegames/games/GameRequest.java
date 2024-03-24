package de.lioncraft.arcadegames.games;

import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.games.enums.GameType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class GameRequest {
    Player p1;
    Player p2;
    GameType gameType;
    long time;

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }

    public GameType getGameType() {
        return gameType;
    }

    public long getTime() {
        return time;
    }

    public int getAmount() {
        return amount;
    }

    int amount;
    public GameRequest(Player p1, Player p2, GameType gameType, long time, int amount){
        /* p1 ist der, der Anfragt,
            p2 bekommt die Anfrage

         */
        this.amount = amount;
        this.gameType = gameType;
        this.p1 = p1;
        this.time = time;
        this.p2 = p2;
    }
    public Component getRequestMessage(){
        Component c = strings.chatPrefix.append(Component.text(p1.getName() + " hat dich zu einem ", TextColor.color(255,128,0)));
        if(gameType.equals(GameType.SchiffeVersenken)){
            c = c.append(Component.text("Schiffe-Versenken-Match herausgefordert. Klicke zum Annehmen.", TextColor.color(255,128,0)));
        } else if (gameType.equals(GameType.SSP)) {
            c = c.append(Component.text("Schere-Stein-Papier-Match herausgefordert. Klicke zum Annehmen.", TextColor.color(255,128,0)));
        } else if (gameType.equals(GameType.UNO)) {
            c = c.append(Component.text("UNO-Spiel herausgefordert. Klicke zum Annehmen.", TextColor.color(255,128,0)));
        } else if (gameType.equals(GameType.TikTakToe)) {
            c = c.append(Component.text("Tik Tak Toe-Match herausgefordert. Klicke zum Annehmen.", TextColor.color(255,128,0)));
        } else if (gameType.equals(GameType.VierGewinnt)) {
            c = c.append(Component.text("VIER-GEWINNT-Match herausgefordert. Klicke zum Annehmen.", TextColor.color(255,128,0)));
        }
        c = c.append(Component.text(" (" + amount + " Runden)", TextColor.color(255, 255, 0)));
        c = c.clickEvent(ClickEvent.runCommand("/ag accept " + p1.getName() + " " + gameType));
        c = c.hoverEvent(HoverEvent.showText(Component.text("Spiele gegen " + p1.getName())));
        return c;
    }
    public Component getRequestFeedbackMessage(){
        /* Gibt die Message aus die der, der anfragt (p1) bekommt
        *
        */
        Component c = strings.chatPrefix.append(Component.text("Du hast " + p2.getName() + " zu einem Match herausgefordert. Warte auf eine Antwort...", TextColor.color(255,128,0)));

        return c;
    }
    public void startGame(){
        if(gameType.equals(GameType.TikTakToe)){
            tiktaktoe tiktaktoe = new tiktaktoe(p1, p2, amount);
        } else if (gameType.equals(GameType.SSP)) {
            ssp ssp = new ssp(p1, p2, amount);
        } else if (gameType.equals(GameType.VierGewinnt)) {
            vierGewinnt vierGewinnt = new vierGewinnt(p1, p2, amount);
        }
    }
}
