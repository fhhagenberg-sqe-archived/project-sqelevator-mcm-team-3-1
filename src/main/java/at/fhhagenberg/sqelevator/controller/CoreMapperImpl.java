package at.fhhagenberg.sqelevator.controller;

import at.fhhagenberg.sqelevator.IElevator;
import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.model.Environment;
import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.model.LocalElevator;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Timer;

public class CoreMapperImpl implements ICoreMapper {
    private Environment environment;
    private IElevator elevator;
    private Timer coreMapperTimer;
    private CoreMapperTimerTask coreMapperTimerTask;

    private PropertyChangeSupport environmentLoadedListener
            = new PropertyChangeSupport(this);
    private PropertyChangeSupport elevatorCallLoadedListener
            = new PropertyChangeSupport(this);
    private PropertyChangeSupport elevatorLoadedListener
            = new PropertyChangeSupport(this);
    private PropertyChangeSupport floorLoadedListener
            = new PropertyChangeSupport(this);

    public CoreMapperImpl() throws MalformedURLException, RemoteException, NotBoundException {
        this.elevator = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
        this.coreMapperTimerTask = new CoreMapperTimerTask(this);
        this.coreMapperTimer = new Timer("CoreMapper Timer");
        this.coreMapperTimer.schedule(this.coreMapperTimerTask, REMOTE_FETCH_INTERVAL);
    }

    public void updateFloors() throws RemoteException {
        for (int i = 0; i < elevator.getFloorNum(); ++i) {
            var floor = new Floor(i);

        }
    }

    @Override
    public void updateElevators() throws RemoteException {
        for (int i = 0; i < this.elevator.getElevatorNum(); ++i) {
            var localElevator = new LocalElevator(i);
            localElevator.setCurrentAcceleration(elevator.getElevatorAccel(i));
            localElevator.setCurrentFloor(elevator.getElevatorFloor(i));
            localElevator.setCurrentPosition(elevator.getElevatorPosition(i));
            localElevator.setTargetFloor(elevator.getTarget(i));
            localElevator.setDoorState(DoorState.from(elevator.getElevatorDoorStatus(i)));
            localElevator.setLbsWeight(elevator.getElevatorWeight(i));
            localElevator.setCurrentSpeed(elevator.getElevatorSpeed(i));
            localElevator.setMode(new ElevatorModeAuto());
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
    public void addElevatorCallLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorCallLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeElevatorCallLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorCallLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void addFloorLoadedEventListener(PropertyChangeListener listener) {
        this.floorLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeFloorLoadedEventListener(PropertyChangeListener listener) {
        this.floorLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void addElevatorLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeElevatorLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorLoadedListener.removePropertyChangeListener(listener);
    }
}
