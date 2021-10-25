import objects.Cell;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    @Test
    void should_HaveCorrectAmountOfNeighbours() {
        // Given
        GameOfLife app = new GameOfLife(20, 20);
        app.initialize();
        int cornerNeighbourCount = 3;
        int edgeNeighbourCount = 5;
        int anywhereElseNeighbourCount = 8;

        // When
        List<Cell> actual01 = app.getCellNeighbours(0, 0);
        List<Cell> actual02 = app.getCellNeighbours(19, 19);
        List<Cell> actual03 = app.getCellNeighbours(0, 19);
        List<Cell> actual04 = app.getCellNeighbours(19, 0);
        List<Cell> actual05 = app.getCellNeighbours(9, 19);
        List<Cell> actual06 = app.getCellNeighbours(19, 9);
        List<Cell> actual07 = app.getCellNeighbours(0, 9);
        List<Cell> actual08 = app.getCellNeighbours(9, 0);
        List<Cell> actual09 = app.getCellNeighbours(9, 9);

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
}