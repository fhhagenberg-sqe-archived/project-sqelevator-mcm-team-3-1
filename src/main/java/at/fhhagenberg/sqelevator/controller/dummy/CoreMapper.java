/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.controller.dummy;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.interfaces.IBackendInteractionMapper;
import at.fhhagenberg.sqelevator.model.Environment;
import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.model.LocalElevator;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.model.ElevatorCall;
import at.fhhagenberg.sqelevator.propertychanged.event.CoreMapperEvent;

/**
 *
 * @author jmayr
 */
public class CoreMapper implements ICoreMapper {

    private Environment environment;

    private PropertyChangeSupport environmentLoadedListener
            = new PropertyChangeSupport(this);
    private PropertyChangeSupport elevatorCallLoadedListener
            = new PropertyChangeSupport(this);
    private PropertyChangeSupport elevatorLoadedListener
            = new PropertyChangeSupport(this);
    private PropertyChangeSupport floorLoadedListener
            = new PropertyChangeSupport(this);

    public void dummyGenerateFloors() {
        Floor f1 = new Floor(0);
        Floor f2 = new Floor(1);
        Floor f3 = new Floor(2);
        Floor f4 = new Floor(3);

        f1.setServicedBy(0);
        f1.setServicedBy(1);
        f1.setServicedBy(2);

        f2.setServicedBy(0);
        f2.setServicedBy(1);
        f2.setServicedBy(2);

        f3.setServicedBy(2);

        f4.setServicedBy(0);
        f4.setServicedBy(2);

        pause();
        this.floorLoadedListener.firePropertyChange(CoreMapperEvent.FLOOR_LOADED, null, f1);
        pause();
        this.floorLoadedListener.firePropertyChange(CoreMapperEvent.FLOOR_LOADED, null, f2);
        this.floorLoadedListener.firePropertyChange(CoreMapperEvent.FLOOR_LOADED, null, f3);
        pause();
        this.floorLoadedListener.firePropertyChange(CoreMapperEvent.FLOOR_LOADED, null, f4);
    }

    public void dummyElevators() {
        LocalElevator e1 = new LocalElevator(0);
        LocalElevator e2 = new LocalElevator(1);
        LocalElevator e3 = new LocalElevator(2);
        int[] selectedFloors1 = {1, 3};
        //
        e1.setCurrentAcceleration(0);
        e1.setCurrentFloor(0);
        e1.setCurrentPosition(0);
        e1.setTargetFloor(1);
        e1.setDoorState(DoorState.OPEN);
        e1.setSelectedFloors(selectedFloors1);
        e1.setMaxLoad(670);
        e1.setLbsWeight(300);
        e1.setCurrentSpeed(0);

        int[] selectedFloors2 = {1};
        e2.setSelectedFloors(selectedFloors2);
        e2.setCurrentAcceleration(0);
        e2.setCurrentFloor(4);
        e2.setCurrentPosition(0);
        e2.setTargetFloor(2);
        e2.setDoorState(DoorState.CLOSED);
        e2.setMaxLoad(670);
        e2.setLbsWeight(300);
        e2.setCurrentSpeed(0);

        int[] selectedFloors3 = {0, 1, 3};
        e3.setSelectedFloors(selectedFloors3);
        e3.setCurrentAcceleration(0);
        e3.setCurrentFloor(2);
        e3.setCurrentPosition(0);
        e3.setTargetFloor(1);
        e3.setDoorState(DoorState.OPEN);
        e3.setMaxLoad(670);
        e3.setLbsWeight(300);
        e3.setCurrentSpeed(0);
        pause();
        this.elevatorLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_LOADED, null, e1);
        this.elevatorLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_LOADED, null, e2);
        pause();
        this.elevatorLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_LOADED, null, e3);
    }

    public void dummyCalls() {
        ElevatorCall c1 = new ElevatorCall(ElevatorDirection.UP, 0);
        pause();
        this.elevatorCallLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_CALL_LOADED, null, c1);
        ElevatorCall c2 = new ElevatorCall(ElevatorDirection.DOWN, 2);
        pause();
        this.elevatorCallLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_CALL_LOADED, null, c2);
        ElevatorCall c3 = new ElevatorCall(ElevatorDirection.UP, 2);
        pause();
        this.elevatorCallLoadedListener.firePropertyChange(CoreMapperEvent.ELEVATOR_CALL_LOADED, null, c3);
    }

    public void dummyGenerateEnvironment() {

        this.environment = new Environment();
        environment.setNumberOfFloors(4);
        environment.setFloorHeight(8);
        environment.setNumberOfElevators(3);
        this.addElevatorCallLoadedEventListener(environment);
        this.addFloorLoadedEventListener(environment);
        this.environmentLoadedListener.firePropertyChange(CoreMapperEvent.ENVIRONMENT_LOADED, null, this.environment);
    }

    public void dummyFakeLoad() {
        //Thread t = new Thread() {
          //  public void run() {
                System.out.println("Generating environment");
                dummyGenerateEnvironment();
                System.out.println("Generating Floors");
                dummyGenerateFloors();
                System.out.println("Generating elevators");
                dummyElevators();
                System.out.println("Generating calls");
                
                dummyCalls();
            //}
       // };

       // t.start();
    }

    @Override
    public void setBackendMapper(IBackendInteractionMapper mapper) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEnvironmentLoadedEventListener(PropertyChangeListener listener) {
        this.environmentLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void addElevatorCallLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorCallLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void addElevatorLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorLoadedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeEnvironmentLoadedEventListener(PropertyChangeListener listener) {
        this.environmentLoadedListener.removePropertyChangeListener(listener);
    }

    @Override
    public void removeElevatorCallLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorCallLoadedListener.removePropertyChangeListener(listener);
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
    public void removeElevatorLoadedEventListener(PropertyChangeListener listener) {
        this.elevatorLoadedListener.removePropertyChangeListener(listener);
    }

    @Override
    public void setTargetFloor(ILocalElevator e, int floorNumber) {

    }

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}
