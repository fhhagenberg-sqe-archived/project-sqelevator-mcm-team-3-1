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
import java.util.LinkedList;

/**
 *
 * @author jmayr
 */
public class Environment implements IEnvironment {

    private LinkedList<IElevatorCall> unhandledCalls;
    private LinkedList<IElevatorCall> handledCalls;
    private LinkedList<IFloor> floors;
    private int floorHeight = 0;
    private int numberOfFloors;

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
        }
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

}
