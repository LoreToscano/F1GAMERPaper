package org.Models.GameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Lorenzo Toscano - 110204
 * 
 * Classe per gestire un turno di gioco
 */
public class Round {
    
    
    private int roundIndex, maxRound;
    private long timeCounter;
    private List<Integer> rounds;
    private boolean randomOrder;

    /**
     * Costruttore della classe
     * @param playerNumber il numero di giocatori
     * @param random se l'ordine dei turni deve essere casuale
     */

    public Round(int playerNumber, boolean random){
        roundIndex = 0;
        timeCounter = 0;
        maxRound = playerNumber;
        rounds = new ArrayList<>(IntStream.range(0, playerNumber).boxed().toList());
        this.randomOrder = random;
        if(random)
            shuffleRounds();
    }

     /**
     * Mescola l'ordine dei turni in modo casuale
     */
    private void shuffleRounds(){
        for(int i = 0; i < maxRound; i++){
            int from = (int)(Math.random() * maxRound);
            int to = (int)(Math.random() * maxRound);
            int temp = this.rounds.get(to);
            this.rounds.set(to, this.rounds.get(from));
            this.rounds.set(from, temp);
        }
    }

    /**
     * Ottiene il turno attuale
     * @return il turno oppure -1 se non ci sono turni
     */
    public int getRound() {
        if(rounds.isEmpty())
            return -1;
        return rounds.get(roundIndex);
    }

    /**
     * Passa al turno successivo
     * @return il turno successivo, o -1 se non ci sono turn
     */
    public int nextRound() {
        if(rounds.isEmpty())
            return -1;
        if(++roundIndex == maxRound){
            if(this.randomOrder)
                shuffleRounds();
            roundIndex = 0;
            timeCounter++;
        }
        return getRound();
    }

    /**
     * Elimina un turno specifico
     * @param id l'ID del turno da eliminare
     */
    public void deleteRound(int id){
        rounds.removeIf(x -> x == id);
        maxRound = rounds.size();
        if(roundIndex >= maxRound){
            if(this.randomOrder)
                shuffleRounds();
            roundIndex = 0;
        }
    }
/** tempo che scorre
 * @return il contatore del tempo
 */
    public long getTime(){
        return this.timeCounter;
    }

}
