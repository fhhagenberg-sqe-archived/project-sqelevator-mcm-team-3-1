/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.IFloor;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author jmayr
 */
public class FXElevatorCallView extends VBox {

    public FXElevatorCallView(IEnvironment e) {
        this.minWidth(160);
        RowConstraints r1 = new RowConstraints();
        r1.setMinHeight(100);
        this.setPadding(new Insets(5, 5, 5, 5));
        setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
        populateHeader();
    }

    public void addFloor(IFloor floor) {
        var floorCallView = new FXFloorCallView(floor);
        this.getChildren().add(floorCallView);
    }

    /**
     * Function populates the header it sets the name of the elevator, door
     * state and direction
     */
    private void populateHeader() {
        var up = new Label("UP");
        var down = new Label("DOWN");
        var header = new HBox();
        header.getChildren().add(up);
        header.getChildren().add(down);
        this.getChildren().add(header);
    }
}
