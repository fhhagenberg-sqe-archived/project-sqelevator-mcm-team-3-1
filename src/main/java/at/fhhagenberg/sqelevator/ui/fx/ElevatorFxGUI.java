/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.interfaces.IBackendShader;
import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jmayr
 *
 * NOTES from the lecture: in the used Objects someclasspropertyProperty():
 * StringProperty; adding change listeners for javaFX also provides binding
 * properties WARNING: JAVA FX PROPERITARY!!!! SO INTERMEDIATE OBJECTS HAVE TO
 * BE CREATED!!!!
 *
 */
public class ElevatorFxGUI extends Stage {

    private FXSelectedElevator evtr;
    private LinkedList<FXElevator> evtrs;

    public ElevatorFxGUI(IBackendShader shader) {
        Scene scene = new Scene(renderLayout(), 1000, 600);
        evtr = new FXSelectedElevator();
        evtrs = new LinkedList<>();
    }

    private HBox renderLayout() {
        HBox layout = new HBox();
        layout.getChildren().add(generateElevators());
        layout.getChildren().add(evtr);
        layout.setPadding(new Insets(10, 20, 50, 10));
        layout.setSpacing(10);
        return layout;
    }

    private HBox generateElevators() {
        return null;
    }
}
