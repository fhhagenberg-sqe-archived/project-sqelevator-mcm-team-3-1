package at.fhhagenberg.sqelevator.controller;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.interfaces.*;
import at.fhhagenberg.sqelevator.model.factory.ElevatorFactory;
import at.fhhagenberg.sqelevator.model.factory.EnvironmentFactory;
import at.fhhagenberg.sqelevator.model.factory.FloorFactory;
import at.fhhagenberg.sqelevator.propertychanged.event.CoreMapperEvent;
import sqelevator.IElevator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.*;

public class CoreMapper implements ICoreMapper {
    private IElevator elevator;
    private Timer coreMapperTimer;
    private CoreMapperTimerTask coreMapperTimerTask;

    private final EnvironmentFactory environmentFactory;
    private final ElevatorFactory elevatorFactory;
    private final FloorFactory floorFactory;

    private IEnvironment environment;
    private HashMap<Integer, ILocalElevator> elevators = new HashMap<>();
    private HashMap<Integer, IFloor> floors = new HashMap<>();

    private PropertyChangeSupport mappingLoadedListener = new PropertyChangeSupport(this);

    public CoreMapper(final IElevator elevator,
                      final EnvironmentFactory environmentFactory,
                      final ElevatorFactory elevatorFactory,
                      final FloorFactory floorFactory) {
        this.elevator = elevator;
        this.environmentFactory = environmentFactory;
        this.elevatorFactory = elevatorFactory;
        this.floorFactory = floorFactory;
        this.coreMapperTimerTask = new CoreMapperTimerTask(this);
        this.coreMapperTimer = new Timer("CoreMapper Timer");
    }

    @Override
    public void schedulePeriodicUpdates(int intervalMs) {
        this.coreMapperTimer.scheduleAtFixedRate(this.coreMapperTimerTask, 0, intervalMs);
    }

    @Override
    public void cancelPeriodicUpdates() {
        this.coreMapperTimer.cancel();
    }

    @Override
    public void loadEnvironment() throws RemoteException {
        this.environment = environmentFactory.createEnvironment();
        updateEnvironmentData(environment);
        mappingLoadedListener.firePropertyChange(CoreMapperEvent.ENVIRONMENT_LOADED, null, environment);
    }

    @Override
    public void updateEnvironment() throws RemoteException {
        updateEnvironmentData(environment);
    }

    @Override
    public void loadElevators() throws RemoteException {
        for (int elevatorNumber = 0; elevatorNumber < environment.getNumberOfElevators(); ++elevatorNumber) {
            var localElevator = elevatorFactory.createElevator(elevatorNumber);
            updateElevatorData(localElevator, elevatorNumber);
            elevators.put(localElevator.getElevatorNumber(), localElevator);
            mappingLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_LOADED, null, localElevator);
        }
    }

    @Override
    public void updateElevators() throws RemoteException {
        for (Map.Entry<Integer, ILocalElevator> e : elevators.entrySet()) {
            updateElevatorData(e.getValue(), e.getKey());
        }
    }

    @Override
    public void loadFloors() throws RemoteException {
        for (int floorNumber = 0; floorNumber < elevator.getFloorNum(); ++floorNumber) {
            var floor = floorFactory.createFloor(floorNumber);
            updateFloorData(floor, floorNumber);
            floors.put(floor.getFloorNumber(), floor);
            mappingLoadedListener.firePropertyChange(CoreMapperEvent.FLOOR_LOADED, null, floor);
        }
    }

    @Override
    public void updateFloors() throws RemoteException {
        for (Map.Entry<Integer, IFloor> f : floors.entrySet()) {
            updateFloorData(f.getValue(), f.getKey());
        }
    }

    @Override
    public void setTargetFloor(ILocalElevator e, int floorNumber) throws RemoteException {
        elevator.setTarget(e.getElevatorNumber(), floorNumber);
    }

    @Override
    public void setMode(int elevatorNumber, IElevatorMode mode) throws RemoteException {
        elevators.get(elevatorNumber).setMode(mode);
    }

    @Override
    public void addMappingLoadedListener(PropertyChangeListener listener) {
        this.mappingLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeMappingLoadedListener(PropertyChangeListener listener) {
        this.mappingLoadedListener.removePropertyChangeListener(listener);
    }

    private void updateEnvironmentData(IEnvironment environment) throws RemoteException {
        if (environment != null) {
            environment.setNumberOfElevators(elevator.getElevatorNum());
            environment.setNumberOfFloors(elevator.getFloorNum());
            environment.setFloorHeight(elevator.getFloorHeight());
            environment.setClockTick(elevator.getClockTick());
        }
    }

    private void updateElevatorData(ILocalElevator localElevator, int elevatorNumber) throws RemoteException {
        if (localElevator != null) {
            localElevator.setAcceleration(elevator.getElevatorAccel(elevatorNumber));
            localElevator.setFloor(elevator.getElevatorFloor(elevatorNumber));
            localElevator.setPosition(elevator.getElevatorPosition(elevatorNumber));
            localElevator.setTargetFloor(elevator.getTarget(elevatorNumber));
            localElevator.setDoorState(DoorState.from(elevator.getElevatorDoorStatus(elevatorNumber)));
            localElevator.setWeight(elevator.getElevatorWeight(elevatorNumber));
            localElevator.setCapacity(elevator.getElevatorCapacity(elevatorNumber));
            localElevator.setSpeed(elevator.getElevatorSpeed(elevatorNumber));
            localElevator.setDirection(ElevatorDirection.from(elevator.getCommittedDirection(elevatorNumber)));
            List<Integer> floorList = new ArrayList<>();

            for (var f : floors.entrySet()) {
                if (elevator.getElevatorButton(elevatorNumber, f.getValue().getFloorNumber())) {
                    floorList.add(f.getValue().getFloorNumber());
                }
            }

            localElevator.setSelectedFloors(floorList.stream().mapToInt(Integer::intValue).toArray());
        }
    }

    private void updateFloorData(IFloor floor, int floorNumber) throws RemoteException {
        if (floor != null) {
            floor.setFloorButtonDown(elevator.getFloorButtonDown(floorNumber));
            floor.setFloorButtonUp(elevator.getFloorButtonUp(floorNumber));
        }
    }
}
