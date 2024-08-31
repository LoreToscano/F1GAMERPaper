package org.Models.DTO;

import org.Models.FormulaUnoElements.FormulaUnoGameState;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.FormulaUnoElements.PlayerType;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Vector2D;

/**@author Lorenzo Toscano - 110204
 * 
 * Classe ceh rappresenta le informazioni del giocatore
 * Questa classe contiene tutte le possibili informazioni del giocatore tipo il nome, l'ID ecc..
 * Contiene la coordinata, lo stato, i giri completati, la posizione del vincitore,
 * la percentuale di completamento, il tipo di giocatore, la velocità ecc..
 * 
 */

public class FormulaUnoPlayerInfoDTO {

    private String name;
    private int id;
    private Coordinate2D coordinate;
    private FormulaUnoGameState state;
    private int laps;
    private int winnerPosition;
    private long winnerTime;
    private double percent;
    private int actualSection;
    private PlayerType type;
    private Vector2D speed;

    /**
     * Costruttore.
     * Inizializza le informazioni del giocatore con i dati forniti.
     *
     * @param player il giocatore
     * @param state lo stato del giocatore
     * @param percent la percentuale di completamento del giocatore
     */
    
    public FormulaUnoPlayerInfoDTO(FormulaUnoPlayer player, FormulaUnoGameState state, double percent) {
        this.name = player.getPlayerName();
        this.id = player.getPlayerId();
        this.laps = player.getLaps();
        this.coordinate = player.getCoordinate();
        this.state = state;
        this.winnerPosition = player.getWinnerPosition();
        this.winnerTime = player.getWinnerTime();
        this.percent = percent;
        this.actualSection = player.getSection();
        this.type = player.getPlayerType();
        this.speed = player.getSpeed();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Coordinate2D getCoordinate() {
        return coordinate;
    }

    public FormulaUnoGameState getState() {
        return state;
    }

    public int getLaps(){
        return this.laps;
    }

    public int getWinnerPosition() {
        return winnerPosition;
    }

    public double getPercent() {
        return percent;
    }

    public int getActualSection() {
        return actualSection;
    }

    public PlayerType getType(){
        return this.type;
    }

    public Vector2D getSpeed(){
        return this.speed;
    }

    public long getWinnerTime() {
        return winnerTime;
    }

}