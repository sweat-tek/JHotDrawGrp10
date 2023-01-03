package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertNotEquals;

public class SVGPathFigureThenDrawn extends Stage<SVGPathFigureThenDrawn> {
    public SVGPathFigureThenDrawn lineConfirm(BufferedImage buf) {
        assertNotEquals(0, buf.getRGB(10, 10));
        return this;
    }
}
