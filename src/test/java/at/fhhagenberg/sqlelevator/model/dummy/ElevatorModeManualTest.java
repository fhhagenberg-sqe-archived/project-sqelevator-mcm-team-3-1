package at.fhhagenberg.sqlelevator.model.dummy;

import at.fhhagenberg.sqelevator.enums.ElevatorModeType;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorModeManualTest {
    @Test
    public void testElevatorModeTypeEqualsManual() throws Exception {
        var elevatorModeManual = new ElevatorModeManual();
        assertEquals(ElevatorModeType.MANUAL, elevatorModeManual.getModeType());
    }
}
