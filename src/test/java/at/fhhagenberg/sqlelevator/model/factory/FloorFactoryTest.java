package at.fhhagenberg.sqlelevator.model.factory;

import at.fhhagenberg.sqelevator.model.factory.FloorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FloorFactoryTest {
    @Test
    public void testCreateFloor() {
        FloorFactory factory = new FloorFactory();
        var floor = factory.createFloor(2);
        assertEquals(2, floor.getFloorNumber());
        assertFalse(floor.getFloorButtonDown());
        assertFalse(floor.getFloorButtonUp());
    }
}
