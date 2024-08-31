package org.Controller.IngameLogic;

import java.util.List;
import java.util.stream.IntStream;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoGameSettings;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Round;
import org.Models.GameElements.Vector2D;

/**
*@author Lorenzo Toscano - 110204
*Questa classe rappresenta l'arbitro del gioco
* gestendole le regole del gioco, le mosse dei giocatori e determina il vincitore.
*/
public class FormulaUnoArbitrator {

    private FormulaUnoGameMap gameMap;
    private Coordinate2D[] players;
    private Round round;
    private boolean veiclesCanOverlap, dontEliminateIfExit;
    private Vector2D[] OneMoveVectors;
    private final int lapsToDo;
    private int winnerPositionCount;

    /**
     * Costruttore che inizializza l'arbitro con la mappa del gioco
     *
     * @param gameMap la mappa del gioco
     * @param players le coordinate dei giocatori
     * @param settings le impostazioni del gioco
     * @param round il round corrente
     */

    public FormulaUnoArbitrator(FormulaUnoGameMap gameMap, Coordinate2D[] players, FormulaUnoGameSettings settings, Round round) {
        this.gameMap = gameMap; //La mappa del gioco
        this.players = players; //Le coordinate dei giocatori
        this.round = round; //Le impostazioni del gioco
        this.veiclesCanOverlap = settings.isVeiclesCanOverlap();
        this.dontEliminateIfExit = settings.isDontEliminateIfExit();
        this.OneMoveVectors = new Vector2D[9];
        int count = 0;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                this.OneMoveVectors[count++] = new Vector2D(i, j);
            }
        }
        this.lapsToDo = settings.getLaps();
        this.winnerPositionCount = 1;
    }
/**
     * Ottiene la lista delle mosse accettabili per un giocatore.
     *
     * @param player il giocatore
     * @return la lista delle mosse accettabili
     */    public List<FormulaUnoMove> getAcceptableMoves(FormulaUnoPlayer player){
        Vector2D speed = player.getSpeed(); // Ottiene la velocità attuale del giocatore
        boolean isFirst = player.doFirstMove(); // Verifica se è la prima mossa del giocatore
        return IntStream.range(0, 9).boxed() // Genera una lista di mosse accettabili
                        .map(i -> { // Calcola la nuova velocità sommando il vettore di movimento
                            Vector2D s = speed.sum(this.OneMoveVectors[i]);
                            FormulaUnoMove move = new FormulaUnoMove(); // Crea una nuova mossa
                            move.setStartPosition(player.getCoordinate()); // Imposta la posizione iniziale della mossa
                            move.setEndPosition(s.sum(player.getCoordinate())); // Imposta la posizione finale della mossa
                            move.setLastSpeed(speed); // Ripristina la velocità al valore precedente
                            move.setChosedMove(this.OneMoveVectors[i]); // Imposta il vettore di movimento scelto
                            move.setNewSpeed(s); // Imposta la nuova velocità
                            move.setLastSection(player.getSection()); // Imposta la sezione precedente
                            move.setNewSection(this.gameMap.getSectionFromCoordinate(player.getSection(), move.getEndPosition())); // Calcola e imposta la nuova sezione
                            move.setOutOfRoad(this.gameMap.checkIfOutOfRoad(player.getSection(), move.getStartPosition(), move.getNewSpeed())); // Verifica se la mossa porta fuori strada
                            move.setWinTheGame(move.getNewSection() == gameMap.getSectionsCount() -1); // Verifica se la mossa è vincente
                            return move;
                            // Filtra le mosse in base alla possibilità di sovrapposizione dei veicoli e alla posizione libera e filtra le mosse per evitare che la prima mossa sia nulla
                        }).filter(m -> veiclesCanOverlap || freePosition(m.getEndPosition()))
                        .filter(m -> !(isFirst && m.getChosedMove().equals(new Vector2D())))
                        .toList();
        }
        
     /**
     * Verifica se una posizione è libera.
     * Il parametro coordinate è la coordinata da verificare
     * @param coordinate la coordinata da verificare
     * @return true se la posizione è libera, false altrimenti
     */
    private boolean freePosition(Coordinate2D coordinate){
        // Controlla se la coordinata è occupata da un altro giocatore
        for(Coordinate2D c : players){
            if(c.getCoordinate().equals(coordinate))
                return false; //true se la posizione è libera, false altrimenti
        }
        return true;
    }


    /**
     * Applica una mossa al giocatore.
     *
     * @param player il giocatore
     * @param move la mossa da applicare al giocatore
     */

    public void applyMove(FormulaUnoPlayer player, FormulaUnoMove move) {
        if(move.isWinTheGame()){ /*  Verifica se la mossa è vincente e se
            il giocatore non ha completato tutti i giri, resetta la sezione e incrementa i giri */
            if(player.getLaps() + 1 < lapsToDo){
                move.setNewSection(0);
                player.setLaps(player.getLaps() + 1);
            } else { // altrimenti, segna il giocatore come vincitore e termina il round.
                player.setEndRoad(true);
                round.deleteRound(player.getPlayerId());
                player.setWinnerPosition(winnerPositionCount++);
                player.setWinnerTime(this.round.getTime());
            }
        }
        player.setNotIntoRoad(move.isOutOfRoad()); // Imposta lo stato del giocatore fuori strada
        if(move.isOutOfRoad()){ // Se il giocatore è fuori strada e l'eliminazione è abilitata, elimina il giocatore dal round
            if(!dontEliminateIfExit){
                round.deleteRound(player.getPlayerId());
                player.setWinnerTime(this.round.getTime());
            }
        }
        player.applyNewSpeed(move.getChosedMove()); // Applica la nuova velocità al giocatore
        player.setSection(move.getNewSection()); // Aggiorna la sezione del giocatore
        round.nextRound(); // Passa al prossimo round
    }
    
}
