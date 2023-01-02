package org.jhotdraw.draw.tool;


import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.action.AlignAction;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class DelegationSelectionToolTest {

   @Test
    public void TestGetDrawingAction(){

        //New instantiation of DelegationSelectionTool
        DelegationSelectionTool selectionTool = new DelegationSelectionTool();

        // Instantiation of new Collection of Actions
        Collection<Action> actions = new ArrayList<Action>();
        Action action = new AlignAction.Horizontal(new DefaultDrawingEditor());
        actions.add(action);

       // Check if Action does not exist in current Collection
       assertTrue(!selectionTool.getDrawingActions().contains(action));

       // insert Collections of Actions in DelegationSelectionTool
        selectionTool.setDrawingActions(actions);

        // Check if the Action exist in the Collection
        assertTrue(selectionTool.getDrawingActions().contains(action));
    }

    @Test
    public void TestGetFigureAction(){

       //New instantiation of DelegationSelectionTool
       DelegationSelectionTool selectionTool = new DelegationSelectionTool();

       // Instantiation of new Collection of Actions
       Collection<Action> actions = new ArrayList<Action>();
       Action action = new AlignAction.Horizontal(new DefaultDrawingEditor());
       actions.add(action);

       //Check Action does not consist in the current Collection
       assertTrue(!selectionTool.getSelectionActions().contains(action));

       // Insert Collection of Actions in the DelegationSelectionTool
       selectionTool.setSelectionActions(actions);

        // Check if the Action exist in the Collection
       assertTrue(selectionTool.getSelectionActions().contains(action));

    }

}
