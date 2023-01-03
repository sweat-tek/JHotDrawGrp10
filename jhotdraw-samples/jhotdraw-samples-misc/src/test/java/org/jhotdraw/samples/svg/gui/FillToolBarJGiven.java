package org.jhotdraw.samples.svg.gui;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.jhotdraw.samples.svg.jgiven.GivenSelectFigureTest;
import org.jhotdraw.samples.svg.jgiven.ThenSelectedColorTest;
import org.jhotdraw.samples.svg.jgiven.WhenColorSelectedTest;
import org.junit.Test;

public class FillToolBarJGiven extends ScenarioTest<GivenSelectFigureTest, WhenColorSelectedTest, ThenSelectedColorTest> {

    @Test
    public void fillFigure(){
        given().user_select_a_figure();
        when().user_selects_a_color();
        then().get_Right_Color_Test();

    }
}
