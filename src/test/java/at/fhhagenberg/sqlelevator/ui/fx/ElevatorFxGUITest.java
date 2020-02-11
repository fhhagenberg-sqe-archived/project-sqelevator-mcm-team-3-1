package at.fhhagenberg.sqlelevator.ui.fx;

import at.fhhagenberg.sqelevator.LogManager;
import at.fhhagenberg.sqelevator.model.Environment;
import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.ui.fx.ElevatorFxGUI;
import at.fhhagenberg.sqelevator.ui.fx.FXElevatorCallView;
import at.fhhagenberg.sqlelevator.ElevatorStub;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class ElevatorFxGUITest {

    private  ElevatorFxGUI elevatorFxGUI;

    @Start
    public void start(Stage stage) throws Exception {
        elevatorFxGUI = new ElevatorFxGUI();
        elevatorFxGUI.setTestingMode(true);
        elevatorFxGUI.initElevatorInterface(new ElevatorStub());
        elevatorFxGUI.start(stage);
    }

    @Test
    public void TestInit(FxRobot robot) throws InterruptedException {
        assertTrue(elevatorFxGUI.getTestingModeActive());

        var elevatorInformation = (HBox) robot.lookup("#GeneralElevatorInformation").queryAll().iterator().next();
        var elevatorArea = (HBox) robot.lookup("#ElevatorArea").queryAll().iterator().next();
        var floorCallArea = (HBox) robot.lookup("#FloorCallArea").queryAll().iterator().next();

        assertEquals(1, elevatorInformation.getChildren().size());
        assertEquals(3, elevatorArea.getChildren().size());
        assertEquals(1, floorCallArea.getChildren().size());
    }
}
