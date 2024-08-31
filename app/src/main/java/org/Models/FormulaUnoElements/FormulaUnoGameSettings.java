package org.Models.FormulaUnoElements;

import org.Models.GameElements.GameSettings;

/**
 * @author Lorenzo Toscano - 110204
 * 
 * Questa classe contiene informazioni come il numero di giocatori,
 * il numero di giri, i nomi dei giocatori, i tipi di giocatori e le varie opzioni di gioco...
 */

public class FormulaUnoGameSettings {
    public static final String NUMBERPLAYER_NAME = "numberOfPlayers";
    public static final String NUMBERLAPS_NAME = "numberOfLaps";
    public static final String NAMES_NAME = "names";
    public static final String DIFFICULTS_NAME = "difficults";
    public static final String DONT_ELIMINATE_IF_EXIT_NAME = "dontEliminateIfExit";
    public static final String VEHICLES_CAN_OVERLAP_NAME = "vehiclesCanOverlap";
    public static final String RANDOM_ORDER_CHANGE_NAME = "randomOrderChange";
    public static final String RIGHT_STARTING_POSITIONING_NAME = "rightStartingPositioning";
    private int numberPlayer;
    private int laps;
    private PlayerType[] types;
    private String[] names;
    private boolean dontEliminateIfExit;
    private boolean veiclesCanOverlap;
    private boolean randomOrderChange;
    private boolean rightStartingPositioning;

    /**
     * Costruttore della classe
     * Inizializza le impostazioni di gioco con i dati forniti.
     *
     * @param settings le impostazioni di gioco
     * @param isOpen un flag che indica se il gioco è pronto
     */
    public FormulaUnoGameSettings(GameSettings settings, boolean isOpen) {
        this.laps = isOpen ? 1 : settings.getSettingPositiveNumber(NUMBERLAPS_NAME, 3);
        numberPlayer = settings.getSettingPositiveNumber(NUMBERPLAYER_NAME, 4);
        setNames(settings);
        setDifficults(settings);
        setVariantOptions(settings);
    }

     /**
     * Imposta i nomi dei giocatori.
     *
     * @param settings le impostazioni del gioco custom o non
     */
    private void setNames(GameSettings settings) {
        if(settings.haveSetting(NAMES_NAME)){
            String[] names = settings.getSetting(NAMES_NAME, "").split(" ");
            if(names.length == numberPlayer){
                this.names = names;
                return;
            }
        }
        names = new String[this.numberPlayer];
        for(int i = 0; i < numberPlayer; i++){
            names[i] = "Player_" + i;
        }
    }

    /**
     * Imposta i tipi di giocatori
     *
     * @param settings le impostazioni di gioco
     */
    private void setDifficults(GameSettings settings){
        if(settings.haveSetting(DIFFICULTS_NAME)){
            String[] difs = settings.getSetting(DIFFICULTS_NAME, "").split(" ");
            if(difs.length == numberPlayer){
                types = PlayerType.getFromNumber(difs);
                return;
            }
        }
        types = new PlayerType[this.numberPlayer];
        for(int i = 0; i < numberPlayer; i++){
            types[i] = PlayerType.MEDIUM;
        }
    }


    /**
     * Imposta le opzioni di variante
     *
     * @param settings le impostazioni del gioco custom
     */

    private void setVariantOptions(GameSettings settings){
        this.dontEliminateIfExit = settings.getBoolSetting(DONT_ELIMINATE_IF_EXIT_NAME, false);
        this.veiclesCanOverlap = settings.getBoolSetting(VEHICLES_CAN_OVERLAP_NAME, false);
        this.randomOrderChange = settings.getBoolSetting(RANDOM_ORDER_CHANGE_NAME, false);
        this.rightStartingPositioning = settings.getBoolSetting(RIGHT_STARTING_POSITIONING_NAME, false);
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public int getLaps() {
        return laps;
    }

    public String[] getNames() {
        String[] clone = new String[this.names.length];
        for(int i = 0; i < clone.length; i++){
            clone[i] = this.names[i];
        }
        return clone;
    }

    public PlayerType[] getTypes() {
        PlayerType[] clone = new PlayerType[this.types.length];
        for(int i = 0; i < clone.length; i++){
            clone[i] = this.types[i];
        }
        return clone;
    }

    public boolean isDontEliminateIfExit() {
        return dontEliminateIfExit;
    }

    public boolean isVeiclesCanOverlap() {
        return veiclesCanOverlap;
    }

    public boolean isRandomOrderChange() {
        return randomOrderChange;
    }

    public boolean isRightStartingPositioning() {
        return rightStartingPositioning;
    }

}
