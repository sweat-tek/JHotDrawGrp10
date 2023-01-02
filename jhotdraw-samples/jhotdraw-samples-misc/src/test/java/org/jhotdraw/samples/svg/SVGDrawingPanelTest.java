package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.Drawing;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static org.junit.Assert.*;
public class SVGDrawingPanelTest {

    @Test
    public void checkInput(){
        SVGDrawingPanel panel = new SVGDrawingPanel();
        Drawing drawing = panel.createDrawing();
        LinkedList list = (LinkedList) drawing.getInputFormats();

        assertNotNull(drawing.getInputFormats());

        assertEquals(list.size(), 5);

        assertEquals(drawing, panel.createDrawing());
    }

    @Test
    public void checkOutput(){

        SVGDrawingPanel panel = new SVGDrawingPanel();
        Drawing drawing = panel.createDrawing();
        LinkedList list = (LinkedList) drawing.getOutputFormats();

        assertNotNull(drawing.getOutputFormats());

        assertEquals(list.size(), 6);

        assertEquals(drawing, panel.createDrawing());

    }

}
