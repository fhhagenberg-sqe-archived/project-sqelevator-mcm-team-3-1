package at.fhhagenberg.sqlelevator.ui.fx;

import at.fhhagenberg.sqelevator.model.Environment;
import at.fhhagenberg.sqelevator.ui.fx.FXGeneralElevatorInformation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class FXGeneralElevatorInformationTest {

    Environment environment;
    final Object lock = new Object();

    @Start
    public void start(Stage stage) throws Exception {
        environment = new Environment();
        var fxGeneralElevatorInformation = new FXGeneralElevatorInformation(environment);

        Scene scene = new Scene(fxGeneralElevatorInformation, 1080, 600);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testUpdateClockTick(FxRobot robot) throws InterruptedException {
        var clockTickLabel = (Label) robot.lookup("#ClockTickLabel").queryAll().iterator().next();
        // Need to wait some time so PropertyChange is fired
        synchronized (lock) {
            environment.setClockTick(10000);
            robot.wait(100);
            assertEquals("0 h, 16 min, 40 sec", clockTickLabel.getText());
        }
    }
}
