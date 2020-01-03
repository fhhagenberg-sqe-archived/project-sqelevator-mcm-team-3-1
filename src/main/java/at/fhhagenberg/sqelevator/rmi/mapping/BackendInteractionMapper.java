/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.rmi.mapping;

import at.fhhagenberg.sqelevator.interfaces.IBackendInteractionMapper;
import at.fhhagenberg.sqelevator.interfaces.IElevatorCall;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;

/**
 *
 * @author jmayr
 */
public class BackendInteractionMapper implements IBackendInteractionMapper{

    @Override
    public boolean setFloor(ILocalElevator e, int floor) {
        return false;
    }

    @Override
    public int getNumberOfFloors() {
        return 0;
    }

    @Override
    public int getNumberOfElevators() {
        return 0;
    }

    @Override
    public ILocalElevator getElevatorByNumber(int no) {
        return null;
    }

    @Override
    public IElevatorCall[] getCurrentCalls() {
        return new IElevatorCall[0];
    }

    @Override
    public IEnvironment getInitialEnvironment() {
        return null;
    }
}
