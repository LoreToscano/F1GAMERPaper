package org.Models.GameElements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo Toscano - 110204
 * 
 * Classe che rappresenta le informazioni di gioco.
 */

public class GameInfo {

    /**
     * Costruttore della classe
     * @param i la lista di informazioni da aggiungere
     */

    private List<String> info;

    public GameInfo(List<String> i) {
        this.info = new ArrayList<String>();
        i.forEach(s -> this.info.add(s));
    }

    /**
     * Ottiene una copia della lista di informazioni
     * @return ritorna la copia della lista
     */
    public List<String> getInfo() {
        List<String> copy = new ArrayList<String>();
        this.info.forEach(s -> copy.add(s));
        return copy;
    }

}
