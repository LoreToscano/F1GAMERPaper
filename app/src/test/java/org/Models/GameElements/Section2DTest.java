package org.Models.GameElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class Section2DTest {

    @Test
    public void testGetters() {
        Section2D section = new Section2D(0, 
            new Segment2D(new Coordinate2D(0, 0), new Coordinate2D(10, 0)),
            new Segment2D(new Coordinate2D(0, 10), new Coordinate2D(10, 10)));
        assertEquals(section.getDistance(), 10);
        assertEquals(section.getDirection(), Direction2D.UP);
        assertEquals(section.getAreaSection(), 100);
        assertEquals(section.clone(0), section);
        Section2D next = new Section2D(0, 
            new Segment2D(new Coordinate2D(0, 10), new Coordinate2D(10, 10)),
            new Segment2D(new Coordinate2D(0, 20), new Coordinate2D(10, 20)));
        assertEquals(section.getNext(0), next);
        assertEquals(section.getMaxX(), 10);
        assertEquals(section.getMaxY(), 10);
        assertEquals(section.getMinX(), 0);
        assertEquals(section.getMinY(), 0);
    }

    @Test
    public void testIntoSection() {
        Section2D section = new Section2D(0, 
            new Segment2D(new Coordinate2D(0, 0), new Coordinate2D(5, 0)),
            new Segment2D(new Coordinate2D(0, 30), new Coordinate2D(5, 30)));
        assertTrue(section.isIntoSection(new Coordinate2D(1, 1)));
        assertFalse(section.isIntoSection(new Coordinate2D(7, 1)));
    }

    @Test
    public void testGetIntoSectionPositions(){
        Section2D section = new Section2D(0, 
            new Segment2D(new Coordinate2D(0, 0), new Coordinate2D(10, 0)),
            new Segment2D(new Coordinate2D(0, 10), new Coordinate2D(10, 10)));
        List<List<Coordinate2D>> positions = section.getIntoSectionPositions();
        assertTrue(positions.size() == 11);
        assertEquals(positions.get(0).get(0), new Coordinate2D(0, 10));
    }

}
