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
import at.fhhagenberg.sqelevator.propertychanged.event.ElevatorEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jmayr
 */
public class LocalElevator implements ILocalElevator {

    private final int elevatorNumber;
    private int[] selectedFloors = {};
    private DoorState doorState = DoorState.UNKNOWN;
    private ElevatorDirection direction = ElevatorDirection.UNSET;
    private IElevatorMode mode;
    private ElevatorState lastState = ElevatorState.UNKNOWN;
    private int currentFloor = -1;
    private int targetFloor = -1;
    private int lbsWeight = -1;
    private int currentSpeedFts = 0;
    private int currentAccelerationFtsqr = 0;
    private int currentPosition = 0;
    private int capacity = 0;

    private PropertyChangeSupport selectedFloorsListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport doorStateListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport directionListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport stateListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport floorListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport targetListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport weightListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport capacityListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport accelerationListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport positionListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport speedListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport modeListener = new PropertyChangeSupport(this);

    public LocalElevator(int elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getElevatorNumber() {
        return this.elevatorNumber;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int[] getSelectedFloors() {
        return this.selectedFloors;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean setMode(IElevatorMode mode) {
        if (mode != null) {
            var old = mode;
            this.mode = mode;
            this.modeListener.firePropertyChange(ElevatorEvent.MODE, old, this.mode);

        }
        return mode != null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DoorState getDoorState() {
        return this.doorState;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ElevatorState getElevatorState() {
        return this.lastState;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setElevatorState(ElevatorState state) {
        if (this.lastState != state) {
            var old = this.lastState;
            this.lastState = state;
            this.stateListener.firePropertyChange(ElevatorEvent.CURRENT_STATE, lastState, mode);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCurrentSpeedInFts() {
        return this.currentSpeedFts;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getAccelerationInFtsqr() {
        return this.currentAccelerationFtsqr;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCurrentWeightInLbs() {
        return this.currentPosition;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getTargetFloor() {
        return this.targetFloor;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean updateElevatorData(ILocalElevator other) {
        if (other.getElevatorNumber() == this.elevatorNumber) {
            this.setDirection(other.getDirection());
            this.setSelectedFloors(other.getSelectedFloors());
            this.setDoorState(other.getDoorState());
            this.setCurrentFloor(other.getCurrentFloor());
            this.setTargetFloor(other.getTargetFloor());
            this.setLbsWeight(this.getCurrentWeightInLbs());
            this.setCurrentSpeed(other.getCurrentSpeedInFts());
            this.setCurrentAcceleration(other.getAccelerationInFtsqr());
            this.setCurrentPosition(other.getCurrentPosition());
        }
        return other.getElevatorNumber() == this.elevatorNumber;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ElevatorDirection getDirection() {
        return this.direction;
    }

    /**
     * @inheritDoc
     */
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

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return elevatorNumber + direction.hashCode() + Arrays.hashCode(this.selectedFloors) + this.currentPosition;
    }

    /**
     * @inheritDoc
     */
    @Override
    public IElevatorMode getCurrentMode() {
        return this.mode;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ElevatorState getCurrentState() {
        // TODO: Add actual checkings to check the elevator state
        return ElevatorState.UNKNOWN;
    }

    public void setSelectedFloors(int[] selectedFloors) {
        if (this.selectedFloors.length != selectedFloors.length) {
            var old = this.selectedFloors;
            this.selectedFloors = selectedFloors;
            this.selectedFloorsListener.firePropertyChange(ElevatorEvent.SELECTED_FLOORS, old, selectedFloors);
        } else {
            boolean hasNew = false;
            for (int selection : selectedFloors) {
                boolean contains = false;
                for (int old : this.selectedFloors) {
                    if (old == selection) {
                        contains = true;
                    }
                }
                hasNew = hasNew || !contains; //previously detected a new element or has a new element in previous check
            }
            if (hasNew) {
                var old = this.selectedFloors;
                this.selectedFloors = selectedFloors;
                this.selectedFloorsListener.firePropertyChange(ElevatorEvent.SELECTED_FLOORS, old, selectedFloors);
            }
        }
    }

    public void setDoorState(DoorState state) {
        if (this.doorState != state && state != null) {
            var old = this.doorState;
            this.doorState = state;
            this.doorStateListener.firePropertyChange(ElevatorEvent.DOOR_STATE, old, this.doorState);
        }
    }

    public void setDirection(ElevatorDirection direction) {
        if (this.direction != direction && direction != null) {
            var old = this.direction;
            this.direction = direction;
            this.directionListener.firePropertyChange(ElevatorEvent.DIRECTION, old, this.direction);
        }
    }

    public void setCurrentFloor(int floor) {
        if (this.currentFloor != floor) {
            var old = this.currentFloor;
            this.currentFloor = floor;
            this.floorListener.firePropertyChange(ElevatorEvent.CURRENT_FLOOR, old, this.currentFloor);
        }
    }

    public void setTargetFloor(int target) {
        if (this.targetFloor != target) {
            var old = this.targetFloor;
            this.targetFloor = target;
            this.targetListener.firePropertyChange(ElevatorEvent.TARGET_FLOOR, old, this.targetFloor);
        }
    }

    public void setLbsWeight(int weight) {
        if (this.lbsWeight != weight && weight > -1) {
            var old = this.lbsWeight;
            this.lbsWeight = weight;
            this.weightListener.firePropertyChange(ElevatorEvent.LBS_WEIGHT, old, this.lbsWeight);
            this.updateElevatorData(this);
        }
    }

    public void setCapacity(int capacity) {
        if (this.capacity != capacity && capacity > 0) {
            this.capacityListener.firePropertyChange(ElevatorEvent.CAPACITY, this.capacity, capacity);
            this.capacity = capacity;
        }
    }

    public void setCurrentSpeed(int speed) {
        if (this.currentSpeedFts != speed) {
            var old = this.currentSpeedFts;
            this.currentSpeedFts = speed;
            this.speedListener.firePropertyChange(ElevatorEvent.CURRENT_SPEED_FTS, old, this.currentSpeedFts);
        }
    }

    public void setCurrentAcceleration(int acceleration) {
        if (this.currentAccelerationFtsqr != acceleration) {
            var old = this.currentAccelerationFtsqr;
            this.currentAccelerationFtsqr = acceleration;
            this.accelerationListener.firePropertyChange(ElevatorEvent.CURRENT_ACCELERATION_FTSQR, old, this.currentAccelerationFtsqr);
        }
    }

    public void setCurrentPosition(int pos) {
        if (this.currentPosition != pos) {
            var old = this.currentPosition;
            this.currentPosition = pos;
            this.positionListener.firePropertyChange(ElevatorEvent.CURRENT_POSITION, old, this.currentPosition);
        }
    }

    @Override
    public void addSelectedFloorsListener(PropertyChangeListener l) {
        this.selectedFloorsListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeSelectedFloorsListener(PropertyChangeListener l) {
        this.selectedFloorsListener.removePropertyChangeListener(l);
    }

    @Override
    public void addDoorStateListener(PropertyChangeListener l) {
        this.doorStateListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeDoorStateListener(PropertyChangeListener l) {
        this.doorStateListener.removePropertyChangeListener(l);
    }

    @Override
    public void addDirectionListener(PropertyChangeListener l) {
        this.directionListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeDirectionListener(PropertyChangeListener l) {
        this.directionListener.removePropertyChangeListener(l);
    }

    @Override
    public void addStateListener(PropertyChangeListener l) {
        this.stateListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeStateListener(PropertyChangeListener l) {
        this.stateListener.removePropertyChangeListener(l);
    }

    @Override
    public void addFloorListener(PropertyChangeListener l) {
        this.floorListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeFloorListener(PropertyChangeListener l) {
        this.floorListener.removePropertyChangeListener(l);
    }

    @Override
    public void addTargetListener(PropertyChangeListener l) {
        this.targetListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeTargetListener(PropertyChangeListener l) {
        this.targetListener.removePropertyChangeListener(l);
    }

    @Override
    public void addCurrentWeightListener(PropertyChangeListener l) {
        this.weightListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeCurrentWeightListener(PropertyChangeListener l) {
        this.weightListener.removePropertyChangeListener(l);
    }

    @Override
    public void addCurrentSpeedListener(PropertyChangeListener l) {
        this.speedListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeCurrentSpeedListener(PropertyChangeListener l) {
        this.speedListener.removePropertyChangeListener(l);
    }

    @Override
    public void addAccelerationListener(PropertyChangeListener l) {
        this.accelerationListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeAccelerationListener(PropertyChangeListener l) {
        this.accelerationListener.removePropertyChangeListener(l);
    }

    @Override
    public void addPositionListener(PropertyChangeListener l) {
        this.positionListener.addPropertyChangeListener(l);
    }

    @Override
    public void removePositionListener(PropertyChangeListener l) {
        this.positionListener.removePropertyChangeListener(l);
    }

    @Override
    public void addModeListener(PropertyChangeListener l) {
        this.modeListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeModeListener(PropertyChangeListener l) {
        this.modeListener.removePropertyChangeListener(l);
    }

    @Override
    public void removeAllListener(PropertyChangeListener l) {
        selectedFloorsListener.removePropertyChangeListener(l);
        doorStateListener.removePropertyChangeListener(l);
        directionListener.removePropertyChangeListener(l);
        stateListener.removePropertyChangeListener(l);
        floorListener.removePropertyChangeListener(l);
        targetListener.removePropertyChangeListener(l);
        weightListener.removePropertyChangeListener(l);
        accelerationListener.removePropertyChangeListener(l);
        positionListener.removePropertyChangeListener(l);
        speedListener.removePropertyChangeListener(l);
        modeListener.removePropertyChangeListener(l);
    }
}
