package org.Controller.IngameLogic.DifficultMover;

import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Vector2D;

/**
 * @author Lorenzo Toscano - 110204
 * Questa classe stende FormulaUnoMediumMoveCalculator e implementa
 * il metodo per trovare la prossima mosse del giocatore con una difficoltà elevata.
 */
public class FormulaUnoHardMoveCalculator extends FormulaUnoMediumMoveCalculator {
    /**
     * Costruttore.
     * Inizializza la classe con una mappa di gioco.
     *
     * @param gameMap la mappa del gioco 
     */

    public FormulaUnoHardMoveCalculator(FormulaUnoGameMap gameMap) {
        super(gameMap);
    }

    /**
     * Override del metodo findNextMove per trovare la prossima mossa del giocatore e
     * e ffiltra le mosse per trovare quellle che rispettano certe condizioni.
     *
     * @param player il giocatore per cui calcolare la mossa
     * @param moves una lista delle mosse possibili 
     * @return la prossima mossa del giocatore  
     */
    @Override
    public FormulaUnoMove findNextMove(FormulaUnoPlayer player, List<FormulaUnoMove> moves) {
        List<FormulaUnoMove> limitedMoves = moves.stream().filter(m ->{ // Filtra le mosse per trovare quelle che rispettano certe condizioni
            Vector2D speed = m.getNewSpeed(); // Ottiene la nuova velocità della mossa
            int x = Math.abs(speed.getX()); // Ottiene il valore assoluto della componente X della velocità
            int y = Math.abs(speed.getY()); // Ottiene il valore assoluto della componente Y della velocità
            Coordinate2D futureCoor = speed.sum(speed.sum(speed.sum(m.getEndPosition()))); // Calcola la posizione futura del giocatore
            boolean safe = gameMap.getSectionFromCoordinate(player.getSection(), futureCoor) != -1; // Verifica se la posizione futura è sicura
            return x < 4 && y < 4 && safe;  // Ritorna true se la velocità è inferiore a 4 in entrambe le direzioni e la posizione è sicura
        }).toList();
        if(!limitedMoves.isEmpty()) // Se ci sono mosse limitate, ritorna la prima
            return limitedMoves.get(0);  // Altrimenti, utilizza il metodo della classe padre
        return super.findNextMove(player, moves);
    }
    
}
