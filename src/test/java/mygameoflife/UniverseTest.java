package mygameoflife;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Collection;
import java.util.Set;

public class UniverseTest {

    @Test
    public void getShouldReturnLiveCell() {
        Universe universe = Universe.of(Set.of(Cell.ofLive(0, 0)));

        assertEquals(Cell.ofLive(0, 0), universe.get(0, 0));
    }

    @Test
    public void getShouldReturnDeadCell() {
        Universe universe = Universe.of(Set.of(Cell.ofDead(0, 0)));

        assertEquals(Cell.ofDead(0, 0), universe.get(0, 0));
    }

    @Test
    public void getShouldReturnDeadCellWhenNotFound() {
        Universe universe = Universe.of(Set.of(Cell.ofLive(0, 0)));

        assertEquals(Cell.ofDead(1, 1), universe.get(1, 1));
    }

    @Test
    public void toStringOneCell() {
        Universe universe =
                Universe.of(Set.of(Cell.ofLive(0, 0)));

        assertEquals("X\n", universe.toString());
    }

    @Test
    public void toStringOneRow() {
        Universe universe =
                Universe.of(Set.of(Cell.ofLive(0, 0), Cell.ofLive(0, 1), Cell.ofLive(0, 2)));

        assertEquals("XXX\n", universe.toString());
    }

    @Test
    public void toStringOneRowWithDeadCells() {
        Universe universe =
                Universe.of(Set.of(Cell.ofLive(0, 0), Cell.ofLive(0, 1), Cell.ofLive(0, 5)));

        assertEquals("XX   X\n", universe.toString());
    }

    @Test
    public void toStringOneCol() {
        Universe universe =
                Universe.of(Set.of(Cell.ofLive(0, 0), Cell.ofLive(1, 0), Cell.ofLive(2, 0)));

        assertEquals("X\nX\nX\n", universe.toString());
    }

    @Test
    public void toStringOneColWithDeadCells() {
        Universe universe =
                Universe.of(Set.of(Cell.ofLive(0, 0), Cell.ofLive(1, 0), Cell.ofLive(5, 0)));

        assertEquals("X\nX\n \n \n \nX\n", universe.toString());
    }

    @Test
    public void toStringMatrix() {
        Universe universe =
                Universe.of(Set.of(
                        Cell.ofLive(0, 0), Cell.ofLive(0, 1), Cell.ofLive(0, 2),
                        Cell.ofLive(1, 0), Cell.ofLive(1, 1), Cell.ofLive(1, 2),
                        Cell.ofLive(2, 0), Cell.ofLive(2, 1), Cell.ofLive(2, 2)
                ));

        assertEquals("XXX\nXXX\nXXX\n", universe.toString());
    }

    @Test
    public void toStringMatrixWithDeadCells() {
        Universe universe =
                Universe.of(Set.of(
                        Cell.ofLive(0, 0), Cell.ofLive(0, 1),
                        Cell.ofLive(1, 0), Cell.ofLive(1, 2),
                        Cell.ofLive(2, 1), Cell.ofLive(2, 2)
                ));

        assertEquals("XX \nX X\n XX\n", universe.toString());
    }

    @Test
    public void getNeighbors() {
        Universe universe = Universe.of(Set.of(Cell.ofLive(0, 0)));

        Collection<Cell> expectedNeighbors =
                Set.of(
                        Cell.ofDead(-1, -1), Cell.ofDead(-1, 0), Cell.ofDead(-1, 1),
                        Cell.ofDead(0, -1), Cell.ofDead(0, 1),
                        Cell.ofDead(1, -1), Cell.ofDead(1, 0), Cell.ofDead(1, 1)
                );

        assertEquals(expectedNeighbors, universe.getNeighbors(0, 0));
    }

    @Test
    public void equals() {
        assertEquals(UniverseFixture.block(), UniverseFixture.block());
        assertEquals(UniverseFixture.beeHive(), UniverseFixture.beeHive());
        assertEquals(UniverseFixture.blinker1(), UniverseFixture.blinker1());
        assertEquals(UniverseFixture.blinker2(), UniverseFixture.blinker2());
    }

    @Test
    public void generate() {
        assertEquals(UniverseFixture.block(), UniverseFixture.block().generate());
        assertEquals(UniverseFixture.beeHive(), UniverseFixture.beeHive().generate());
        assertEquals(UniverseFixture.blinker2(), UniverseFixture.blinker1().generate());
        assertEquals(UniverseFixture.blinker1(), UniverseFixture.blinker2().generate());
        assertEquals(UniverseFixture.glider2(), UniverseFixture.glider1().generate());
    }

    @Test
    public void expandTopBottom() {
        Universe expectedExpandedBlinker = Universe.of(Set.of(
                Cell.ofDead(-1, 0), Cell.ofLive(-1, 1), Cell.ofDead(-1, 2),
                Cell.ofDead(0, 0), Cell.ofLive(0, 1), Cell.ofDead(0, 2),
                Cell.ofDead(1, 0), Cell.ofLive(1, 1), Cell.ofDead(1, 2)
            ));

        assertEquals(expectedExpandedBlinker, UniverseFixture.tightBlinker1().generate());
    }

    @Test
    public void expandLeftRight() {
        Universe expectedExpandedBlinker = Universe.of(Set.of(
                Cell.ofDead(0, -1), Cell.ofDead(0, 0), Cell.ofDead(0, 1),
                Cell.ofLive(1, -1), Cell.ofLive(1, 0), Cell.ofLive(1, 1),
                Cell.ofDead(2, -1), Cell.ofDead(2, 0), Cell.ofDead(2, 1)
            ));

        assertEquals(expectedExpandedBlinker, UniverseFixture.tightBlinker2().generate());
    }
}
