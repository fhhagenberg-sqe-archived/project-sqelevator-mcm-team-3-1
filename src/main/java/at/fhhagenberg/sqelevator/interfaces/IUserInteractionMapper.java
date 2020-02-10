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

    public void addUiEventListener(PropertyChangeListener l);

    public void removeUiEventListener(PropertyChangeListener l);

    public void storeFloor() throws RemoteException;
}
