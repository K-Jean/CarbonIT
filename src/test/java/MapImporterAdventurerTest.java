import com.carbon.it.exception.InvalidLineException;
import com.carbon.it.map.MapImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapImporterAdventurerTest {

    @Test
    public void tooMuchArgs() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapAdventurer/tooMuchArgs.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
        });
    }

    @Test
    public void positionOutOfBound() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapAdventurer/positionOutOfBound.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }

    @Test
    public void directionNotExist() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapAdventurer/wrongDirection.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }

    @Test
    public void actionNotExist() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapAdventurer/wrongAction.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }

    @Test
    public void positionIsNotANumber() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapAdventurer/positionNotANumber.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }
}
