package at.fhhagenberg.sqelevator.controller;

import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class CoreMapperTimerTask extends TimerTask {

    private ICoreMapper coreMapper;

    CoreMapperTimerTask(ICoreMapper coreMapper) {
        this.coreMapper = coreMapper;
    }

    @Override
    public void run() {
        try {
            coreMapper.updateElevators();
        } catch (RemoteException rme) {
            System.err.println("Remote exception: " + rme.getMessage());
        }
    }
}
