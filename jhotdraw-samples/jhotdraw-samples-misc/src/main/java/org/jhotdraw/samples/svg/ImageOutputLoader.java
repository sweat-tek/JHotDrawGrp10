package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.io.ImageOutputFormat;
import org.jhotdraw.draw.io.OutputFormat;
import org.jhotdraw.samples.svg.io.ImageMapOutputFormat;
import org.jhotdraw.samples.svg.io.SVGOutputFormat;
import org.jhotdraw.samples.svg.io.SVGZOutputFormat;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ImageOutputLoader {

    public ImageOutputLoader(){

    }

    protected LinkedList<OutputFormat> loadOutput(){
        LinkedList<OutputFormat> outputFormats = new LinkedList<OutputFormat>();
        outputFormats.add(new SVGOutputFormat());
        outputFormats.add(new SVGZOutputFormat());
        outputFormats.add(new ImageOutputFormat());
        outputFormats.add(new ImageOutputFormat("JPG", "Joint Photographics Experts Group (JPEG)", "jpg", BufferedImage.TYPE_INT_RGB));
        outputFormats.add(new ImageOutputFormat("BMP", "Windows Bitmap (BMP)", "bmp", BufferedImage.TYPE_BYTE_INDEXED));
        outputFormats.add(new ImageMapOutputFormat());
        return outputFormats;
    }
}
