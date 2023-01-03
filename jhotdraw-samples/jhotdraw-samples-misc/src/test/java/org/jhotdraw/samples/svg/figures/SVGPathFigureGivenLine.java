package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import java.awt.geom.Point2D;

public class SVGPathFigureGivenLine extends Stage<SVGPathFigureGivenLine> {

    @ProvidedScenarioState
    private SVGPathFigure svgPathFigure;
    public SVGPathFigureGivenLine createLine() {
        Point2D.Double pointA = new Point2D.Double(1, 10);
        Point2D.Double pointB = new Point2D.Double(15, 10);
        svgPathFigure = new SVGPathFigure();
        svgPathFigure.setBounds(pointA, pointB);
        return this;
    }
}
