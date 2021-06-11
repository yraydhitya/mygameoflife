package mygameoflife;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
}
