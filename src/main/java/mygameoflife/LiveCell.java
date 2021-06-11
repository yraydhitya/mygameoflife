package mygameoflife;

import java.util.Collection;

public class LiveCell extends Cell {

    protected LiveCell(int row, int col) {
        super(row, col);
    }

    @Override
    protected boolean isAlive() {
        return true;
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public Cell generate(Collection<Cell> neighbors) {
        long aliveCount = aliveCount(neighbors);

        return isUnderpopulation(aliveCount) || isOverpopulation(aliveCount)
                ? Cell.ofDead(getRow(), getCol())
                : Cell.ofLive(getRow(), getCol());
    }

    protected boolean isUnderpopulation(long aliveCount) {
        return aliveCount < 2;
    }

    protected boolean isOverpopulation(long aliveCount) {
        return aliveCount > 3;
    }
}
