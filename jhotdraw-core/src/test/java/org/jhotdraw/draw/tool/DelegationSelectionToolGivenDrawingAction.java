package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class DelegationSelectionToolGivenDrawingAction extends Stage<DelegationSelectionToolGivenDrawingAction> {

    @ProvidedScenarioState
    private DelegationSelectionTool delegationSelectionTool;


    public DelegationSelectionToolGivenDrawingAction createSelectionTool() {

        //New instantiation of DelegationSelectionTool
        delegationSelectionTool = new DelegationSelectionTool();

        return this;
    }
}
