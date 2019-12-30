/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.exceptions.NoNextFloorException;

/**
 *
 * @author jmayr
 */
public interface IEnvironment {
    public int getNumberOfFloors();
    public int getFloorHeightInFt();
    public void addFloorCall(int floor, ElevatorDirection direction);
    public void addFloorCall(IElevatorCall call);
    public IElevatorCall getNextFloorCall(ILocalElevator e) throws NoNextFloorException;
    public IElevatorCall getNextFloorCall(ILocalElevator e, ElevatorDirection direction) throws NoNextFloorException;
    public void addFloor(IFloor f);
    public void putBack(IElevatorCall call);
    public boolean hasCalls();
    public boolean hasCalls(ILocalElevator e);
}
