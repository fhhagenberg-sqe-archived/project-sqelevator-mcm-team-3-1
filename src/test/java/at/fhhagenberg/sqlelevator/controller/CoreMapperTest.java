package at.fhhagenberg.sqlelevator.controller;

import at.fhhagenberg.sqelevator.controller.CoreMapper;
import at.fhhagenberg.sqelevator.enums.DoorState;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;
import at.fhhagenberg.sqelevator.model.EnvironmentImpl;
import at.fhhagenberg.sqelevator.model.Floor;
import at.fhhagenberg.sqelevator.model.LocalElevator;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;
import at.fhhagenberg.sqelevator.model.factory.ElevatorFactory;
import at.fhhagenberg.sqelevator.model.factory.EnvironmentFactory;
import at.fhhagenberg.sqelevator.model.factory.FloorFactory;
import at.fhhagenberg.sqlelevator.ElevatorStub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.mockito.Mockito.*;

public class CoreMapperTest {

    private ElevatorStub elevatorStub;
    private CoreMapper coreMapper;

    @BeforeEach
    public void setup() {
        elevatorStub = new ElevatorStub();
    }

    @Test
    public void testEnvironmentLoaded() throws Exception {
        var environmentMock = mock(EnvironmentImpl.class);
        var environmentFactoryMock = mock(EnvironmentFactory.class);
        var mappingLoadedListenerMock = mock(PropertyChangeListener.class);
        when(environmentFactoryMock.createEnvironment()).thenReturn(environmentMock);

        coreMapper = new CoreMapper(elevatorStub, environmentFactoryMock, new ElevatorFactory(), new FloorFactory());
        coreMapper.addMappingLoadedListener(mappingLoadedListenerMock);

        coreMapper.loadEnvironment();
        verify(mappingLoadedListenerMock, times(1)).propertyChange(any());

        verify(environmentMock, times(1)).setClockTick(elevatorStub.getClockTick());
        verify(environmentMock, times(1)).setFloorHeight(elevatorStub.getFloorHeight());
        verify(environmentMock, times(1)).setNumberOfElevators(elevatorStub.getElevatorNum());
        verify(environmentMock, times(1)).setNumberOfFloors(elevatorStub.getFloorNum());
    }

    @Test
    public void testElevatorsLoaded() throws Exception {
        var elevatorMock = mock(LocalElevator.class);
        var elevatorFactoryMock = mock(ElevatorFactory.class);
        var mappingLoadedListenerMock = mock(PropertyChangeListener.class);
        when(elevatorFactoryMock.createElevator(0)).thenReturn(elevatorMock);
        when(elevatorFactoryMock.createElevator(1)).thenReturn(elevatorMock);
        when(elevatorFactoryMock.createElevator(2)).thenReturn(elevatorMock);

        coreMapper = new CoreMapper(elevatorStub, new EnvironmentFactory(), elevatorFactoryMock, new FloorFactory());
        coreMapper.addMappingLoadedListener(mappingLoadedListenerMock);

        coreMapper.loadEnvironment();
        verify(mappingLoadedListenerMock, times(1)).propertyChange(any());

        coreMapper.loadElevators();
        verify(mappingLoadedListenerMock, times(1 + elevatorStub.getElevatorNum())).propertyChange(any());

        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setAcceleration(elevatorStub.getElevatorAccel(elevatorMock.getElevatorNumber()));
        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setFloor(elevatorStub.getElevatorFloor(elevatorMock.getElevatorNumber()));
        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setPosition(elevatorStub.getElevatorPosition(elevatorMock.getElevatorNumber()));
        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setTargetFloor(elevatorStub.getTarget(elevatorMock.getElevatorNumber()));
        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setDoorState(DoorState.from(elevatorStub.getElevatorDoorStatus(elevatorMock.getElevatorNumber())));
        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setWeight(elevatorStub.getElevatorWeight(elevatorMock.getElevatorNumber()));
        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setCapacity(elevatorStub.getElevatorCapacity(elevatorMock.getElevatorNumber()));
        verify(elevatorMock, times(elevatorStub.getElevatorNum())).setSpeed(elevatorStub.getElevatorSpeed(elevatorMock.getElevatorNumber()));
    }

    @Test
    public void testFloorsLoaded() throws Exception {
        var floorMock = mock(Floor.class);
        var floorFactoryMock = mock(FloorFactory.class);
        var mappingLoadedListenerMock = mock(PropertyChangeListener.class);
        when(floorFactoryMock.createFloor(0)).thenReturn(floorMock);
        when(floorFactoryMock.createFloor(1)).thenReturn(floorMock);
        when(floorFactoryMock.createFloor(2)).thenReturn(floorMock);
        when(floorFactoryMock.createFloor(3)).thenReturn(floorMock);

        coreMapper = new CoreMapper(elevatorStub, new EnvironmentFactory(), new ElevatorFactory(), floorFactoryMock);
        coreMapper.addMappingLoadedListener(mappingLoadedListenerMock);

        coreMapper.loadEnvironment();
        verify(mappingLoadedListenerMock, times(1)).propertyChange(any());

        coreMapper.loadElevators();
        verify(mappingLoadedListenerMock, times(1 + elevatorStub.getElevatorNum())).propertyChange(any());

        coreMapper.loadFloors();
        verify(mappingLoadedListenerMock, times(1 + elevatorStub.getElevatorNum() + elevatorStub.getFloorNum())).propertyChange(any());

        verify(floorMock, times(elevatorStub.getFloorNum())).setFloorButtonDown(elevatorStub.getFloorButtonDown(floorMock.getFloorNumber()));
        verify(floorMock, times(elevatorStub.getFloorNum())).setFloorButtonUp(elevatorStub.getFloorButtonUp(floorMock.getFloorNumber()));
    }

    @Test
    public void testSetMode() throws Exception {
        var elevatorMock = mock(LocalElevator.class);
        var elevatorFactoryMock = mock(ElevatorFactory.class);
        when(elevatorMock.getMode()).thenReturn(new ElevatorModeManual());
        when(elevatorFactoryMock.createElevator(0)).thenReturn(elevatorMock);
        when(elevatorFactoryMock.createElevator(1)).thenReturn(elevatorMock);
        when(elevatorFactoryMock.createElevator(2)).thenReturn(elevatorMock);

        coreMapper = new CoreMapper(elevatorStub, new EnvironmentFactory(), elevatorFactoryMock, new FloorFactory());
        coreMapper.loadEnvironment();
        coreMapper.loadElevators();
        coreMapper.setMode(0, new ElevatorModeAuto());

        verify(elevatorMock, times(1)).setMode(any(IElevatorMode.class));
    }

    @Test
    public void testSetTargetFloor() throws Exception {
        final int elevatorNumber = 2;
        final int floorNumber = 5;
        var elevatorStubMock = mock(ElevatorStub.class);
        var elevatorMock = mock(LocalElevator.class);
        when(elevatorMock.getElevatorNumber()).thenReturn(elevatorNumber);
        var elevatorFactoryMock = mock(ElevatorFactory.class);

        coreMapper = new CoreMapper(elevatorStubMock, new EnvironmentFactory(), elevatorFactoryMock, new FloorFactory());
        coreMapper.setTargetFloor(elevatorMock, floorNumber);
        
        verify(elevatorStubMock).setTarget(elevatorNumber, floorNumber);
    }
}
