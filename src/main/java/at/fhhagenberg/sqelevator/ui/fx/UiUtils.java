package at.fhhagenberg.sqelevator.ui.fx;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UiUtils {
    public static Pane generatePane(double availableWidth, double availableHeight) {
        var p = new Pane();
        p.setMaxWidth(availableWidth);
        p.setMaxHeight(availableHeight);
        p.setMinSize(availableWidth, availableHeight);
        p.setPadding(new Insets(5, 5, 5, 5));
        p.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return p;
    }
}
