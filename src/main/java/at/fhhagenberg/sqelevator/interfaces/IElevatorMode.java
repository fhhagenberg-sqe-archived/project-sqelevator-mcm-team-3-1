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
     * Function returns the next floor, the elevator should go to
     *
     * @return int containing the number of the next floor.
     */
    public int getNextFloor();

    /**
     * Sets the environment, the elevator mode uses
     *
     * @param e
     */
    public void setEnvironment(IEnvironment e);

    /**
     * Returns the current type of the current elevatorMode
     *
     * @return ElevatorModeType type
     */
    public ElevatorModeType getModeType();
}
