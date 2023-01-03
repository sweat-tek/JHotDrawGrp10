package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.action.AlignAction;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class DelegationSelectionToolBDDTest extends ScenarioTest<DelegationSelectionToolGivenDrawingAction, DelegationSelectionToolWhenGetActions, DelegationSelectionToolThenSetAction> {

    @Test
    public void DelegationSelectionToolTest(){

        // Instantiation of new Collection of Actions
        Collection<Action> actions = new ArrayList<Action>();
        Action action = new AlignAction.Horizontal(new DefaultDrawingEditor());
        actions.add(action);

        given().createSelectionTool();
        when().setActions(actions);
        then().getActions(action);
    }
}
