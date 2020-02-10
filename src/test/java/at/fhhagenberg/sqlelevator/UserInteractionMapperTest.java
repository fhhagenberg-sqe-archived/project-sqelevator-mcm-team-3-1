package at.fhhagenberg.sqlelevator;

import at.fhhagenberg.sqelevator.controller.UserInteractionMapper;
import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;
import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.interfaces.ILocalElevator;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeAuto;
import at.fhhagenberg.sqelevator.model.dummy.ElevatorModeManual;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserInteractionMapperTest {

    @Test
    public void testToggleMode() throws Exception {
        var coreMapperMock = mock(ICoreMapper.class);
        var userInteractionMapper = new UserInteractionMapper(coreMapperMock);
        var elevatorNumber = 0;
        var mode = new ElevatorModeAuto();

        userInteractionMapper.toggleMode(elevatorNumber, mode);
        verify(coreMapperMock, times(1)).setMode(elevatorNumber, mode);
    }

    @Test
    public void testSelectElevator() throws Exception {
        var elevatorMock = mock(ILocalElevator.class);
        var coreMapperMock = mock(ICoreMapper.class);
        var selectedElevatorListenerMock = mock(PropertyChangeListener.class);
        var userInteractionMapper = new UserInteractionMapper(coreMapperMock);

        userInteractionMapper.addUiEventListener(selectedElevatorListenerMock);
        userInteractionMapper.selectElevator(elevatorMock);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(selectedElevatorListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(elevatorMock, argument.getValue().getNewValue());
    }

    @Test
    public void testUnselectElevator() throws Exception {
        var elevatorMock = mock(ILocalElevator.class);
        var coreMapperMock = mock(ICoreMapper.class);
        var uiEventListenerMock = mock(PropertyChangeListener.class);
        var userInteractionMapper = new UserInteractionMapper(coreMapperMock);

        userInteractionMapper.addUiEventListener(uiEventListenerMock);
        userInteractionMapper.selectElevator(elevatorMock);
        userInteractionMapper.selectElevator(elevatorMock);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(uiEventListenerMock, times(2)).propertyChange(argument.capture());
        var firstPropertyChange = argument.getAllValues().get(0);
        var secondPropertyChange = argument.getAllValues().get(1);

        assertEquals(elevatorMock, firstPropertyChange.getNewValue());
        assertNull(firstPropertyChange.getOldValue());

        assertNull(secondPropertyChange.getNewValue());
        assertEquals(elevatorMock, secondPropertyChange.getOldValue());
    }

    @Test
    public void testProcessInput() throws Exception {
        var elevatorMock = mock(ILocalElevator.class);
        var coreMapperMock = mock(ICoreMapper.class);
        var uiEventListenerMock = mock(PropertyChangeListener.class);
        var environmentMock = mock(IEnvironment.class);
        var userInteractionMapper = new UserInteractionMapper(coreMapperMock);

        when(environmentMock.getNumberOfFloors()).thenReturn(2);
        when(elevatorMock.getMode()).thenReturn(new ElevatorModeManual());

        userInteractionMapper.setEnvironment(environmentMock);
        userInteractionMapper.addUiEventListener(uiEventListenerMock);
        userInteractionMapper.selectElevator(elevatorMock);

        userInteractionMapper.processInput("1");

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(uiEventListenerMock, times(2)).propertyChange(argument.capture());

        assertTrue((Boolean) argument.getValue().getNewValue());
    }

    @Test
    public void testStoreFloor() throws Exception {
        var elevatorMock = mock(ILocalElevator.class);
        var coreMapperMock = mock(ICoreMapper.class);
        var userInteractionMapper = new UserInteractionMapper(coreMapperMock);
        var environmentMock = mock(IEnvironment.class);

        when(environmentMock.getNumberOfFloors()).thenReturn(2);
        when(elevatorMock.getMode()).thenReturn(new ElevatorModeManual());

        userInteractionMapper.setEnvironment(environmentMock);
        userInteractionMapper.selectElevator(elevatorMock);
        userInteractionMapper.processInput("1");
        userInteractionMapper.storeFloor();
        verify(coreMapperMock, times(1)).setTargetFloor(elevatorMock, 1);
    }
}
