/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.propertychanged.ElevatorCallLoadedListener;
import at.fhhagenberg.sqelevator.propertychanged.ElevatorLoadedListener;
import at.fhhagenberg.sqelevator.propertychanged.EnvironmentLoadedListener;
import at.fhhagenberg.sqelevator.propertychanged.FloorLoadedListener;

/**
 *
 * @author jmayr
 */
public interface IBackendShader {
    public void setBackendMapper(IBackendInteractionMapper mapper);
    public void addEnvironmentLoadedEventListener(EnvironmentLoadedListener listener);
    public void addElevatorCallLoadedEventListener(ElevatorCallLoadedListener listener);
    public void addFloorLoadedEventListener(FloorLoadedListener listener);
    public void addElevatorLoadedEventListener(ElevatorLoadedListener listener);
    
}
