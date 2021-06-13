package mygameoflife;

import java.util.Collection;
import java.util.Objects;

public abstract class Cell implements Comparable<Cell>{
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

    public abstract Cell generate(Collection<Cell> neighbors);

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int previousRow() {
        return this.row - 1;
    }

    public int previousCol() {
        return this.col - 1;
    }

    public int nextRow() {
        return this.row + 1;
    }

    public int nextCol() {
        return this.col + 1;
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
    public int compareTo(Cell o) {
        if (isPosition(o.row, o.col)){
            return 0;
        }

        if (row < o.row) {
            return -1;
        } else {
            if (col < o.col) {
                return -1;
            } else {
                return 1;
            }
        }
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
