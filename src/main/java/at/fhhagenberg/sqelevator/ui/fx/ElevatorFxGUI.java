/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.controller.CoreMapperImpl;
import at.fhhagenberg.sqelevator.controller.UserInteractionMapper;
import at.fhhagenberg.sqelevator.interfaces.*;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

/**
 * @author jmayr
 * <p>
 * NOTES from the lecture: in the used Objects someclasspropertyProperty():
 * StringProperty; adding change listeners for javaFX also provides binding
 * properties WARNING: JAVA FX PROPERITARY!!!! SO INTERMEDIATE OBJECTS HAVE TO
 * BE CREATED!!!!
 */
public class ElevatorFxGUI extends Application implements PropertyChangeListener {

    private FXSelectedElevator selectedElevator;
    private FXElevatorCallView elevatorCallView;
    private LinkedList<FXElevator> evtrs;
    private HBox floorCallArea;
    private HBox elevatorArea;
    private IUserInteractionMapper mapper;
    private IEnvironment environment;
    private ICoreMapper core;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        core = new CoreMapperImpl();
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

    private void handleEnvironmentLoaded(IEnvironment environment) {
        if (environment != null) {
            System.out.println("Floor view added!");
            this.environment = environment;
            elevatorCallView = new FXElevatorCallView(this.environment);
            this.floorCallArea.getChildren().clear();
            this.floorCallArea.getChildren().add(elevatorCallView);
        }
    }

    private void handleElevatorLoaded(ILocalElevator elevator) {
        if (elevator != null) {
            if (this.environment != null && this.evtrs.stream().filter(evtr -> evtr.getElevator().equals(elevator)).count() == 0) {
                System.out.println("Elevator added!");
                var evtr = new FXElevator(elevator, environment.getNumberOfFloors());
                evtr.setOnMouseClicked((MouseEvent t) -> {
                    this.mapper.selectElevator(elevator);
                });
                this.evtrs.add(evtr);
                this.elevatorArea.getChildren().add(evtr);
                this.mapper.addSelectedElevatorListener(evtr);
            }
        }
    }

    private void handleFloorLoaded(IFloor floor) {
        if (floor != null && elevatorCallView != null) {
            elevatorCallView.addFloor(floor);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (evt.getPropertyName()) {
                    case UIEvent.ENVIRONMENT_LOADED:
                        handleEnvironmentLoaded((IEnvironment) evt.getNewValue());
                        break;
                    case UIEvent.NEW_ELEVATOR_ADDED:
                        handleElevatorLoaded((ILocalElevator) evt.getNewValue());
                        break;
                    case UIEvent.FLOOR_LOADED:
                        handleFloorLoaded((IFloor) evt.getNewValue());
                        break;
                    case UIEvent.UPDATE_ERROR_MESSAGE:
                        System.out.println("TODO: Add proper error output " + evt.getNewValue());
                        break;
                }
            }
        });
    }
}
