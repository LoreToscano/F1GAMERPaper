package org.Models.GameElements;


/**
 * @author Lorenzo Toscano - 110204
 * Casse che rappresenta un segmento bidimensionale
 *
 * 
*/

public class Segment2D {
    
    private Coordinate2D coordinateLeft, coordinateRight;

    public Coordinate2D getCoordinateLeft() {
        return coordinateLeft;
    }

    public Coordinate2D getCoordinateRight() {
        return coordinateRight;
    }

    public Coordinate2D getCoordinateMiddle(){
        return new Coordinate2D((coordinateLeft.x + coordinateRight.x) / 2, (coordinateLeft.y + coordinateRight.y) / 2);
    }

    public Segment2D(Coordinate2D coordinateLeft, Coordinate2D coordinateRight) {
        this.coordinateLeft = coordinateLeft;
        this.coordinateRight = coordinateRight;
    }

     /**
     * Calcola la dimensione del segmento
     * @return la dimensione del segmento
     */
    public double dimensionSegment(){
        return this.coordinateLeft.getDistance(this.coordinateRight);
    }

    public Segment2D clone(){
        return new Segment2D(coordinateLeft.getCoordinate(), coordinateRight.getCoordinate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordinateLeft == null) ? 0 : coordinateLeft.hashCode());
        result = prime * result + ((coordinateRight == null) ? 0 : coordinateRight.hashCode());
        return result;
    }


    /**
     * Verifica se due oggetti sono uguali
     * @param obj l'oggetto da confrontare
     * @return true se gli oggetti sono uguali, altrimenti fals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Segment2D other = (Segment2D) obj;
        if (coordinateLeft == null) {
            if (other.coordinateLeft != null)
                return false;
        } else if (!coordinateLeft.equals(other.coordinateLeft))
            return false;
        if (coordinateRight == null) {
            if (other.coordinateRight != null)
                return false;
        } else if (!coordinateRight.equals(other.coordinateRight))
            return false;
        return true;
    }

    public int getMinX(){
        return this.coordinateLeft.getX() < this.coordinateRight.getX() ? this.coordinateLeft.getX() : this.coordinateRight.getX();
    }

    public int getMinY(){
        return this.coordinateLeft.getY() < this.coordinateRight.getY() ? this.coordinateLeft.getY() : this.coordinateRight.getY();
    }

    public int getMaxX(){
        return this.coordinateLeft.getX() > this.coordinateRight.getX() ? this.coordinateLeft.getX() : this.coordinateRight.getX();
    }

    public int getMaxY(){
        return this.coordinateLeft.getY() > this.coordinateRight.getY() ? this.coordinateLeft.getY() : this.coordinateRight.getY();
    }

    @Override
    public String toString() {
        return "{ 'coordinateLeft': " + coordinateLeft + ", 'CoordinateRight': " + coordinateRight + " }";
    }
    
}
