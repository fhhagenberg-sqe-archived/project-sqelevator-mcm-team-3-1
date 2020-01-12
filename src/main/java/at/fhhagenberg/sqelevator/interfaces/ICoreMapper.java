/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

/**
 *
 * @author jmayr
 */
public interface ICoreMapper {

    /**
     * The interval in milliseconds on which the elevator data is going to be fetched from the remote
     */
    int REMOTE_FETCH_INTERVAL = 100;

    /**
     * Updates the accessible values of the elevators
     * @throws RemoteException
     */
    public void updateElevators() throws RemoteException;

    /**
     * Function is used to navigate the elevator.
     *
     * @param e ILocalElevator elevator that should go to the defined floor
     * @param floorNumber number of floor, the elevator should go to
     */
    public void setTargetFloor(ILocalElevator e, int floorNumber) throws RemoteException;

    /**
     *
     * @param listener
     */
    public void addEnvironmentLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeEnvironmentLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void addElevatorCallLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeElevatorCallLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void addFloorLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeFloorLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void addElevatorLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeElevatorLoadedEventListener(PropertyChangeListener listener);

}
