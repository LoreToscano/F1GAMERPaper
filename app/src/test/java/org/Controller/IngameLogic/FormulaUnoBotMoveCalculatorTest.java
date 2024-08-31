package org.Controller.IngameLogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.Controller.Parsing.MapLoader;
import org.Models.FormulaUnoElements.FormulaUnoGameMap;
import org.Models.FormulaUnoElements.FormulaUnoGameSettings;
import org.Models.FormulaUnoElements.FormulaUnoMove;
import org.Models.FormulaUnoElements.FormulaUnoPlayer;
import org.Models.FormulaUnoElements.PlayerType;
import org.Models.GameElements.Coordinate2D;
import org.Models.GameElements.GameSettings;
import org.Models.GameElements.Round;
import org.Models.GameElements.Vector2D;
import org.View.FormulaUnoStandardGameMapBuilder;

public class FormulaUnoBotMoveCalculatorTest {

    @Test
    public void testMovingCalculation() {
        FormulaUnoGameMap map = new MapLoader().LoadGameMap(new FormulaUnoStandardGameMapBuilder().getStandardClosedMap());
        FormulaUnoGameSettings settings = new FormulaUnoGameSettings(new GameSettings(new HashMap<>()), false);
        FormulaUnoPlayer[] players = {
            new FormulaUnoPlayer(3, 15, 0, "aaa", PlayerType.MEDIUM),
            new FormulaUnoPlayer(4, 17, 1, "bbb", PlayerType.MEDIUM),
            new FormulaUnoPlayer(2, 17, 2, "ccc", PlayerType.MEDIUM),
        };
        players[0].setSpeed(new Vector2D(0, 2));
        players[0].doFirstMove();
        Round round = new Round(3, false);
        FormulaUnoArbitrator arbitrator = new FormulaUnoArbitrator(map, players, settings, round);
        List<FormulaUnoMove> moves = arbitrator.getAcceptableMoves(players[0]);
        FormulaUnoBotMoveCalculator calculator = new FormulaUnoBotMoveCalculator(map);
        calculator.setDifficult(PlayerType.HARD);
        FormulaUnoMove move = calculator.findNextMove(players[0], moves);
        assertEquals(move.getEndPosition(), new Coordinate2D(3, 18));
        calculator.setDifficult(PlayerType.MEDIUM);
        FormulaUnoMove move2 = calculator.findNextMove(players[0], moves);
        assertEquals(move2.getEndPosition(), new Coordinate2D(3, 17));
        calculator.setDifficult(PlayerType.EASY);
        FormulaUnoMove move3 = calculator.findNextMove(players[0], moves);
        assertEquals(move3.getEndPosition(), new Coordinate2D(2, 16));
    }
}
