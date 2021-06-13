package mygameoflife;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Universe {
    private final Collection<Cell> cells;
    private final Cell smallestCell, biggestCell;

    public static Universe of(Collection<Cell> cells) {
        return new Universe(cells);
    }

    private Universe(Collection<Cell> cells) {
        this.cells = cells;
        this.smallestCell = smallestCell();
        this.biggestCell = biggestCell();
    }

    public Universe generate() {
        Collection<Cell> cells = new HashSet<>();
        for (int row = smallestCell.getRow(); row <= biggestCell.getRow(); row++) {
            for (int col = smallestCell.getCol(); col <= biggestCell.getCol(); col++) {
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
        for (int row = smallestCell.getRow(); row <= biggestCell.getRow(); row++) {
            cells.add(get(row, smallestCell.getCol() - 1).generate(getNeighbors(row, smallestCell.getCol() - 1)));
        }
        return aliveCells(cells);
    }

    private Collection<Cell> expandRight() {
        Collection<Cell> cells = new HashSet<>();
        for (int row = smallestCell.getRow(); row <= biggestCell.getRow(); row++) {
            cells.add(get(row, biggestCell.getRow() + 1).generate(getNeighbors(row, biggestCell.getRow() + 1)));
        }
        return aliveCells(cells);
    }

    private Collection<Cell> expandTop() {
        Collection<Cell> cells = new HashSet<>();
        for (int col = smallestCell.getCol(); col <= biggestCell.getCol(); col++) {
            cells.add(get(smallestCell.getRow() - 1, col).generate(getNeighbors(smallestCell.getRow() - 1, col)));
        }
        return aliveCells(cells);
    }

    private Collection<Cell> expandBottom() {
        Collection<Cell> cells = new HashSet<>();
        for (int col = smallestCell.getCol(); col <= biggestCell.getCol(); col++) {
            cells.add(get(biggestCell.getRow() + 1, col).generate(getNeighbors(biggestCell.getRow() + 1, col)));
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
        for (int row = smallestCell.getRow(); row <= biggestCell.getRow(); row++) {
            for (int col = smallestCell.getCol(); col <= biggestCell.getCol(); col++) {
                stringBuilder.append(get(row, col));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private Cell smallestCell() {
        return cells.stream().min(Comparator.naturalOrder()).get();
    }

    private Cell biggestCell() {
        return cells.stream().max(Comparator.naturalOrder()).get();
    }

    private Set<Cell> aliveCells(Collection<Cell> cells) {
        return cells.stream().filter(Cell::isAlive).collect(Collectors.toSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(biggestCell, cells, smallestCell);
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
        return Objects.equals(biggestCell, other.biggestCell) && Objects.equals(cells, other.cells)
                && Objects.equals(smallestCell, other.smallestCell);
    }
}
