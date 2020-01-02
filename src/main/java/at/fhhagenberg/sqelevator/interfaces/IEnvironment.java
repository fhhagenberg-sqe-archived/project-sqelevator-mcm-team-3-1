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
    /**
     * Gets the number of floors, the elevator goes to.
     * @return int number of floors in the environment
     */
    public int getNumberOfFloors();
    /**
     * Gets the height of a floor in thebuilding.
     * @return int height in ft.
     */
    public int getFloorHeightInFt();
    /**
     * function is used to place a new call from the environment
     * This is a call, that is set to call an elevator, not the call within the elevator
     * @param floor the floor, on which the elevator is called
     * @param direction the direction the caller wants to go.
     */
    public void addFloorCall(int floor, ElevatorDirection direction);
    /**
     * function is used to place a new call from the environment
     * This is a call, that is set to call an elevator, not the call within the elevator
     * @param call ElevatorCall containing hte call information
     */
    public void addFloorCall(IElevatorCall call);
    /**
     * Gets the next call from the queue holding the call intependent of it's direction
     * @param e The elevator, which will take the call. This is required, because not all elevators might be able to go to all floors.
     * @return ElevatorCall containing the call information
     * @throws NoNextFloorException This exception is thrown, when there is no call for the elevator available
     * This is done in favour of returning null, beccause it has to be caught, and can't be forgotten
     */
    public IElevatorCall getNextFloorCall(ILocalElevator e) throws NoNextFloorException;
    /**
     * Gets the next floor call depentend on the elevator and the direction
     * @param e ILocalElevator that will handle the call
     * @param direction ElevatorDirection, in which the call should go
     * @return IElevatorCall the first call of the queue fitting the requirements.
     * @throws NoNextFloorException This exception is thrown, when there is no call for the elevator available
     * This is done in favour of returning null, beccause it has to be caught, and can't be forgotten
     */
    public IElevatorCall getNextFloorCall(ILocalElevator e, ElevatorDirection direction) throws NoNextFloorException;
    /**
     * Function that adds an Floor with it's specific properties to the environment
     * @param f IFloor that should be added.
     */
    public void addFloor(IFloor f);
    /**
     * Function to put back a call, that was taken out of the queue.
     * This is normally done when an Elevator is set to manual, so no automatic handling will be done.
     * Also, this should be called, when an elevator can no longer fulfull the call because of some exceptional event.
     * E.g. Eerror state, ....
     * @param call IElevatorCall the call that should be put back in the queue
     */
    public void putBack(IElevatorCall call);
    /**
     * Function returns if there are any calls to be handled
     * @return boolean True if there are calls to be handled, false otherwhire
     */
    public boolean hasCalls();
    /**
     * Function that returns if there are any calls, that can be handled by a specific elevator.
     * @param e ILocalElevator that is supposed to handle a call
     * @return boolean true if the call can be handled, false otherwhise.
     */
    public boolean hasCalls(ILocalElevator e);
}
