package mygameoflife;

import java.util.Collection;
import java.util.Objects;

public abstract class Cell {
    private final int row;
    private final int col;

    public static Cell ofLive(int row, int col) {
        return new LiveCell(row, col);
    }

    public static Cell ofDead(int row, int col) {
        return new DeadCell(row, col);
    }

    protected Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public long aliveCount(Collection<Cell> cells) {
        return cells.stream().filter(Cell::isAlive).count();
    }

    protected abstract boolean isAlive();

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean isPosition(int row, int col) {
        return isRow(row) && isCol(col);
    }

    private boolean isRow(int row) {
        return this.row == row;
    }

    private boolean isCol(int col) {
        return this.col == col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        return col == other.col && row == other.row;
    }
}
