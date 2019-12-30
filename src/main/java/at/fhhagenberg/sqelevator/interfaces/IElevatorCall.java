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
    public ElevatorDirection getDirection();
    public int getFloorNumber();
}
