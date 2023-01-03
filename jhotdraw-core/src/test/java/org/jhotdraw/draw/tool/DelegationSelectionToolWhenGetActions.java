package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import javax.swing.*;
import java.util.Collection;

public class DelegationSelectionToolWhenGetActions extends Stage<DelegationSelectionToolWhenGetActions> {
    @ProvidedScenarioState
    @ExpectedScenarioState
    private DelegationSelectionTool delegationSelectionTool;

    public DelegationSelectionToolWhenGetActions setActions(Collection<Action> actions) {

        // Check if Action does not exist in current Collection
        delegationSelectionTool.setDrawingActions(actions);

        return this;
    }
}
