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

        Collection<Cell> expandLeft = expandCol(smallestCell.previousCol());
        if (hasAliveCell(expandLeft)) {
            cells.addAll(expandLeft);
        }

        Collection<Cell> expandRight = expandCol(biggestCell.nextCol());
        if (hasAliveCell(expandRight)) {
            cells.addAll(expandRight);
        }

        Collection<Cell> expandTop = expandRow(smallestCell.previousRow());
        if (hasAliveCell(expandTop)) {
            cells.addAll(expandTop);
        }

        Collection<Cell> expandBottom = expandRow(biggestCell.nextRow());
        if (hasAliveCell(expandBottom)) {
            cells.addAll(expandBottom);
        }

        return Universe.of(cells);
    }

    private Collection<Cell> expandRow(int row) {
        return cells.stream()
                .map(Cell::getCol)
                .distinct()
                .map(col -> get(row, col).generate(getNeighbors(row, col)))
                .collect(Collectors.toSet());
    }

    private Collection<Cell> expandCol(int col) {
        return cells.stream()
                .map(Cell::getRow)
                .distinct()
                .map(row -> get(row, col).generate(getNeighbors(row, col)))
                .collect(Collectors.toSet());
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

    private boolean hasAliveCell(Collection<Cell> cells) {
        return cells.stream().anyMatch(Cell::isAlive);
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
