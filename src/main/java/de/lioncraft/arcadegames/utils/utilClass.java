package de.lioncraft.arcadegames.utils;

import de.lioncraft.arcadegames.games.enums.nearbySlotType;

import javax.annotation.Nullable;
import java.util.*;

public class utilClass {
    public static HashMap<Integer, nearbySlotType> getNearSlots(int middleSlot, int denySlot, @Nullable nearbySlotType ns){
        /*middleSlot: Slot dessen umliegende Slots inklusive der Richtung ausgegeben werden sollen.
        * denySlot: ein Slot, der nicht in der Liste enthalten sein soll.
        * ns: Gibt nur Slots des entsprechenden Typs aus, wenn ns nicht null ist
        * */
        HashMap<Integer, nearbySlotType> list = new HashMap<>();
        switch (middleSlot){
            case 0:
                list.put(1, nearbySlotType.horizontal);
                list.put(10, nearbySlotType.diagonalLinksOben);
                list.put(9, nearbySlotType.vertikal);
                break;
            case 1, 2, 3, 4, 5, 6, 7:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot + 8,nearbySlotType.diagonalRechtsOben);
                list.put(middleSlot + 9,nearbySlotType.vertikal);
                list.put(middleSlot + 10, nearbySlotType.diagonalLinksOben);
                break;
            case 9, 18, 27, 36:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot + 9, nearbySlotType.vertikal);
                list.put(middleSlot + 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot - 8, nearbySlotType.diagonalRechtsOben);
                break;
            case 17, 26, 35, 44:
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot + 9, nearbySlotType.vertikal);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot + 8, nearbySlotType.diagonalRechtsOben);
                list.put(middleSlot - 10, nearbySlotType.diagonalLinksOben);
                break;
            case 46, 47, 48, 49, 50, 51, 52:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot - 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot - 8, nearbySlotType.diagonalRechtsOben);
                break;
            case 8:
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(16, nearbySlotType.diagonalRechtsOben);
                list.put(17, nearbySlotType.vertikal);
                break;
            case 45:
                list.put(36, nearbySlotType.vertikal);
                list.put(37, nearbySlotType.diagonalRechtsOben);
                list.put(46, nearbySlotType.horizontal);
                break;
            case 53:
                list.put(43, nearbySlotType.diagonalLinksOben);
                list.put(44, nearbySlotType.vertikal);
                list.put(52, nearbySlotType.horizontal);
                break;
            default:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot + 9, nearbySlotType.vertikal);
                list.put(middleSlot + 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot + 8, nearbySlotType.diagonalRechtsOben);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot - 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot - 8, nearbySlotType.diagonalRechtsOben);
                break;
        }
        list.remove(denySlot);
        if(ns != null){
            HashMap<Integer, nearbySlotType> list2 = new HashMap<>();
            for(int i : list.keySet()){
                if(list.get(i).equals(ns)){
                    list2.put(i, list.get(i));
                }
            }
            return list2;
        }

        return list;
    }
    public static Integer getNextInRow(int middleSlot, int denySlot, nearbySlotType ns){
        HashMap<Integer, nearbySlotType> list = new HashMap<>();
        switch (middleSlot){
            case 0:
                list.put(1, nearbySlotType.horizontal);
                list.put(10, nearbySlotType.diagonalLinksOben);
                list.put(9, nearbySlotType.vertikal);
                break;
            case 1, 2, 3, 4, 5, 6, 7:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot + 8,nearbySlotType.diagonalRechtsOben);
                list.put(middleSlot + 9,nearbySlotType.vertikal);
                list.put(middleSlot + 10, nearbySlotType.diagonalLinksOben);
                break;
            case 9, 18, 27, 36:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot + 9, nearbySlotType.vertikal);
                list.put(middleSlot + 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot - 8, nearbySlotType.diagonalRechtsOben);
                break;
            case 17, 26, 35, 44:
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot + 9, nearbySlotType.vertikal);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot + 8, nearbySlotType.diagonalRechtsOben);
                list.put(middleSlot - 10, nearbySlotType.diagonalLinksOben);
                break;
            case 46, 47, 48, 49, 50, 51, 52:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot - 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot - 8, nearbySlotType.diagonalRechtsOben);
                break;
            case 8:
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(16, nearbySlotType.diagonalRechtsOben);
                list.put(17, nearbySlotType.vertikal);
                break;
            case 45:
                list.put(36, nearbySlotType.vertikal);
                list.put(37, nearbySlotType.diagonalRechtsOben);
                list.put(46, nearbySlotType.horizontal);
                break;
            case 53:
                list.put(43, nearbySlotType.diagonalLinksOben);
                list.put(44, nearbySlotType.vertikal);
                list.put(52, nearbySlotType.horizontal);
                break;
            default:
                list.put(middleSlot + 1, nearbySlotType.horizontal);
                list.put(middleSlot - 1, nearbySlotType.horizontal);
                list.put(middleSlot + 9, nearbySlotType.vertikal);
                list.put(middleSlot + 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot + 8, nearbySlotType.diagonalRechtsOben);
                list.put(middleSlot - 9, nearbySlotType.vertikal);
                list.put(middleSlot - 10, nearbySlotType.diagonalLinksOben);
                list.put(middleSlot - 8, nearbySlotType.diagonalRechtsOben);
                break;
        }
        list.remove(denySlot);
        if(ns != null){
            for(int i : list.keySet()){
                if(list.get(i).equals(ns)){
                    return i;
                }
            }
        }

        return null;
    }
}
