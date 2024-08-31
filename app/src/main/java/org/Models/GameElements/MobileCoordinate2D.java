package org.Models.GameElements;

/**@author Lorenzo Toscano - 110204
 * 
 * 
 * Classe che rappresenta la coordinata bidimensionale con il vettore applicabile
 */

public class MobileCoordinate2D extends Coordinate2D {

    public MobileCoordinate2D(int x, int y) {
        super(x, y);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setCoordiante(Coordinate2D c){
        this.x = c.x;
        this.y = c.y;
    }

    /**
     * Applica un vettore alle coordinate
     * @param vector il vettore da applicare
     */
    public void applyVector(Vector2D vector){
        this.x += vector.x;
        this.y += vector.y;
    }
    
}
