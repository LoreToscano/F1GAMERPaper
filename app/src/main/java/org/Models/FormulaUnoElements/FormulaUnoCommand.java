package org.Models.FormulaUnoElements;

import org.Models.GameElements.Coordinate2D;
import org.apache.commons.lang3.NotImplementedException;


/**
 * 
 * @author Lorenzo Toscano - 110204
 * 
 * La classe rappresenta un comando di gioco
 * Questa classe contiene informazioni sul target del comando e un metodo per eseguire il movimento.
 */

public class FormulaUnoCommand {

    private Coordinate2D target;

     /**
     * Costruttore della classe
     * Inizializza il comando con il target specificato.
     *
     * @param target la coordinata target del comando
     */

    public FormulaUnoCommand(Coordinate2D target) {
        this.target = target;
    }
    
    /**
     * Esegue il movimento del comando.
     * Questo metodo non è implementato per i giocatori reali.
     *
     * @return la coordinata del movimento
     * @throws NotImplementedException se il metodo viene chiamato
     */

    public Coordinate2D move(){
        throw new NotImplementedException("I giocatori reali non sono stati implementati");
    }

}
