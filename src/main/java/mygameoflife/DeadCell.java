package mygameoflife;

public class DeadCell extends Cell {

    protected DeadCell(int row, int col) {
        super(row, col);
    }

    @Override
    public String toString() {
        return " ";
    }
}
