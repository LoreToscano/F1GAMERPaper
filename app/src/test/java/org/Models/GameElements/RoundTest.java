package org.Models.GameElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    @Test
    public void testClassicRound() {
        Round round = new Round(4, false);
        assertTrue(round.getRound() == 0);
        assertTrue(round.getRound() == 0);
        assertTrue(round.nextRound() == 1);
        assertTrue(round.nextRound() == 2);
        assertTrue(round.getTime() == 0);
        assertTrue(round.nextRound() == 3);
        assertTrue(round.nextRound() == 0);
        assertTrue(round.getTime() == 1);
    }
}
