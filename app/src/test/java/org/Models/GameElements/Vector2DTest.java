package org.Models.GameElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2DTest {

    @Test
    public void testDirection() {
        Vector2D vector = new Vector2D(20, 0);
        assertTrue(vector.getDirection() == Direction2D.RIGHT);
        Vector2D vector2 = new Vector2D(Direction2D.DOWN, 5);
        assertEquals(vector2, new Vector2D(0, -5));
    }

    @Test
    public void testSum() {
        Vector2D vector1 = new Vector2D(7, 2);
        Vector2D vector2 = new Vector2D(3, 4);
        assertEquals(vector1.sum(vector2), new Vector2D(10, 6));
    }
}
