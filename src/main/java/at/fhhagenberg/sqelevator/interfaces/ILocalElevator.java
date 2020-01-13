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
     * Retrieves the maximum number of passengers that can fit on the elevator
     *
     * @return maximum number of passengers that can fit
     */
    public int getCapacity();

    /**
     * Gets the floor the elevator is targeting to.
     *
     * @return int number of the floor the elevator is heading to.
     */
    public int getTargetFloor();

    /**
     * Gets the direction the elevator going to
     *
     * @return ElevatorDirection direction the elevator is heading
     */
    public ElevatorDirection getDirection();

    /**
     * Updates alle the elevator data and it's states, but not it's mode The id
     * of both elevators must match
     *
     * @param other ILocalElevator elevator from which the data should be taken
     * @return boolean true if the data could be updated
     */
    public boolean updateElevatorData(ILocalElevator other);

    /**
     * Sets the mode of the elevator
     *
     * @param mode IElevatorMode the new mode that will be used for navigation
     * @return boolean true when the mode was changed
     */
    public boolean setMode(IElevatorMode mode);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addSelectedFloorsListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeSelectedFloorsListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addDoorStateListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeDoorStateListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addDirectionListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeDirectionListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addStateListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeStateListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addFloorListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeFloorListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addTargetListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeTargetListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addCurrentWeightListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeCurrentWeightListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addCurrentSpeedListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeCurrentSpeedListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addAccelerationListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeAccelerationListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addPositionListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removePositionListener(PropertyChangeListener l);

    /**
     * Function takes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be added
     */
    public void addModeListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeModeListener(PropertyChangeListener l);

    /**
     * Function removes a listener, that is notified on changes
     *
     * @param l ElevatorChangedListener listener that should be removed
     */
    public void removeAllListener(PropertyChangeListener l);

    public IElevatorMode getCurrentMode();

    /**
     * function returns the current state of the elevator
     *
     * @return ElevatorState current state of the elevator
     */
    public ElevatorState getCurrentState();

    /**
     * function sets the state of the elevator
     *
     * @param state ElevatorState current state of the elevator
     */
    public void setElevatorState(ElevatorState state);
}
