package org.Models.DTO;

import java.util.Arrays;

import org.Models.FormulaUnoElements.FormulaUnoGameState;

/**
 * @author Lorenzo Toscano - 110204
 * 
 * La classe FormulaUnoGameInfoDTO rappresenta le informazioni
 * Questa classe contiene tuttee le informazioni sui giocatori e sul round corrente.
 */

public class FormulaUnoGameInfoDTO {
    private FormulaUnoPlayerInfoDTO[] players;
    private int round;

    
    public FormulaUnoGameInfoDTO(FormulaUnoPlayerInfoDTO[] players, int round) {
        this.players = players;
        this.round = round;
    }

    /**
     * Ottiene le informazioni sui giocatori.
     *
     * @return un array che rappresenta i giocatori
     */

    public FormulaUnoPlayerInfoDTO[] getPlayers() {
        FormulaUnoPlayerInfoDTO[] copy = new FormulaUnoPlayerInfoDTO[players.length];
        for(int i = 0; i < players.length; i++)
            copy[i] = players[i];
        return copy;
    }

    public int getRound() {
        return round;
    }

    /**
     *
     * @return il numero di giocatori che stanno ancora giocando
     */

    public int playingPlayers(){
        return (int) Arrays.asList(getPlayers()).stream().filter(p -> p.getState() != FormulaUnoGameState.FINISHED 
                                                                    && p.getState() != FormulaUnoGameState.ELIMINATED).count();
    }

    
}
