/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.enums.ElevatorModeType;

/**
 *
 * @author jmayr
 */
public interface IElevatorMode {

    /**
     * Sets the mode of the elevator
     *
     * @param type ElevatorModeType
     */
    public void setModeType(ElevatorModeType type);

    /**
     * Returns the current type of the current elevatorMode
     *
     * @return ElevatorModeType type
     */
    public ElevatorModeType getModeType();
}
