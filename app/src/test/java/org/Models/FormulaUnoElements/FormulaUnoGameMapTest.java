package org.Models.FormulaUnoElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.Vector2D;

public class FormulaUnoGameMapTest {

    @Test
    public void testCheck() {
        FormulaUnoGameMap gameMap = new FormulaUnoGameMap();
        gameMap.addCheck(new Coordinate2D(0, 0), new Coordinate2D(10, 0));
        gameMap.addCheck(new Coordinate2D(50, 50), new Coordinate2D(50, 40));
        gameMap.addCheck(new Coordinate2D(0, 100), new Coordinate2D(0, 90));
        gameMap.addCheck(new Coordinate2D(-50, 50), new Coordinate2D(-40, 50));
        gameMap.addCheck(new Coordinate2D(0, 0), new Coordinate2D(10, 0));
        gameMap.setNotEditable();
        assertEquals((int)gameMap.getDimension(), 268);
        assertEquals(gameMap.getSectionsCount(), 5);
        assertTrue(gameMap.isCircle());
        assertTrue(gameMap.checkIfOutOfRoad(0, new Coordinate2D(25, 25), new Vector2D(0, 15)));
        assertFalse(gameMap.checkIfOutOfRoad(0, new Coordinate2D(25, 25), new Vector2D(5, 5)));
        assertEquals(gameMap.getPercentFromCoordinate(2, new Coordinate2D(0, 95)), 50);
    }
}
