package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class SVGRectFigureGivenRectangle extends Stage<SVGRectFigureGivenRectangle> {
    @ProvidedScenarioState
    private double x = 0;

    @ProvidedScenarioState
    private double height = 6;

    @ProvidedScenarioState
    private SVGRectFigure svgRectFigure;

    public SVGRectFigureGivenRectangle creatingRectangle() {
        svgRectFigure = new SVGRectFigure(x, 0, 6, height, 1d, 1d);
        return this;
    }
}
