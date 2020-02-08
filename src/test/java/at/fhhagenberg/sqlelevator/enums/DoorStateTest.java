package at.fhhagenberg.sqlelevator.enums;

import at.fhhagenberg.sqelevator.enums.DoorState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoorStateTest {

    @Test
    public void testDoorStateFrom() {
        assertEquals(DoorState.OPEN, DoorState.from(1));
        assertEquals(DoorState.CLOSED, DoorState.from(2));
        assertEquals(DoorState.UNKNOWN, DoorState.from(0));
        assertEquals(DoorState.UNKNOWN, DoorState.from(-1));
        assertEquals(DoorState.UNKNOWN, DoorState.from(3));
    }
}
