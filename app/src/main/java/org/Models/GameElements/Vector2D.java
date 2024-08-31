package org.Models.GameElements;


/**
 * @author Lorenzo Toscano - 110204
 * 
 * Classe che rappresenta un vettore bidimensionale dalle coordinate
 */

public class Vector2D extends Coordinate2D {

    public Vector2D(int x, int y) {
        super(x, y);
    }

    public Vector2D(){
        this(0, 0);
    }
    
    public Vector2D(Direction2D direction, int mul){
        this(direction == Direction2D.RIGHT ? mul : direction == Direction2D.LEFT ? -mul : 0,
             direction == Direction2D.UP ? mul : direction == Direction2D.DOWN ? -mul : 0);
    }

    /**
     * Inverte la direzione del vettore
     * @return un nuovo vettore con la direzione invertita
     */
    public Vector2D reverse(){
        return new Vector2D(x*-1, y*-1);
    }

    /**
     * Somma due vettori
     * @param other l'altro vettore da sommare
     * @return un nuovo vettore risultante dalla somma
     */
    public Vector2D sum(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Coordinate2D sum(Coordinate2D other) {
        return new Coordinate2D(x + other.x, y + other.y);
    }

    /**
     * Ottiene la direzione del vettore
     * @return la direzione del vettore
     */
    public Direction2D getDirection(){
        if(this.x == 0 && this.y == 0)
            return Direction2D.NONE;
        int absX = Math.abs(this.x), absY = Math.abs(this.y);
        if(absX > absY) {
            if(this.x > 0) return Direction2D.RIGHT;
            else return Direction2D.LEFT;
        } else {
            if(this.y > 0) return Direction2D.UP;
            else return Direction2D.DOWN;
        }
    }
    
}
