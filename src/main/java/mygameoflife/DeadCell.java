package mygameoflife;

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
}
