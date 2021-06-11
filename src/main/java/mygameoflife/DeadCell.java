package mygameoflife;

import java.util.Collection;

public class DeadCell extends Cell {

    protected DeadCell(int row, int col) {
        super(row, col);
    }

    @Override
    protected boolean isAlive() {
        return false;
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public Cell generate(Collection<Cell> neighbors) {
        long aliveCount = aliveCount(neighbors);

        return shouldReproduce(aliveCount)
                ? Cell.ofLive(getRow(), getCol())
                : Cell.ofDead(getRow(), getCol());
    }

    private boolean shouldReproduce(long aliveCount) {
        return 3 == aliveCount;
    }
}
