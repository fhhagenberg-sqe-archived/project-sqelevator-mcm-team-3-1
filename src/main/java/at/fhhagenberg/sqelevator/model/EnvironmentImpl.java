package at.fhhagenberg.sqelevator.model;

public class EnvironmentImpl {
    private int numberOfElevators;
    private int numberOfFloors;
    private int floorHeight;
    private long clockTick;

    public int getNumberOfElevators() {
        return numberOfElevators;
    }

    public void setNumberOfElevators(int numberOfElevators) {
        this.numberOfElevators = numberOfElevators;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

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
}
