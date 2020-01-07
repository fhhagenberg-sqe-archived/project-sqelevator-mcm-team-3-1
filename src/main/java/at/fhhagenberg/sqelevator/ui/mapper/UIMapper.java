/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.mapper;

import at.fhhagenberg.sqelevator.enums.ElevatorModeType;
import at.fhhagenberg.sqelevator.interfaces.IBackendShader;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.BackendShaderEvent;
import at.fhhagenberg.sqelevator.propertychanged.event.UIEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

/**
 *
 * @author jmayr
 */
public class UIMapper implements PropertyChangeListener {

    private ILocalElevator selectedElevator;
    private LinkedList<ILocalElevator> elevators;
    private IEnvironment environment;
    private int enteredFloor;
    private final PropertyChangeSupport selectedElevatorListener
            = new PropertyChangeSupport(this);
    private final PropertyChangeSupport elevatorListener
            = new PropertyChangeSupport(this);
    private final PropertyChangeSupport environmentLoadedListener
            = new PropertyChangeSupport(this);
    private final PropertyChangeSupport saveFloorEnabledListener
            = new PropertyChangeSupport(this);
    private final PropertyChangeSupport updateErrorMessageListener
            = new PropertyChangeSupport(this);

    public UIMapper(IBackendShader shader) {
        shader.addEnvironmentLoadedEventListener(this);
        shader.addElevatorLoadedEventListener(this);
    }

    public void toggleMode() {
        System.out.println("TODO: Toggle Elevator mode!");

    }

    public void processInput(String input) {
        try {
            this.enteredFloor = Integer.parseInt(input);

        } catch (NumberFormatException ex) {
        }
    }

    public void selectElevator(ILocalElevator e) {
        var prev = this.selectedElevator;
        if (e != this.selectedElevator) {
            this.selectedElevator = e;
        } else {
            this.selectedElevator = null;
        }
        this.selectedElevatorListener.firePropertyChange(UIEvent.SELECTED_ELEVATOR_CHANGED, prev, this.selectedElevator);

    }

    public void addSelectedElevatorListener(PropertyChangeListener l) {
        this.selectedElevatorListener.addPropertyChangeListener(l);
    }

    public void removeSelectedElevatorListener(PropertyChangeListener l) {
        this.selectedElevatorListener.removePropertyChangeListener(l);
    }

    public void addElevatorListener(PropertyChangeListener l) {
        this.elevatorListener.addPropertyChangeListener(l);
    }

    public void removeElevatorListener(PropertyChangeListener l) {
        this.elevatorListener.removePropertyChangeListener(l);
    }

    public void addEnvironmentListener(PropertyChangeListener l) {
        this.environmentLoadedListener.addPropertyChangeListener(l);
    }

    public void removeEnvironmentListener(PropertyChangeListener l) {
        this.environmentLoadedListener.removePropertyChangeListener(l);
    }

    public void addSaveFloorEnabledListener(PropertyChangeListener l) {
        this.saveFloorEnabledListener.addPropertyChangeListener(l);
    }

    public void removeSaveFloorEnabledListener(PropertyChangeListener l) {
        this.saveFloorEnabledListener.removePropertyChangeListener(l);
    }

    public void addUpdateErrorMessageListener(PropertyChangeListener l) {
        this.updateErrorMessageListener.addPropertyChangeListener(l);
    }

    public void removeUpdateErrorMessageListener(PropertyChangeListener l) {
        this.updateErrorMessageListener.removePropertyChangeListener(l);
    }

    public void storeFloor() {
        //TODO: Store floor
        System.out.println("TODO: Store floor on update!");
    }

    public boolean isStorable() {
        return this.environment != null && this.selectedElevator != null
                && this.environment.isServicedBy(selectedElevator, enteredFloor)
                && this.selectedElevator.getCurrentMode().getModeType() == ElevatorModeType.MANUAL
                && this.enteredFloor >= 0 && this.enteredFloor < this.environment.getNumberOfFloors();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(BackendShaderEvent.ENVIRONMENT_LOADED)) {
            if (evt.getNewValue() != null) {
                this.environment = (IEnvironment) evt.getNewValue();
                this.environmentLoadedListener.firePropertyChange(UIEvent.ENVIRONMENT_LOADED, null, this.environment);
                this.elevators.forEach((e) -> {
                    this.elevatorListener.firePropertyChange(UIEvent.NEW_ELEVATOR_ADDED, null, e);
                });
            }
        } else if (evt.getPropertyName().equals(BackendShaderEvent.ELEVATOR_LOADED)) {
            ILocalElevator e = (ILocalElevator) evt.getNewValue();
            if (!this.elevators.contains(e)) {
                this.elevators.add(e);
                if (this.environment != null) {
                    this.elevatorListener.firePropertyChange(UIEvent.NEW_ELEVATOR_ADDED, null, e);
                }
            }
        }
    }

}
