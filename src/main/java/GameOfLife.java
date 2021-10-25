import objects.*;
import java.util.*;

public class GameOfLife {
    private final int width;
    private final int height;
    private Cell[][] grid;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void initialize() {
        this.grid = new Cell[this.width][this.width];
        Random random = new Random();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (random.nextBoolean()) {
                    grid[x][y] = new Cell(CellStatus.ALIVE);
                }
                else {
                    grid[x][y] = new Cell(CellStatus.DEAD);
                }
            }
        }
    }

    public void nextStep() {
        Cell[][] gridClone = cloneGrid();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int liveNeighbours = countLivingNeighbours(x, y, gridClone);

                if (liveNeighbours == 3) {
                    grid[x][y].setStatus(CellStatus.ALIVE);
                }
                else if (liveNeighbours != 2) {
                    grid[x][y].setStatus(CellStatus.DEAD);
                }
                // else { remains the same }
            }
        }
    }

    public int countLivingNeighbours(int row, int column, Cell[][] grid) {
        int liveCount = 0;
        List<Cell> directNeighbours = getCellNeighbours(row, column, grid);

        for (Cell neighbour : directNeighbours) {
            if (neighbour.isLive()) {
                liveCount++;
            }
        }

        return liveCount;
    }

    public List<Cell> getCellNeighbours(int row, int column, Cell[][] grid) {
        List<Cell> result = new ArrayList<>();

        for (int x = row - 1; x < (row + 2); x++) {
            for (int y = column - 1; y < (column + 2); y++) {
                boolean cellIsInGrid = x >= 0 && y >= 0 &&
                        x < width && y < height;
                boolean cellIsNotItself = !(x == row && y == column);

                if (cellIsInGrid && cellIsNotItself) {
                    result.add(grid[x][y]);
                }
            }
        }

        return result;
    }

    private Cell[][] cloneGrid() {
        Cell[][] clone = new Cell[this.width][this.height];

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                clone[x][y] = new Cell(this.grid[x][y].getStatus());
            }
        }

        return clone;
    }

    // For testing purposes only
    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return this.grid;
    }
}
