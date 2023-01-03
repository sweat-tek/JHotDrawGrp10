package org.jhotdraw.samples.svg.jgiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class ThenSelectedColorTest extends Stage<ThenSelectedColorTest> {
    @ExpectedScenarioState
    private DefaultDrawingView view;

    public ThenSelectedColorTest get_Right_Color_Test() {
        Figure figure = view.getSelectedFigures().iterator().next();
        assertEquals(new Color(255, 0, 0), figure.getAttributes().get(AttributeKeys.FILL_COLOR));
        return self();
    }
}

