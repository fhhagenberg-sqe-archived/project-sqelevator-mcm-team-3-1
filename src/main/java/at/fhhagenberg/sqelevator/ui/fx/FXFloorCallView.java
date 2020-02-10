package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.interfaces.IFloor;
import at.fhhagenberg.sqelevator.propertychanged.event.FloorEvent;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FXFloorCallView extends HBox implements PropertyChangeListener {
    private IFloor floor;

    private final Pane downButtonPane;
    private final Pane upButtonPane;

    public FXFloorCallView(IFloor floor, double availableWidth, double availableHeight) {
        this.downButtonPane = UiUtils.generatePane(availableWidth, availableHeight);
        this.upButtonPane = UiUtils.generatePane(availableWidth, availableHeight);
        this.downButtonPane.setId("DownButtonPane");
        this.upButtonPane.setId("UpButtonPane");
        this.getChildren().add(downButtonPane);
        this.getChildren().add(upButtonPane);
        setFloor(floor);
    }

    public void setFloor(IFloor floor) {
        if (this.floor != null) {
            this.floor.removeFloorUpdatedListener(this);
        }
        this.floor = floor;

        this.floor.addFloorUpdatedListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (evt.getPropertyName()) {
                    case FloorEvent.FLOOR_BUTTON_DOWN:
                        updatePane(downButtonPane, (boolean) evt.getNewValue());
                        break;
                    case FloorEvent.FLOOR_BUTTON_UP:
                        updatePane(upButtonPane, (boolean) evt.getNewValue());
                        break;
                }
            }
        });
    }

    private void updatePane(Pane pane, boolean active) {
        var color = active ? Color.LIGHTBLUE : Color.RED;
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
