package at.fhhagenberg.sqelevator.ui.fx;

import at.fhhagenberg.sqelevator.interfaces.IEnvironment;
import at.fhhagenberg.sqelevator.propertychanged.event.EnvironmentEvent;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;

public class FXGeneralElevatorInformation extends HBox implements PropertyChangeListener {
    private final Label valueClockTick;

    public FXGeneralElevatorInformation(IEnvironment environment) {
        final Label labelClockTick = new Label("Clock:");
        valueClockTick = new Label();
        valueClockTick.setPadding(new Insets(0, 5, 0, 5));
        setPadding(new Insets(5, 10, 5, 20));
        getChildren().addAll(labelClockTick, valueClockTick);

        environment.addClockTickListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            if (EnvironmentEvent.CLOCK_TICK.equals(event.getPropertyName())) {
                valueClockTick.setText(convertClockStringToTime((Long) event.getNewValue()));
            }
        });
    }

    private String convertClockStringToTime(long clock) {
        var clockMs = clock * 100;
        return String.format("%d h, %d min, %d sec",
                TimeUnit.MILLISECONDS.toHours(clockMs),
                TimeUnit.MILLISECONDS.toMinutes(clockMs) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(clockMs)),
                TimeUnit.MILLISECONDS.toSeconds(clockMs) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(clockMs))
        );
    }
}
