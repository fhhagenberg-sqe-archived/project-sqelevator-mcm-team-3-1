/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.mapper;

import at.fhhagenberg.sqelevator.enums.ElevatorModeType;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

/**
 *
 * @author jmayr
 */
public class UIMapper {

    private ILocalElevator selectedElevator;
    private LinkedList<ILocalElevator> elevators;
    private int enteredFloor;
    private final PropertyChangeSupport selectedElevatorListener
            = new PropertyChangeSupport(this);
    private final PropertyChangeSupport elevatorListener
            = new PropertyChangeSupport(this);

    public void toggleMode() {

    }

    public void processInput(String input) {
        try {
            this.enteredFloor = Integer.parseInt(input);

        } catch (NumberFormatException ex) {
        }
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

    public void storeFloor() {
        //TODO: Store floor
    }

    public boolean isStorable() {
        return this.selectedElevator != null && this.selectedElevator.getCurrentMode().getModeType() == ElevatorModeType.MANUAL;
    }

}
