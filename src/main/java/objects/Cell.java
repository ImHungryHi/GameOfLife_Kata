package objects;

public class Cell {
    private CellStatus status;

    public Cell(CellStatus status) {
        this.status = status;
    }

    public boolean isLive() {
        return this.status == CellStatus.ALIVE;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
