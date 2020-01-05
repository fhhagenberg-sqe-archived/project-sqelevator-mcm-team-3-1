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
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author jmayr
 */
public class LocalElevator implements ILocalElevator {

    private int elevatorNumber;
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
            boolean updated = false;
            updated = this.setDirection(other.getDirection()) || updated;
            updated = this.setSelectedFloors(other.getSelectedFloors()) || updated;
            updated = this.setDoorState(other.getDoorState()) || updated;
            updated = this.setCurrentFloor(other.getCurrentFloor()) || updated;
            updated = this.setTargetFloor(other.getTargetFloor()) || updated;
            updated = this.setLbsWeight(this.getCurrentWeightInLbs()) || updated;
            // updated = this.setMaxLoad(other.getLoadCapacityInLbs()) | updated; //DOES NOT CHANGE DURING RUNTIME
            updated = this.setCurrentSpeed(other.getCurrentSpeedInFts()) || updated;
            updated = this.setCurrentAcceleration(other.getAccelerationInFtsqr()) || updated;
            updated = this.setCurrentPosition(other.getCurrentPosition()) || updated;
            if (updated) {
                ...//TODO: rise events on change!
            }
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

    public boolean setSelectedFloors(int[] selectedFloors) {
        if (this.selectedFloors.length != selectedFloors.length) {
            this.selectedFloors = selectedFloors;
            return true;
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
                this.selectedFloors = selectedFloors;
            }
            return hasNew;
        }
    }

    public boolean setDoorState(DoorState state) {
        if (this.doorState != state && state != null) {
            this.doorState = state;
            return true;
        }
        return false;
    }

    public boolean setDirection(ElevatorDirection direction) {
        if (this.direction != direction && direction != null) {
            this.direction = direction;
            return true;
        }
        return false;
    }

    public boolean setCurrentFloor(int floor) {

        if (this.currentFloor != floor) {
            this.currentFloor = floor;
            return true;
        }
        return false;
    }

    public boolean setTargetFloor(int target) {
        if (this.targetFloor != target) {
            this.targetFloor = target;
            return true;
        }
        return false;
    }

    public boolean setLbsWeight(int weight) {
        if (this.lbsWeight != weight && weight > -1) {
            this.lbsWeight = weight;
            return true;
        }
        return false;
    }

    public boolean setMaxLoad(int maxLoad) {
        if (this.lbsMaxLoad != maxLoad) {
            this.lbsMaxLoad = maxLoad;
            return true;
        }
        return false;
    }

    public boolean setCurrentSpeed(int speed) {
        if (this.currentSpeedFts != speed) {
            this.currentSpeedFts = speed;
            return true;
        }
        return false;
    }

    public boolean setCurrentAcceleration(int acceleration) {
        if (this.currentAccelerationFtsqr != acceleration) {
            this.currentAccelerationFtsqr = acceleration;
            return true;
        }
        return false;
    }

    public boolean setCurrentPosition(int pos) {
        if (this.currentPosition != pos) {
            this.currentPosition = pos;
            return true;
        }
        return false;
    }

}
