/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.enums;

/**
 * Normally STRINGS are used to differ events. This is used in order to avoid typos!
 * PLACE ALL EVENTS HERE, THIS ALSO AVOIDS DOUBLE USAGE!
 * @author jmayr
 */
public enum ElevatorEvent {
    NEW_ELEVATOR_CALL("NEW_ELEVATOR_CALL"),
    ELEVATOR_UPDATED("ELEVATOR_UPDATED"),
    ELEVATOR_LOADED_FROM_BACKEND_EVENT("ELEVATOR_LOADED_FROM_BACKEND_EVENT"),
    ENVIRONMENT_LOADED_FROM_BACKEND_EVENT("ENVIRONMENT_LOADED_FROM_BACKEND_EVENT"),
    FLOOR_LOADED_FROM_BACKEND_EVENT("FLOOR_LOADED_FROM_BACKEND_EVENT"),
    CALL_LOADED_FROM_BACKEND_EVENT("CALL_LOADED_FROM_BACKEND_EVENT"),
    FLOOR_INPUT_CHANGED("FLOOR_INPUT_CHANGED"),
    SET_FLOOR_SELECTED("SET_FLOOR_CLICKED"),
    ELEVATOR_SELECTED("ELEVATOR_SELECTED"),
    CHANGE_ELEVATOR_MODE_SELECTED("CHANGE_ELEVATOR_MODE_SELECTED");

    private String eventName;

    ElevatorEvent(String evn) {
        this.eventName = evn;
    }

    public String getEventName() {
        return this.eventName;
    }
}
