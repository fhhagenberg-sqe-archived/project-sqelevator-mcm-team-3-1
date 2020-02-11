package at.fhhagenberg.sqlelevator.ui.fx;

import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.ui.fx.FXFloorCallView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class FXFloorCallViewTest {

    private Floor floor;
    final Object lock = new Object();

    @Start
    public void start(Stage stage) throws Exception {
        floor = new Floor(2);
        var fxFloorCallView = new FXFloorCallView(floor, 80, 20);

        Scene scene = new Scene(fxFloorCallView, 1080, 600);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void InitTest(FxRobot robot) {
        var upButtonPane = (Pane) robot.lookup("#UpButtonPane").queryAll().iterator().next();
        var downButtonPane = (Pane) robot.lookup("#DownButtonPane").queryAll().iterator().next();

        assertEquals(80.0, upButtonPane.getWidth());
        assertEquals(20.0, upButtonPane.getHeight());
        assertEquals(80.0, downButtonPane.getWidth());
        assertEquals(20.0, downButtonPane.getHeight());
    }

    @Test
    public void testUpdateUpButtonPane(FxRobot robot) throws InterruptedException {
        var upButtonPane = (Pane) robot.lookup("#UpButtonPane").queryAll().iterator().next();
        // Need to wait some time so PropertyChange is fired
        synchronized (lock) {
            floor.setFloorButtonUp(true);
            robot.wait(100);
            assertEquals(Color.LIGHTBLUE, upButtonPane.getBackground().getFills().get(0).getFill());
        }
    }

    @Test
    public void testUpdateDownButtonPane(FxRobot robot) throws InterruptedException {
        var upButtonPane = (Pane) robot.lookup("#DownButtonPane").queryAll().iterator().next();
        // Need to wait some time so PropertyChange is fired
        synchronized (lock) {
            floor.setFloorButtonDown(true);
            robot.wait(100);
            assertEquals(Color.LIGHTBLUE, upButtonPane.getBackground().getFills().get(0).getFill());
        }
    }
}
