/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui;

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
public class FXElevator extends GridPane {

    private ILocalElevator e;
    private int numberOfFloors;
    private Pane[] floors;
    private HBox header;

    public FXElevator(ILocalElevator e, int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        this.e = e;
        floors = new Pane[numberOfFloors];
        this.add(header, 0, 0, 2, 1);
        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new Pane();
            this.add(floors[i], i + 1, 0);
        }
    }

    public void updateView() {
        clearAll();
        for (int floor : this.e.getSelectedFloors()) {
            this.setSelection(floor);
        }
        this.setPosition(this.e.getCurrentFloor());
        this.setTarget(this.e.getTargetFloor());
    }

    private void clearAll() {
        for (Pane p : floors) {
            p.setBackground(Background.EMPTY);
            p.setBorder(Border.EMPTY);
        }
    }

    private void setTarget(int floor) {
        floors[floor].setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setPosition(int floor) {
        floors[floor].setBackground(new Background(new BackgroundFill(Color.DARKGREEN,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setSelection(int floor) {
        floors[floor].setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

}
