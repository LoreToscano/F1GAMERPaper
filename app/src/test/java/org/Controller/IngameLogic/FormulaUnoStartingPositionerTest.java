package org.Controller.IngameLogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.Controller.Parsing.MapLoader;
import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoGameSettings;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.GameSettings;
import org.View.FormulaUnoStandardGameMapBuilder;


public class FormulaUnoStartingPositionerTest {

    @Test
    public void testDefault() {
        FormulaUnoGameMap map = new MapLoader().LoadGameMap(new FormulaUnoStandardGameMapBuilder().getStandardClosedMap());
        FormulaUnoGameSettings settings = new FormulaUnoGameSettings(new GameSettings(new HashMap<>()), false);
        FormulaUnoStartingPositioner startingPositioner = new FormulaUnoStartingPositioner(map, settings);
        Coordinate2D[] coordinates = startingPositioner.getPositions();
        assertEquals(coordinates.length, 4);
        assertEquals(coordinates[0], new Coordinate2D(0, 2));
        assertEquals(coordinates[1], new Coordinate2D(1, 2));
        assertEquals(coordinates[2], new Coordinate2D(0, 1));
        assertEquals(coordinates[3], new Coordinate2D(1, 1));
    }
}
