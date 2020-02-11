/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.model.dummy;

import at.fhhagenberg.sqelevator.enums.ElevatorModeType;
import at.fhhagenberg.sqelevator.interfaces.IElevatorMode;

/**
 * @author jmayr
 */
public class ElevatorModeAuto implements IElevatorMode {

    @Override
    public ElevatorModeType getModeType() {
        return ElevatorModeType.AUTOMATIC;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ElevatorModeAuto mode = (ElevatorModeAuto) other;
        return mode.getModeType().equals(this.getModeType());
    }
}
