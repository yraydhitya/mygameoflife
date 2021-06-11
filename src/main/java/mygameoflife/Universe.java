package mygameoflife;

import java.util.Collection;

public class Universe {
    private final Collection<Cell> cells;

    public static Universe of(Collection<Cell> cells) {
        return new Universe(cells);
    }

    private Universe(Collection<Cell> cells) {
        this.cells = cells;
    }

    public Cell get(int row, int col) {
        return cells.stream()
                .filter(cell -> cell.isPosition(row, col))
                .findFirst()
                .orElse(Cell.ofDead(row, col));
    }
}
