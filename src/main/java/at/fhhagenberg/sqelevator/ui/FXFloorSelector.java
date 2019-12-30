/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.interfaces.IElevatorCall;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import javafx.geometry.Insets;
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
public class FXFloorSelector extends GridPane {

    private int numberOfFloors;
    private Pane[][] floors;
    private HBox header;

    public FXFloorSelector(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        floors = new Pane[numberOfFloors][2];
        this.add(header, 0, 0, 2, 1);
        for (int i = 0; i < numberOfFloors; i++) {
            floors[i][0] = new Pane();
            floors[i][1] = new Pane();
            this.add(floors[i][0], i + 1, 0);
            this.add(floors[i][1], i + 1, 1);
        }
    }

    public void updateSelections(IElevatorCall[] calls) {
        clearAll();
        for (IElevatorCall call : calls) {
            this.setSelection(call.getFloorNumber(), call.getDirection());
        }
    }

    private void clearAll() {
        for (Pane[] p : floors) {
            p[0].setBackground(Background.EMPTY);
            p[1].setBackground(Background.EMPTY);
        }
    }

    private void setSelection(int floor, ElevatorDirection direction) {
        if (direction == ElevatorDirection.UP) {
            floors[floor][0].setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        } else if (direction == ElevatorDirection.DOWN) {
            floors[floor][1].setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }

}
