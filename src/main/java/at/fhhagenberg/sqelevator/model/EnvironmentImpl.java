package at.fhhagenberg.sqelevator.model;

import at.fhhagenberg.sqelevator.interfaces.IEnvironment;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EnvironmentImpl implements IEnvironment {
    private int numberOfElevators;
    private int numberOfFloors;
    private int floorHeight;
    private long clockTick;

    private PropertyChangeSupport clockTickListener = new PropertyChangeSupport(this);

    @Override
    public int getNumberOfElevators() {
        return numberOfElevators;
    }

    public void setNumberOfElevators(int numberOfElevators) {
        this.numberOfElevators = numberOfElevators;
    }

    @Override
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public int getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(int floorHeight) {
        this.floorHeight = floorHeight;
    }

    public long getClockTick() {
        return clockTick;
    }

    public void setClockTick(long clockTick) {
        this.clockTick = clockTick;
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
