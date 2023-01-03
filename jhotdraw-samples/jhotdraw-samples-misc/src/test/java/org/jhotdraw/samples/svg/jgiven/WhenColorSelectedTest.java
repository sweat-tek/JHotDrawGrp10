package org.jhotdraw.samples.svg.jgiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class WhenColorSelectedTest extends Stage<WhenColorSelectedTest> {

    @ExpectedScenarioState
    private DefaultDrawingView view;

    public WhenColorSelectedTest user_selects_a_color() {
        // Assert figure is selected
        assertEquals(1, view.getSelectedFigures().size());

        // Get the first selected figure and color its FILL_COLOR
        Figure figure = view.getSelectedFigures().iterator().next();
        figure.set(AttributeKeys.FILL_COLOR, new Color(255, 0, 0));

        return self();
    }

}