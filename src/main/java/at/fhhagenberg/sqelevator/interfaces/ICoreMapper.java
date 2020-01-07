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
public interface ICoreMapper {

    /**
     *
     * @param mapper
     */
    public void setBackendMapper(IBackendInteractionMapper mapper);

    /**
     * Function is used to navigate the elevator.
     *
     * @param e ILocalElevator elevator that should go to the defined floor
     * @param floorNumber number of floor, the elevator should go to
     */
    public void setTargetFloor(ILocalElevator e, int floorNumber);

    /**
     *
     * @param listener
     */
    public void addEnvironmentLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeEnvironmentLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void addElevatorCallLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeElevatorCallLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void addFloorLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeFloorLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void addElevatorLoadedEventListener(PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removeElevatorLoadedEventListener(PropertyChangeListener listener);

}
