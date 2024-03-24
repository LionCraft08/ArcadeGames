package de.lioncraft.arcadegames.commands;

import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.games.GameRequest;
import de.lioncraft.arcadegames.games.enums.GameType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class defaultCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1){
            switch (strings[0]){
                case "setpos1":
                    if(commandSender.hasPermission("arcadegames.operator")){
                        Player p = (Player) commandSender;
                        if(p.getTargetBlockExact(10)!= null){
                            Location loc = p.getTargetBlockExact(10).getLocation();
                            defaultData.pos1 = loc;
                            commandSender.sendMessage("Set position 1 to "+ loc.getX() + ", "+ loc.getY() + ", "+ loc.getZ());
                            if(defaultData.pos2 != null){
                                defaultData.isAreaSet = true;
                                new defaultData().orderLocations();
                                commandSender.sendMessage("Arcade Area is set!");
                            }
                        }else{
                            commandSender.sendMessage("You need to target a block");
                        }

                    }
                    break;
                case "setpos2":
                    if(commandSender.hasPermission("arcadegames.operator")){
                        Player p = (Player) commandSender;
                        if(p.getTargetBlockExact(10)!= null){
                            Location loc = p.getTargetBlockExact(10).getLocation();
                            defaultData.pos2 = loc;
                            commandSender.sendMessage("Set position 2 to "+ loc.getX() + ", "+ loc.getY() + ", "+ loc.getZ());
                            if(defaultData.pos1 != null){
                                defaultData.isAreaSet = true;
                                new defaultData().orderLocations();
                                commandSender.sendMessage("Arcade Area is set!");
                            }
                        }else{
                            commandSender.sendMessage("You need to target a block");
                        }
                    }
                    break;
                case "addentity":
                    if (commandSender.hasPermission("arcadegames.operator")) {
                        Player p = (Player) commandSender;
                        if (p.getTargetEntity(10) != null) {
                            defaultData.interactableEntity.add(p.getTargetEntity(10).getUniqueId());
                            p.sendMessage(de.lioncraft.arcadegames.data.strings.chatPrefix.append(Component.text("You addet ").append(Component.translatable(p.getTargetEntity(10).getType().translationKey())).append(Component.text(" to the list!"))));

                        }else{
                            commandSender.sendMessage("You need to target an Entity");
                        }

                    }
                    break;
                default:
                    commandSender.sendMessage("Syntax-Fehler! Nutze einen Spiele-Automaten in der Arcade-Area!");
                    break;

            }
        } else if (strings.length == 3) {
            switch (strings[0]){
                case "accept":
                    Player player = Bukkit.getPlayer(strings[1]);
                    if(player != null) {
                        if (player.isOnline()) {
                            GameType gameType;
                            try {
                                gameType = GameType.valueOf(strings[2]);
                            } catch (IllegalArgumentException e) {
                                commandSender.sendMessage(de.lioncraft.arcadegames.data.strings.chatPrefix + "Dieses Spiel existiert nicht.");
                                commandSender.sendMessage(de.lioncraft.arcadegames.data.strings.chatPrefix + "Bitte verwende die Spiele-Automaten in der Arcade-Area!");
                                break;
                            }
                            GameRequest gr = defaultData.playerRequest.get(player);
                            if(gr != null){
                                gr.startGame();
                                defaultData.playerRequest.remove(player);
                            }else{
                                commandSender.sendMessage(de.lioncraft.arcadegames.data.strings.requestExpiredMessage);
                            }
                        }else{
                            commandSender.sendMessage(de.lioncraft.arcadegames.data.strings.chatPrefix + "Dieser Spieler hat die Lobby verlassen.");
                        }
                    }else{
                        commandSender.sendMessage(de.lioncraft.arcadegames.data.strings.chatPrefix + "Dieser Spieler hat die Lobby verlassen.");
                    }
                    break;
                default:
                    commandSender.sendMessage("Syntax-Fehler! Nutze einen Spiele-Automaten in der Arcade-Area!");
                    break;
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        switch (strings.length) {
            case 1:
            if (commandSender.hasPermission("arcadegames.operator")) {
                list.add("setpos1");
                list.add("setpos2");
                list.add("addentity");
            }
                list.add("accept");
                break;
            case 3:
                if(strings[0].equals("challenge")){
                    for(GameType gt : GameType.values()){
                        list.add(gt.toString());
                    }
                }
            break;
        }
        return list;
    }
}
