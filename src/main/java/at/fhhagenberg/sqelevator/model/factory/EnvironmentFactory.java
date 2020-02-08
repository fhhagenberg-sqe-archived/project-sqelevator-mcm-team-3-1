package at.fhhagenberg.sqelevator.model.factory;

import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.model.EnvironmentImpl;

public class EnvironmentFactory {
    public IEnvironment createEnvironment() {
        return new EnvironmentImpl();
    }
}
