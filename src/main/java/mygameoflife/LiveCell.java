package mygameoflife;

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
}
