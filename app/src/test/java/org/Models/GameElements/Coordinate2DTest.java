package org.Models.GameElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Coordinate2DTest {

    @Test
    public void testDistance() {
        Coordinate2D a = new Coordinate2D(10, 10);
        Coordinate2D b = new Coordinate2D(10, 30);
        assertTrue(a.getDistance(b) == 20);
    }

    @Test
    public void testGetVector() {
        Coordinate2D a = new Coordinate2D(10, 10);
        Coordinate2D b = new Coordinate2D(10, 30);
        assertEquals(a.getVector(b), new Vector2D(0, 20));
    }

    @Test
    public void testGetAverage(){
        Coordinate2D a = new Coordinate2D(25, -50);
        Coordinate2D b = new Coordinate2D(190, 320);
        assertEquals(a.getAverage(b), new Coordinate2D(107, 135));
        Coordinate2D c = new Coordinate2D(50, 50);
        assertEquals(c, c.getAverage(c));
    }
}
