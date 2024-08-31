package org.Controller.Parsing;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.Exception.ParsingException;
import org.Models.GameElements.Coordinate2D;

/**
 * @author Lorenzo Toscano - 110204
 * La classe MapSemanticParser è responsabile della validazione semantica delle mappe di gioco
 * fornendo metodi per verificare la correttezza delle coordinate delle mappe.
 */

public class MapSemanticParser {

    /**
     * Verifica la correttezza di una mappa di gioco rappresentata da una lista di coordinate.
     *
     * @param map una lista di array di Coordinate2D che rappresentano i checkpoint della mappa
     * @throws NullPointerException se la lista di coordinate è nulla
     * @throws IllegalArgumentException se un array di coordinate è nullo o non contiene esattamente 2 coordinate
     */

    public void parseMap(List<Coordinate2D[]> map){
        if(map == null)
            throw new NullPointerException("La lista di coordinate è nulla");
        for(int i = 0; i < map.size(); i++){
            if(map.get(i) == null || map.get(i).length != 2)
                throw new IllegalArgumentException("Array nullo o coordinate di numero diverso da 2 a riga " + i);
        }
    }

    /**
     * Verifica la correttezza di una mappa di gioco rappresentata da un file di testo.
     *
     * @param path il percorso del file di testo che contiene le coordinate della mappa
     * @throws Exception se si verifica un errore durante la lettura del file
     * @throws ParsingException se una riga del file non contiene esattamente 2 coordinate
     */
    public void parseMap(String path) throws Exception{
        List<String> lines = Files.readAllLines(Paths.get(path));
        for(int i = 0; i < lines.size() ;i++){
            String line = lines.get(i);
            String[] coors = line.split(" ");
            if(coors.length != 2)
                throw new ParsingException(i);
            parseCoordinates(coors, i);
        }
    }

    /**
     * Verifica la correttezza delle coordinate in una riga del file.
     *
     * @param coors un array di stringhe che rappresentano le coordinate
     * @param line il numero della riga nel file
     * @throws ParsingException se una coordinata non è valida
     */
    private void parseCoordinates(String[] coors, int line) throws ParsingException{
        for(String coor : coors){
            String[] nums = coor.split("\\.");
            if(nums.length != 2)
                throw new ParsingException(line);
            parseNums(nums, line);
        }
    }

    /**
     * Verifica la correttezza dei numeri in una coordinata.
     *
     * @param nums un array di stringhe che rappresentano i numeri della coordinata
     * @param line il numero della riga nel file
     * @throws ParsingException se un numero non è valido
     */
    private void parseNums(String[] nums, int line) throws ParsingException{
        for(String num : nums){
            if(num.charAt(0) == '-')
                num = num.replace("-", "");
            for(char c : num.toCharArray()){
                if(c < '0' || c > '9')
                    throw new ParsingException(line);
            }
        }
    }
    
}
