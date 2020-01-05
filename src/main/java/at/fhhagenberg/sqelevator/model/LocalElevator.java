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
import java.util.Arrays;

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
    private int currentFloor = -1;
    private int targetFloor = -1;
    private int lbsWeight = -1;
    private int lbsMaxLoad = -1;
    private int currentSpeedFts = 0;
    private int currentAccelerationFtsqr = 0;
    private int currentPosition = 0;
    private PropertyChangeSupport propertyChangedHandler
            = new PropertyChangeSupport(this);

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
            this.mode = mode;
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
     * TODO: Implement some self-checking of the elevator
     */
    @Override
    public ElevatorState getElevatorState() {
        return ElevatorState.UNKNOWN;
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
    public int getLoadCapacityInLbs() {
        return this.lbsMaxLoad;
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
    public void addListener(PropertyChangeListener l) {
        this.propertyChangedHandler.addPropertyChangeListener(l);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeListener(PropertyChangeListener l) {
        this.propertyChangedHandler.removePropertyChangeListener(l);
    }

    public void setSelectedFloors(int[] selectedFloors) {
        if (this.selectedFloors.length != selectedFloors.length) {
            var old = this.selectedFloors;
            this.selectedFloors = selectedFloors;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.SELECTED_FLOORS.name(), old, selectedFloors);
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
                this.propertyChangedHandler.firePropertyChange(ElevatorEvent.SELECTED_FLOORS.name(), old, selectedFloors);
            }
        }
    }

    public void setDoorState(DoorState state) {
        if (this.doorState != state && state != null) {
            var old = this.doorState;
            this.doorState = state;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.DOOR_STATE.name(), old, this.doorState);
        }
    }

    public void setDirection(ElevatorDirection direction) {
        if (this.direction != direction && direction != null) {
            var old = this.direction;
            this.direction = direction;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.DIRECTION.name(), old, this.direction);
        }
    }

    public void setCurrentFloor(int floor) {

        if (this.currentFloor != floor) {
            var old = this.currentFloor;
            this.currentFloor = floor;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.CURRENT_FLOOR.name(), old, this.currentFloor);
        }
    }

    public void setTargetFloor(int target) {
        if (this.targetFloor != target) {
            var old = this.targetFloor;
            this.targetFloor = target;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.TARGET_FLOOR.name(), old, this.targetFloor);
        }
    }

    public void setLbsWeight(int weight) {
        if (this.lbsWeight != weight && weight > -1) {
            var old = this.lbsWeight;
            this.lbsWeight = weight;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.LBS_WEIGHT.name(), old, this.lbsWeight);
        }
    }

    public void setMaxLoad(int maxLoad) {
        if (this.lbsMaxLoad != maxLoad) {
            var old = this.lbsMaxLoad;
            this.lbsMaxLoad = maxLoad;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.LBS_MAX_LOAD.name(), old, this.lbsMaxLoad);
        }
    }

    public void setCurrentSpeed(int speed) {
        if (this.currentSpeedFts != speed) {
            var old = this.currentSpeedFts;
            this.currentSpeedFts = speed;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.CURRENT_SPEED_FTS.name(), old, this.currentSpeedFts);
        }
    }

    public void setCurrentAcceleration(int acceleration) {
        if (this.currentAccelerationFtsqr != acceleration) {
            var old = this.currentAccelerationFtsqr;
            this.currentAccelerationFtsqr = acceleration;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.CURRENT_ACCELERATION_FTSQR.name(), old, this.currentAccelerationFtsqr);
        }
    }

    public void setCurrentPosition(int pos) {
        if (this.currentPosition != pos) {
            var old = this.currentPosition;
            this.currentPosition = pos;
            this.propertyChangedHandler.firePropertyChange(ElevatorEvent.CURRENT_POSITION.name(), old, this.currentPosition);
        }
    }
}
