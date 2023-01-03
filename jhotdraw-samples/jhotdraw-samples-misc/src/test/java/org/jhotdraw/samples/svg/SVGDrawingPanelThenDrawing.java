package org.jhotdraw.samples.svg;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.Drawing;

import static org.junit.Assert.assertEquals;
public class SVGDrawingPanelThenDrawing extends Stage <SVGDrawingPanelThenDrawing> {
    @ExpectedScenarioState
    Drawing drawing;

    public SVGDrawingPanelThenDrawing inputsExist(Drawing drawing) {
        assertEquals(drawing.getInputFormats().size(), 1);
        return this;
    }
}
