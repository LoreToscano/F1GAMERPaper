package org.Controller.IngameLogic.DifficultMover;

import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;

/**
 * @author Lorenzo Toscano - 110204
 * Questa è la classe astratta responsabile del calcolo delle mosse difficili nel gioco
 * Le classi derivate devono implementare il metodo per trovare la prossima mossa del giocatore.
 */

public abstract class FormulaUnoDifficultMoveCalculator {
    

    protected FormulaUnoGameMap gameMap;
    /**
     * Mappa del gioco utilizzata per calcolare le mosse.
     * Costruttore della classe astratta
     * Inizializza la mappa del gioco.
     *
     * @param gameMap la mappa del gioco
     */

    public FormulaUnoDifficultMoveCalculator(FormulaUnoGameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * Metodo astratto per trovare la prossima mossa del giocatore.
     * Deve essere implementato dalle classi derivate.
     *
     * @param player il giocatore per cui calcolare la mossa
     * @param moves una lista di mosse possibili
     * @return la prossima mossa del giocatore
     */
    public abstract FormulaUnoMove findNextMove(FormulaUnoPlayer player, List<FormulaUnoMove> moves);
}
