/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.ElevatorEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jmayr
 */
public class FXElevator extends GridPane implements PropertyChangeListener {

    private ILocalElevator elevator;
    private int numberOfFloors;
    private Pane[] floors;
    private VBox header;
    private Label elevatorName;
    private Label elevatorDoorState;
    private Label elevatorDirection;
    private Label elevatorMode;

    public FXElevator(ILocalElevator elevator, int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        this.elevator = elevator;
        floors = new Pane[numberOfFloors];
        this.populateHeader();
        this.add(header, 0, 0);
        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new Pane();
            floors[i].minWidth(40);
            floors[i].minHeight(40);
            this.add(floors[i], i + 1, 0);
        }
        this.setMinWidth(50);
        this.elevator.addSelectedFloorsListener(this);
        this.elevator.addTargetListener(this);
        this.elevator.addFloorListener(this);
        this.elevator.addDirectionListener(this);
        this.elevator.addDoorStateListener(this);
        this.elevator.addModeListener(this);
        this.elevator.addStateListener(this);
    }

    /**
     * Function populates the header it sets the name of the elevator, door
     * state and direction
     */
    private void populateHeader() {
        this.header = new VBox();
        this.elevatorName = new Label("E " + elevator.getElevatorNumber());
        this.elevatorDoorState = new Label(elevator.getDoorState().name());
        this.elevatorDirection = new Label(elevator.getDirection().name());
        this.elevatorMode = new Label(elevator.getCurrentMode().getModeType().name());
        header.getChildren().add(elevatorName);
        header.getChildren().add(elevatorDoorState);
        header.getChildren().add(elevatorDirection);
        header.getChildren().add(elevatorMode);
    }

    /**
     * updates the view data This function clears and re-sets the selected
     * floors, the elevator position and the target floor
     *
     */
    public void updateView() {
        clearAll();
        for (int floor : this.elevator.getSelectedFloors()) {
            this.setSelection(floor);
        }
        this.setPosition(this.elevator.getCurrentFloor());
        this.setTarget(this.elevator.getTargetFloor());
    }

    /**
     * clears all the floors
     */
    private void clearAll() {
        for (Pane p : floors) {
            p.setBackground(Background.EMPTY);
            p.setBorder(Border.EMPTY);
        }
    }

    /**
     * function sets the floor the elevator is targeting to
     *
     * @param floor int number of the floor that is the target ID
     */
    private void setTarget(int floor) {
        floors[floor].setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Function sets the position of the elevator
     *
     * @param floor int number of the floor the elevator is currently located at
     */
    private void setPosition(int floor) {
        floors[floor].setBackground(new Background(new BackgroundFill(Color.DARKGREEN,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Sets the selection representation when a floor is selected within the
     * elevator
     *
     * @param floor int number of the floor that is a selected one
     */
    private void setSelection(int floor) {
        floors[floor].setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    /**
     * Updates the Elevator state representation of the UI
     *
     * @param state ElevatorState state
     */
    private void setElevatorState(ElevatorState state) {
        switch (state) {
            case ERROR:
                this.header.setBackground(new Background(new BackgroundFill(Color.RED,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case ACTIVE:
                this.header.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case INACTIVE:
                this.header.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case UNKNOWN:
                this.header.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            default:
                this.header.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
        }
    }

    public void setSelected(ILocalElevator e) {
        if (this.elevator.equals(e)) {
            setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        } else {
            setBorder(Border.EMPTY);
        }

    }

    public ILocalElevator getElevator() {
        return this.elevator;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case ElevatorEvent.SELECTED_FLOORS:
            case ElevatorEvent.TARGET_FLOOR:
            case ElevatorEvent.CURRENT_FLOOR:
                updateView();
                break;
            case ElevatorEvent.DIRECTION:
                this.elevatorDirection.setText(((ElevatorDirection) evt.getNewValue()).name());
                break;
            case ElevatorEvent.DOOR_STATE:
                this.elevatorDoorState.setText(((DoorState) evt.getNewValue()).name());
                break;
            case ElevatorEvent.MODE:
                this.elevatorMode.setText(((IElevatorMode) evt.getNewValue()).getModeType().name());
                break;
            case ElevatorEvent.CURRENT_STATE:
                this.setElevatorState((ElevatorState) evt.getNewValue());
                break;
        }
    }
}
