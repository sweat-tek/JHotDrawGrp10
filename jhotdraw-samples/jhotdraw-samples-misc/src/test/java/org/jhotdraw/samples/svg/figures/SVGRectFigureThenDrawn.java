package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertNotEquals;

public class SVGRectFigureThenDrawn extends Stage<SVGRectFigureThenDrawn> {
    @ExpectedScenarioState
    double x;

    @ExpectedScenarioState
    double height;

    public SVGRectFigureThenDrawn rectangleExists(BufferedImage buf) {
        assertNotEquals(0, buf.getRGB((int) x, (int) (height / 2)));
        return this;
    }
}
