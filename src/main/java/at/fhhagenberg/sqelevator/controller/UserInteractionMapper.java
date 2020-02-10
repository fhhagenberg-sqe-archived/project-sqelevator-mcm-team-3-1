/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.controller;

import at.fhhagenberg.sqelevator.enums.ElevatorModeType;
import at.fhhagenberg.sqelevator.interfaces.*;
import at.fhhagenberg.sqelevator.propertychanged.event.CoreMapperEvent;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * @author jmayr
 */
public class UserInteractionMapper implements IUserInteractionMapper {

    private ICoreMapper shader;
    private ILocalElevator selectedElevator;
    private LinkedList<ILocalElevator> elevators = new LinkedList<>();
    private IEnvironment environment;
    private int enteredFloor;

    private final PropertyChangeSupport uiEventListener = new PropertyChangeSupport(this);

    public UserInteractionMapper(ICoreMapper shader) throws RemoteException {
        this.shader = shader;
        shader.addMappingLoadedListener(this);
    }

    public void storeFloor() throws RemoteException {
        if (this.isStorable()) {
            shader.setTargetFloor(selectedElevator, enteredFloor);
        }
    }

    public void toggleMode(int elevatorNumber, IElevatorMode mode) throws RemoteException {
        shader.setMode(elevatorNumber, mode);
    }

    public void processInput(String input) {
        if (this.selectedElevator != null) {
            try {
                this.enteredFloor = Integer.parseInt(input);
                this.uiEventListener.firePropertyChange(UIEvent.SAVE_FLOOR_ENABLED, null, this.isStorable());
            } catch (NumberFormatException ex) {
                this.enteredFloor = -1;
            }
        }
    }

    public void selectElevator(ILocalElevator e) {
        var prev = this.selectedElevator;
        if (e != this.selectedElevator) {
            this.selectedElevator = e;
        } else {
            this.selectedElevator = null;
        }
        this.uiEventListener.firePropertyChange(UIEvent.SELECTED_ELEVATOR_CHANGED, prev, this.selectedElevator);
    }

    public void addUiEventListener(PropertyChangeListener l) {
        this.uiEventListener.addPropertyChangeListener(l);
    }

    public void removeUiEventListener(PropertyChangeListener l) {
        this.uiEventListener.removePropertyChangeListener(l);
    }

    private boolean isStorable() {
        return this.environment != null
                && this.selectedElevator != null
                && this.selectedElevator.getFloor() != this.enteredFloor
                && this.selectedElevator.getMode().getModeType() == ElevatorModeType.MANUAL
                && this.enteredFloor >= 0 && this.enteredFloor < this.environment.getNumberOfFloors();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(CoreMapperEvent.ENVIRONMENT_LOADED)) {
            if (evt.getNewValue() != null) {
                this.environment = (IEnvironment) evt.getNewValue();
                this.uiEventListener.firePropertyChange(UIEvent.ENVIRONMENT_LOADED, null, environment);
                this.elevators.forEach((localElevator) -> {
                    this.uiEventListener.firePropertyChange(UIEvent.NEW_ELEVATOR_ADDED, null, localElevator);
                });
            }
        } else if (evt.getPropertyName().equals(CoreMapperEvent.ELEVATOR_LOADED)) {
            ILocalElevator localElevator = (ILocalElevator) evt.getNewValue();
            if (!this.elevators.contains(localElevator)) {
                this.elevators.add(localElevator);
                if (this.environment != null) {
                    this.uiEventListener.firePropertyChange(UIEvent.NEW_ELEVATOR_ADDED, null, localElevator);
                }
            }
        } else if (evt.getPropertyName().equals(CoreMapperEvent.FLOOR_LOADED)) {
            IFloor floor = (IFloor) evt.getNewValue();
            if (floor != null) {
                this.uiEventListener.firePropertyChange(UIEvent.FLOOR_LOADED, null, floor);
            }
        }
    }

    @Override
    public void toggleDoorState() {
        System.out.println("TODO: Toggle door state");
    }

    public void setEnvironment(IEnvironment environment) {
        this.environment = environment;
    }
}
