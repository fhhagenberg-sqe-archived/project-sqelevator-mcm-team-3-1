/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.interfaces.IFloor;
import at.fhhagenberg.sqelevator.propertychanged.event.FloorEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author jmayr
 */
public class Floor implements IFloor {
    private int floorNumber;
    private boolean floorButtonDown;
    private boolean floorButtonUp;

    private PropertyChangeSupport floorUpdatedListener = new PropertyChangeSupport(this);

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
            floorUpdatedListener.firePropertyChange(FloorEvent.FLOOR_BUTTON_DOWN, this.floorButtonDown, active);
            this.floorButtonDown = active;
        }
    }

    @Override
    public void setFloorButtonUp(boolean active) {
        if (active != this.floorButtonUp) {
            floorUpdatedListener.firePropertyChange(FloorEvent.FLOOR_BUTTON_UP, this.floorButtonUp, active);
            this.floorButtonUp = active;
        }
    }

    @Override
    public void addFloorUpdatedListener(PropertyChangeListener listener) {
        this.floorUpdatedListener.addPropertyChangeListener(listener);
    }

    @Override
    public void removeFloorUpdatedListener(PropertyChangeListener listener) {
        this.floorUpdatedListener.removePropertyChangeListener(listener);
    }
}
