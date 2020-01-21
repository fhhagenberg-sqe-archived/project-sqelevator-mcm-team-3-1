/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.enums;

/**
 * @author jmayr
 * State of the elevator doors
 * 1 = Door is open
 * 2 = Door is closed
 * else = Unknown state
 */
public enum DoorState {
    UNKNOWN,
    OPEN,
    CLOSED;

    public static DoorState from(int value) {
        switch (value) {
            case 1:
                return DoorState.OPEN;
            case 2:
                return DoorState.CLOSED;
            default:
                return DoorState.UNKNOWN;
        }
    }
}
