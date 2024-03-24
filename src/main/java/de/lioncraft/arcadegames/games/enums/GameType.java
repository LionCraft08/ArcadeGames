package de.lioncraft.arcadegames.games.enums;

public enum GameType {
    SSP,
    TikTakToe,
    SchiffeVersenken,
    VierGewinnt,
    UNO;
    public String getDisplayName(){
        if(equals(SSP)){
            return "Schere Stein Papier";
        } else if (equals(TikTakToe)) {
            return "Tik Tak Toe";
        } else if (equals(SchiffeVersenken)) {
            return "Fische Verschenken";
        } else if (equals(VierGewinnt)) {
            return "Vier Gewinnt";
        } else{
            return toString();
        }
    }
}
