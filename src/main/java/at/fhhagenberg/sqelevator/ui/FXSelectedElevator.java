/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.interfaces.IElevatorCall;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author jmayr
 */
public class FXSelectedElevator extends GridPane {

    private Label header;
    private Label speedLabel;
    private Label currentSpeed;
    private Label accelerationLabel;
    private Label currentAcceleration;
    private Label currentLoad;
    private Label loadLabel;
    private Label maxLoad;
    private Label maxLoadLabel;
    private Label floorLabel;
    private Label currentFloor;
    private Label modeLabel;
    private Label currentMode;
    private Button changeMode;
    private Label doorStateLabel;
    private Label currentDoorState;
    private Label stateLabel;
    private Label currentState;
    private Label directionLabel;
    private Label currentDirection;
    private Label nextFloorLabel;
    private TextField nextFloor;
    private Button submitNextFloor;

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

        currentSpeed = new Label();
        currentAcceleration = new Label();
        currentFloor = new Label();
        currentMode = new Label();
        maxLoad = new Label();
        currentLoad = new Label();
        currentDoorState = new Label();
        currentState = new Label();
        currentDirection = new Label();

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

        this.add(floorLabel, 0, 2);
        this.add(currentFloor, 1, 2);

        this.add(speedLabel, 0, 3);
        this.add(currentSpeed, 1, 3);

        this.add(accelerationLabel, 0, 4);
        this.add(currentAcceleration, 1, 4);

        this.add(modeLabel, 0, 5);
        this.add(currentMode, 1, 5);
        this.add(changeMode, 2, 5);

        this.add(nextFloorLabel, 0, 6);
        this.add(nextFloor, 1, 6);
        this.add(submitNextFloor, 2, 6);

    }

    /**
     * Function changes the elected elevator in the UI and updates the UI
     * elevents. Null clears the set data
     *
     * @param e ILocalElevator selected elevator
     */
    public void setSelectedElevator(ILocalElevator e) {
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
        } else {
            this.header.setText("Elevator E " + this.elevator.getElevatorNumber());
            this.currentLoad.setText(Integer.toString(elevator.getCurrentWeightInLbs()));
            this.maxLoad.setText(Integer.toString(this.elevator.getLoadCapacityInLbs()));
            this.currentAcceleration.setText(Double.toString(this.elevator.getAccelerationInFtsqr()));
            this.currentFloor.setText(Integer.toString(this.elevator.getCurrentFloor()));
            this.currentDoorState.setText(this.elevator.getDoorState().name());
            this.currentMode.setText(this.elevator.getElevatorState().name());
            this.currentSpeed.setText(Double.toString(this.elevator.getCurrentSpeedInFts()));
            this.currentState.setText(this.elevator.getElevatorState().name());
            this.currentDirection.setText(this.elevator.getDirection().name());
        }
    }

}
