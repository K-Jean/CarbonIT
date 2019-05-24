import com.carbon.it.exception.InvalidLineException;
import com.carbon.it.map.MapImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapImporterSizeTest {

    @Test
    public void tooMuchArgs() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapSize/tooMuchArgs.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
        });
    }

    @Test
    public void sizeIsNotANumber() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapSize/sizeNotANumber.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }

    @Test
    public void sizeEqualsToZero() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapSize/sizeEqualsToZero.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }
}
