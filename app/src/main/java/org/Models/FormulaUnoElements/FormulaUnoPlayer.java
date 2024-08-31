package org.Models.FormulaUnoElements;
import org.Models.GameElements.*;

/**
 * @author Lorenzo Toscano - 110204
 * 
 * Classe che rappresenta un giocatore nel gioco ed estende
 */

public class FormulaUnoPlayer extends MobileCoordinate2D {

    private final String playerName;
    private final int playerId;
    private int section, laps, winnerPosition;
    private long winnerTime;
    private boolean notIntoRoad, endRoad, firstMove;
    private PlayerType type;
    private Vector2D speed;


    /**
     * Costruttore della classe
     * @param x la coordinata x iniziale
     * @param y la coordinata y iniziale
     * @param id l'ID del giocatore
     * @param name il nome del giocatore
     * @param type il tipo di giocatore
     */
    public FormulaUnoPlayer(int x, int y, int id, String name, PlayerType type) {
        super(x, y);
        this.playerId = id;
        this.playerName = name;
        this.section = 0;
        this.laps = 0;
        this.endRoad = false;
        this.notIntoRoad = false;
        this.firstMove = true;
        this.winnerPosition = -1;
        this.winnerTime = -1;
        this.type = type;
        this.speed = new Vector2D();
    }
    
    public String getPlayerName() {
        return playerName;
    }

    
    public int getPlayerId() {
        return playerId;
    }

    
    public Coordinate2D getPlayerCoordinate() {
        return getCoordinate();
    }

    public int getSection(){
        return section;
    }

    public int getLaps(){
        return laps;
    }
    
    public boolean isNotIntoRoad() {
        return notIntoRoad;
    }

    public boolean doFirstMove(){
        if(firstMove){
            firstMove = false;
            return true;
        }
        return false;
    }

    
    public boolean isEndRoad() {
        return endRoad;
    }

    public PlayerType getPlayerType(){
        return this.type;
    }

    public Vector2D getSpeed(){
        return this.speed;
    }
    
    public int getWinnerPosition() {
        return winnerPosition;
    }

    public void setSection(int section){
        this.section = section;
    }

    public void setLaps(int laps){
        this.laps = laps;
    }

    public void setNotIntoRoad(boolean notIntoRoad) {
        this.notIntoRoad = notIntoRoad;
    }

    public void setEndRoad(boolean endRoad) {
        this.endRoad = endRoad;
    }

    public void setWinnerPosition(int winnerPosition) {
        if(endRoad)
            this.winnerPosition = winnerPosition;
    }

    public void setSpeed(Vector2D speed){
        this.speed = speed;
    }

    public void applyNewSpeed(Vector2D newSpeed){
        this.speed = this.speed.sum(newSpeed);
        applyVector(this.speed);
    }

    public long getWinnerTime() {
        return winnerTime;
    }

    public void setWinnerTime(long winnerTime) {
        this.winnerTime = winnerTime;
    }
    
}
