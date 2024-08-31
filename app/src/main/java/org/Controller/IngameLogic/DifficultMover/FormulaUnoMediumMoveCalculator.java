package org.Controller.IngameLogic.DifficultMover;

import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Vector2D;


/**
 * @author Lorenzo Toscano - 110204 
 * La classe va ad estende FormulaUnoEasyMoveCalculator e implementa per
 * il metodo per trovare la prossima mossa del giocatore con una difficoltà media.
 *
 * questa classe rappresenta un calcolatore di mosse per il livello medio del gioco
 * 
 */

public class FormulaUnoMediumMoveCalculator extends FormulaUnoEasyMoveCalculator {
    
/**
     * Costruttore che inizializza il calcolatore con la mappa del gioco
     *
     * @param gameMap la mappa del gioco
     */

    public FormulaUnoMediumMoveCalculator(FormulaUnoGameMap gameMap) {
        super(gameMap);
    }
     /**
     * Trova la prossima mossa per il giocatore, filtrando le mosse disponibili in base a criteri di sicurezza e velocità.
     *
     * @param player il giocatore
     * @param moves la lista delle mosse possibili
     * @return la prossima mossa da eseguire
     */
    @Override
    public FormulaUnoMove findNextMove(FormulaUnoPlayer player, List<FormulaUnoMove> moves) {
        // Filtra le mosse per limitare la velocità e garantire la sicurezza
        List<FormulaUnoMove> limitedMoves = moves.stream().filter(m ->{ // Ottiene la nuova velocità della mossa
            Vector2D speed = m.getNewSpeed(); // Calcola i valori assoluti delle componenti x e y della velocità
            int x = Math.abs(speed.getX()); // Ottiene il valore assoluto della componente X della velocità
            int y = Math.abs(speed.getY()); // Ottiene il valore assoluto della componente Y della velocità
            Coordinate2D futureCoor = speed.sum(speed.sum(speed.sum(m.getEndPosition()))); // Calcola la posizione futura del giocatore
            boolean safe = gameMap.getSectionFromCoordinate(player.getSection(), futureCoor) != -1; // Verifica se la posizione futura è sicura
            return x < 3 && y < 3 && safe; // Ritorna true se la velocità è inferiore a 3 e la posizione futura è sicura
        }).toList();
        if(!limitedMoves.isEmpty()) // Se ci sono mosse limitate disponibili, restituisce la prima
            return limitedMoves.get(0);
        return super.findNextMove(player, moves); // Altrimenti, utilizza il metodo della classe base
    }
    
}
