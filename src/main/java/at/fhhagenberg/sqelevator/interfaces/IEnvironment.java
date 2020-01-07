/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.exceptions.NoNextFloorException;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

/**
 *
 * @author jmayr
 */
public interface IEnvironment extends PropertyChangeListener{

    /**
     * Gets the number of floors, the elevator goes to.
     *
     * @return int number of floors in the environment
     */
    public int getNumberOfFloors();

    /**
     * Gets the height of a floor in thebuilding.
     *
     * @return int height in ft.
     */
    public int getFloorHeightInFt();

    /**
     * function is used to place a new call from the environment This is a call,
     * that is set to call an elevator, not the call within the elevator
     *
     * @param call ElevatorCall containing hte call information
     */
    public void addFloorCall(IElevatorCall call);

    /**
     * Gets the next call from the queue holding the call intependent of it's
     * direction
     *
     * @param e The elevator, which will take the call. This is required,
     * because not all elevators might be able to go to all floors.
     * @return ElevatorCall containing the call information
     * @throws NoNextFloorException This exception is thrown, when there is no
     * call for the elevator available This is done in favour of returning null,
     * beccause it has to be caught, and can't be forgotten
     */
    public IElevatorCall getNextFloorCall(ILocalElevator e) throws NoNextFloorException;

    /**
     * Gets the all flor calls no matter if they are currently beeing processes
     * or are still unhandled
     *
     * @return ElevatorCall containing the call information
     */
    public LinkedList<IElevatorCall> getAllFloorCalls();

    /**
     * Gets the next floor call depentend on the elevator and the direction
     *
     * @param e ILocalElevator that will handle the call
     * @param direction ElevatorDirection, in which the call should go
     * @return IElevatorCall the first call of the queue fitting the
     * requirements.
     * @throws NoNextFloorException This exception is thrown, when there is no
     * call for the elevator available This is done in favour of returning null,
     * beccause it has to be caught, and can't be forgotten
     */
    public IElevatorCall getNextFloorCall(ILocalElevator e, ElevatorDirection direction) throws NoNextFloorException;

    /**
     * Function that adds an Floor with it's specific properties to the
     * environment
     *
     * @param f IFloor that should be added.
     */
    public void addFloor(IFloor f);

    /**
     * Function is called after a certain floor has been handled.
     *
     * @param c IElevatorCall Call that was handled
     */
    public void confirmHandle(IElevatorCall c);

    /**
     * Function is used to check if a certain elevator can service a certain
     * floor.
     *
     * @param e Elevator that the might go to a certain floor
     * @param floorNumber Floor number the elevator wants to service
     * @return boolean true when the elevator can service the given floor, false
     * otherwhise
     */
    public boolean isServicedBy(ILocalElevator e, int floorNumber);

    /**
     * Function to put back a call, that was taken out of the queue. This is
     * normally done when an Elevator is set to manual, so no automatic handling
     * will be done. Also, this should be called, when an elevator can no longer
     * fulfull the call because of some exceptional event. E.g. Eerror state,
     * ....
     *
     * @param call IElevatorCall the call that should be put back in the queue
     */
    public void putBack(IElevatorCall call);

    /**
     * Function returns if there are any calls to be handled
     *
     * @return boolean True if there are calls to be handled, false otherwhire
     */
    public boolean hasCalls();

    /**
     * Function that returns if there are any calls, that can be handled by a
     * specific elevator.
     *
     * @param e ILocalElevator that is supposed to handle a call
     * @return boolean true if the call can be handled, false otherwhise.
     */
    public boolean hasCalls(ILocalElevator e);

    /**
     * Function takes a listener, that is notified when an element is removed
     * The removed element is placed in the old object, the new object is null
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addCallRemovedListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeCallRemovedListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes The new call is
     * placed in the new object, the old object is null, because there is no old
     * object
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addCallAddedListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeCallAddedListener(PropertyChangeListener l);

    /**
     * Function returns the number of elevators in the envrionment
     *
     * @return int number of elevators
     */
    public int getNumberOfElevators();
    
}
