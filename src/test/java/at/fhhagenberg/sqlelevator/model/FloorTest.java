package at.fhhagenberg.sqlelevator.model;

import at.fhhagenberg.sqelevator.model.Floor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FloorTest {

    @Test
    public void testSetFloorButtonDown() throws Exception {
        var floorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var floor = new Floor(1);
        assertEquals(1, floor.getFloorNumber());
        floor.addFloorUpdatedListener(floorUpdatedListenerMock);

        floor.setFloorButtonDown(true);
        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(floorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertTrue((Boolean) argument.getValue().getNewValue());
        assertTrue(floor.getFloorButtonDown());
    }

    @Test
    public void testSetFloorButtonDownSame() throws Exception {
        var floorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var floor = new Floor(1);
        floor.addFloorUpdatedListener(floorUpdatedListenerMock);

        floor.setFloorButtonDown(false);
        verify(floorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetFloorButtonUp() throws Exception {
        var floorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var floor = new Floor(1);
        floor.addFloorUpdatedListener(floorUpdatedListenerMock);

        floor.setFloorButtonUp(true);
        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(floorUpdatedListenerMock, times(1)).propertyChange(argument.capture());

        assertTrue((Boolean) argument.getValue().getNewValue());
        assertTrue(floor.getFloorButtonUp());
    }

    @Test
    public void testSetFloorButtonUpSame() throws Exception {
        var floorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var floor = new Floor(1);
        floor.addFloorUpdatedListener(floorUpdatedListenerMock);

        floor.setFloorButtonUp(false);
        verify(floorUpdatedListenerMock, times(0)).propertyChange(any());
    }

    @Test
    public void testSetFloorButtonDownListenerRemoved() throws Exception {
        var floorUpdatedListenerMock = mock(PropertyChangeListener.class);
        var floor = new Floor(1);
        floor.addFloorUpdatedListener(floorUpdatedListenerMock);
        floor.removeFloorUpdatedListener(floorUpdatedListenerMock);

        floor.setFloorButtonDown(true);
        verify(floorUpdatedListenerMock, times(0)).propertyChange(any());
    }
}
