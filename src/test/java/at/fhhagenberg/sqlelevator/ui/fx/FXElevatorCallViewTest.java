package at.fhhagenberg.sqlelevator.ui.fx;

import at.fhhagenberg.sqelevator.model.Environment;
import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.ui.fx.FXElevatorCallView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class FXElevatorCallViewTest {

    private Environment environment;
    private FXElevatorCallView fxElevatorCallView;

    @Start
    public void start(Stage stage) throws Exception {
        Floor floor = new Floor(2);
        environment = new Environment();
        environment.setNumberOfFloors(5);
        environment.setNumberOfElevators(2);
        fxElevatorCallView = new FXElevatorCallView(environment, 500);

        Scene scene = new Scene(fxElevatorCallView, 1080, 600);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void AddFloorsTest() {
        int noOfChildren = fxElevatorCallView.getChildren().size();
        Platform.runLater(() -> {
            fxElevatorCallView.addFloor(new Floor(1));
            assertEquals(noOfChildren+1, fxElevatorCallView.getChildren().size());
        });
    }
}
