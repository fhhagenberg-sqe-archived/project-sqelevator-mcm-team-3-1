/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import at.fhhagenberg.sqelevator.propertychanged.event.ElevatorEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

/**
 * @author jmayr
 */
public class LocalElevator implements ILocalElevator {

    private final int elevatorNumber;
    private int[] selectedFloors = {};
    private DoorState doorState = DoorState.UNKNOWN;
    private ElevatorDirection direction = ElevatorDirection.UNSET;
    private IElevatorMode mode = new ElevatorModeAuto();
    private ElevatorState lastState = ElevatorState.UNKNOWN;
    private int currentFloor = -1;
    private int targetFloor = -1;
    private int lbsWeight = -1;
    private int currentSpeedFts = 0;
    private int currentAccelerationFtsqr = 0;
    private int currentPosition = 0;
    private int capacity = 0;

    private PropertyChangeSupport elevatorUpdatedListener = new PropertyChangeSupport(this);

    public LocalElevator(int elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
    }

    @Override
    public int getElevatorNumber() {
        return this.elevatorNumber;
    }

    @Override
    public int[] getSelectedFloors() {
        return this.selectedFloors;
    }

    @Override
    public void setMode(IElevatorMode mode) {
        if (mode != null) {
            this.mode = mode;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.MODE, null, this.mode);
        }
    }

    @Override
    public int getFloor() {
        return this.currentFloor;
    }

    @Override
    public DoorState getDoorState() {
        return this.doorState;
    }

    @Override
    public ElevatorState getElevatorState() {
        return this.lastState;
    }

    @Override
    public int getSpeed() {
        return this.currentSpeedFts;
    }

    @Override
    public int getAcceleration() {
        return this.currentAccelerationFtsqr;
    }

    @Override
    public int getPosition() {
        return this.currentPosition;
    }

    @Override
    public int getWeight() {
        return this.currentPosition;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getTargetFloor() {
        return this.targetFloor;
    }

    @Override
    public ElevatorDirection getDirection() {
        return this.direction;
    }

    @Override
    public IElevatorMode getMode() {
        return this.mode;
    }

    @Override
    public void setSelectedFloors(int[] selectedFloors) {
        if (this.selectedFloors.length != selectedFloors.length) {
            var old = this.selectedFloors;
            this.selectedFloors = selectedFloors;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.SELECTED_FLOORS, old, selectedFloors);
        }
    }

    @Override
    public void setDoorState(DoorState state) {
        if (this.doorState != state && state != null) {
            var old = this.doorState;
            this.doorState = state;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.DOOR_STATE, old, this.doorState);
        }
    }

    @Override
    public void setElevatorState(ElevatorState state) {
        if (this.lastState != state) {
            var old = this.lastState;
            this.lastState = state;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.CURRENT_STATE, old, mode);
        }
    }

    @Override
    public void setDirection(ElevatorDirection direction) {
        if (this.direction != direction && direction != null) {
            var old = this.direction;
            this.direction = direction;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.DIRECTION, old, this.direction);
        }
    }

    @Override
    public void setFloor(int floor) {
        if (this.currentFloor != floor) {
            var old = this.currentFloor;
            this.currentFloor = floor;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.CURRENT_FLOOR, old, this.currentFloor);
        }
    }

    @Override
    public void setTargetFloor(int target) {
        if (this.targetFloor != target) {
            var old = this.targetFloor;
            this.targetFloor = target;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.TARGET_FLOOR, old, this.targetFloor);
        }
    }

    @Override
    public void setWeight(int weight) {
        if (this.lbsWeight != weight && weight > -1) {
            var old = this.lbsWeight;
            this.lbsWeight = weight;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.LBS_WEIGHT, old, this.lbsWeight);
        }
    }

    @Override
    public void setCapacity(int capacity) {
        if (this.capacity != capacity && capacity > 0) {
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.CAPACITY, this.capacity, capacity);
            this.capacity = capacity;
        }
    }

    @Override
    public void setSpeed(int speed) {
        if (this.currentSpeedFts != speed) {
            var old = this.currentSpeedFts;
            this.currentSpeedFts = speed;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.CURRENT_SPEED_FTS, old, this.currentSpeedFts);
        }
    }

    @Override
    public void setAcceleration(int acceleration) {
        if (this.currentAccelerationFtsqr != acceleration) {
            var old = this.currentAccelerationFtsqr;
            this.currentAccelerationFtsqr = acceleration;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.CURRENT_ACCELERATION_FTSQR, old, this.currentAccelerationFtsqr);
        }
    }

    @Override
    public void setPosition(int pos) {
        if (this.currentPosition != pos) {
            var old = this.currentPosition;
            this.currentPosition = pos;
            this.elevatorUpdatedListener.firePropertyChange(ElevatorEvent.CURRENT_POSITION, old, this.currentPosition);
        }
    }

    @Override
    public void addElevatorUpdatedListener(PropertyChangeListener l) {
        this.elevatorUpdatedListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeElevatorUpdatedListener(PropertyChangeListener l) {
        this.elevatorUpdatedListener.removePropertyChangeListener(l);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        LocalElevator elevator = (LocalElevator) other;
        return this.elevatorNumber == elevator.elevatorNumber;
    }

    @Override
    public int hashCode() {
        return elevatorNumber + direction.hashCode() + Arrays.hashCode(this.selectedFloors) + this.currentPosition;
    }
}
