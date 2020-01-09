/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.exceptions.NoNextFloorException;
import at.fhhagenberg.sqelevator.interfaces.IElevatorCall;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.IFloor;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.CoreMapperEvent;
import at.fhhagenberg.sqelevator.propertychanged.event.EnvironmentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

/**
 *
 * @author jmayr
 */
public class Environment implements IEnvironment {

    private LinkedList<IElevatorCall> unhandledCalls = new LinkedList<>();
    private LinkedList<IElevatorCall> handledCalls = new LinkedList<>();
    private LinkedList<IFloor> floors = new LinkedList<>();
    private PropertyChangeSupport callRemovedHandler
            = new PropertyChangeSupport(this);
    private PropertyChangeSupport newCallHandler
            = new PropertyChangeSupport(this);
    private int floorHeight = 0;
    private int numberOfFloors;
    private int numberOfElevators;

    /**
     * @inheritDoc
     */
    @Override
    public int getNumberOfFloors() {
        return this.numberOfFloors;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getFloorHeightInFt() {
        return this.floorHeight;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addFloorCall(IElevatorCall call) {
        if (!this.handledCalls.contains(call) && !this.unhandledCalls.contains(call)) {
            this.unhandledCalls.addLast(call);
            this.newCallHandler.firePropertyChange(EnvironmentEvent.ELEVATOR_CALL_ADDED, null, call);
        }
    }

    /**
     * @inheritDoc
     */
    public void confirmHandle(IElevatorCall c) {
        if (this.handledCalls.contains(c)) {
            this.handledCalls.remove(c);
            this.callRemovedHandler.firePropertyChange(EnvironmentEvent.ELEVATOR_CALL_REMOVED, c, null);
        }
    }

    /**
     * @inheritDoc
     */
    public boolean isServicedBy(ILocalElevator e, int floorNumber) {
        for (var floor : this.floors) {
            if (floor.getFloorNumber() == floorNumber) {
                return floor.isServicedBy(e);
            }
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public IElevatorCall getNextFloorCall(ILocalElevator e) throws NoNextFloorException {
        IElevatorCall[] calls = (IElevatorCall[]) this.unhandledCalls.stream().
                filter(call -> this.getFloorByNo(
                call.getFloorNumber()).isServicedBy(e)).toArray();
        if (calls.length > 0) {
            this.unhandledCalls.remove(calls[0]);
            this.handledCalls.add(calls[0]);
            return calls[0];
        }
        throw new NoNextFloorException();
    }

    /**
     * @inheritDoc
     */
    @Override
    public IElevatorCall getNextFloorCall(ILocalElevator e, ElevatorDirection direction) throws NoNextFloorException {
        IElevatorCall[] calls = (IElevatorCall[]) this.unhandledCalls.stream().
                filter(call -> this.getFloorByNo(
                call.getFloorNumber()).isServicedBy(e)
                && call.getDirection() == direction).toArray();
        if (calls.length > 0) {
            this.unhandledCalls.remove(calls[0]);
            this.handledCalls.add(calls[0]);
            return calls[0];
        }
        throw new NoNextFloorException();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addFloor(IFloor f) {
        if (!this.floors.contains(f)) {
            this.floors.add(f);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void putBack(IElevatorCall call) {
        this.handledCalls.remove(call);
        this.unhandledCalls.addFirst(call);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasCalls() {
        return this.unhandledCalls.size() > 0;
    }

    private IFloor getFloorByNo(int floorNumber) {
        for (IFloor f : this.floors) {
            if (f.getFloorNumber() == floorNumber) {
                return f;
            }
        }
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasCalls(ILocalElevator e) {
        IElevatorCall[] calls = (IElevatorCall[]) this.unhandledCalls.stream().
                filter(call -> this.getFloorByNo(
                call.getFloorNumber()).isServicedBy(e)).toArray();
        return calls.length > 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public LinkedList<IElevatorCall> getAllFloorCalls() {
        LinkedList<IElevatorCall> c = new LinkedList<>();
        c.addAll(this.unhandledCalls);
        c.addAll(this.handledCalls);
        return c;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getNumberOfElevators() {
        return this.numberOfElevators;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addCallRemovedListener(PropertyChangeListener l) {
        this.callRemovedHandler.addPropertyChangeListener(l);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeCallRemovedListener(PropertyChangeListener l) {
        this.callRemovedHandler.removePropertyChangeListener(l);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addCallAddedListener(PropertyChangeListener l) {
        this.newCallHandler.addPropertyChangeListener(l);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeCallAddedListener(PropertyChangeListener l) {
        this.newCallHandler.removePropertyChangeListener(l);
    }

    public boolean setFloorHeight(int height) {
        if (height > 0) {
            this.floorHeight = height;
        }

        return height > 0;
    }

    public boolean setNumberOfFloors(int numberOfFloors) {
        if (numberOfFloors > 0) {
            this.numberOfFloors = numberOfFloors;
        }
        return numberOfFloors > 0;
    }

    public void setNumberOfElevators(int numberOfElevators) {
        if (numberOfElevators > 0) {
            this.numberOfElevators = numberOfElevators;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case CoreMapperEvent.FLOOR_LOADED:
                this.addFloor((IFloor) evt.getNewValue());
                break;
            case CoreMapperEvent.ELEVATOR_CALL_LOADED:
                this.addFloorCall((IElevatorCall) evt.getNewValue());
                break;
        }
    }
}
