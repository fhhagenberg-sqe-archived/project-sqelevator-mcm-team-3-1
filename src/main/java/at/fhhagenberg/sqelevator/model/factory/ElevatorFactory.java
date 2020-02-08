package at.fhhagenberg.sqelevator.model.factory;

import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.model.LocalElevator;

public class ElevatorFactory {
    public ILocalElevator createElevator(int elevatorNumber) {
        return new LocalElevator(elevatorNumber);
    }
}
