package at.fhhagenberg.sqelevator.controller;

import sqelevator.IElevator;
import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.model.EnvironmentImpl;
import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.model.LocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.CoreMapperEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class CoreMapperImpl implements ICoreMapper {
    private IElevator elevator;
    private Timer coreMapperTimer;
    private CoreMapperTimerTask coreMapperTimerTask;

    private EnvironmentImpl environment;
    private HashMap<Integer, LocalElevator> elevators = new HashMap<>();
    private HashMap<Integer, Floor> floors = new HashMap<>();

    private PropertyChangeSupport environmentLoadedListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport elevatorLoadedListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport floorLoadedListener = new PropertyChangeSupport(this);

    public CoreMapperImpl() throws MalformedURLException, RemoteException, NotBoundException {
        this.environment = new EnvironmentImpl();
        this.elevator = (IElevator) Naming.lookup("rmi://localhost:1099/ElevatorSim");
        this.coreMapperTimerTask = new CoreMapperTimerTask(this);
        this.coreMapperTimer = new Timer("CoreMapper Timer");
        this.coreMapperTimer.scheduleAtFixedRate(this.coreMapperTimerTask, 0, REMOTE_FETCH_INTERVAL);
    }

    @Override
    public void loadEnvironment() throws RemoteException {
        updateEnvironmentData(environment);
        environmentLoadedListener.firePropertyChange(CoreMapperEvent.ENVIRONMENT_LOADED, null, environment);
    }

    @Override
    public void updateEnvironment() throws RemoteException {
        updateEnvironmentData(environment);
    }

    @Override
    public void loadElevators() throws RemoteException {
        for (int elevatorNumber = 0; elevatorNumber < environment.getNumberOfElevators(); ++elevatorNumber) {
            var localElevator = new LocalElevator(elevatorNumber);
            updateElevatorData(localElevator, elevatorNumber);
            elevators.put(localElevator.getElevatorNumber(), localElevator);
            elevatorLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_LOADED, null, localElevator);
        }
    }

    @Override
    public void updateElevators() throws RemoteException {
        for (Map.Entry<Integer, LocalElevator> e : elevators.entrySet()) {
            updateElevatorData(e.getValue(), e.getKey());
        }
    }

    @Override
    public void loadFloors() throws RemoteException {
        for (int floorNumber = 0; floorNumber < elevator.getFloorNum(); ++floorNumber) {
            var floor = new Floor(floorNumber);
            updateFloorData(floor, floorNumber);
            floors.put(floor.getFloorNumber(), floor);
            floorLoadedListener.firePropertyChange(CoreMapperEvent.FLOOR_LOADED, null, floor);
        }
    }

    @Override
    public void updateFloors() throws RemoteException {
        for (Map.Entry<Integer, Floor> f : floors.entrySet()) {
            updateFloorData(f.getValue(), f.getKey());
        }
    }

    @Override
    public void setTargetFloor(ILocalElevator e, int floorNumber) throws RemoteException {
        elevator.setTarget(e.getElevatorNumber(), floorNumber);
    }

    @Override
    public void addEnvironmentLoadedEventListener(PropertyChangeListener listener) {
        this.environmentLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeEnvironmentLoadedEventListener(PropertyChangeListener listener) {
        this.environmentLoadedListener.removePropertyChangeListener(listener);
    }

    @Override
    public void addFloorLoadedEventListener(PropertyChangeListener listener) {
        this.floorLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeFloorLoadedEventListener(PropertyChangeListener listener) {
        this.floorLoadedListener.removePropertyChangeListener(listener);
    }

    @Override
    public void addElevatorLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeElevatorLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorLoadedListener.removePropertyChangeListener(listener);
    }

    private void updateEnvironmentData(EnvironmentImpl environment) throws RemoteException {
        if (environment != null) {
            environment.setNumberOfElevators(elevator.getElevatorNum());
            environment.setNumberOfFloors(elevator.getFloorNum());
            environment.setFloorHeight(elevator.getFloorHeight());
            environment.setClockTick(elevator.getClockTick());
        }
    }

    private void updateElevatorData(LocalElevator localElevator, int elevatorNumber) throws RemoteException {
        if (localElevator != null) {
            localElevator.setCurrentAcceleration(elevator.getElevatorAccel(elevatorNumber));
            localElevator.setCurrentFloor(elevator.getElevatorFloor(elevatorNumber));
            localElevator.setCurrentPosition(elevator.getElevatorPosition(elevatorNumber));
            localElevator.setTargetFloor(elevator.getTarget(elevatorNumber));
            localElevator.setDoorState(DoorState.from(elevator.getElevatorDoorStatus(elevatorNumber)));
            localElevator.setLbsWeight(elevator.getElevatorWeight(elevatorNumber));
            localElevator.setCapacity(elevator.getElevatorCapacity(elevatorNumber));
            localElevator.setCurrentSpeed(elevator.getElevatorSpeed(elevatorNumber));
        }
    }

    private void updateFloorData(Floor floor, int floorNumber) throws RemoteException {
        if (floor != null) {
            floor.setFloorButtonDown(elevator.getFloorButtonDown(floorNumber));
            floor.setFloorButtonUp(elevator.getFloorButtonUp(floorNumber));
        }
    }
}
