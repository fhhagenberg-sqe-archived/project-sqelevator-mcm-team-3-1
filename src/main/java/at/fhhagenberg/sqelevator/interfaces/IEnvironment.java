/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import java.beans.PropertyChangeListener;

/**
 * @author jmayr
 */
public interface IEnvironment {

    /**
     * Function returns the number of elevators in the environment
     *
     * @return int number of elevators
     */
    public int getNumberOfElevators();

    /**
     * Gets the number of floors, the elevator goes to.
     *
     * @return int number of floors in the environment
     */
    public int getNumberOfFloors();

    /**
     * Gets the height of a floor in the building.
     *
     * @return int height in ft.
     */
    public int getFloorHeight();

    /**
     * Gets the clock tick rate of the elevator control system
     *
     * @return clock tick rate of the elevator control system
     */
    public long getClockTick();

    public void addClockTickListener(PropertyChangeListener l);

    public void removeClockTickListener(PropertyChangeListener l);
}
