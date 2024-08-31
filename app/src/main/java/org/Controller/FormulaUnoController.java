package org.Controller;

import java.nio.file.FileSystems;
import java.util.List;
import java.util.Map;

import org.Controller.Parsing.MapLoader;
import org.Controller.Parsing.MapSemanticParser;
import org.Controller.Parsing.MapSyntacticParser;
import org.Controller.Parsing.SettingsLoader;
import org.Controller.Parsing.SettingsParser;
import org.Exception.ParsingException;
import org.Models.DTO.FormulaUnoGameInfoDTO;
import org.Models.DTO.TwoDimensionGameMapDTO;
import org.Models.FormulaUnoElements.FormulaUnoCommand;
import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoGameSettings;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.GameSettings;

/**
 * @author Lorenzo Toscano - 110204
 * 
 * Classe che funge da controller principale.
 * Implementa l'interfaccia GameController e gestisce il caricamento delle mappe,
 * l'applicazione delle impostazioni di gioco e l'esecuzione dei comandi di gioco
 * definendo i tipi con cui lavora e i metodi degli oggetti figli da chiamare.
 */
public class FormulaUnoController implements GameController<TwoDimensionGameMapDTO, FormulaUnoCommand, FormulaUnoGameInfoDTO>{

    private MapLoader mapLoader;
    private MapSemanticParser mapSemParser;
    private MapSyntacticParser mapSynParser;
    private SettingsParser settingsParser;
    private SettingsLoader settingsLoader;
    private FormulaUnoGameMap gameMap;
    private GameSettings gameSettings;
    private FormulaUnoGameHandler gameHandler;

    /**
     * Costruttore del controller.
     * Inizializza i parser e i loader necessari per il caricamento delle mappe e delle impostazioni.
     */
    public FormulaUnoController() {
        this.mapLoader=new MapLoader();
        this.settingsParser = new SettingsParser();
        this.settingsLoader = new SettingsLoader();
        this.mapSemParser = new MapSemanticParser();
    }

    /**
     * Carica una mappa di gioco da un file di testo.
     *
     * @param pathOfMap il percorso del file di testo che contiene la mappa di gioco
     * @throws Exception se si verifica un errore durante il caricamento della mappa
     */

    @Override
    public void loadGameMap(String pathOfMap) throws Exception {
        pathOfMap = getAbsolutePath(pathOfMap);
        if(mapSynParser == null)
            throw new Exception("Caricare le impostazioni prima della mappa.");
        mapSemParser.parseMap(pathOfMap);
        this.gameMap = mapLoader.LoadGameMap(pathOfMap);
        mapSynParser.parseMap(gameMap);
    }



    /**
     * Carica una mappa di gioco da una lista di coordinate.
     *
     * @param map una lista di array di Coordinate2D che rappresentano i checkpoint della mappa
     * @throws ParsingException se si verifica un errore durante la validazione della mappa
     */

    @Override
    public void loadGameMap(List<Coordinate2D[]> map) throws ParsingException {
        if(mapSynParser == null)
            throw new IllegalArgumentException("Caricare le impostazioni prima della mappa.");
        mapSemParser.parseMap(map);
        this.gameMap = mapLoader.LoadGameMap(map);
        mapSynParser.parseMap(gameMap);
    }

    /**
     * Applica le impostazioni di gioco da un file di testo.
     *
     * @param pathOfSetting il percorso del file di testo che contiene le impostazioni di gioco
     * @throws Exception se si verifica un errore durante il caricamento delle impostazioni
     */
    @Override
    public void applySettingsGame(String pathOfSetting) throws Exception {
        pathOfSetting = getAbsolutePath(pathOfSetting);
        settingsParser.parseSettingsFile(pathOfSetting);
        this.gameSettings = settingsLoader.LoadSettings(pathOfSetting);
        this.mapSynParser = new MapSyntacticParser(this.gameSettings.getSettingPositiveNumber(FormulaUnoGameSettings.NUMBERPLAYER_NAME, 4));
    }

    /**
     * Applica le impostazioni di gioco da una mappa di impostazioni.
     *
     * @param settings una mappa di stringhe che rappresentano le impostazioni di gioco
     */
    @Override
    public void applySettingsGame(Map<String,String> settings){
        this.gameSettings = new GameSettings(settings);
        this.mapSynParser = new MapSyntacticParser(this.gameSettings.getSettingPositiveNumber(FormulaUnoGameSettings.NUMBERPLAYER_NAME, 4));
    }

    /**
     * Metodo di utilità per ricavare il path assoluto da quello relativo
     * @param relativePath path relativo di partenza
     * @return path/percorso assoluto
     */
    private String getAbsolutePath(String relativePath){
        return FileSystems.getDefault().getPath(relativePath).normalize().toAbsolutePath().toString();
    }

    /**
     * Avvia il gioco.
     */
    @Override
    public void startGame() {
        this.gameHandler = new FormulaUnoGameHandler(gameMap, gameSettings);
    }

    /**
     * Ottiene le informazioni del gioco.
     *
     * @return un oggetto FormulaUnoGameInfoDTO che rappresenta le informazioni del gioco
     */
    @Override
    public FormulaUnoGameInfoDTO getInfo() {
        return this.gameHandler.getPlayersInfo();
    }

    /**
     * Ottiene la mappa di gioco.
     *
     * @return un oggetto TwoDimensionGameMapDTO che rappresenta la mappa di gioco
     */
    @Override
    public TwoDimensionGameMapDTO getGameMap() {
        return this.gameHandler.getMapInfo();
    }

    /**
     * Esegue un comando di gioco.
     *
     * @param command un oggetto FormulaUnoCommand che rappresenta il comando di gioco
     * @return true se il comando è stato eseguito con successo, false altrimenti
     */
    @Override
    public boolean executeMove(FormulaUnoCommand command) {
        return this.gameHandler.executeMove(command);
    }

    /**
     * Esegue un comando di gioco predefinito.
     *
     * @return true se il comando è stato eseguito con successo, false altrimenti
     */
    @Override
    public boolean executeMove() {
        return this.gameHandler.executeMove();
    }

}
