import com.carbon.it.exception.TileAlreadyUsedException;
import com.carbon.it.map.Map;
import com.carbon.it.map.Position;
import com.carbon.it.map.element.Empty;
import com.carbon.it.map.element.Mountain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapTest {

    private Map map;

    @BeforeEach
    void init() {
        this.map = new Map(5,5);
        Mountain mountain = new Mountain(new Position(2 ,2));
        this.map.addElement(mountain);
    }

    @Test
    public void addObjectOnAnother(){
        Mountain mountain = new Mountain(new Position(2 ,2));
        Assertions.assertThrows(TileAlreadyUsedException.class, () -> {
            this.map.addElement(mountain);
        });
    }

    @Test
    public void addObject(){
        Position position = new Position(4 ,4);
        Mountain mountain = new Mountain(position);
        this.map.addElement(mountain);
        Assertions.assertEquals(this.map.getElement(position), mountain);
    }

    @Test
    public void removeObject(){
        Position position = new Position(2,2);
        this.map.removeElement(position);
        Assertions.assertTrue(this.map.getElement(position) instanceof Empty);
    }

    @Test
    public void removeObjectOutOfBound(){
        Position position = new Position(7,7);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.map.removeElement(position);
        });
    }

    @Test
    public void getObjectOutOfBound(){
        Position position = new Position(7,7);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.map.getElement(position);
        });
    }

    @Test
    public void addObjectOutOfBound(){
        Position position = new Position(7,7);
        Mountain mountain = new Mountain(position);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.map.addElement(mountain);
        });
    }
}
