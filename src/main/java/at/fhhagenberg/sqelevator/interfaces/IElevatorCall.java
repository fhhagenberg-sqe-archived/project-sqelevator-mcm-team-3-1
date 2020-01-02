/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

import at.fhhagenberg.sqelevator.enums.ElevatorDirection;

/**
 *
 * @author jmayr
 */
public interface IElevatorCall {
    /**
     * gets the direction of the elevator call.
     * It is basically the direction the elevator should go to (either up or down)
     * @return ElevatorDirection representing the direction of the call
     */
    public ElevatorDirection getDirection();
    /**
     * Gets the number of the floor the call was set
     * @return int number of the floor the call is located at
     */
    public int getFloorNumber();
}
