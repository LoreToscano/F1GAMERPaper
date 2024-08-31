package org.Models.GameElements;

/**
 * @author Lorenzo Toscano - 110204
 * 
 * La classe rappresenta la cordinata bidimensionale
 */


public class Coordinate2D {
    protected int x;
    protected int y;

    /**
     * Costruttore della classe Coordinate2D.
     * @param x la coordinata x.
     * @param y la coordinata y.
     */
    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Ottiene una nuova istanza con le stesse coordinate
     * @return una nuova istanza di coordinate..
     */
    public Coordinate2D getCoordinate(){
        return new Coordinate2D(x, y);
    }

    /**
     * Calcola il codice hash per l'oggetto Cordinate2d
     * @return il codice hash
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

     /**
     * Verifica se due oggetti sono uguali
     * @param obj l'oggetto da confrontare
     * @return true se gli oggetti sono uguali, altrimenti false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate2D other = (Coordinate2D) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }


     /**
     * Calcola la distanza tra questa coordinata e un'altra
     * @param other l'altra coordinata
     * @return la distanza tra le due coordinate.
     */
    public double getDistance(Coordinate2D other){
        double dimX = Math.abs(this.x - other.x);
        double dimY = Math.abs(this.y - other.y);
        dimX *= dimX;
        dimY *= dimY;
        return Math.sqrt( dimX + dimY );
    }

    /**
     * Calcola il vettore tra questa coordinata e un'altra
     * @param other l'altra coordinata..
     * @return il vettore tra le due coordinate...
     */
    public Vector2D getVector(Coordinate2D other){
        return new Vector2D(other.x - this.x, other.y - this.y);
    }

    public Coordinate2D getAverage(Coordinate2D other){
        Vector2D vector = getVector(other);
        vector = new Vector2D(vector.getX() / 2, vector.getY() / 2);
        return vector.sum(this);
    }

     /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto Coordinate2d
     * @return una stringa che rappresenta l'oggetto Coordinate2D
     */
    @Override
    public String toString() {
        return "{ 'x': " + x + ", 'y': " + y + " }";
    }
    
}
