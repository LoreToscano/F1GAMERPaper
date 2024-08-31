package org.Controller;

import java.util.List;
import java.util.Map;

import org.Exception.ParsingException;
import org.Models.GameElements.Coordinate2D;

/**
 * @author Lorenzo Toscano - 110204
 * 
 *  Questa interfaccia ha la responsabilità di definire il comportamento di un 
 *  generico controller di un gioco 
 */
public interface GameController<GM, C, I> {

    /**
     * Metodo per caricare una mappa file txt
     * fornendo il path dell'apposito file
     * è indispensabile chiamarlo dopo aver caricato le impostazioni
     * @param pathOfMap path del file contenente la mappa
     * @return true se il caricamento è andato a buon fine, false altrimenti
     * @throws Exception 
     */
    public void loadGameMap(String pathOfMap) throws Exception;

    /**
     * Metodo per caricare una mappa da una lista di array di coordinate
     * ogni array deve avere solo due coordinate
     * è indispensabile chiamarlo dopo aver caricato le impostazioni
     * @param map
     * @throws ParsingException
     */
    public void loadGameMap(List<Coordinate2D[]> map) throws ParsingException;

    /**
     * Metodo per applicare le impostazioni da un file txt
     * il formato del file deve essere nome = parole di impostazione
     * @param pathOfSettings path per il file con le impostazioni
     * @throws Exception
     */
    public void applySettingsGame(String pathOfSettings) throws Exception;

    /**
     * Metodo per applicare le impostazioni da una mappa
     * @param map mappa con le impostazioni
     */
    public void applySettingsGame(Map<String,String> map);

    /**
     * Metodo da chiamare dopo aver caricato tutte le impostazioni
     * da inizio alla partita
     */
    public void startGame();

    /**
     * Metodo che ritorna le info relative all'attuale stato della partita
     * da chiamare dopo aver avviato la partita
     * @return info sulla partita
     */
    public I getInfo();

    /**
     * metodo che ritorna le info relative alla mappa della partita
     * da chiamare dopo aver avviato la partita
     * @return info sulla mappa della partita
     */
    public GM getGameMap();

    /**
     * Comando che esegue una mossa, da chiamare dopo aver avviato la partita
     * @param command comando da eseguire
     * @return true se il comando ha avuto successo, false altrimenti
     */
    public boolean executeMove(C command);

    /**
     * Comando per eseguire una mossa automatica, se ce ne sono
     * da chiamare dopo aver avviato la partita
     */
    public boolean executeMove();

}
