import com.carbon.it.exception.InvalidLineException;
import com.carbon.it.map.MapImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MapImporterTest {


    @Test
    public void mapWithNoTreasure() throws IOException, InvalidLineException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("MapWithNoTreasure.txt").getFile();
        MapImporter parser = new MapImporter();
        parser.importTreasureFile(inputFile);
        Assertions.assertTrue(parser.getTreasures().isEmpty());
        Assertions.assertEquals(parser.getAdventurers().size(), 1);
        Assertions.assertEquals(parser.getMountains().size(), 1);
    }

    @Test
    public void mapWithNoMountains() throws IOException, InvalidLineException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("MapWithNoMountains.txt").getFile();
        MapImporter parser = new MapImporter();
        parser.importTreasureFile(inputFile);
        Assertions.assertTrue(parser.getMountains().isEmpty());
        Assertions.assertEquals(parser.getAdventurers().size(), 1);
        Assertions.assertEquals(parser.getTreasures().size(), 2);
    }

    @Test
    public void mapWithNoAdventurers() throws IOException, InvalidLineException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("MapWithNoAdventurers.txt").getFile();
        MapImporter parser = new MapImporter();
        parser.importTreasureFile(inputFile);
        Assertions.assertTrue(parser.getAdventurers().isEmpty());
        Assertions.assertEquals(parser.getMountains().size(), 2);
        Assertions.assertEquals(parser.getTreasures().size(), 2);
    }

    @Test
    public void mapWithoutC() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("MapWithoutC.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }

    @Test
    public void mapWithUnknownLine() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("mapWrongLine.txt").getFile();
        MapImporter parser = new MapImporter();
        Assertions.assertThrows(InvalidLineException.class, () -> {
            parser.importTreasureFile(inputFile);
            parser.buildMap();
        });
    }

}
