/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;


/**
 * THIS CLASS IS USED TO MAP THE BACKEND INTERACTION AND IT'S ISSUES/EXCEPTIONS
 * THIS CLASS IS HANDLED BY THE BACKEND SHADER
 * @author jmayr
 */
public interface IBackendInteractionMapper{
    /**
     * Function sets the floor a specific elevator has to target next.
     * @param e Elevator which has to go to the specified floor
     * @param floor Floor number the elevator should target
     * @return boolean true if the elevator can go to the specified floor number,
     * false otherwhise
     */
   public boolean setFloor(ILocalElevator e, int floor);
   /**
    * Function retrieves the number of floors the building offers
    * @return int number of floors the building has.
    */
   public int getNumberOfFloors();
   /**
    * Function retriefes the number of elevators the building offers
    * @return int number of elevators in the building
    */
   public int getNumberOfElevators();
   /**
    * Function that gets an elevator and all it's states by it's ID
    * @param no number of the elevator which should be retrieved
    * @return an elevator with its current internal state.
    */
   public ILocalElevator getElevatorByNumber(int no);
   /**
    * Function retrieves an array of all current calls in the building.
    * If there are no open calls, an empty array is returned.
    * @return array of current calls
    */
   public IElevatorCall[] getCurrentCalls();
   /**
    * Function retrieves the environmental settings of the building.
    * This contains
    * the number of floors, the number of elevators, the floor height and
    * the current calls of the floors.
    * @return IEnvironment fully set up environment
    */
   public IEnvironment getInitialEnvironment();
}
