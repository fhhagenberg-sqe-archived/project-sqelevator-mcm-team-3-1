/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.ElevatorEvent;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author jmayr
 */
public class FXElevator extends GridPane implements PropertyChangeListener {

    private static final double COLUMN_WIDTH = 80;

    private ILocalElevator elevator;
    private Pane[] floors;
    private VBox header;
    private Label elevatorDirection;
    private Label elevatorMode;

    public FXElevator(ILocalElevator elevator, int numberOfFloors, double availableHeight) {
        this.elevator = elevator;
        floors = new Pane[numberOfFloors];
        this.populateHeader();
        this.add(header, 0, 0);
        for (int i = 0; i < numberOfFloors; i++) {
            double columnHeight = (availableHeight - 100) / numberOfFloors;
            floors[i] = UiUtils.generatePane(COLUMN_WIDTH, columnHeight);
            this.add(floors[i], 0, i + 1);
        }
        RowConstraints r1 = new RowConstraints();
        r1.setMinHeight(100);
        this.getRowConstraints().add(r1);
        this.setMinWidth(100);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.elevator.addElevatorUpdatedListener(this);
        this.setSelected(null);
        this.updateView();
    }

    /**
     * Function populates the header it sets the name of the elevator, door
     * state and direction
     */
    private void populateHeader() {
        this.header = new VBox();
        Label elevatorName = new Label("E " + elevator.getElevatorNumber());
        Label elevatorDoorState = new Label(elevator.getDoorState().name());
        this.elevatorDirection = new Label(elevator.getDirection().name());
        this.elevatorMode = new Label(elevator.getMode().getModeType().name());
        elevatorName.setId("ElevatorNameLabel");
        elevatorDoorState.setId("ElevatorDoorStateLabel");
        this.elevatorDirection.setId("ElevatorDirectionLabel");
        this.elevatorMode.setId("ElevatorModeLabel");
        this.header.setId("Header");
        header.getChildren().add(elevatorName);
        header.getChildren().add(elevatorDoorState);
        header.getChildren().add(elevatorDirection);
        header.getChildren().add(elevatorMode);
    }

    /**
     * updates the view data This function clears and re-sets the selected
     * floors, the elevator position and the target floor
     */
    public void updateView() {
        clearAll();
        for (int floor : this.elevator.getSelectedFloors()) {
            this.setSelection(floor);
        }
        this.setPosition(this.elevator.getFloor());
        this.setTarget(this.elevator.getTargetFloor());
    }

    /**
     * clears all the floors
     */
    private void clearAll() {
        for (Pane p : floors) {
            p.setBackground(Background.EMPTY);
            p.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }

    /**
     * function sets the floor the elevator is targeting to
     *
     * @param floor int number of the floor that is the target ID
     */
    private void setTarget(int floor) {
        floors[floor].setBorder(new Border(new BorderStroke(Color.DARKGREEN,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8))));
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
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8))));
    }

    public void setSelected(ILocalElevator e) {
        if (this.elevator.equals(e)) {
            setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
        } else {
            setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
        }
    }

    public ILocalElevator getElevator() {
        return this.elevator;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()) {
                case UIEvent.SELECTED_ELEVATOR_CHANGED:
                    setSelected((ILocalElevator) evt.getNewValue());
                    break;
                case ElevatorEvent.SELECTED_FLOORS:
                case ElevatorEvent.TARGET_FLOOR:
                case ElevatorEvent.CURRENT_FLOOR:
                    updateView();
                    break;
                case ElevatorEvent.DIRECTION:
                    elevatorDirection.setText(((ElevatorDirection) evt.getNewValue()).name());
                    break;
                case ElevatorEvent.MODE:
                    elevatorMode.setText(((IElevatorMode) evt.getNewValue()).getModeType().name());
                    break;
                default:
                    break;
            }
        });
    }
}
