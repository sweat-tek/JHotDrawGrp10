package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SVGPathFigureBDDTest extends ScenarioTest<SVGPathFigureGivenLine, SVGPathFigureWhenDrawing, SVGPathFigureThenDrawn> {
    @Test
    public void drawingRectangleTest() {
        //Create Graphics2D instance
        BufferedImage buf = new BufferedImage(
                100,
                100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buf.createGraphics();

        given().createLine();
        when().drawingLine(g);
        then().lineConfirm(buf);
    }
}
