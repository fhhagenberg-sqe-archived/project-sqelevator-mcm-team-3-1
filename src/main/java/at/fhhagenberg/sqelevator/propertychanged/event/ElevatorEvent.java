/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.propertychanged.event;

/**
 * STRINGS are used to differ events. This is used in order to avoid typos.
 * Elevator properties
 * NAMING CONVENTION FOR EVENTS:
 * Interfacename.propertyname
 * @author jmayr
 */
public enum ElevatorEvent {
    SELECTED_FLOORS("ILocalElevator.selectedFloors"),
    DOOR_STATE("ILocalElevator.doorState"),
    DIRECTION("ILocalElevator.direction"),
    MODE("ILocalElevator.mode"),
    CURRENT_FLOOR("ILocalElevator.currentFloor"),
    TARGET_FLOOR("ILocalElevator.targetFloor"),
    LBS_WEIGHT("ILocalElevator.lbsWeight"),
    LBS_MAX_LOAD("ILocalElevator.lbsMaxLoad"),
    CURRENT_SPEED_FTS("ILocalElevator.currentSpeedFts"),
    CURRENT_ACCELERATION_FTSQR("ILocalElevator.currentAccelerationFtsqr"),
    CURRENT_POSITION("ILocalElevator.currentPosition");

    private String eventName;

    ElevatorEvent(String evn) {
        this.eventName = evn;
    }

    public String getEventName() {
        return this.eventName;
    }
}
