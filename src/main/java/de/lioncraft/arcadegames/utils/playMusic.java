package de.lioncraft.arcadegames.utils;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class playMusic extends BukkitRunnable {
    int step;
    Player p;
    @Override
    public void run() {
        switch (step) {
            case 0:
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, new Note(8));
            p.playNote(p.getLocation(), Instrument.GUITAR, new Note(8));
            p.playNote(p.getLocation(), Instrument.BELL, new Note(8));
            break;
            case 1:
                p.playNote(p.getLocation(), Instrument.BASS_GUITAR, new Note(12));
                p.playNote(p.getLocation(), Instrument.BELL, new Note(15));
                break;
            case 2:
                p.playNote(p.getLocation(), Instrument.BASS_GUITAR, new Note(15));
                p.playNote(p.getLocation(), Instrument.BELL, new Note(12));
                break;
            case 3:
                p.playNote(p.getLocation(), Instrument.BASS_GUITAR, new Note(20));
                p.playNote(p.getLocation(), Instrument.GUITAR, new Note(20));
                p.playNote(p.getLocation(), Instrument.BELL, new Note(20));
                break;
        }
    }
    public playMusic(Player p, int step){
        this.step = step;
        this.p = p;
    }
}
