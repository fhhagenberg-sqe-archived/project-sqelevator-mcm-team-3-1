/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.propertychanged.event.FloorEvent;
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
public class FXElevatorCallView extends GridPane implements PropertyChangeListener {

    private IEnvironment e;
    private int numberOfFloors;
    private Pane[] upFloors;
    private Pane[] downFloors;

    public FXElevatorCallView(IEnvironment e) {
        this.e = e;
        this.minWidth(160);
        RowConstraints r1 = new RowConstraints();
        r1.setMinHeight(100);
        this.getRowConstraints().add(r1);
        this.setPadding(new Insets(5, 5, 5, 5));
        setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
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
    }

    /**
     * Function populates the header it sets the name of the elevator, door
     * state and direction
     */
    private void populateHeader() {
        var up = new Label("UP");
        var down = new Label("DOWN");
        this.add(up, 0, 0);
        this.add(down, 1, 0);
    }

    private Pane generatePane() {
        var p = new Pane();
        p.setMinSize(80, 80);
        p.setPadding(new Insets(5, 5, 5, 5));
        p.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return p;
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (evt.getPropertyName()) {
                    case FloorEvent.FLOOR_BUTTON_DOWN:
                        break;
                    case FloorEvent.FLOOR_BUTTON_UP:
                        break;
                }
            }
        });
    }
}
