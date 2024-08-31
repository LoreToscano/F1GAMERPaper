package org.Controller.IngameLogic.DifficultMover;

import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Vector2D;

/**
 * @author Lorenzo Toscano - 110204
 * 
 *  Questa classe ha la responsabilità di definire il comportamento di un 
 *  giocatore bot nel livello di difficoltà "champion"
 */
public class FormulaUnoChampionMoveCalculator extends FormulaUnoHardMoveCalculator {

    /**
     * Costruttore della classe FormulaUnoChampionMoveCalculator
     * Inizializza la classe con una mappa di gioco
     * @param gameMap mappa del gioco
     */
    public FormulaUnoChampionMoveCalculator(FormulaUnoGameMap gameMap) {
        super(gameMap);
    }

    
    @Override
    public FormulaUnoMove findNextMove(FormulaUnoPlayer player, List<FormulaUnoMove> moves) {
         // Filtra le mosse per trovare quelle che rispettano certe condizioni
        List<FormulaUnoMove> limitedMoves = moves.stream().filter(m ->{
            Vector2D speed = m.getNewSpeed(); // Ottiene la nuova velocità della mossa
            int x = Math.abs(speed.getX()); // Ottiene il valore assoluto della componente X della velocità
            int y = Math.abs(speed.getY()); // Ottiene il valore assoluto della componente Y della velocità e poi calcola la posizione futura del giocatore
            Coordinate2D futureCoor = speed.sum(speed.sum(speed.sum(m.getEndPosition())));
            // Verifica se la posizione futura è sicura
            boolean safe = gameMap.getSectionFromCoordinate(player.getSection(), futureCoor) != -1;
            // Ritorna true se la velocità è inferiore a 5 in entrambe le direzioni e la posizione è sicura
            return x < 5 && y < 5 && safe;
        }).toList();
        // Se ci sono mosse limitate, ritorna la prima
        if(!limitedMoves.isEmpty())
            return limitedMoves.get(0);
            // Altrimenti, utilizza il metodo della classe padre
        return super.findNextMove(player, moves);
    }
    
}
