/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.propertychanged.event;

/**
 * STRINGS are used to differ events. This is used in order to avoid typos.
 * Elevator properties NAMING CONVENTION FOR EVENTS: Interfacename.propertyname
 *
 * @author jmayr
 */
public class ElevatorEvent {

    public static final String SELECTED_FLOORS = "ILocalElevator.selectedFloors";
    public static final String DOOR_STATE = "ILocalElevator.doorState";
    public static final String DIRECTION = "ILocalElevator.direction";
    public static final String MODE = "ILocalElevator.mode";
    public static final String CURRENT_FLOOR = "ILocalElevator.currentFloor";
    public static final String TARGET_FLOOR = "ILocalElevator.targetFloor";
    public static final String LBS_WEIGHT = "ILocalElevator.lbsWeight";
    public static final String LBS_MAX_LOAD = "ILocalElevator.lbsMaxLoad";
    public static final String CURRENT_SPEED_FTS = "ILocalElevator.currentSpeedFts";
    public static final String CURRENT_ACCELERATION_FTSQR = "ILocalElevator.currentAccelerationFtsqr";
    public static final String CURRENT_POSITION = "ILocalElevator.currentPosition";
    public static final String CURRENT_STATE = "ILocalElevator.currentState";

    private ElevatorEvent() {
    }
}
