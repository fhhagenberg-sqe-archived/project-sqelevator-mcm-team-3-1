/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

/**
 *
 * @author jmayr
 */
public interface IUserInteractionMapper extends PropertyChangeListener {

    public void toggleMode(int elevatorNumber, IElevatorMode mode) throws RemoteException;

    public void toggleDoorState();

    public void processInput(String input);

    public void selectElevator(ILocalElevator e);

    public void addSelectedElevatorListener(PropertyChangeListener l);

    public void removeSelectedElevatorListener(PropertyChangeListener l);

    public void addElevatorListener(PropertyChangeListener l);

    public void removeElevatorListener(PropertyChangeListener l);

    public void addEnvironmentListener(PropertyChangeListener l);

    public void removeEnvironmentListener(PropertyChangeListener l);

    public void addSaveFloorEnabledListener(PropertyChangeListener l);

    public void removeSaveFloorEnabledListener(PropertyChangeListener l);

    public void addUpdateErrorMessageListener(PropertyChangeListener l);

    public void removeUpdateErrorMessageListener(PropertyChangeListener l);

    public void storeFloor() throws RemoteException;
}
