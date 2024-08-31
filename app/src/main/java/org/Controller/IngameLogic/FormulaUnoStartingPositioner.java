package org.Controller.IngameLogic;

import java.util.List;

import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoGameSettings;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Section2D;


/**@author Lorenzo Toscano - 110204
 ** La classe FormulaUnoStartingPositioner è responsabile del posizionamento iniziale dei giocatori nel gioco
 * questa classe si occupa di determinare le posizioni di partenza dei giocatori in base alla mappa e alle impostazioni.
 */

public class FormulaUnoStartingPositioner {

    // Costruttore che inizializza il posizionatore con la mappa del gioco e le sue impostazioni
    private Section2D startSection, firstSection;
    private int playerNumber;
    private boolean rightStartingPositioning;

    /**
     * Costruttore che inizializza il posizionatore con la mappa del gioco e le impostazioni del gioco.
     *
     * @param map la mappa del gioco
     * @param settings le impostazioni del gioco
     */

    public FormulaUnoStartingPositioner(FormulaUnoGameMap map, FormulaUnoGameSettings settings) {
        this.startSection = map.getSection(0); // Ottiene la sezione di partenza dalla mappa del gioco
        this.firstSection = map.getSection(1); // Ottiene la prima sezione dalla mappa del gioco
        this.playerNumber = settings.getNumberPlayer(); // Imposta il numero di giocatori dalle impostazioni del gioco
        this.rightStartingPositioning = settings.isRightStartingPositioning(); // Imposta se il posizionamento iniziale deve essere a destra dalle impostazioni del gioco
    }

   /**
     * Ottiene le posizioni iniziali dei giocatori.
     *
     * @return un array di Coordinate2D che rappresenta le posizioni iniziali dei giocatori
     */
    public Coordinate2D[] getPositions() {
        // Ottiene le posizioni all'interno della sezione di partenza
        List<List<Coordinate2D>> positions = startSection.getIntoSectionPositions(); // Determina la prima linea di partenza
        int firstLine = getFirstLine(positions);
        if(rightStartingPositioning){
            List<Coordinate2D> line = positions.get(firstLine);
            Coordinate2D startLine = line.get(0), 
                         endLine = line.get(line.size() - 1),
                         target = firstSection.getSegmentEnd().getCoordinateMiddle();
            boolean reverse = startLine.getDistance(target) < endLine.getDistance(target); // Determina se l'ordine delle posizioni deve essere invertito
            return getPositions(positions, reverse, firstLine);
        }
        return getPositions(positions, false, firstLine);
    }

     /**
     * Ottiene le posizioni dei giocatori in base all'ordine specificato.
     *
     * @param positions le posizioni all'interno della sezione
     * @param reverseOrder se true, le posizioni saranno invertite
     * @param firstLine l'indice della prima linea di partenza
     * @return un array di Coordinate2D che rappresenta le posizioni dei giocatori
     */
    private Coordinate2D[] getPositions(List<List<Coordinate2D>> positions, boolean reverseOrder, int firstLine) {
        Coordinate2D[] coors = new Coordinate2D[this.playerNumber];
        int count = 0;
        for(int j = firstLine; j < positions.size(); j++){
            List<Coordinate2D> pos = positions.get(j);
            for(int i = reverseOrder ? pos.size() - 1 : 0; (reverseOrder && i >= 0) || ((!reverseOrder) && i < pos.size()); i += reverseOrder ? -1 : +1){
                coors[count++] = pos.get(i);
                if(count >= this.playerNumber)
                    return coors; // Ritorna l'array di coordinate che rappresentano le posizioni dei giocatori
            }
        }
        return coors;
    }

    /**
     * Determina la prima linea di partenza in base al numero di giocatori.
     *
     * @param positions le posizioni all'interno della sezione
     * @return l'indice della prima linea di partenza
     * @throws IllegalArgumentException se non ci sono abbastanza posti per i giocatori
     */
    private int getFirstLine(List<List<Coordinate2D>> positions) {
        int count = playerNumber;
        for(int i = positions.size() - 1; i >= 0; i--){
            count -= positions.get(i).size();
            if(count < 1) {
                return i; //L'indice della prima linea di partenza
            }
        }
        throw new IllegalArgumentException("Impossibile partire da questa sezione con questi giocatori. Posti non sufficienti.");
    }

}
