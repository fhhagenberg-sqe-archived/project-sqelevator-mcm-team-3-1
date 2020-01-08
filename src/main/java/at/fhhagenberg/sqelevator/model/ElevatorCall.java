/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.interfaces.IElevatorCall;

/**
 *
 * @author jmayr
 */
public class ElevatorCall implements IElevatorCall {

    private ElevatorDirection direction;
    private int floorNumber;

    public ElevatorCall(ElevatorDirection direction, int floorNumber) {
        this.direction = direction;
        this.floorNumber = floorNumber;
    }

    @Override
    public ElevatorDirection getDirection() {
        return this.direction;
    }

    @Override
    public int getFloorNumber() {
        return this.floorNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ElevatorCall call = (ElevatorCall) other;
        return this.direction == call.direction
                && this.floorNumber == call.floorNumber;
    }

    @Override
    public int hashCode() {
        return floorNumber * direction.hashCode();
    }

}
