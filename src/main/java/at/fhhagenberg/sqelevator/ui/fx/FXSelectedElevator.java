/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorModeType;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.interfaces.IUserInteractionMapper;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;
import at.fhhagenberg.sqelevator.propertychanged.event.ElevatorEvent;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

/**
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
    private final Label currentCapacity;
    private final Label capacityLabel;
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
    private IUserInteractionMapper m;
    private ILocalElevator elevator;

    public FXSelectedElevator(IUserInteractionMapper m) {
        this.m = m;
        header = new Label();
        speedLabel = new Label("Current Speed");
        accelerationLabel = new Label("Current Acceleration");
        floorLabel = new Label("Current Floor");
        nextFloorLabel = new Label("Next floor");
        modeLabel = new Label("Current Mode");
        loadLabel = new Label("Current Load");
        capacityLabel = new Label("Capacity");
        doorStateLabel = new Label("Door State");
        stateLabel = new Label("Elevator State");
        directionLabel = new Label("Direction");
        positionLabel = new Label("Position");

        currentSpeed = new Label();
        currentAcceleration = new Label();
        currentFloor = new Label();
        currentMode = new Label();
        currentLoad = new Label();
        currentCapacity = new Label();
        currentDoorState = new Label();
        currentState = new Label();
        currentDirection = new Label();
        currentPosition = new Label();
        changeMode = new Button("Switch Mode");
        submitNextFloor = new Button("Submit");

        nextFloor = new TextField();

        this.setupUI();
        this.updateUI();

        changeMode.setOnMouseClicked((MouseEvent t) -> {
            var mode = elevator.getMode() instanceof ElevatorModeAuto ?
                    new ElevatorModeManual() : new ElevatorModeAuto();
            //Todo: handling remote exception
            try {
                m.toggleMode(elevator.getElevatorNumber(), mode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        submitNextFloor.setOnMouseClicked((MouseEvent t) -> {
            //Todo: handling remote exception
            try {
                m.storeFloor();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        nextFloor.textProperty().addListener((observable, oldValue, newValue) -> {
            m.processInput(newValue);
        });
    }

    /**
     * Function that sets up the ui elements to their position
     */
    private void setupUI() {
        for (int i = 0; i < 11; i++) {
            RowConstraints r1 = new RowConstraints();
            r1.setMinHeight(35);
            this.getRowConstraints().add(r1);
        }
        this.add(header, 0, 0, 5, 1);
        this.add(stateLabel, 0, 1);
        this.add(currentState, 1, 1);
        this.add(directionLabel, 3, 1);
        this.add(currentDirection, 4, 1);

        this.add(loadLabel, 0, 2);
        this.add(currentLoad, 1, 2);

        this.add(capacityLabel, 0, 3);
        this.add(currentCapacity, 1, 3);

        this.add(doorStateLabel, 0, 4);
        this.add(currentDoorState, 1, 4);

        this.add(floorLabel, 0, 5);
        this.add(currentFloor, 1, 5);

        this.add(speedLabel, 0, 6);
        this.add(currentSpeed, 1, 6);

        this.add(accelerationLabel, 0, 7);
        this.add(currentAcceleration, 1, 7);

        this.add(positionLabel, 0, 8);
        this.add(currentPosition, 1, 8);

        this.add(modeLabel, 0, 9);
        this.add(currentMode, 1, 9);
        this.add(changeMode, 2, 9);

        this.add(nextFloorLabel, 0, 10);
        this.add(nextFloor, 1, 10);
        this.add(submitNextFloor, 2, 10);

    }

    /**
     * Function changes the elected elevator in the UI and updates the UI
     * elevents. Null clears the set data
     *
     * @param e ILocalElevator selected elevator
     */
    public void setSelectedElevator(ILocalElevator e) {
        if (this.elevator != null) {
            this.elevator.removeElevatorUpdatedListener(this);
        }
        this.elevator = e;
        this.updateUI();
    }

    /**
     * Updates the UI elements, clears the fields and sets the respective values
     */
    private void updateUI() {
        this.changeMode.setDisable(elevator == null);
        this.submitNextFloor.setDisable(true);
        this.nextFloor.setDisable(elevator == null || elevator.getMode().getModeType() != ElevatorModeType.MANUAL);
        if (elevator == null) {
            this.header.setText("");
            this.currentLoad.setText("");
            this.currentCapacity.setText("");
            this.currentAcceleration.setText("");
            this.currentFloor.setText("");
            this.currentDoorState.setText("");
            this.currentMode.setText("");
            this.currentSpeed.setText("");
            this.currentState.setText("");
            this.currentDirection.setText("");
            this.currentPosition.setText("");
            this.nextFloor.setText("");
        } else {
            this.elevator.addElevatorUpdatedListener(this);
            this.header.setText("Elevator E " + this.elevator.getElevatorNumber());
            this.currentLoad.setText(Integer.toString(elevator.getWeight()));
            this.currentCapacity.setText(Integer.toString(elevator.getCapacity()));
            this.currentAcceleration.setText(Double.toString(this.elevator.getAcceleration()));
            this.currentFloor.setText(Integer.toString(this.elevator.getFloor()));
            this.currentDoorState.setText(this.elevator.getDoorState().name());
            this.currentMode.setText(this.elevator.getMode().getModeType().name());
            this.currentSpeed.setText(Double.toString(this.elevator.getSpeed()));
            this.currentState.setText(this.elevator.getElevatorState().name());
            this.currentDirection.setText(this.elevator.getDirection().name());
            this.currentPosition.setText(Integer.toString(this.elevator.getPosition()));
            this.nextFloor.setText(Integer.toString(this.elevator.getTargetFloor()));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (evt.getPropertyName()) {
                    case UIEvent.SELECTED_ELEVATOR_CHANGED:
                        setSelectedElevator((ILocalElevator) evt.getNewValue());
                        break;
                    case UIEvent.SAVE_FLOOR_ENABLED:
                        submitNextFloor.setDisable(!(boolean) evt.getNewValue());
                        break;
                    case ElevatorEvent.CURRENT_ACCELERATION_FTSQR:
                        currentAcceleration.setText(evt.getNewValue().toString());
                        break;
                    case ElevatorEvent.CURRENT_FLOOR:
                        currentFloor.setText(evt.getNewValue().toString());
                        break;
                    case ElevatorEvent.CURRENT_POSITION:
                        currentPosition.setText(evt.getNewValue().toString());
                        break;
                    case ElevatorEvent.CURRENT_SPEED_FTS:
                        currentSpeed.setText(evt.getNewValue().toString());
                        break;
                    case ElevatorEvent.DIRECTION:
                        currentDirection.setText(evt.getNewValue().toString());
                        break;
                    case ElevatorEvent.DOOR_STATE:
                        currentDoorState.setText(((DoorState) evt.getNewValue()).name());
                        break;
                    case ElevatorEvent.LBS_WEIGHT:
                        currentLoad.setText(evt.getNewValue().toString());
                        break;
                    case ElevatorEvent.CAPACITY:
                        currentCapacity.setText(evt.getNewValue().toString());
                    case ElevatorEvent.MODE:
                        currentMode.setText(((IElevatorMode) evt.getNewValue()).getModeType().name());
                        updateUI();
                        break;
                    case ElevatorEvent.TARGET_FLOOR:
                        nextFloor.setText(evt.getNewValue().toString());
                        break;
                    case ElevatorEvent.CURRENT_STATE:
                        currentState.setText(((ElevatorState) evt.getNewValue()).name());
                        break;
                }
            }
        });
    }
}
