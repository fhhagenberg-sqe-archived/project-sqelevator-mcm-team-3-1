package at.fhhagenberg.sqelevator.ui.fx;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UiUtils {
    public static Pane generatePane() {
        var p = new Pane();
        p.setMinSize(80, 80);
        p.setPadding(new Insets(5, 5, 5, 5));
        p.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return p;
    }
}
