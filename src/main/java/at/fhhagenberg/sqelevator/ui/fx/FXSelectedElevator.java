/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.ElevatorEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jmayr
 */
public class FXSelectedElevator extends GridPane implements PropertyChangeListener {

    private final Label header;
    private final Label speedLabel;
    private final Label currentSpeed;
    private final Label accelerationLabel;
    private final Label currentAcceleration;
    private final Label currentLoad;
    private final Label loadLabel;
    private final Label maxLoad;
    private final Label maxLoadLabel;
    private final Label floorLabel;
    private final Label currentFloor;
    private final Label modeLabel;
    private final Label currentMode;
    private final Label positionLabel;
    private final Label currentPosition;
    private final Button changeMode;
    private final Label doorStateLabel;
    private final Label currentDoorState;
    private final Label stateLabel;
    private final Label currentState;
    private final Label directionLabel;
    private final Label currentDirection;
    private final Label nextFloorLabel;
    private final TextField nextFloor;
    private final Button submitNextFloor;

    private ILocalElevator elevator;

    public FXSelectedElevator() {
        header = new Label();
        speedLabel = new Label("Current Speed");
        accelerationLabel = new Label("Current Acceleration");
        floorLabel = new Label("Current Floor");
        nextFloorLabel = new Label("Next floor");
        modeLabel = new Label("Current Mode");
        loadLabel = new Label("Current Load");
        maxLoadLabel = new Label("MAX Load");
        doorStateLabel = new Label("Door State");
        stateLabel = new Label("Elevator State");
        directionLabel = new Label("Direction");
        positionLabel = new Label("Position");

        currentSpeed = new Label();
        currentAcceleration = new Label();
        currentFloor = new Label();
        currentMode = new Label();
        maxLoad = new Label();
        currentLoad = new Label();
        currentDoorState = new Label();
        currentState = new Label();
        currentDirection = new Label();
        currentPosition = new Label();

        changeMode = new Button("Switch Mode");
        submitNextFloor = new Button("Submit");
        nextFloor = new TextField();
        this.setupUI();
        this.updateUI();
    }

    /**
     * Function that sets up the ui elements to their position
     */
    private void setupUI() {
        this.add(header, 0, 0, 5, 1);
        this.add(stateLabel, 0, 1);
        this.add(currentState, 1, 1);
        this.add(directionLabel, 3, 1);
        this.add(currentDirection, 4, 1);

        this.add(loadLabel, 0, 2);
        this.add(currentLoad, 1, 2);
        this.add(maxLoadLabel, 3, 2);
        this.add(maxLoad, 4, 2);

        this.add(doorStateLabel, 0, 3);
        this.add(currentDoorState, 1, 3);

        this.add(floorLabel, 0, 4);
        this.add(currentFloor, 1, 4);

        this.add(speedLabel, 0, 5);
        this.add(currentSpeed, 1, 5);

        this.add(accelerationLabel, 0, 6);
        this.add(currentAcceleration, 1, 6);

        this.add(positionLabel, 0, 7);
        this.add(currentPosition, 1, 7);

        this.add(modeLabel, 0, 8);
        this.add(currentMode, 1, 8);
        this.add(changeMode, 2, 8);

        this.add(nextFloorLabel, 0, 9);
        this.add(nextFloor, 1, 9);
        this.add(submitNextFloor, 2, 9);

    }

    /**
     * Function changes the elected elevator in the UI and updates the UI
     * elevents. Null clears the set data
     *
     * @param e ILocalElevator selected elevator
     */
    public void setSelectedElevator(ILocalElevator e) {
        if (this.elevator != null) {
            this.elevator.removeAllListener(this);
        }
        this.elevator = e;
        this.updateUI();
    }

    /**
     * Updates the UI elements, clears the fields and sets the respective values
     */
    private void updateUI() {
        this.changeMode.setDisable(elevator == null);
        this.submitNextFloor.setDisable(elevator == null);
        this.nextFloor.setDisable(elevator == null);
        if (elevator == null) {
            this.header.setText("");
            this.currentLoad.setText("");
            this.maxLoad.setText("");
            this.currentAcceleration.setText("");
            this.currentFloor.setText("");
            this.currentDoorState.setText("");
            this.currentMode.setText("");
            this.currentSpeed.setText("");
            this.currentState.setText("");
            this.currentDirection.setText("");
            this.currentPosition.setText("");
        } else {
            this.header.setText("Elevator E " + this.elevator.getElevatorNumber());
            this.currentLoad.setText(Integer.toString(elevator.getCurrentWeightInLbs()));
            this.elevator.addCurrentWeightListener(this);
            this.maxLoad.setText(Integer.toString(this.elevator.getLoadCapacityInLbs()));
            this.currentAcceleration.setText(Double.toString(this.elevator.getAccelerationInFtsqr()));
            this.elevator.addAccelerationListener(this);
            this.currentFloor.setText(Integer.toString(this.elevator.getCurrentFloor()));
            this.elevator.addFloorListener(this);
            this.currentDoorState.setText(this.elevator.getDoorState().name());
            this.elevator.addDoorStateListener(this);
            this.currentMode.setText(this.elevator.getCurrentMode().getModeType().name());
            this.elevator.addModeListener(this);
            this.currentSpeed.setText(Double.toString(this.elevator.getCurrentSpeedInFts()));
            this.elevator.addCurrentSpeedListener(this);
            this.currentState.setText(this.elevator.getElevatorState().name());
            this.elevator.addStateListener(this);
            this.currentDirection.setText(this.elevator.getDirection().name());
            this.elevator.addDirectionListener(this);
            this.currentPosition.setText(Integer.toString(this.elevator.getCurrentPosition()));
            this.elevator.addPositionListener(this);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case ElevatorEvent.CURRENT_ACCELERATION_FTSQR:
                this.currentAcceleration.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.CURRENT_FLOOR:
                this.currentFloor.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.CURRENT_POSITION:
                this.currentPosition.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.CURRENT_SPEED_FTS:
                this.currentSpeed.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.DIRECTION:
                this.currentDirection.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.DOOR_STATE:
                this.currentDoorState.setText(((DoorState) evt.getNewValue()).name());
                break;
            case ElevatorEvent.LBS_MAX_LOAD:
                this.maxLoad.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.LBS_WEIGHT:
                this.currentLoad.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.MODE:
                this.currentMode.setText(((IElevatorMode) evt.getNewValue()).getModeType().name());
                break;
            case ElevatorEvent.TARGET_FLOOR:
                this.nextFloor.setText(evt.getNewValue().toString());
                break;
            case ElevatorEvent.CURRENT_STATE:
                this.currentState.setText(((ElevatorState) evt.getNewValue()).name());
                break;
        }
    }
}
