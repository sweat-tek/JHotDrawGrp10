package org.jhotdraw.samples.svg.figures;

import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SVGPathFigureTest {
    @Test
    public void drawLine() {
        Point2D.Double pointA = new Point2D.Double(1, 10);
        Point2D.Double pointB = new Point2D.Double(15, 10);
        SVGPathFigure svgPathFigure = new SVGPathFigure();
        svgPathFigure.setBounds(pointA, pointB);
        BufferedImage buf = new BufferedImage(
                100,
                100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buf.createGraphics();
        //Check if the pixel color is white
        assertEquals(0, buf.getRGB(10, 10));

        svgPathFigure.drawStroke(g);

        //Check if something was drawn and the pixel color has changed
        assertNotEquals(0, buf.getRGB(10, 10));
    }
}
