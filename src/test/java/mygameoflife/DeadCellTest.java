package mygameoflife;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Collection;
import java.util.Set;

public class DeadCellTest {
    private Cell deadCell = Cell.ofDead(0, 0);

    @Test
    public void generateShouldReturnDeadCellWhenNeighborsContainOneLiveCell() {
        Collection<Cell> neighbors =
                Set.of(Cell.ofLive(0, 1));

        assertEquals(Cell.ofDead(0, 0), deadCell.generate(neighbors));
    }

    @Test
    public void generateShouldReturnDeadCellWhenNeighborsContainTwoLiveCells() {
        Collection<Cell> neighbors =
                Set.of(Cell.ofLive(0, 1), Cell.ofLive(0, 2));

        assertEquals(Cell.ofDead(0, 0), deadCell.generate(neighbors));
    }

    @Test
    public void generateShouldReturnLiveCellWhenNeighborsContainThreeLiveCells() {
        Collection<Cell> neighbors =
                Set.of(Cell.ofLive(0, 1), Cell.ofLive(0, 2), Cell.ofLive(0, 3));

        assertEquals(Cell.ofLive(0, 0), deadCell.generate(neighbors));
    }

    @Test
    public void generateShouldReturnDeadCellWhenNeighborsContainFourLiveCells() {
        Collection<Cell> neighbors =
                Set.of(Cell.ofLive(0, 1), Cell.ofLive(0, 2), Cell.ofLive(0, 3), Cell.ofLive(0, 4));

        assertEquals(Cell.ofDead(0, 0), deadCell.generate(neighbors));
    }
}
