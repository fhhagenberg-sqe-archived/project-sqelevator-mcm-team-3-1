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
    public boolean setSectedFloors(int[] floors) {
        if (floors != null) {
            this.selectedFloors = floors;
        }
        return floors != null;
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
    public boolean updateElevatorData(ILocalElevator e) {
        if (e.getElevatorNumber() == this.elevatorNumber) {
            boolean changed = false;
            if (e.getDirection() != e.getDirection()) {
                this.direction = e.getDirection();
                changed = true;
            }
            ...//TODO: Add checkings, and also rise events on change!
        }
        return e.getElevatorNumber() == this.elevatorNumber;
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
        return elevatorNumber + direction.hashCode() + this.selectedFloors.hashCode() + this.currentPosition;
    }

}
