import com.carbon.it.Manager;
import com.carbon.it.enums.Action;
import com.carbon.it.enums.Direction;
import com.carbon.it.map.Map;
import com.carbon.it.map.Position;
import com.carbon.it.map.element.Adventurer;
import com.carbon.it.map.element.Mountain;
import com.carbon.it.map.element.Treasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ManagerTest {


    @Test
    public void moveForwardToSouth(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.S, Arrays.asList(Action.A), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(2,3)) instanceof Adventurer);
    }

    @Test
    public void moveForwardToNorth(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.N, Arrays.asList(Action.A), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(2,1)) instanceof Adventurer);
    }

    @Test
    public void moveForwardToEast(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.E, Arrays.asList(Action.A), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(3,2)) instanceof Adventurer);
    }

    @Test
    public void moveForwardToWest(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.O, Arrays.asList(Action.A), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(1,2)) instanceof Adventurer);
    }

    @Test
    public void moveBackwardToSouth(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.S, Arrays.asList(Action.R), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(2,1)) instanceof Adventurer);
    }

    @Test
    public void moveBackwardToNorth(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.N, Arrays.asList(Action.R), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(2,3)) instanceof Adventurer);
    }

    @Test
    public void moveBackwardToEast(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.E, Arrays.asList(Action.R), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(1,2)) instanceof Adventurer);
    }

    @Test
    public void moveBackwardToWest(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.O, Arrays.asList(Action.R), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(3,2)) instanceof Adventurer);
    }

    @Test
    public void moveToASolidObject(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.O, Arrays.asList(Action.A), 0);
        map.addElement(new Mountain(new Position(1,2)));
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(1,2)) instanceof Mountain);
        Assertions.assertTrue(map.getElement(new Position(2,2)) instanceof Adventurer);
    }

    @Test
    public void moveToATreasureObject(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(2 ,2), "marcel", Direction.O, Arrays.asList(Action.A), 0);
        map.addElement(new Treasure(new Position(1,2), 3));
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(1,2)) instanceof Adventurer);
        Assertions.assertEquals(map.getElement(new Position(1,2)).getLoot(), 3);
    }

    @Test
    public void moveOutOfBound(){
        Map map = new Map(5,5);
        Adventurer adventurer = new Adventurer(new Position(4 ,4), "marcel", Direction.E, Arrays.asList(Action.A), 0);
        map.addElement(adventurer);
        Manager manager = new Manager(map, Arrays.asList(adventurer));
        map = manager.startAdventure();
        Assertions.assertTrue(map.getElement(new Position(4,4)) instanceof Adventurer);
    }
}
