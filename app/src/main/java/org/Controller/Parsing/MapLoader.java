package org.Controller.Parsing;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.GameElements.Coordinate2D;

/**
 * @author Lorenzo Toscano - 110204
 * La classe MapLoader è responsabile del caricamento delle mappe di gioco.
 * Questa classe fornisce metodi per caricare le mappe da una lista di coordinate
 * o da un file di testo.
 */

public class MapLoader {
    /**
     * Carica una mappa di gioco da una lista di coordinate.
     *
     * @param map una lista di array di Coordinate2D che rappresentano i checkpoint della mappa
     * @return un oggetto FormulaUnoGameMap che rappresenta la mappa di gioco caricata
     */

    public FormulaUnoGameMap LoadGameMap(List<Coordinate2D[]> map){
        FormulaUnoGameMap gameMap = new FormulaUnoGameMap();
        map.forEach(x -> gameMap.addCheck(x[0], x[1]));
        gameMap.setNotEditable();
        return gameMap;
    }

    /**
     * Carica una mappa di gioco da un file di testo.
     *
     * @param path il percorso del file di testo che contiene le coordinate della mappa
     * @return un oggetto FormulaUnoGameMap che rappresenta la mappa di gioco caricata
     * @throws Exception se si verifica un errore durante la lettura del file
     */

    public FormulaUnoGameMap LoadGameMap(String path) throws Exception {
        FormulaUnoGameMap gameMap = new FormulaUnoGameMap();
        Files.readAllLines(Paths.get(path))
             .forEach(x -> {
                String[] coors = x.split(" ");
                Coordinate2D left = getCoordinateFromString(coors[0]);
                Coordinate2D right = getCoordinateFromString(coors[1]);
                gameMap.addCheck(left, right);
             });
        gameMap.setNotEditable();
        return gameMap;
    }

    /**
     * Converte una stringa di coordinate in un oggetto Coordinate2D.
     *
     * @param coor la stringa che rappresenta le coordinate, nel formato "x.y"
     * @return un oggetto Coordinate2D che rappresenta le coordinate
     */

    private Coordinate2D getCoordinateFromString(String coor){
        String[] nums = coor.split("\\.");
        return new Coordinate2D(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
    }
    
}
