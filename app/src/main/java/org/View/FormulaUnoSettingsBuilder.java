package org.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.Models.FormulaUnoElements.FormulaUnoGameSettings;

/**
 * @author Lorenzo Toscano - 11020
 * 
 * Classe che costruisce le impostazioni del gioco
 */
public class FormulaUnoSettingsBuilder {

    private Scanner input = new Scanner(System.in);


     /**
     * Ottiene le impostazioni del gioco rendendole visibili
     * @return le impostazioni visibili
     */
    public FormulaUnoViewSettings getViewSettings(){
        FormulaUnoViewSettings settings = new FormulaUnoViewSettings();
        String answer = getAnswer("Do you want load a custom game map? (false)");
        settings.setCustomMap(getBoolean(answer, false));
        if(settings.isCustomMap()){
            settings.setPathMap(getAnswer("Insert the path of the map file."));
        } else {
            settings.setIdStandardMap(getInteger(getAnswer("Insert the id of the standard map from 0 to 2. (0)"), 0));
        }
        settings.setMills(getInteger(getAnswer("Insert the time in milliseconds between every round. (500)"), 500));
        answer = getAnswer("Do you want load a custom settings file? (false)");
        settings.setCustomSettings(getBoolean(answer, false));
        if(settings.isCustomSettings()){
            settings.setPathSettings(getAnswer("Insert the path of the settings file."));
        }
        return settings;
    }
    

    /**
     * Ottiene le impostazioni del gioco
     * @return una mappa delle impostazioni
     */
    public Map<String, String> getSettings(){
        Map<String, String> settings = new HashMap<>();
        settings.put(FormulaUnoGameSettings.NUMBERPLAYER_NAME, getAnswer("How many players? (4)"));
        settings.put(FormulaUnoGameSettings.DIFFICULTS_NAME, getAnswer("Set the difficults, from 0 to 3, to every player.\n(if 4 players, 1 1 1 1)"));
        settings.put(FormulaUnoGameSettings.NAMES_NAME, getAnswer("Set the player's names, for every player.\n(player_0 player_1 player_2 player_3)"));
        settings.put(FormulaUnoGameSettings.NUMBERLAPS_NAME, getAnswer("Set the number of laps, used only if it is a closed map. (4)"));
        settings.put(FormulaUnoGameSettings.DONT_ELIMINATE_IF_EXIT_NAME, getAnswer("Do you want don't eliminate a player if he escape from map? (false)"));
        settings.put(FormulaUnoGameSettings.VEHICLES_CAN_OVERLAP_NAME, getAnswer("Do you want vehicles to be able to overlap? (false)"));
        settings.put(FormulaUnoGameSettings.RANDOM_ORDER_CHANGE_NAME, getAnswer("Do you want the player order to change every turn? (false)"));
        settings.put(FormulaUnoGameSettings.RIGHT_STARTING_POSITIONING_NAME, getAnswer("Do you want the initial positioning not to benefit the first players? (false)"));
        return settings;
    }

    //domande all'utente
    private String getAnswer(String question){
        System.out.println(question);
        return input.nextLine();
    }

    /**
     * Converte una stringa in un valore booleano
     * @param s la stringa da convertire
     * @param def il valore predefinito se la conversione fallisce
     * @return il valore booleano risultante
     */
    private boolean getBoolean(String s, boolean def){
        if(s.toLowerCase().equals("true"))
            return true;
        if(s.toLowerCase().equals("false"))
            return false;
        return def;
    }


    /**
     * Converte una stringa in un valore intero
     * @param s la stringa da convertire
     * @param def il valore predefinito se le conversioni falliscono
     * @return il valore intero risultante
     */
    private int getInteger(String s, int def){
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        }
    }
}
