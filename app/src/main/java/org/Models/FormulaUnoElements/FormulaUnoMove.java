package org.Models.FormulaUnoElements;

import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Vector2D;

/**
 * @author Lorenzo Toscano - 110204
 *Classe che rappresenta la mossa nel gioco
 */

public class FormulaUnoMove {
    
    
    private Coordinate2D startPosition, endPosition;
    private Vector2D lastSpeed, chosedMove, newSpeed;
    private boolean outOfRoad, winTheGame;
    private int lastSection, newSection;

    /**
     * Ottiene la posizione iniziale
     * @return torna la posizione iniziale
     */
    public Coordinate2D getStartPosition() {
        return startPosition;
    }

     /**
     * Imposta la posizione iniziale
     * @param startPosition la posizione iniziale da impostare
     */
    public void setStartPosition(Coordinate2D startPosition) {
        this.startPosition = startPosition;
    }
    public Coordinate2D getEndPosition() {
        return endPosition;
    }
    public void setEndPosition(Coordinate2D endPosition) {
        this.endPosition = endPosition;
    }
    public Vector2D getLastSpeed() {
        return lastSpeed;
    }
    public void setLastSpeed(Vector2D lastSpeed) {
        this.lastSpeed = lastSpeed;
    }
    public Vector2D getChosedMove() {
        return chosedMove;
    }
    public void setChosedMove(Vector2D chosedMove) {
        this.chosedMove = chosedMove;
    }
    public Vector2D getNewSpeed() {
        return newSpeed;
    }
    public void setNewSpeed(Vector2D newSpeed) {
        this.newSpeed = newSpeed;
    }
    public boolean isOutOfRoad() {
        return outOfRoad;
    }
    public void setOutOfRoad(boolean outOfRoad) {
        this.outOfRoad = outOfRoad;
    }
    public boolean isWinTheGame() {
        return winTheGame;
    }
    public void setWinTheGame(boolean winTheGame) {
        this.winTheGame = winTheGame;
    }
    public int getLastSection() {
        return lastSection;
    }
    public void setLastSection(int lastSection) {
        this.lastSection = lastSection;
    }
    public int getNewSection() {
        return newSection;
    }
    public void setNewSection(int newSection) {
        this.newSection = newSection;
    }

}
