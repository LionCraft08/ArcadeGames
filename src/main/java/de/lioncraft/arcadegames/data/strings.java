package de.lioncraft.arcadegames.data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class strings {
    public static Component MainGUITitle, SettingsGUITitle, GUITitlePrefix, PlayerEnterAreaMessage, chatPrefix, PlayerLeaveAreaMessage,
            tiktaktoeGUITitle, sspGUITitle, playerSelectionGUITitle, amountSelectionGUITitle, requestExpiredMessage,
            winMessage, looseMessage, vierGewinntGUITitle, unentschiedenMessage;
    public void setContents(){
        MainGUITitle = Component.text("Arcade Games");
        MainGUITitle = MainGUITitle.color(TextColor.color(51, 255, 255));
        GUITitlePrefix = Component.text("AG ");
        GUITitlePrefix = GUITitlePrefix.color(TextColor.color(150, 0, 204));
        SettingsGUITitle = GUITitlePrefix.append(Component.text("Einstellungen").color(TextColor.color(51, 255, 255)));
        chatPrefix = Component.text("[");
        chatPrefix = chatPrefix.append(Component.text("AG").color(TextColor.color(0, 255, 255)));
        chatPrefix = chatPrefix.append(Component.text("] "));
        PlayerEnterAreaMessage = chatPrefix.append(Component.text("Du hast den Arcade-Bereich betreten. Hier kannst du Minispiele mit deinen Freunden oder gegen andere spielen. "));
        PlayerEnterAreaMessage = PlayerEnterAreaMessage.append(Component.text("Klicke dafür einfach auf einen der Spiele-Automaten!"));
        PlayerLeaveAreaMessage = chatPrefix.append(Component.text("Du hast den Arcade-Bereich verlassen."));
        tiktaktoeGUITitle = GUITitlePrefix.append(Component.text("Tik Tak Toe ", TextColor.color(204, 0, 204)));
        playerSelectionGUITitle = GUITitlePrefix.append(Component.text("Wähle einen Gegner ", TextColor.color(204, 0, 204)));
        requestExpiredMessage = chatPrefix.append(Component.text("Diese Anfrage ist leider abgelaufen. Benutze einen Spiele-Automate um erneut eine Anfrage zu senden. "));
        amountSelectionGUITitle = GUITitlePrefix.append(Component.text(" Anzahl an Spielen"));
        sspGUITitle = GUITitlePrefix.append(Component.text("Schere Stein Papier", TextColor.color(204, 0, 204)));
        winMessage = chatPrefix.append(Component.text("Du hast das Spiel gewonnen!"));
        looseMessage = chatPrefix.append(Component.text("Du hast das Spiel verloren... Schade!"));
        unentschiedenMessage = chatPrefix.append(Component.text("Unentschieden..."));
        vierGewinntGUITitle = GUITitlePrefix.append(Component.text("Vier Gewinnt", TextColor.color(204, 0, 204)));
    }
}
