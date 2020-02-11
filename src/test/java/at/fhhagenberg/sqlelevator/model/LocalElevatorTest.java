package at.fhhagenberg.sqlelevator.model;

import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.enums.ElevatorDirection;
import at.fhhagenberg.sqelevator.enums.ElevatorState;
import at.fhhagenberg.sqelevator.model.LocalElevator;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LocalElevatorTest {

    @Test
    public void testSetMode() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var mode = new ElevatorModeManual();
        elevator.setMode(mode);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(mode, argument.getValue().getNewValue());
        assertEquals(mode, elevator.getMode());
    }

    @Test
    public void testSetModeSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var mode = new ElevatorModeAuto();
        elevator.setMode(mode);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetSelectedFloors() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        int[] selected = {1, 2};
        elevator.setSelectedFloors(selected);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(selected, argument.getValue().getNewValue());
        assertEquals(selected, elevator.getSelectedFloors());
    }

    @Test
    public void testSetSelectedFloorsSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        int[] selected = {};
        elevator.setSelectedFloors(selected);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetDoorState() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var state = DoorState.OPEN;
        elevator.setDoorState(state);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(state, argument.getValue().getNewValue());
        assertEquals(state, elevator.getDoorState());
    }

    @Test
    public void testSetDoorStateSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var state = DoorState.UNKNOWN;
        elevator.setDoorState(state);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetElevatorState() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var state = ElevatorState.ACTIVE;
        elevator.setElevatorState(state);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(state, argument.getValue().getNewValue());
        assertEquals(state, elevator.getElevatorState());
    }

    @Test
    public void testSetElevatorStateSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var state = ElevatorState.UNKNOWN;
        elevator.setElevatorState(state);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetDirection() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var direction = ElevatorDirection.UP;
        elevator.setDirection(direction);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(direction, argument.getValue().getNewValue());
        assertEquals(direction, elevator.getDirection());
    }

    @Test
    public void testSetDirectionSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var direction = ElevatorDirection.UNSET;
        elevator.setDirection(direction);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetFloor() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var floor = 9;
        elevator.setFloor(floor);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(floor, argument.getValue().getNewValue());
        assertEquals(floor, elevator.getFloor());
    }

    @Test
    public void testSetFloorSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var floor = -1;
        elevator.setFloor(floor);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetTargetFloor() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var targetFloor = 9;
        elevator.setTargetFloor(targetFloor);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(targetFloor, argument.getValue().getNewValue());
        assertEquals(targetFloor, elevator.getTargetFloor());
    }

    @Test
    public void testSetTargetFloorSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var targetFloor = -1;
        elevator.setTargetFloor(targetFloor);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetWeight() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var weight = 100;
        elevator.setWeight(weight);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(weight, argument.getValue().getNewValue());
        assertEquals(weight, elevator.getWeight());
    }

    @Test
    public void testSetWeightSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var weight = -1;
        elevator.setWeight(weight);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetCapacity() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var capacity = 100;
        elevator.setCapacity(capacity);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(capacity, argument.getValue().getNewValue());
        assertEquals(capacity, elevator.getCapacity());
    }

    @Test
    public void testSetCapacitySame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var capacity = 0;
        elevator.setCapacity(capacity);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetSpeed() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var speed = 100;
        elevator.setSpeed(speed);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(speed, argument.getValue().getNewValue());
        assertEquals(speed, elevator.getSpeed());
    }

    @Test
    public void testSetSpeedSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var speed = 0;
        elevator.setSpeed(speed);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetAcceleration() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var acceleration = 100;
        elevator.setAcceleration(acceleration);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(acceleration, argument.getValue().getNewValue());
        assertEquals(acceleration, elevator.getAcceleration());
    }

    @Test
    public void testSetAccelerationSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var acceleration = 0;
        elevator.setSpeed(acceleration);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetPosition() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var position = 5;
        elevator.setPosition(position);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(elevatorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(position, argument.getValue().getNewValue());
        assertEquals(position, elevator.getPosition());
    }

    @Test
    public void testSetPositionSame() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var position = 0;
        elevator.setSpeed(position);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetPositionListenerRemoved() {
        var elevatorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var elevator = new LocalElevator(1);
        assertEquals(1, elevator.getElevatorNumber());
        elevator.addElevatorUpdatedListener(elevatorUpdatedListenerMock);
        elevator.removeElevatorUpdatedListener(elevatorUpdatedListenerMock);

        var position = 5;
        elevator.setSpeed(position);

        verify(elevatorUpdatedListenerMock, times(0)).propertyChange(any());
    }

}
