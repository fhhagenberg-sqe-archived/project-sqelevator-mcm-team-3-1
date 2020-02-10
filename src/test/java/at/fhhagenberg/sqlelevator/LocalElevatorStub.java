package at.fhhagenberg.sqlelevator;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;

import java.beans.PropertyChangeListener;

public class LocalElevatorStub implements ILocalElevator {

    @Override
    public int getElevatorNumber() {
        return 1;
    }

    @Override
    public int[] getSelectedFloors() {
        return new int[]{2, 3};
    }

    @Override
    public void setSelectedFloors(int[] selectedFloors) {

    }

    @Override
    public int getFloor() {
        return 0;
    }

    @Override
    public void setFloor(int currentFloor) {

    }

    @Override
    public DoorState getDoorState() {
        return DoorState.CLOSED;
    }

    @Override
    public void setDoorState(DoorState doorState) {

    }

    @Override
    public ElevatorState getElevatorState() {
        return ElevatorState.ACTIVE;
    }

    @Override
    public void setElevatorState(ElevatorState elevatorState) {

    }

    @Override
    public int getSpeed() {
        return 20;
    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public int getAcceleration() {
        return 5;
    }

    @Override
    public void setAcceleration(int acceleration) {

    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public void setPosition(int position) {

    }

    @Override
    public int getWeight() {
        return 200;
    }

    @Override
    public void setWeight(int weight) {

    }

    @Override
    public int getCapacity() {
        return 1000;
    }

    @Override
    public void setCapacity(int capacity) {

    }

    @Override
    public int getTargetFloor() {
        return 2;
    }

    @Override
    public void setTargetFloor(int targetFloor) {

    }

    @Override
    public ElevatorDirection getDirection() {
        return ElevatorDirection.UP;
    }

    @Override
    public void setDirection(ElevatorDirection direction) {

    }

    @Override
    public IElevatorMode getMode() {
        return new ElevatorModeManual();
    }

    @Override
    public void setMode(IElevatorMode mode) {

    }

    @Override
    public void addElevatorUpdatedListener(PropertyChangeListener l) {

    }

    @Override
    public void removeElevatorUpdatedListener(PropertyChangeListener l) {

    }
}
