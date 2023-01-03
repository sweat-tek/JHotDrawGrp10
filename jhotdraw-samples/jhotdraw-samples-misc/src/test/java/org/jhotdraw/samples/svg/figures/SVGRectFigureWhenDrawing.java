package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import java.awt.*;

public class SVGRectFigureWhenDrawing extends Stage<SVGRectFigureWhenDrawing> {
    @ExpectedScenarioState
    private SVGRectFigure svgRectFigure;

    public SVGRectFigureWhenDrawing drawingRectangle(Graphics2D g) {
        svgRectFigure.drawStroke(g);
        return this;
    }
}
