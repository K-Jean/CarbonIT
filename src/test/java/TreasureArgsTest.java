import com.carbon.it.SearchTreasure;
import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

public class TreasureArgsTest {

    @TempDir
    static Path tempDir;

    @Test
    @ExpectSystemExitWithStatus(0)
    public void runApplicationWithTwoArgs(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String inputFile = classLoader.getResource("simpleMap.txt").getFile();
        String outFile = tempDir.resolve("output.txt").toString();
        System.out.println(outFile);
        SearchTreasure.main(new String[]{inputFile, "output.txt"});
    }

    @Test
    @ExpectSystemExitWithStatus(1)
    public void runApplicationWithLessThanTwoArgs(){
        SearchTreasure.main(new String[]{"simpleMap.txt"});
    }

    @Test
    @ExpectSystemExitWithStatus(1)
    public void runApplicationWithMoreThanTwoArgs(){
        SearchTreasure.main(new String[]{"simpleMap.txt", "output.txt", "tooMuchArgs"});
    }
}
