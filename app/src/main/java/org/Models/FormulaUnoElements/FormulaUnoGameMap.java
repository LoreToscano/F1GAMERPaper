package org.Models.FormulaUnoElements;

import java.util.ArrayList;
import java.util.List;

import org.Models.GameElements.*;
/**
 * @author Lorenzo Toscano - 110204
 * 
 * Questa classe contiene informazioni sulle sezioni della mappa,
 * la dimensione della mappa e se la mappa è modificabile.
*/
public class FormulaUnoGameMap {

    private List<Section2D> sections;
    private int count;
    private boolean editable;
    private Segment2D start;
    private Segment2D last;
    private double dimension;

    /**
     * Costruttore della classe
     * Inizializza una nuova mappa di gioco vuota.
     */

    public FormulaUnoGameMap(){
        sections = new ArrayList<>();
        count = 0;
        editable = true;
        dimension = 0;
    }

    /**
     * Ottiene il numero di sezioni della mappa.
     *
     * @return il numero di sezioni
     */

    public int getSectionsCount(){
        return count;
    }


    /**
     * Verifica se la mappa è chiusa
     *
     * @return true se la mappa è circolare, false altrimenti
     */
    public boolean isCircle(){
        return count > 0 && last.equals(start);
    }


    /**
     * Aggiunge un checkpoint alla mappa.
     *
     * @param left la coordinata sinistra del checkpoint
     * @param right la coordinata destra del checkpoint
     */
    public void addCheck(Coordinate2D left, Coordinate2D right){
        if(!editable)
            return;
        Segment2D segment = new Segment2D(left,right);
        if(start == null){
            start = segment;
        }else{
            Section2D section = new Section2D(count++, last, segment);
            sections.add(section);
            dimension += section.getDistance();
        }
        last = segment;
    }

     /**
     * Imposta la mappa come non modificabile.
     */
    public void setNotEditable(){
        if(this.editable){
            this.editable = false;
            if(isCircle()){
                sections.add(sections.get(0).clone(count++));
            }else{
                sections.add(sections.get(count - 1).getNext(count++));
            }
        }
    }

     /**
     * Ottiene la dimensione della mappa.
     *
     * @return la dimensione
     */
    public double getDimension(){
        return dimension;
    }


    /**
     * Ottiene una sezione della mappa.
     *
     * @param index l'indice della sezione
     * @return la sezione della mappa
     */
    public Section2D getSection(int index){
        return sections.get(index).clone(index);
    }

    /**
     * Ottiene l'indice della sezione che contiene una determinata coordinata.
     *
     * @param last l'ultima sezione conosciuta
     * @param c la coordinata da cercare
     * @return l'indice della sezione che contiene la coordinata, o -1 se non trovata
     */
    public int getSectionFromCoordinate(int last, Coordinate2D c){
        List<Integer> list = new ArrayList<>();
        if(last > -1)
            list.add(last);
        if(last + 1 < count)
            list.add(last + 1);
        if(last > 0)
            list.add(last - 1);
        list.add(0);
        for(int i : list){
            if(getSection(i).isIntoSection(c)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica se una coordinata è fuori strada.
     *
     * @param last l'ultima sezione conosciuta
     * @param from la coordinata di partenza
     * @param speed la velocità del movimento
     * @return true se la coordinata è fuori strada, false altrimenti
     */
    public boolean checkIfOutOfRoad(int last, Coordinate2D from, Vector2D speed){
        while(!speed.equals(new Vector2D())){
            int x = speed.getX() > 0 ? 1 : speed.getX() < 0 ? -1 : 0;
            int y = speed.getY() > 0 ? 1 : speed.getY() < 0 ? -1 : 0;
            Vector2D s = new Vector2D(x, y);
            from = s.sum(from);
            if(getSectionFromCoordinate(last, from) == -1)
                return true;
            speed = s.reverse().sum(speed);
        }
        return false;
    }

    /**
     * Ottiene la percentuale di completamento della mappa da una determinata coordinata.
     *
     * @param last l'ultima sezione conosciuta
     * @param c la coordinata da cercare
     * @return la percentuale di completamento della mappa
     */
    public double getPercentFromCoordinate(int last, Coordinate2D c){
        int index = getSectionFromCoordinate(last, c);
        if(index < 0) return 0;
        if(index == count - 1) return 100;
        double count = 0;
        for(int i = 0; i < index; i++){
            count += getSection(i).getDistance();
        }
        count += getSection(index).getSegmentStart()
                                  .getCoordinateMiddle()
                                  .getDistance(c);
        return (count * 100) / getDimension();
    }

}
