package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SVGRectFigureBDDTest extends ScenarioTest<SVGRectFigureGivenRectangle, SVGRectFigureWhenDrawing, SVGRectFigureThenDrawn> {
    @Test
    public void drawingRectangleTest() {
        //Create Graphics2D instance
        BufferedImage buf = new BufferedImage(
                100,
                100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buf.createGraphics();

        given().creatingRectangle();
        when().drawingRectangle(g);
        then().rectangleExists(buf);
    }
}
