package at.fhhagenberg.sqlelevator.controller;

import at.fhhagenberg.sqelevator.controller.CoreMapperTimerTask;
import at.fhhagenberg.sqelevator.interfaces.ICoreMapper;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.mockito.Mockito.*;

public class CoreMapperTimerTaskTest {

    @Test
    public void testUpdatingCoreMapper() throws Exception {
        var coreMapperMock = mock(ICoreMapper.class);
        var coreMapperTimerTask = new CoreMapperTimerTask(coreMapperMock);
        coreMapperTimerTask.run();
        verify(coreMapperMock, times(1)).updateEnvironment();
        verify(coreMapperMock, times(1)).updateElevators();
        verify(coreMapperMock, times(1)).updateFloors();
    }

    @Test
    public void testRemoteExceptionWhenEnvironmentUpdated() throws Exception {
        var coreMapperMock = mock(ICoreMapper.class);
        doThrow(RemoteException.class).when(coreMapperMock).updateEnvironment();
        var coreMapperTimerTask = new CoreMapperTimerTask(coreMapperMock);

        coreMapperTimerTask.run();

        verify(coreMapperMock, times(1)).updateEnvironment();
        verify(coreMapperMock, times(0)).updateElevators();
        verify(coreMapperMock, times(0)).updateFloors();
    }
}
