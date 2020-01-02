/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

/**
 *
 * @author jmayr
 */
public interface IFloor {
    /**
     * Function that returns the floor number. 
     * @return int representing the floor number
     */
    public int getFloorNumber();
    /**
     * Function that checks if it can be serviced by a specific elevator
     * @param e ILocalElevator elevator that should be checked for
     * @return boolean true if the elevator can be serviced by the floor,
     * returns false otherwhise
     */
    public boolean isServicedBy(ILocalElevator e);
    /**
     * Function that adds an elevator, that services the specific floor.
     * @param e ILocalElevator elevator that services this floor
     * @return true when the elevator can be added.
     */
    public boolean setServicedBy(ILocalElevator e);
}
