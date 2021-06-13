package mygameoflife;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Universe generate() {
        Collection<Cell> cells = new HashSet<>();
        for (int row = smallestRow; row <= biggestRow; row++) {
            for (int col = smallestCol; col <= biggestCol; col++) {
                cells.add(get(row, col).generate(getNeighbors(row, col)));
            }
        }

        Collection<Cell> expandLeft = expandLeft();
        if (!expandLeft.isEmpty()) {
            cells.addAll(expandLeft);
        }

        Collection<Cell> expandRight = expandRight();
        if (!expandRight.isEmpty()) {
            cells.addAll(expandRight);
        }

        Collection<Cell> expandTop = expandTop();
        if (!expandTop.isEmpty()) {
            cells.addAll(expandTop);
        }

        Collection<Cell> expandBottom = expandBottom();
        if (!expandBottom.isEmpty()) {
            cells.addAll(expandBottom);
        }

        return Universe.of(cells);
    }

    private Collection<Cell> expandLeft() {
        Collection<Cell> cells = new HashSet<>();
        for (int row = smallestRow; row <= biggestRow; row++) {
            cells.add(get(row, smallestCol - 1).generate(getNeighbors(row, smallestCol - 1)));
        }
        return aliveCells(cells);
    }

    private Collection<Cell> expandRight() {
        Collection<Cell> cells = new HashSet<>();
        for (int row = smallestRow; row <= biggestRow; row++) {
            cells.add(get(row, biggestCol + 1).generate(getNeighbors(row, biggestCol + 1)));
        }
        return aliveCells(cells);
    }

    private Collection<Cell> expandTop() {
        Collection<Cell> cells = new HashSet<>();
        for (int col = smallestCol; col <= biggestCol; col++) {
            cells.add(get(smallestRow - 1, col).generate(getNeighbors(smallestRow - 1, col)));
        }
        return aliveCells(cells);
    }

    private Collection<Cell> expandBottom() {
        Collection<Cell> cells = new HashSet<>();
        for (int col = smallestCol; col <= biggestCol; col++) {
            cells.add(get(biggestRow + 1, col).generate(getNeighbors(biggestRow + 1, col)));
        }
        return aliveCells(cells);
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
        return cells.stream().map(Cell::getRow).min(Comparator.naturalOrder()).get();
    }

    private int biggestRow() {
        return cells.stream().map(Cell::getRow).max(Comparator.naturalOrder()).get();
    }

    private int smallestCol() {
        return cells.stream().map(Cell::getCol).min(Comparator.naturalOrder()).get();
    }

    private int biggestCol() {
        return cells.stream().map(Cell::getCol).max(Comparator.naturalOrder()).get();
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

    private Set<Cell> aliveCells(Collection<Cell> cells) {
        return cells.stream().filter(Cell::isAlive).collect(Collectors.toSet());
    }
}
