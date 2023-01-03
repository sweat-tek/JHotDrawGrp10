package org.jhotdraw.samples.svg;


import com.tngtech.jgiven.Stage;

import org.jhotdraw.draw.Drawing;

public class SVGDrawingPanelGivenDrawing extends  Stage<SVGDrawingPanelGivenDrawing> {

    public SVGDrawingPanelGivenDrawing createDrawing(){
        SVGDrawingPanel panel = new SVGDrawingPanel();
        Drawing drawing = panel.createDrawing();
        return this;
    }

}
