package at.fhhagenberg.sqelevator.model.factory;

import at.fhhagenberg.sqelevator.interfaces.IFloor;
import at.fhhagenberg.sqelevator.model.Floor;

public class FloorFactory {
    public IFloor createFloor(int floorNumber) {
        return new Floor(floorNumber);
    }
}
