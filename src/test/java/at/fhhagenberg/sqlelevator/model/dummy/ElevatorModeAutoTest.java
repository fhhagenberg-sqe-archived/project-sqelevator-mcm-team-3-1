package at.fhhagenberg.sqlelevator.model.dummy;

import at.fhhagenberg.sqelevator.enums.ElevatorModeType;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorModeAutoTest {

    @Test
    public void testElevatorModeTypeEqualsAuto() throws Exception {
        var elevatorModeAuto = new ElevatorModeAuto();
        assertEquals(ElevatorModeType.AUTOMATIC, elevatorModeAuto.getModeType());
    }
}
