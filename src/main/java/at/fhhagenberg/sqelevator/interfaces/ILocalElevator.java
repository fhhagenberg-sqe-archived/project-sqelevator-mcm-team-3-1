/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;

import java.beans.PropertyChangeListener;

/**
 * @author jmayr
 */
public interface ILocalElevator {

    public int getElevatorNumber();

    public int[] getSelectedFloors();

    public void setSelectedFloors(int[] selectedFloors);

    public int getFloor();

    public void setFloor(int currentFloor);

    public DoorState getDoorState();

    public void setDoorState(DoorState doorState);

    public ElevatorState getElevatorState();

    public void setElevatorState(ElevatorState elevatorState);

    public int getSpeed();

    public void setSpeed(int speed);

    public int getAcceleration();

    public void setAcceleration(int acceleration);

    public int getPosition();

    public void setPosition(int position);

    public int getWeight();

    public void setWeight(int weight);

    public int getCapacity();

    public void setCapacity(int capacity);

    public int getTargetFloor();

    public void setTargetFloor(int targetFloor);

    public ElevatorDirection getDirection();

    public void setDirection(ElevatorDirection direction);

    public IElevatorMode getMode();

    public void setMode(IElevatorMode mode);

    public void addElevatorUpdatedListener(PropertyChangeListener l);

    public void removeElevatorUpdatedListener(PropertyChangeListener l);
}
