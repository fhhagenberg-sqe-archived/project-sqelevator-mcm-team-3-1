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

    public int getNumberOfElevators();

    public void setNumberOfElevators(int numberOfElevators);

    public int getNumberOfFloors();

    public void setNumberOfFloors(int numberOfFloors);

    public int getFloorHeight();

    public void setFloorHeight(int floorHeight);

    public long getClockTick();

    public void setClockTick(long clockTick);

    public void addClockTickListener(PropertyChangeListener l);

    public void removeClockTickListener(PropertyChangeListener l);
}
