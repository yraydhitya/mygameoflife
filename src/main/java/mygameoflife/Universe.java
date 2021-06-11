package mygameoflife;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Universe {
    private final Collection<Cell> cells;
    private final int smallestRow, biggestRow, smallestCol, biggestCol;

    public static Universe of(Collection<Cell> cells) {
        return new Universe(cells);
    }

    private Universe(Collection<Cell> cells) {
        this.cells = cells;
        this.smallestRow = smallestRow();
        this.biggestRow = biggestRow();
        this.smallestCol = smallestCol();
        this.biggestCol = biggestCol();
    }

    public Cell get(int row, int col) {
        return cells.stream()
                .filter(cell -> cell.isPosition(row, col))
                .findFirst()
                .orElse(Cell.ofDead(row, col));
    }

    public Collection<Cell> getNeighbors(int row, int col) {
        return Set.of(
                get(row - 1, col - 1), get(row - 1, col), get(row - 1, col + 1),
                get(row, col - 1), get(row, col + 1),
                get(row + 1, col - 1), get(row + 1, col), get(row + 1, col + 1)
            );
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = smallestRow; row <= biggestRow; row++) {
            for (int col = smallestCol; col <= biggestCol; col++) {
                stringBuilder.append(get(row, col));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private int smallestRow() {
        Cell firstCell = cells.iterator().next();
        int smallestRow = firstCell.getRow();
        for (Cell cell : cells) {
            if (cell.getRow() < smallestRow) {
                smallestRow = cell.getRow();
            }
        }
        return smallestRow;
    }

    private int biggestRow() {
        Cell firstCell = cells.iterator().next();
        int biggestRow = firstCell.getRow();
        for (Cell cell : cells) {
            if (cell.getRow() > biggestRow) {
                biggestRow = cell.getRow();
            }
        }
        return biggestRow;
    }

    private int smallestCol() {
        Cell firstCell = cells.iterator().next();
        int smallestCol = firstCell.getCol();
        for (Cell cell : cells) {
            if (cell.getCol() < smallestCol) {
                smallestCol = cell.getRow();
            }
        }
        return smallestCol;
    }

    private int biggestCol() {
        Cell firstCell = cells.iterator().next();
        int biggestCol = firstCell.getCol();
        for (Cell cell : cells) {
            if (cell.getCol() > biggestCol) {
                biggestCol = cell.getCol();
            }
        }
        return biggestCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(biggestCol, biggestRow, cells, smallestCol, smallestRow);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Universe other = (Universe) obj;
        return biggestCol == other.biggestCol && biggestRow == other.biggestRow && Objects.equals(cells, other.cells)
                && smallestCol == other.smallestCol && smallestRow == other.smallestRow;
    }
}
