package org.jhotdraw.samples.svg;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.jhotdraw.draw.Drawing;

import org.junit.Test;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.net.URISyntaxException;


public class SVGDrawingPanelBDDTest extends ScenarioTest   <SVGDrawingPanelGivenDrawing, SVGDrawingPanelWhenDrawing, SVGDrawingPanelThenDrawing> {
    BufferedImage buf = new BufferedImage(
            100,
            100,
            BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = buf.createGraphics();


    @Test
    public void SVGDrawingPanelBDDTest() throws IOException, URISyntaxException {
        SVGDrawingPanel panel = new SVGDrawingPanel();
        Drawing drawing = panel.createDrawing();

        given().createDrawing();
        when().drawingImage(drawing);
        then().inputsExist(drawing);
    }
}
