/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.interfaces.IFloor;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import java.util.LinkedList;

/**
 *
 * @author jmayr
 */
public class Floor implements IFloor {

    private LinkedList<Integer> servicedBy = new LinkedList<>();
    private int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public int getFloorNumber() {
        return this.floorNumber;
    }

    @Override
    public boolean isServicedBy(ILocalElevator e) {
        return this.servicedBy.contains(e.getElevatorNumber());
    }

    @Override
    public boolean setServicedBy(ILocalElevator e) {
        return this.setServicedBy(e.getElevatorNumber());
    }

    @Override
    public boolean setServicedBy(int elevatorNumber) {
        if (!this.servicedBy.contains(elevatorNumber)) {
            this.servicedBy.add(elevatorNumber);
        }
        return this.servicedBy.contains(elevatorNumber);
    }

}
