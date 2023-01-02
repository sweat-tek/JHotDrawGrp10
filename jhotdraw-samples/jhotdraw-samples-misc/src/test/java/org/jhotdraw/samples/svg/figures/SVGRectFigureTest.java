package org.jhotdraw.samples.svg.figures;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class SVGRectFigureTest {

    @Test
    public void getX() {
        double x = 11.1;
        SVGRectFigure svgRectFigure = new SVGRectFigure(x, 12.2, 13.3, 14.4);
        assertEquals(x, svgRectFigure.getX(), 1);
    }

    @Test
    public void getY() {
        double y = 12.2;
        SVGRectFigure svgRectFigure = new SVGRectFigure(11.1, y, 13.3, 14.4);
        assertEquals(y, svgRectFigure.getY(), 1);
    }

    @Test
    public void getWidth() {
        double width = 13.3;
        SVGRectFigure svgRectFigure = new SVGRectFigure(11.1, 12.2, width, 14.4);
        assertEquals(width, svgRectFigure.getWidth(), 1);
    }

    @Test
    public void getHeight() {
        double height = 14.4;
        SVGRectFigure svgRectFigure = new SVGRectFigure(11.1, 12.2, 13.3, height);
        assertEquals(height, svgRectFigure.getHeight(), 1);
    }

    @Test
    public void drawStroke() {
        double x = 0;
        double height = 6;
        SVGRectFigure svgRectFigure = new SVGRectFigure(x, 0, 6, height, 1d, 1d);
        BufferedImage buf = new BufferedImage(
                100,
                100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buf.createGraphics();

        //Check if the pixel color is white
        assertEquals(0, buf.getRGB((int) x, (int) (height / 2)));

        svgRectFigure.drawStroke(g);

        //Check if something was drawn and the pixel color has changed
        assertNotEquals(0, buf.getRGB((int) x, (int) (height / 2)));
    }
}