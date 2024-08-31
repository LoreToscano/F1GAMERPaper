package org.View;

/**
 * 
 * @author Lorenzo Toscano - 110204
 * Classe che rappresenta le impostazioni della vista
 */

public class FormulaUnoViewSettings {
    
    private boolean customMap, customSettings;
    private String pathMap, pathSettings;
    private int idStandardMap, mills;
    
    /**
     * Verifica se la mappa è personalizzata custom
     * @return true se la mappa è personalizzata, altrimenti false
     */
    public boolean isCustomMap() {
        return customMap;
    }


     /**
     * Imposta se la mappa è personalizzata
     * @param customMap true se la mappa è personalizzata, altrimenti false
     */
    public void setCustomMap(boolean customMap) {
        this.customMap = customMap;
    }

    /**
     * Verifica se le impostazioni sono personalizzate
     * @return true se le impostazioni sono personalizzate, altrimenti false
     */
    public boolean isCustomSettings() {
        return customSettings;
    }
    public void setCustomSettings(boolean customSettings) {
        this.customSettings = customSettings;
    }
    public String getPathMap() {
        return pathMap;
    }
    public void setPathMap(String pathMap) {
        this.pathMap = pathMap;
    }
    public String getPathSettings() {
        return pathSettings;
    }
    public void setPathSettings(String pathSettings) {
        this.pathSettings = pathSettings;
    }
    public int getIdStandardMap() {
        return idStandardMap;
    }
    public void setIdStandardMap(int idStandardMap) {
        this.idStandardMap = idStandardMap;
    }
    public int getMills() {
        return mills;
    }
    public void setMills(int mills) {
        this.mills = mills;
    }

    

}
