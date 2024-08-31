package org.Models.FormulaUnoElements;

/**
 * @author Lorenzo Toscano - 110204
 * 
 *Enum che rappresenta i diversi tipi di giocatore in base alla difficoltà
 */


public enum PlayerType {
    EASY, 
    MEDIUM, 
    HARD, 
    CHAMPION,
    REAL;


    /**
     * Si ottiene il tipo di giocatore dal numero
     * @param n il numero che rappresenta il tipo di giocatore
     * @return il tipo di giocatore corrispondente alla difficoltà
     */
    static public PlayerType getFromNumber(int n){
        switch (n) {
            case 0:
                return EASY;
            case 1:
                return MEDIUM;
            case 2:
                return HARD;
            case 3:
                return CHAMPION;
            case 4:
                return REAL;
            default:
                return MEDIUM;
        }
    }

    /**
     * Ottiene il tipo di giocatore dalla stringa
     * @param s la stringa che rappresenta il numero del tipo di giocatore
     * @return il tipo di giocatore
     */

    static public PlayerType getFromNumber(String s){
        try{
            int n = Integer.parseInt(s);
            return getFromNumber(n);
        }catch(Exception e){
            return MEDIUM;
        }
    }

    /**
     * Ottiene un array di tipi di giocatore dalle stringhe
     * @param a l'array di stringhe che rappresentano i numeri dei tipi di giocatore
     * @return l'array di tipi di giocatore corrispondente
     */
    static public PlayerType[] getFromNumber(String[] a){
        PlayerType[] a2 = new PlayerType[a.length];
        for(int i = 0; i < a2.length; i++){
            a2[i] = getFromNumber(a[i]);
        }
        return a2;
    }

}
