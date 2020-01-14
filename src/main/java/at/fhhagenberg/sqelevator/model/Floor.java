/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.interfaces.IFloor;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.propertychanged.event.FloorEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

/**
 * @author jmayr
 */
public class Floor implements IFloor {
    private int floorNumber;
    private boolean floorButtonDown;
    private boolean floorButtonUp;

    private PropertyChangeSupport floorButtonDownListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport floorButtonUpListener = new PropertyChangeSupport(this);

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public int getFloorNumber() {
        return this.floorNumber;
    }

    @Override
    public boolean getFloorButtonDown() {
        return this.floorButtonDown;
    }

    @Override
    public boolean getFloorButtonUp() {
        return this.floorButtonUp;
    }

    @Override
    public void setFloorButtonDown(boolean active) {
        if (active != this.floorButtonDown) {
            floorButtonDownListener.firePropertyChange(FloorEvent.FLOOR_BUTTON_DOWN, this.floorButtonDown, active);
            this.floorButtonDown = active;
        }
    }

    @Override
    public void setFloorButtonUp(boolean active) {
        if (active != this.floorButtonUp) {
            floorButtonUpListener.firePropertyChange(FloorEvent.FLOOR_BUTTON_UP, this.floorButtonUp, active);
            this.floorButtonUp = active;
        }
    }

    public void addFloorButtonDownListener(PropertyChangeListener listener) {
        this.floorButtonDownListener.addPropertyChangeListener(listener);
    }

    public void removeFloorButtonDownListener(PropertyChangeListener listener) {
        this.floorButtonDownListener.removePropertyChangeListener(listener);
    }

    public void addFloorButtonUpListener(PropertyChangeListener listener) {
        this.floorButtonUpListener.addPropertyChangeListener(listener);
    }

    public void removeFloorButtonUpListener(PropertyChangeListener listener) {
        this.floorButtonUpListener.removePropertyChangeListener(listener);
    }
}
