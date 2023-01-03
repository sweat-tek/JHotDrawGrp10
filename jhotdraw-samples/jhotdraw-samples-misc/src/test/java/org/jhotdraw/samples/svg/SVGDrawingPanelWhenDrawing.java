package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.io.ImageInputFormat;
import org.jhotdraw.draw.io.InputFormat;
import org.jhotdraw.samples.svg.figures.SVGImageFigure;
import java.util.LinkedList;

public class SVGDrawingPanelWhenDrawing {





    public SVGDrawingPanelWhenDrawing drawingImage(Drawing drawing){
        LinkedList<InputFormat> inputFormats = new LinkedList<InputFormat>();
        inputFormats.add(new ImageInputFormat(new SVGImageFigure(), "PNG", "Portable Network Graphics (PNG)", "png", "image/png"));
        drawing.setInputFormats(inputFormats);
        return this;
    }

}
