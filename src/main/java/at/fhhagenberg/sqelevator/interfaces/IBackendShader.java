/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import java.beans.PropertyChangeListener;

/**
 *
 * @author jmayr
 */
public interface IBackendShader {

    /**
     * 
     * @param mapper 
     */
    public void setBackendMapper(IBackendInteractionMapper mapper);

    /**
     * 
     * @param listener 
     */
    public void addEnvironmentLoadedEventListener(PropertyChangeListener listener);

    /**
     * 
     * @param listener 
     */
    public void addElevatorCallLoadedEventListener(PropertyChangeListener listener);

    /**
     * 
     * @param listener 
     */
    public void addFloorLoadedEventListener(PropertyChangeListener listener);

    /**
     * 
     * @param listener 
     */
    public void addElevatorLoadedEventListener(PropertyChangeListener listener);

}
