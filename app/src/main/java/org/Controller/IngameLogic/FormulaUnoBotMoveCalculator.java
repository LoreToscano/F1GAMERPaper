package org.Controller.IngameLogic;

import java.util.Comparator;
import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.FormulaUnoElements.PlayerType;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Section2D;
import org.Controller.IngameLogic.DifficultMover.*;


/**
 * @author Lorenzo Toscano - 110204
 * La classe gestisce e rappresenta il calcolatore delle mosse del Bot
 * Questa classe si occupa di impostare la difficoltà del bot e trovare la prossima mossa da eseguire.
 */

public class FormulaUnoBotMoveCalculator {

    private FormulaUnoGameMap gameMap;
    private FormulaUnoDifficultMoveCalculator difficultCalculator;
    /**
     * Costruttore che inizializza il calcolatore di mosse del bot con la mappa del gioco.
     *
     * @param gameMap la mappa del gioco
     */

    /**Costruttore che inizializza il calcolatore di mosse del bot con la mappa del gioco*/
    public FormulaUnoBotMoveCalculator(FormulaUnoGameMap gameMap) {
        this.gameMap = gameMap;
        this.difficultCalculator = new FormulaUnoMediumMoveCalculator(gameMap);
    }

    /**
     * Imposta la difficoltà del bot in base al tipo di giocatore.
     *
     * @param playerType il tipo di giocatore
     */
    public void setDifficult(PlayerType playerType) {
        if(playerType == PlayerType.EASY)
            this.difficultCalculator = new FormulaUnoEasyMoveCalculator(gameMap);
        else if(playerType == PlayerType.MEDIUM)
            this.difficultCalculator = new FormulaUnoMediumMoveCalculator(gameMap);
        else if(playerType == PlayerType.HARD)
            this.difficultCalculator = new FormulaUnoHardMoveCalculator(gameMap);
        else if(playerType == PlayerType.CHAMPION)
            this.difficultCalculator = new FormulaUnoChampionMoveCalculator(gameMap);
        else throw new UnsupportedOperationException("Tipo di giocatore non supportato");
    }
    
    /**
     * Trova la prossima mossa per il bot in base alle mosse disponibili.
     *
     * @param player il giocatore
     * @param moves la lista delle mosse possibili
     * @return la prossima mossa da eseguire
     */

    public FormulaUnoMove findNextMove(FormulaUnoPlayer player, List<FormulaUnoMove> moves) {
        if(moves.size() == 0) // Se non ci sono mosse disponibili, ritorna null
            return null;
        if(moves.stream().anyMatch(m -> m.isWinTheGame())) // Se c'è una mossa che vince, ritorna quella mossa
            return moves.stream().filter(m -> m.isWinTheGame()).findFirst().get();
        if(moves.stream().anyMatch(m -> !m.isOutOfRoad())) // Filtra le mosse per escludere quelle che portano fuori strada
            moves = moves.stream().filter(m -> !m.isOutOfRoad()).toList();
        Section2D section = gameMap.getSection(player.getSection()); // Ottiene la sezione corrente del giocatore
        Coordinate2D nextTarget; // Calcola il target attuale e il prossimo target
        Coordinate2D target = section.getSegmentEnd().getCoordinateMiddle();
        if(player.getSection() + 1 < gameMap.getSectionsCount())
            nextTarget = this.gameMap.getSection(player.getSection() + 1).getSegmentEnd().getCoordinateMiddle();
        else nextTarget = target.getCoordinate();
        // Ordina le mosse in base alla distanza dal target
        Comparator<FormulaUnoMove> comparator = Comparator.comparingDouble(m -> {
            double dis = m.getStartPosition().getDistance(target);
            if(dis <= 3) dis = m.getEndPosition().getDistance(nextTarget);
            else dis = m.getEndPosition().getDistance(target);
            return dis;
        });
        moves = moves.stream().sorted(comparator).toList();
        // Utilizza il calcolatore di mosse della difficoltà corrente per trovare la prossima mossa
        return this.difficultCalculator.findNextMove(player, moves); 
    }
    
}
