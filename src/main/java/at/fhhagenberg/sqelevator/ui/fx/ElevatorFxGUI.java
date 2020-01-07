/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.interfaces.IBackendShader;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import at.fhhagenberg.sqelevator.ui.mapper.UIMapper;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author jmayr
 *
 * NOTES from the lecture: in the used Objects someclasspropertyProperty():
 * StringProperty; adding change listeners for javaFX also provides binding
 * properties WARNING: JAVA FX PROPERITARY!!!! SO INTERMEDIATE OBJECTS HAVE TO
 * BE CREATED!!!!
 *
 */
public class ElevatorFxGUI extends Stage implements PropertyChangeListener {

    private FXSelectedElevator selectedElevator;
    private LinkedList<FXElevator> evtrs;
    private HBox floorCallArea;
    private HBox elevatorArea;
    private UIMapper mapper;
    private IEnvironment environment;

    public ElevatorFxGUI(UIMapper mapper) {
        this.mapper = mapper;
        Scene scene = new Scene(renderLayout(), 1000, 600);
        selectedElevator = new FXSelectedElevator();
        evtrs = new LinkedList<>();
        this.elevatorArea = new HBox();
        this.floorCallArea = new HBox();
        mapper.addElevatorListener(this);
        mapper.addEnvironmentListener(this);
        mapper.addSelectedElevatorListener(this.selectedElevator);
        mapper.addSaveFloorEnabledListener(this.selectedElevator);

    }

    private HBox renderLayout() {
        HBox layout = new HBox();
        layout.getChildren().add(elevatorArea);
        layout.getChildren().add(floorCallArea);
        layout.getChildren().add(selectedElevator);
        layout.setPadding(new Insets(10, 20, 50, 10));
        layout.setSpacing(10);
        return layout;
    }

    private void handleElevator(ILocalElevator e) {
        if (e != null) {
            if (this.environment != null && this.evtrs.stream().filter(evtr -> evtr.getElevator().equals(e)).count() == 0) {
                var evtr = new FXElevator(e, environment.getNumberOfFloors());
                this.evtrs.add(evtr);
                this.elevatorArea.getChildren().add(evtr);
                this.mapper.addSelectedElevatorListener(evtr);
            }
        }
    }

    private void handleFloorDisplay() {
        if (environment != null) {
            var v = new FXElevatorCallView(environment);
            this.floorCallArea.getChildren().clear();
            this.floorCallArea.getChildren().add(v);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case UIEvent.ENVIRONMENT_LOADED:
                if (evt.getNewValue() != null) {
                    this.environment = (IEnvironment) evt.getNewValue();
                }
                break;
            case UIEvent.NEW_ELEVATOR_ADDED:
                this.handleElevator((ILocalElevator) evt.getNewValue());
                break;
            case UIEvent.SELECTED_ELEVATOR_CHANGED:

            case UIEvent.UPDATE_ERROR_MESSAGE:
                System.out.println("TODO: Add proper error output " + evt.getNewValue());
                break;
        }
    }
}
