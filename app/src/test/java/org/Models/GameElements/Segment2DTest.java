package org.Models.GameElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Segment2DTest {

    @Test
    public void testDimension() {
        Segment2D segment = new Segment2D(new Coordinate2D(4, 0), new Coordinate2D(0, 3));
        assertTrue(segment.dimensionSegment() == 5);
    }

    @Test
    public void testGetters() {
        Segment2D segment = new Segment2D(new Coordinate2D(0, 10), new Coordinate2D(10, 0));
        assertEquals(segment, segment.clone());
        assertEquals(segment.getCoordinateMiddle(), new Coordinate2D(5, 5));
    }

    @Test
    public void testMinMax() {
        Segment2D segment = new Segment2D(new Coordinate2D(10, -7), new Coordinate2D(-10, 7));
        assertTrue(segment.getMaxX() == 10);
        assertTrue(segment.getMinX() == -10);
        assertTrue(segment.getMaxY() == 7);
        assertTrue(segment.getMinY() == -7);
    }
}
