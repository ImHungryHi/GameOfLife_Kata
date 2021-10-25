import objects.Cell;
import objects.CellStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    @Test
    void should_HaveCorrectAmountOfNeighbours() {
        // Given
        GameOfLife app = new GameOfLife(20, 20);
        app.initialize();
        Cell[][] grid = app.getGrid();
        int cornerNeighbourCount = 3;
        int edgeNeighbourCount = 5;
        int anywhereElseNeighbourCount = 8;

        // When
        List<Cell> actual01 = app.getCellNeighbours(0, 0, grid);
        List<Cell> actual02 = app.getCellNeighbours(19, 19, grid);
        List<Cell> actual03 = app.getCellNeighbours(0, 19, grid);
        List<Cell> actual04 = app.getCellNeighbours(19, 0, grid);
        List<Cell> actual05 = app.getCellNeighbours(9, 19, grid);
        List<Cell> actual06 = app.getCellNeighbours(19, 9, grid);
        List<Cell> actual07 = app.getCellNeighbours(0, 9, grid);
        List<Cell> actual08 = app.getCellNeighbours(9, 0, grid);
        List<Cell> actual09 = app.getCellNeighbours(9, 9, grid);

        // Then
        assertAll(
                () -> assertEquals(cornerNeighbourCount, actual01.size()),
                () -> assertEquals(cornerNeighbourCount, actual02.size()),
                () -> assertEquals(cornerNeighbourCount, actual03.size()),
                () -> assertEquals(cornerNeighbourCount, actual04.size()),
                () -> assertEquals(edgeNeighbourCount, actual05.size()),
                () -> assertEquals(edgeNeighbourCount, actual06.size()),
                () -> assertEquals(edgeNeighbourCount, actual07.size()),
                () -> assertEquals(edgeNeighbourCount, actual08.size()),
                () -> assertEquals(anywhereElseNeighbourCount, actual09.size())
        );
    }

    @Test
    void should_HaveCorrectAmountOfLivingNeighbours() {
        // Given
        GameOfLife app = new GameOfLife(10, 10);
        Cell[][] grid = getStaticGrid();
        app.setGrid(grid);
        // r0c1a2n, r1c1d3n, r3c1d2n, r3c2d0n, r1c7a1n, r7c0a4n (row+n;col+m;alive/dead;live neighbours)
        int expected01 = 2;
        int expected02 = 3;
        int expected03 = 2;
        int expected04 = 0;
        int expected05 = 1;
        int expected06 = 4;

        // When
        int actual01 = app.countLivingNeighbours(0, 1, grid);
        int actual02 = app.countLivingNeighbours(1, 1, grid);
        int actual03 = app.countLivingNeighbours(3, 1, grid);
        int actual04 = app.countLivingNeighbours(3, 2, grid);
        int actual05 = app.countLivingNeighbours(1, 7, grid);
        int actual06 = app.countLivingNeighbours(7, 0, grid);

        // Then
        assertAll(
                () -> assertEquals(expected01, actual01),
                () -> assertEquals(expected02, actual02),
                () -> assertEquals(expected03, actual03),
                () -> assertEquals(expected04, actual04),
                () -> assertEquals(expected05, actual05),
                () -> assertEquals(expected06, actual06)
        );
    }

    @Test
    void should_HaveCorrectAmountOfLivingNeighbours_OnNextStep() {
        // Given
        GameOfLife app = new GameOfLife(10, 10);
        Cell[][] grid = getStaticGrid();
        app.setGrid(grid);
        app.nextStep();
        // r0c1a2n, r1c1d3n, r3c1d2n, r3c2d0n, r1c7a1n, r7c0a4n (row+n;col+m;alive/dead;live neighbours)
        CellStatus expected01 = CellStatus.ALIVE;
        CellStatus expected02 = CellStatus.ALIVE;
        CellStatus expected03 = CellStatus.DEAD;
        CellStatus expected04 = CellStatus.DEAD;
        CellStatus expected05 = CellStatus.DEAD;
        CellStatus expected06 = CellStatus.DEAD;

        // When
        CellStatus actual01 = grid[0][1].getStatus();
        CellStatus actual02 = grid[1][1].getStatus();
        CellStatus actual03 = grid[3][1].getStatus();
        CellStatus actual04 = grid[3][2].getStatus();
        CellStatus actual05 = grid[1][7].getStatus();
        CellStatus actual06 = grid[7][0].getStatus();

        // Then
        assertAll(
                () -> assertEquals(expected01, actual01),
                () -> assertEquals(expected02, actual02),
                () -> assertEquals(expected03, actual03),
                () -> assertEquals(expected04, actual04),
                () -> assertEquals(expected05, actual05),
                () -> assertEquals(expected06, actual06)
        );
    }

    private Cell[][] getStaticGrid() {
        return new Cell[][] {
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) },
                { new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE), new Cell(CellStatus.ALIVE), new Cell(CellStatus.DEAD), new Cell(CellStatus.ALIVE) }
        };
    }
}