package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import java.awt.*;

public class SVGPathFigureWhenDrawing extends Stage<SVGPathFigureWhenDrawing> {

    @ExpectedScenarioState
    private SVGPathFigure svgPathFigure;
    public SVGPathFigureWhenDrawing drawingLine(Graphics2D g) {
        svgPathFigure.drawStroke(g);
        return this;
    }
}
