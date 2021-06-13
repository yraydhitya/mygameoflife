package mygameoflife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Collection;
import java.util.Set;

public class CellTest {

    @Test
    public void aliveCoundShouldReturnTwoWhenNeighborsContainTwoLiveCells() {
        Collection<Cell> neighbors =
                Set.of(Cell.ofLive(0, 0), Cell.ofLive(0, 1), Cell.ofDead(0, 0), Cell.ofDead(0, 1));

        assertEquals(2, Cell.ofLive(0, 0).aliveCount(neighbors));
    }

    @Test
    public void isAliveShouldReturnTrueWhenLiveCell() {
        assertTrue(Cell.ofLive(0, 0).isAlive());
    }

    @Test
    public void isAliveShouldReturnFalseWhenDeadCell() {
        assertFalse(Cell.ofDead(0, 0).isAlive());
    }

    @Test
    public void isPositionWhenRowAndColEqualShouldReturnTrue() {
        assertTrue(Cell.ofLive(0, 0).isPosition(0, 0));
    }

    @Test
    public void isPositionWhenRowOrColNotEqualShouldReturnFalse() {
        assertFalse(Cell.ofLive(0, 0).isPosition(1, 0));
    }

    @Test
    public void twoLiveCellsWithSamePositionShouldBeEqual() {
        assertEquals(Cell.ofLive(0, 0), Cell.ofLive(0, 0));
    }

    @Test
    public void twoLiveCellsWithDifferentPositionShouldNotBeEqual() {
        assertNotEquals(Cell.ofLive(0, 0), Cell.ofLive(1, 1));
    }

    @Test
    public void twoDeadCellsWithSamePositionShouldBeEqual() {
        assertEquals(Cell.ofDead(0, 0), Cell.ofDead(0, 0));
    }

    @Test
    public void twoDeadCellsWithDifferentPositionShouldNotBeEqual() {
        assertNotEquals(Cell.ofDead(0, 0), Cell.ofDead(1, 1));
    }

    @Test
    public void liveCellAndDeadCellShouldNotBeEqual() {
        assertNotEquals(Cell.ofLive(0, 0), Cell.ofDead(0, 0));
    }

    @Test
    public void liveAndDeadCellToStringShouldNotBeEqual() {
        assertNotEquals(Cell.ofLive(0, 0).toString(), Cell.ofDead(0, 0).toString());
    }

    @Test
    public void compareToEqualPositionShouldReturnZero() {
        assertEquals(0, Cell.ofLive(0, 0).compareTo(Cell.ofLive(0, 0)));
    }

    @Test
    public void compareToSmallerRowShouldReturnNegativeOne() {
        assertEquals(-1, Cell.ofLive(0, 0).compareTo(Cell.ofLive(1, 0)));
    }

    @Test
    public void compareToBiggerRowShouldReturnPositiveOne() {
        assertEquals(1, Cell.ofLive(0, 0).compareTo(Cell.ofLive(-1, 0)));
    }

    @Test
    public void compareToEqualRowSmallerColShouldReturnNegativeOne() {
        assertEquals(-1, Cell.ofLive(0, 0).compareTo(Cell.ofLive(0, 1)));
    }

    @Test
    public void compareToEqualRowBiggerColShouldReturnPositiveOne() {
        assertEquals(1, Cell.ofLive(0, 0).compareTo(Cell.ofLive(0, -1)));
    }
}
