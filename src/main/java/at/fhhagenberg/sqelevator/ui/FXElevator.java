/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui;

import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author jmayr
 */
public class FXElevator extends GridPane {

    private ILocalElevator e;
    private int numberOfFloors;
    private Pane[] floors;
    private HBox header;
    private Label elevatorName;
    private Label elevatorDoorState;
    private Label elevatorDirection;

    public FXElevator(ILocalElevator e, int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        this.e = e;
        floors = new Pane[numberOfFloors];
        this.populateHeader();
        this.add(header, 0, 0);
        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new Pane();
            this.add(floors[i], i + 1, 0);
        }
    }

    /**
     * Function populates the header it sets the name of the elevator, door
     * state and direction
     */
    private void populateHeader() {
        this.header = new HBox();
        this.elevatorName = new Label("E " + e.getElevatorNumber());
        this.elevatorDoorState = new Label(e.getDoorState().name());
        this.elevatorDirection = new Label(e.getDirection().name());
        header.getChildren().add(elevatorName);
        header.getChildren().add(elevatorDoorState);
        header.getChildren().add(elevatorDirection);
    }

    /**
     * updates the view data This function clears and re-sets the selected
     * floors, the elevator position and the target floor
     *
     */
    public void updateView() {
        clearAll();
        for (int floor : this.e.getSelectedFloors()) {
            this.setSelection(floor);
        }
        this.setPosition(this.e.getCurrentFloor());
        this.setTarget(this.e.getTargetFloor());
        this.elevatorDirection.setText(this.e.getDirection().name());
        this.elevatorDoorState.setText(this.e.getDoorState().name());
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

}
