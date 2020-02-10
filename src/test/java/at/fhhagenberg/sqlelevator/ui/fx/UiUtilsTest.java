package at.fhhagenberg.sqlelevator.ui.fx;

import at.fhhagenberg.sqelevator.ui.fx.UiUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiUtilsTest {

    @Test
    public void testGeneratePane() {
        var pane = UiUtils.generatePane(80, 80);

        assertEquals(80, pane.getMinHeight());
        assertEquals(80, pane.getMaxHeight());

        assertEquals(80, pane.getMinWidth());
        assertEquals(80, pane.getMaxWidth());
    }
}
