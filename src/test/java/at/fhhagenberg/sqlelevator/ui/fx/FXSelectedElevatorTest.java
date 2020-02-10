package at.fhhagenberg.sqlelevator.ui.fx;

import at.fhhagenberg.sqelevator.controller.UserInteractionMapper;
import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;
import at.fhhagenberg.sqelevator.interfaces.IUserInteractionMapper;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;
import at.fhhagenberg.sqelevator.ui.fx.FXElevator;
import at.fhhagenberg.sqelevator.ui.fx.FXSelectedElevator;
import at.fhhagenberg.sqlelevator.LocalElevatorStub;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.awt.*;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
public class FXSelectedElevatorTest {

    private LocalElevatorStub localElevatorStub;
    private FXSelectedElevator fxSelectedElevator;
    private IUserInteractionMapper userInteractionMapper;
    private ICoreMapper coreMapperMock;

    @Start
    public void start(Stage stage) throws Exception {
        localElevatorStub = new LocalElevatorStub();
        coreMapperMock = mock(ICoreMapper.class);
        //userInteractionMapper = new UserInteractionMapper(coreMapperMock);
        userInteractionMapper = mock(IUserInteractionMapper.class);
        fxSelectedElevator = new FXSelectedElevator(userInteractionMapper);
        fxSelectedElevator.setSelectedElevator(localElevatorStub);

        Scene scene = new Scene(fxSelectedElevator, 1080, 600);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void UpdateUITest(FxRobot robot) {

        FxAssert.verifyThat("#HeaderLabel", LabeledMatchers.hasText("Elevator E " + localElevatorStub.getElevatorNumber()));
        FxAssert.verifyThat("#CurrentSpeed", LabeledMatchers.hasText(Double.toString(localElevatorStub.getSpeed())));
        FxAssert.verifyThat("#CurrentLoad", LabeledMatchers.hasText((Integer.toString(localElevatorStub.getWeight()))));
        FxAssert.verifyThat("#CurrentCapacity", LabeledMatchers.hasText(Integer.toString(localElevatorStub.getCapacity())));
        FxAssert.verifyThat("#CurrentAcceleration", LabeledMatchers.hasText(Double.toString(localElevatorStub.getAcceleration())));
        FxAssert.verifyThat("#CurrentFloor", LabeledMatchers.hasText(Integer.toString(localElevatorStub.getFloor())));
        FxAssert.verifyThat("#CurrentMode", LabeledMatchers.hasText(localElevatorStub.getMode().getModeType().name()));
        FxAssert.verifyThat("#CurrentDoorState", LabeledMatchers.hasText(localElevatorStub.getDoorState().name()));
        FxAssert.verifyThat("#CurrentState", LabeledMatchers.hasText(localElevatorStub.getElevatorState().name()));
        FxAssert.verifyThat("#CurrentDirection", LabeledMatchers.hasText(localElevatorStub.getDirection().name()));
        FxAssert.verifyThat("#CurrentPosition", LabeledMatchers.hasText(Integer.toString(localElevatorStub.getPosition())));

        var nextFloorTextField = (TextField) robot.lookup("#NextFloorTextField").queryAll().iterator().next();
        assertEquals(Integer.toString(localElevatorStub.getTargetFloor()), nextFloorTextField.getText());
    }

    @Test
    public void StoreFloorTest(FxRobot robot) throws RemoteException {
        var submitNextFloor = (Button) robot.lookup("#SubmitButton").queryAll().iterator().next();
        var nextFloorTextField = (TextField) robot.lookup("#NextFloorTextField").queryAll().iterator().next();

        robot.doubleClickOn(nextFloorTextField);
        robot.write("3");
        verify(userInteractionMapper, times(1)).processInput("3");

        submitNextFloor.setDisable(false);
        robot.clickOn(submitNextFloor);

        verify(userInteractionMapper, times(1)).storeFloor();
    }

    @Test
    public void ToggleModeTest(FxRobot robot) throws RemoteException {
        var nextFloorTextField = (Button) robot.lookup("#ChangeModeButton").queryAll().iterator().next();
        robot.clickOn(nextFloorTextField);

        verify(userInteractionMapper, times(1)).toggleMode(eq(localElevatorStub.getElevatorNumber()), any(ElevatorModeAuto.class));
    }

}
