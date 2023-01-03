package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import javax.swing.*;

import static org.junit.Assert.assertTrue;

public class DelegationSelectionToolThenSetAction extends Stage<DelegationSelectionToolThenSetAction> {

    @ExpectedScenarioState
    private DelegationSelectionTool delegationSelectionTool;

    public DelegationSelectionToolThenSetAction getActions(Action action){
        // Check if the Action exist in the Collection
        assertTrue(delegationSelectionTool.getDrawingActions().contains(action));
        return this;
    }
}
