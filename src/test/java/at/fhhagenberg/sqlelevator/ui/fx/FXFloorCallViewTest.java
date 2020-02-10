package at.fhhagenberg.sqlelevator.ui.fx;

import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;
import at.fhhagenberg.sqelevator.interfaces.IUserInteractionMapper;
import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.ui.fx.FXFloorCallView;
import at.fhhagenberg.sqelevator.ui.fx.FXSelectedElevator;
import at.fhhagenberg.sqlelevator.LocalElevatorStub;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(ApplicationExtension.class)
public class FXFloorCallViewTest {

    @Start
    public void start(Stage stage) throws Exception {
        Floor floor = new Floor(2);
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
}
