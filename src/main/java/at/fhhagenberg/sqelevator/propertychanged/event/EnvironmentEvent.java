/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.propertychanged.event;

/**
 * @author jmayr
 */
public class EnvironmentEvent {
    public static final String CLOCK_TICK = "EnvironmentEvent.ClockTick";
    public static final String ELEVATOR_CALL_ADDED = "EnvironmentEvent.ElevatorCallAdded";

    private EnvironmentEvent() {
    }
}
