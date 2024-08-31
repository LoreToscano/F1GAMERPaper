package org.Controller.IngameLogic.DifficultMover;

import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.GameElements.Vector2D;


/**
 * @author Lorenzo Toscano - 110204
 * 
 * La classe estende FormulaUnoDifficultMoveCalculator e implementa il metodo per
 * per trovare la prossima mossa del giocatore con una difficoltà facile.
 */

public class FormulaUnoEasyMoveCalculator extends FormulaUnoDifficultMoveCalculator{

    /**
     * Costruttore della classe FormulaUnoEasyMoveCalculator.
     * Inizializza la mappa del gioco.
     *
     * @param gameMap la mappa del gioco
     */
     
    public FormulaUnoEasyMoveCalculator(FormulaUnoGameMap gameMap) {
        super(gameMap);
    }

    /**
     * Override del metodo findNextMove per trovare la prossima mossa del giocatore.
     * Filtra le mosse per trovare quelle che rispettano certe condizioni.
     *
     * @param player il giocatore per cui calcolare la mossa
     * @param moves una lista di mosse possibili
     * @return la prossima mossa del giocatore
     */
    @Override
    public FormulaUnoMove findNextMove(FormulaUnoPlayer player, List<FormulaUnoMove> moves) {
        List<FormulaUnoMove> limitedMoves = moves.stream().filter(m ->{
            Vector2D speed = m.getNewSpeed(); // Ottiene la nuova velocità della mossa
            int x = Math.abs(speed.getX()); // Ottiene il valore assoluto della componente X della velocità
            int y = Math.abs(speed.getY()); // Ottiene il valore assoluto della componente Y della velocità
            return x < 2 && y < 2; // Ritorna true se la velocità è inferiore a 2 in entrambe le direzioni
        }).toList();
        // Se ci sono mosse limitate, ritorna la prima
        if(!limitedMoves.isEmpty())
            return limitedMoves.get(0); // Trova la mossa con la somma minima delle componenti della velocità
        int min = moves.stream().map(m ->{
            Vector2D speed = m.getNewSpeed();
            int x = Math.abs(speed.getX());
            int y = Math.abs(speed.getY());
            return x + y;
        }).reduce(10, (a,b) -> a < b ? a : b); // Ritorna la prima mossa che ha la somma delle componenti della velocità uguale al minimo trovato
        return moves.stream().filter(m ->{
            Vector2D speed = m.getNewSpeed();
            int x = Math.abs(speed.getX());
            int y = Math.abs(speed.getY());
            return x + y == min;
        }).findFirst().get();
    }
    
}
