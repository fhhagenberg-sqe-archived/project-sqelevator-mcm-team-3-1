/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorCall;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.ElevatorEvent;
import at.fhhagenberg.sqelevator.propertychanged.event.EnvironmentEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author jmayr
 */
public class FXElevatorCallView extends GridPane implements PropertyChangeListener {

    private IEnvironment e;
    private int numberOfFloors;
    private Pane[] upFloors;
    private Pane[] downFloors;

    public FXElevatorCallView(IEnvironment e) {
        this.e = e;
        this.minWidth(160);
        this.populateHeader();
        this.upFloors = new Pane[e.getNumberOfFloors()];
        this.downFloors = new Pane[e.getNumberOfFloors()];
        this.numberOfFloors = e.getNumberOfFloors();
        for (int i = 0; i < numberOfFloors; i++) {
            upFloors[i] = generatePane();
            downFloors[i] = generatePane();
            this.add(upFloors[i], 0, i + 1);
            this.add(downFloors[i], 1, i + 1);

        }

        e.addCallRemovedListener(this);
        e.addCallAddedListener(this);
    }

    /**
     * Function populates the header it sets the name of the elevator, door
     * state and direction
     */
    private void populateHeader() {
        this.add(new Label("DOWN"), 0, 0);
        this.add(new Label("UP"), 1, 0);
    }

    private Pane generatePane() {
        var p = new Pane();
        p.setMinSize(80, 80);
        p.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return p;
    }

    /**
     * clears all the floors
     */
    private void clearCall(IElevatorCall c) {
        if (c.getDirection() == ElevatorDirection.DOWN) {
            this.downFloors[c.getFloorNumber()].setBackground(Background.EMPTY);
        } else if (c.getDirection() == ElevatorDirection.UP) {
            this.upFloors[c.getFloorNumber()].setBackground(Background.EMPTY);
        }

    }

    /**
     * Sets the selection representation when a floor is selected within the
     * elevator
     *
     * @param floor int number of the floor that is a selected one
     */
    private void setSelection(IElevatorCall c) {
        if (c.getDirection() == ElevatorDirection.DOWN) {
            this.downFloors[c.getFloorNumber()].setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
                    CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (c.getDirection() == ElevatorDirection.UP) {
            this.upFloors[c.getFloorNumber()].setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
                    CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case EnvironmentEvent.ELEVATOR_CALL_ADDED:
                this.setSelection((IElevatorCall) evt.getNewValue());
                break;
            case EnvironmentEvent.ELEVATOR_CALL_REMOVED:
                this.clearCall((IElevatorCall) evt.getOldValue());
                break;
        }
    }
}
