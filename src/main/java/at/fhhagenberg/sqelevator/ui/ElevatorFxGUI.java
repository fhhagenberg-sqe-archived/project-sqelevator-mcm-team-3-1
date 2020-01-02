/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jmayr
 * 
 * NOTES from the lecture:
 * in the used Objects
 * someclasspropertyProperty(): StringProperty; adding change listeners for javaFX
 * also provides binding properties
 * WARNING: JAVA FX PROPERITARY!!!! SO INTERMEDIATE OBJECTS HAVE TO BE CREATED!!!!
 * 
 */
public class ElevatorFxGUI extends Stage {
    
    public ElevatorFxGUI(int numberOfElevators){
    Scene scene = new Scene(renderLayout(), 500, 200);
    }
     private HBox renderLayout() {
        HBox layout = new HBox();
        layout.setPadding(new Insets(10, 20, 50, 10));
        layout.setSpacing(10);
        return layout;
    }
     
     private VBox generateElevator(){
         return null;
     }
}
