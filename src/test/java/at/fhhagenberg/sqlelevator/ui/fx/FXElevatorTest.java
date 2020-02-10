package at.fhhagenberg.sqlelevator.ui.fx;


import at.fhhagenberg.sqelevator.ui.fx.ElevatorFxGUI;
import at.fhhagenberg.sqelevator.ui.fx.FXElevator;
import at.fhhagenberg.sqlelevator.LocalElevatorStub;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class FXElevatorTest {

    private LocalElevatorStub localElevatorStub;
    private FXElevator fxElevator;


    @Start
    public void start(Stage stage) throws Exception {
        localElevatorStub = new LocalElevatorStub();
        fxElevator = new FXElevator(localElevatorStub, 5, 500);

        Scene scene = new Scene(fxElevator, 1080, 600);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testPopulateHeader(FxRobot robot) {
        var nameLabel = (Label) robot.lookup("#ElevatorNameLabel").queryAll().iterator().next();
        var doorStateLabel = (Label) robot.lookup("#ElevatorDoorStateLabel").queryAll().iterator().next();
        var directionLabel = (Label) robot.lookup("#ElevatorDirectionLabel").queryAll().iterator().next();
        var elevatorMode = (Label) robot.lookup("#ElevatorModeLabel").queryAll().iterator().next();

        assertEquals("E "+ localElevatorStub.getElevatorNumber(), nameLabel.getText());
        assertEquals(localElevatorStub.getDoorState().name(), doorStateLabel.getText());
        assertEquals(localElevatorStub.getDirection().name(), directionLabel.getText());
        assertEquals(localElevatorStub.getMode().getModeType().name(), elevatorMode.getText());
    }


}
