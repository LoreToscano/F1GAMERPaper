package org.Controller.Parsing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.Exception.ParsingException;
import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.GameElements.Coordinate2D;

public class MapSyntacticParserTest {

    @Test
    public void testDefault() {
        FormulaUnoGameMap gameMap = new FormulaUnoGameMap();
        gameMap.addCheck(new Coordinate2D(0, 0), new Coordinate2D(10, 0));
        gameMap.addCheck(new Coordinate2D(50, 50), new Coordinate2D(50, 40));
        gameMap.addCheck(new Coordinate2D(0, 100), new Coordinate2D(0, 90));
        gameMap.addCheck(new Coordinate2D(-50, 50), new Coordinate2D(-40, 50));
        gameMap.addCheck(new Coordinate2D(0, 0), new Coordinate2D(10, 0));
        gameMap.setNotEditable();
        MapSyntacticParser parser = new MapSyntacticParser(4);
        assertDoesNotThrow(() -> parser.parseMap(gameMap));
        FormulaUnoGameMap gameMap2 = new FormulaUnoGameMap();
        gameMap2.addCheck(new Coordinate2D(0, 0), new Coordinate2D(10, 0));
        gameMap2.addCheck(new Coordinate2D(50, 40), new Coordinate2D(50, 50));
        gameMap2.addCheck(new Coordinate2D(0, 100), new Coordinate2D(0, 90));
        gameMap2.setNotEditable();
        assertThrows(ParsingException.class, () -> parser.parseMap(gameMap2));
    }
}
