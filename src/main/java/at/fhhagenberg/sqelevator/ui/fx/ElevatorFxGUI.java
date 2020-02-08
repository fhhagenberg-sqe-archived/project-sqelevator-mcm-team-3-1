/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.controller.CoreMapper;
import at.fhhagenberg.sqelevator.controller.UserInteractionMapper;
import at.fhhagenberg.sqelevator.interfaces.*;
import at.fhhagenberg.sqelevator.model.factory.ElevatorFactory;
import at.fhhagenberg.sqelevator.model.factory.EnvironmentFactory;
import at.fhhagenberg.sqelevator.model.factory.FloorFactory;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sqelevator.IElevator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.Naming;
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

    private static final double ELEVATOR_DISPLAY_HEIGHT = 500;

    private HBox generalElevatorInformation = new HBox();
    private HBox floorCallArea = new HBox();
    private HBox elevatorArea = new HBox();
    private FXSelectedElevator selectedElevator;
    private FXElevatorCallView elevatorCallView;
    private LinkedList<FXElevator> evtrs;
    private IUserInteractionMapper mapper;
    private IEnvironment environment;
    private ICoreMapper core;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        IElevator elevator = (IElevator) Naming.lookup("rmi://localhost:1099/ElevatorSim");
        core = new CoreMapper(elevator,
                new EnvironmentFactory(), new ElevatorFactory(), new FloorFactory());
        this.mapper = new UserInteractionMapper(core);
        selectedElevator = new FXSelectedElevator(mapper);
        evtrs = new LinkedList<>();
        Scene scene = new Scene(renderLayout(), 1080, 600);
        primaryStage.setTitle("DataManager FX");
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(600 + 40);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            core.cancelPeriodicUpdates();
            Platform.exit();
        });
        mapper.addElevatorListener(this);
        mapper.addEnvironmentListener(this);
        mapper.addSelectedElevatorListener(this.selectedElevator);
        mapper.addSaveFloorEnabledListener(this.selectedElevator);

        core.loadEnvironment();
        core.loadElevators();
        core.loadFloors();

        core.schedulePeriodicUpdates(ICoreMapper.DEFAULT_UPDATE_INTERVAL_MS);
    }

    private VBox renderLayout() {
        VBox layout = new VBox();
        HBox elevatorColumns = new HBox();
        elevatorColumns.getChildren().add(elevatorArea);
        elevatorColumns.getChildren().add(floorCallArea);
        elevatorColumns.getChildren().add(selectedElevator);
        elevatorColumns.setPadding(new Insets(10, 20, 10, 10));
        elevatorColumns.setMinHeight(500);

        layout.getChildren().add(elevatorColumns);
        layout.getChildren().add(generalElevatorInformation);
        return layout;
    }

    private void handleEnvironmentLoaded(IEnvironment environment) {
        if (environment != null) {
            System.out.println("Floor view added!");
            this.environment = environment;
            elevatorCallView = new FXElevatorCallView(environment, ELEVATOR_DISPLAY_HEIGHT);
            this.floorCallArea.getChildren().clear();
            this.floorCallArea.getChildren().add(elevatorCallView);
            var fxGeneralElevatorInformation = new FXGeneralElevatorInformation(environment);
            this.generalElevatorInformation.getChildren().add(fxGeneralElevatorInformation);
        }
    }

    private void handleElevatorLoaded(ILocalElevator elevator) {
        if (elevator != null) {
            if (this.environment != null && this.evtrs.stream().noneMatch(fxElevator -> fxElevator.getElevator().equals(elevator))) {
                System.out.println("Elevator added!");
                var fxElevator = new FXElevator(elevator, environment.getNumberOfFloors(), ELEVATOR_DISPLAY_HEIGHT);
                fxElevator.setOnMouseClicked((MouseEvent t) -> {
                    this.mapper.selectElevator(elevator);
                });
                this.evtrs.add(fxElevator);
                this.elevatorArea.getChildren().add(fxElevator);
                this.mapper.addSelectedElevatorListener(fxElevator);
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
