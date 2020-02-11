package at.fhhagenberg.sqlelevator.model;

import at.fhhagenberg.sqelevator.model.Environment;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnvironmentTest {
    @Test
    public void testSetClockTick() throws Exception {
        var clockTickListenerMock = mock(PropertyChangeListener.class);
        var environment = new Environment();
        environment.setNumberOfElevators(3);
        environment.setNumberOfFloors(4);
        environment.setFloorHeight(300);
        assertEquals(3, environment.getNumberOfElevators());
        assertEquals(4, environment.getNumberOfFloors());
        assertEquals(300, environment.getFloorHeight());

        environment.addClockTickListener(clockTickListenerMock);
        environment.setClockTick(999);

        ArgumentCaptor<PropertyChangeEvent> argument = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(clockTickListenerMock, times(1)).propertyChange(argument.capture());

        assertEquals(999, (Long) argument.getValue().getNewValue());
        assertEquals(999, environment.getClockTick());
    }

    @Test
    public void testSetClockTickSame() throws Exception {
        var clockTickListenerMock = mock(PropertyChangeListener.class);
        var environment = new Environment();
        environment.addClockTickListener(clockTickListenerMock);
        environment.setClockTick(0);
        verify(clockTickListenerMock, times(0)).propertyChange(any());
        assertEquals(0, environment.getClockTick());
    }

    @Test
    public void testSetClockTickListenerRemoved() throws Exception {
        var clockTickListenerMock = mock(PropertyChangeListener.class);
        var environment = new Environment();
        environment.addClockTickListener(clockTickListenerMock);
        environment.removeClockTickListener(clockTickListenerMock);
        environment.setClockTick(999);
        verify(clockTickListenerMock, times(0)).propertyChange(any());
    }
}
