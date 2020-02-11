/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.enums;

/**
 * @author jmayr
 * current heading direction of the elevator
 */
public enum ElevatorDirection {
    UP, DOWN, UNSET;

    public static ElevatorDirection from(int id) {
        switch (id) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return UNSET;
        }

        return null;
    }
}
