/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.controller.UserInteractionMapper;
import at.fhhagenberg.sqelevator.controller.dummy.CoreMapper;
import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.interfaces.IUserInteractionMapper;
import at.fhhagenberg.sqelevator.model.Environment;
import at.fhhagenberg.sqelevator.model.LocalElevator;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
public class ElevatorFxGUI extends Application implements PropertyChangeListener {

    private FXSelectedElevator selectedElevator;
    private LinkedList<FXElevator> evtrs;
    private HBox floorCallArea;
    private HBox elevatorArea;
    private IUserInteractionMapper mapper;
    private IEnvironment environment;
    private CoreMapper core;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        core = new CoreMapper();
        var userInteractionHandler = new UserInteractionMapper(core);
        this.mapper = userInteractionHandler;
        selectedElevator = new FXSelectedElevator(mapper);
        evtrs = new LinkedList<>();
        this.elevatorArea = new HBox();
        this.floorCallArea = new HBox();
        Scene scene = new Scene(renderLayout(), 1080, 600);
        primaryStage.setTitle("DataManager FX");
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        mapper.addElevatorListener(this);
        mapper.addEnvironmentListener(this);
        mapper.addSelectedElevatorListener(this.selectedElevator);
        mapper.addSaveFloorEnabledListener(this.selectedElevator);
        core.dummyFakeLoad();
    }

    private HBox renderLayout() {
        HBox layout = new HBox();
        layout.getChildren().add(elevatorArea);
        layout.getChildren().add(floorCallArea);
        layout.getChildren().add(selectedElevator);
        layout.setPadding(new Insets(10, 20, 50, 10));
        layout.setSpacing(5);
        return layout;
    }

    private void handleElevator(ILocalElevator e) {
        if (e != null) {
            if (this.environment != null && this.evtrs.stream().filter(evtr -> evtr.getElevator().equals(e)).count() == 0) {
                System.out.println("Elevator added!");
                var evtr = new FXElevator(e, environment.getNumberOfFloors());
                evtr.setOnMouseClicked((MouseEvent t) -> {
                    this.mapper.selectElevator(e);
                });
                this.evtrs.add(evtr);
                this.elevatorArea.getChildren().add(evtr);
                this.mapper.addSelectedElevatorListener(evtr);
            }
        }
    }

    private void handleFloorDisplay(IEnvironment e) {
        if (e != null) {
            System.out.println("Floor view added!");
            this.environment = e;
            var v = new FXElevatorCallView(environment);
            this.floorCallArea.getChildren().clear();
            this.floorCallArea.getChildren().add(v);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (evt.getPropertyName()) {
                    case UIEvent.ENVIRONMENT_LOADED:
                        handleFloorDisplay((IEnvironment) evt.getNewValue());
                        break;
                    case UIEvent.NEW_ELEVATOR_ADDED:
                        handleElevator((ILocalElevator) evt.getNewValue());
                        break;
                    case UIEvent.UPDATE_ERROR_MESSAGE:
                        System.out.println("TODO: Add proper error output " + evt.getNewValue());
                        break;
                }
            }
        });
    }
}
