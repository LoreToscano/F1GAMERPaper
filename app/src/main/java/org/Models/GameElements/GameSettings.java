package org.Models.GameElements;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lorenzo Toscano - 110204
 * Classe che rappresenta le impostazioni nel gioco
 */

public class GameSettings {
    
    private Map<String, String> settings;

    /**
     * Costruttore della clase
     * @param settings la mappa delle impostazioni che vanno aggiute
     */

    public GameSettings(Map<String, String> settings){
        this.settings = settings;
    }

    /**
     * Costruttore predef della classe GameSetti
     */
    public GameSettings(){
        this.settings = new HashMap<String,String>();
    }

    /**
     * Imposta un valore per una determinata impostazione
     * @param name il nome dell'impostazione
     * @param value valore dell'impostazione
     */

    public void setSetting(String name, String value){
        settings.put(name, value);
    }

     /**
     * Verifica se una determinata impostazione esiste
     * @param name il nome per l'impostazione
     * @return true se l'impostazione esiste, altrimenti false
     */
    public boolean haveSetting(String name){
        return this.settings.containsKey(name);
    }

    /**
     * Ottiene il valore di una determinata impostazione pa
     * @param name il nome dell'impostazione
     * @param defaultValue il valore predefinito se l'impostazione non esiste
     * @return il valore dell'impostazione/il valore predefinito
     */
    public String getSetting(String name, String defaultValue){
        if(this.settings.containsKey(name))
            return this.settings.get(name);
        return defaultValue;
    }

    /**
     * Ottiene il valore booleano di una determinata impostazione
     * @param name il nome dell'impostazione.
     * @param defaultValue il valore predefinito se l'impostazione non esiste
     * @return true o false in base al valore dell'impostazione e se il valore predefinito
     */
    public boolean getBoolSetting(String name, boolean defaultValue){
        if(this.settings.containsKey(name)){
            String value = this.settings.get(name);
            if(value.toLowerCase().equals("true") || value.equals("1"))
                return true;
            if(value.toLowerCase().equals("false") || value.equals("0"))
                return false;
        }
        return defaultValue;
    }

     /**
     * Ottiene il valore numerico di una determinata impostazione
     * @param name il nome dell'impostazione
     * @param defaultValue il valore predefinito se l'impostazione non esiste
     * @return il valore numerico dell'impostazione o il valore predefinito
     */
    public int getSettingNumber(String name, int defaultValue){
        try{
            String value = getSetting(name, defaultValue + "");
            return Integer.parseInt(value);
        }catch( Exception e){
            return defaultValue;
        }
    }

     /**
     * Ottiene il valore numerico positivo di una determinata impostazione
     * @param name il nome dell'impostazione
     * @param defaultValue il valore predefinito se l'impostazione non esiste
     * @return il valore numerico positivo dell'impostazione o il valore predefinito
     */
    public int getSettingPositiveNumber(String name, int defaultValue){
        int value = getSettingNumber(name, defaultValue);
        return value < 1 ? defaultValue : value;
    }

}
