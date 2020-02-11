package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.propertychanged.event.EnvironmentEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Environment implements IEnvironment {
    private int numberOfElevators;
    private int numberOfFloors;
    private int floorHeight;
    private long clockTick;

    private PropertyChangeSupport clockTickListener = new PropertyChangeSupport(this);

    @Override
    public int getNumberOfElevators() {
        return numberOfElevators;
    }

    @Override
    public void setNumberOfElevators(int numberOfElevators) {
        this.numberOfElevators = numberOfElevators;
    }

    @Override
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    @Override
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public int getFloorHeight() {
        return floorHeight;
    }

    @Override
    public void setFloorHeight(int floorHeight) {
        this.floorHeight = floorHeight;
    }

    @Override
    public long getClockTick() {
        return clockTick;
    }

    @Override
    public void setClockTick(long clockTick) {
        if (this.clockTick != clockTick) {
            var old = this.clockTick;
            this.clockTick = clockTick;
            this.clockTickListener.firePropertyChange(EnvironmentEvent.CLOCK_TICK, old, clockTick);
        }
    }

    @Override
    public void addClockTickListener(PropertyChangeListener l) {
        this.clockTickListener.addPropertyChangeListener(l);
    }

    @Override
    public void removeClockTickListener(PropertyChangeListener l) {
        this.clockTickListener.removePropertyChangeListener(l);
    }
}
