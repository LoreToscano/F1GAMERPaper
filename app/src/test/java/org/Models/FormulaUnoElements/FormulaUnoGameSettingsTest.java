package org.Models.FormulaUnoElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.Models.GameElements.GameSettings;

public class FormulaUnoGameSettingsTest {

    @Test
    public void testSettings() {
        GameSettings gs = new GameSettings();
        gs.setSetting(FormulaUnoGameSettings.NUMBERPLAYER_NAME, "7");
        gs.setSetting(FormulaUnoGameSettings.DIFFICULTS_NAME, "3 3 2 2 1 0 0");
        gs.setSetting(FormulaUnoGameSettings.VEHICLES_CAN_OVERLAP_NAME, "true");
        gs.setSetting(FormulaUnoGameSettings.RANDOM_ORDER_CHANGE_NAME, "true");
        FormulaUnoGameSettings settings = new FormulaUnoGameSettings(gs, false);
        assertTrue(settings.isVeiclesCanOverlap());
        assertTrue(settings.isRandomOrderChange());
        assertTrue(settings.getNumberPlayer() == 7);
        PlayerType[] array = { PlayerType.CHAMPION, PlayerType.CHAMPION, 
                               PlayerType.HARD, PlayerType.HARD, 
                               PlayerType.MEDIUM, PlayerType.EASY, 
                               PlayerType.EASY };
        assertArrayEquals(settings.getTypes(), array);
    }
}
