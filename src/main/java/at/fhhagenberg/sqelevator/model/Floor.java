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
    private LinkedList<Integer> servicedBy = new LinkedList<>();

    private PropertyChangeSupport floorButtonDownListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport floorButtonUpListener = new PropertyChangeSupport(this);
    private PropertyChangeSupport servicedByListener = new PropertyChangeSupport(this);

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

    @Override
    public boolean isServicedBy(ILocalElevator e) {
        return this.servicedBy.contains(e.getElevatorNumber());
    }

    @Override
    public boolean setServicedBy(ILocalElevator e, boolean service) {
        return this.setServicedBy(e.getElevatorNumber(), service);
    }

    @Override
    public boolean setServicedBy(int elevatorNumber, boolean service) {
        if (service && !this.servicedBy.contains(elevatorNumber)) {
            // TODO: Well old number should rather be all serviced floors?
            servicedByListener.firePropertyChange(FloorEvent.SERVICED_BY, null, elevatorNumber);
            this.servicedBy.add(elevatorNumber);
        }
        return this.servicedBy.contains(elevatorNumber);
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

    public void addServicedByListener(PropertyChangeListener listener) {
        this.servicedByListener.addPropertyChangeListener(listener);
    }

    public void removeServicedByListener(PropertyChangeListener listener) {
        this.servicedByListener.removePropertyChangeListener(listener);
    }
}
