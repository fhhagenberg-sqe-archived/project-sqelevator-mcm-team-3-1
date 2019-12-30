/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorState;

/**
 *
 * @author jmayr
 */
public interface ILocalElevator {
    public int getElevatorNumber();
    public int[] getSelectedFloors();
    public void updateSectedFloors(int[] floors);
    public boolean setMode(IElevatorMode mode);
    public int getCurrentFloor();
    public DoorState getDoorState();
    public ElevatorState getElevatorState();
    public double getCurrentSpeedInFts();
    public double getElevatorAccelerationInFtsqr();
    public double getCurrentPosition();
    public int getCurrentWeightInLbs();
    public int getLoadCapacityInLbs();
    public int getTargetFloor();
    public boolean updateElevatorData(ILocalElevator e);
    
}
