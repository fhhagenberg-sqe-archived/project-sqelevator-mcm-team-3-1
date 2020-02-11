/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

/**
 * @author jmayr
 */
public interface ICoreMapper {

    public void schedulePeriodicUpdates(int intervalMs);

    public void cancelPeriodicUpdates();


    public void loadEnvironment() throws RemoteException;

    public void updateEnvironment() throws RemoteException;

    public void loadFloors() throws RemoteException;

    public void updateFloors() throws RemoteException;

    public void loadElevators() throws RemoteException;

    public void updateElevators() throws RemoteException;

    /**
     * Function is used to navigate the elevator.
     *
     * @param e           ILocalElevator elevator that should go to the defined floor
     * @param floorNumber number of floor, the elevator should go to
     */
    public void setTargetFloor(ILocalElevator e, int floorNumber) throws RemoteException;

    public void setMode(int elevatorNumber, IElevatorMode mode) throws RemoteException;

    public void addMappingLoadedListener(PropertyChangeListener listener);

    public void removeMappingLoadedListener(PropertyChangeListener listener);
}
