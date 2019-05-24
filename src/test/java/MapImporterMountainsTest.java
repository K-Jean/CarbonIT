import com.carbon.it.exception.InvalidLineException;
import com.carbon.it.map.MapImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapImporterMountainsTest {

    @Test
    public void tooMuchArgs() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapMountains/tooMuchArgs.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
        });
    }

    @Test
    public void positionOutOfBound() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapMountains/positionOutOfBound.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }

    @Test
    public void positionIsNotANumber() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("wrongMapMountains/positionNotANumber.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }
}
