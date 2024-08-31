package org.Controller.Parsing;

import org.Exception.ParsingException;
import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Section2D;
import org.Models.GameElements.Segment2D;

/**
 * @author Lorenzo Toscano - 110204
 * Questa classe è responsabile della validazione sintattica delle mappe di gioco
 * e fornisce metodi per verificare la correttezza delle sezioni e dei segmenti delle mappe.
 */

public class MapSyntacticParser {

    private int playerNumber;

    /**
     * Costruttore della classe MapSyntacticParser.
     *
     * @param playerNum il numero di giocatori
     */
    public MapSyntacticParser(int playerNum){
        this.playerNumber = playerNum;
    }

 /**
     * Verifica la correttezza di una mappa di gioco.
     *
     * @param map un oggetto FormulaUnoGameMap che rappresenta la mappa di gioco
     * @throws ParsingException se si verifica un errore durante la validazione della mappa
     * @throws IllegalArgumentException se la pista è troppo corta o la sezione iniziale è troppo piccola per il numero di giocatori
     */

    public void parseMap(FormulaUnoGameMap map) throws ParsingException{
        if(map.getSectionsCount() < 3)
            throw new IllegalArgumentException("Pista troppo corta");
        if(map.getSection(0).getAreaSection() <= this.playerNumber * 3)
            throw new IllegalArgumentException("Sezione iniziale troppo piccola per il numero di giocatori");
        analyzeSegment(map.getSection(0).getSegmentStart(), 0);
        for(int i = 0; i < map.getSectionsCount(); i++){
            analyzeSection(map.getSection(i));
        }
    }

    /**
     * Verifica la correttezza di una sezione della mappa.
     *
     * @param section un oggetto Section2D che rappresenta la sezione della mappa
     * @throws ParsingException se si verifica un errore durante la validazione della sezione
     */
    private void analyzeSection(Section2D section) throws ParsingException{
        analyzeSegment(section.getSegmentEnd(), section.getId() + 1);
        doSectionOverlap(section);
    }


    /**
     * Verifica la correttezza di un segmento della mappa.
     *
     * @param segment un oggetto Segment2D che rappresenta il segmento della mappa
     * @param line il numero della riga nel file
     * @throws ParsingException se la distanza tra le coordinate del segmento è inferiore a 2
     */
    private void analyzeSegment(Segment2D segment, int line) throws ParsingException{
        if(segment.getCoordinateLeft().getDistance(segment.getCoordinateRight()) < 2)
            throw new ParsingException(line);
    }
    
    /**
     * Verifica se le sezioni della mappa si sovrappongono.
     *
     * @param section un oggetto Section2D che rappresenta la sezione della mappa
     * @throws ParsingException se le sezioni si sovrappongono
     */
    private void doSectionOverlap(Section2D section) throws ParsingException{
        Coordinate2D p1 = section.getSegmentStart().getCoordinateLeft(), 
                     q1 = section.getSegmentEnd().getCoordinateLeft(),
                     p2 = section.getSegmentStart().getCoordinateRight(), 
                     q2 = section.getSegmentEnd().getCoordinateRight();
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) {
            throw new ParsingException(section.getId() + 1);
        }
    }

    /**
     * Calcola l'orientamento di tre punti.
     *
     * @param p il primo punto
     * @param q il secondo punto
     * @param r il terzo punto
     * @return 0 se i punti sono collineari, 1 se sono in senso orario, 2 se sono in senso antiorario
     */
    private int orientation(Coordinate2D p, Coordinate2D q, Coordinate2D r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0.0) return 0;
        return (val > 0) ? 1 : 2;
    }
    
}
