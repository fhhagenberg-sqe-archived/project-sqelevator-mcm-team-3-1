/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.interfaces;

/**
 *
 * @author jmayr
 */
public interface IFloor {
    public int getFloorNumber();
    public boolean isServicedBy(ILocalElevator e);
    public boolean setServicedBy(ILocalElevator e);
}
