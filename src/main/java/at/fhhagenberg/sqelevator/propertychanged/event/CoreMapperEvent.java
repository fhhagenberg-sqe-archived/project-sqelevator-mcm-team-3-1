/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.propertychanged.event;

/**
 *
 * @author jmayr
 */
public class CoreMapperEvent {

    public static final String ELEVATOR_LOADED = "CoreMapperrEvent.ElevatorLoaded";
    public static final String ELEVATOR_CALL_LOADED = "CoreMapperrEvent.ElevatorCallLoaded";
    public static final String FLOOR_LOADED = "CoreMapperrEvent.FloorLoaded";
    public static final String ENVIRONMENT_LOADED = "CoreMapperrEvent.EnvironmentLoaded";

    private CoreMapperEvent() {
    }
}
