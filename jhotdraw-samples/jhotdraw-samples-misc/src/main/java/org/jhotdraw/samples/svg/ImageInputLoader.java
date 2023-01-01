package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.io.ImageInputFormat;
import org.jhotdraw.draw.io.InputFormat;
import org.jhotdraw.draw.io.TextInputFormat;
import org.jhotdraw.samples.svg.figures.SVGImageFigure;
import org.jhotdraw.samples.svg.figures.SVGTextFigure;
import org.jhotdraw.samples.svg.io.SVGZInputFormat;

import java.util.LinkedList;

public class ImageInputLoader {

    public ImageInputLoader() {

    }

    protected LinkedList<InputFormat> loadInput() {
        LinkedList<InputFormat> inputFormats = new LinkedList<InputFormat>();
        inputFormats.add(new SVGZInputFormat());
        inputFormats.add(new ImageInputFormat(new SVGImageFigure(), "PNG", "Portable Network Graphics (PNG)", "png", "image/png"));
        inputFormats.add(new ImageInputFormat(new SVGImageFigure(), "JPG", "Joint Photographics Experts Group (JPEG)", "jpg", "image/jpg"));
        inputFormats.add(new ImageInputFormat(new SVGImageFigure(), "GIF", "Graphics Interchange Format (GIF)", "gif", "image/gif"));
        inputFormats.add(new TextInputFormat(new SVGTextFigure()));
        return inputFormats;
    }
}
