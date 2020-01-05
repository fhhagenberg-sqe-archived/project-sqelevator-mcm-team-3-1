/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import java.beans.PropertyChangeListener;

/**
 *
 * @author jmayr
 */
public interface ILocalElevator {

    /**
     * Gets the number of the elevator
     *
     * @return int number of the elevator
     */
    public int getElevatorNumber();

    /**
     * Gets all floors that are selected within the elevator
     *
     * @return integer array containing the selected floors;
     */
    public int[] getSelectedFloors();

    /**
     * Sets the mode of the elevator
     *
     * @param mode IElevatorMode the new mode that will be used for navigation
     * @return boolean true when the mode was changed
     */
    public boolean setMode(IElevatorMode mode);

    /**
     * Gets the floor the elevator is currently located
     *
     * @return int floor number
     */
    public int getCurrentFloor();

    /**
     * Gets the current state of the door.
     *
     * @return DoorState current state of the elevator doors
     */
    public DoorState getDoorState();

    /**
     * Gets the current state of the elevator
     *
     * @return ElevatorState state of the elevator
     */
    public ElevatorState getElevatorState();

    /**
     * Gets the current speed of the elevator in Ft/s
     *
     * @return int current speed ot the elevator in Ft/s
     */
    public int getCurrentSpeedInFts();

    /**
     * Gets the current acceleration of the Elevator in Ft/s^2
     *
     * @return int current acceleration of the elevator
     */
    public int getAccelerationInFtsqr();

    /**
     * Gets the current position in the building (measured from the bottom of
     * the building)
     *
     * @return int containing the position within the building
     */
    public int getCurrentPosition();

    /**
     * Gets the current weight that is in the elevator
     *
     * @return int current weight within the elevator
     */
    public int getCurrentWeightInLbs();

    /**
     * Gets the maximum load capacity of the elevator in Lb
     *
     * @return int load capacity of the elevator
     */
    public int getLoadCapacityInLbs();

    /**
     * Gets the floor the elevator is targeting to.
     *
     * @return int number of the floor the elevator is heading to.
     */
    public int getTargetFloor();

    /**
     * Updates alle the elevator data and it's states, but not it's mode The id
     * of both elevators must match
     *
     * @param other ILocalElevator elevator from which the data should be taken
     * @return boolean true if the data could be updated
     */
    public boolean updateElevatorData(ILocalElevator other);

    /**
     * Gets the direction the elevator going to
     *
     * @return ElevatorDirection direction the elevator is heading
     */
    public ElevatorDirection getDirection();

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeListener(PropertyChangeListener l);

}
