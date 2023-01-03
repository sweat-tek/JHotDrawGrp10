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


/*
    @Test
    public void TestHandleDoubleClick(){
        DelegationSelectionTool delegationSelectionTool = new DelegationSelectionTool();

        DrawingView drawingView = new DefaultDrawingView();

        DrawingEditor editor = new DefaultDrawingEditor();
        editor.add(drawingView);
        editor.setActiveView(drawingView);


        delegationSelectionTool.activate(editor);

        System.out.println(delegationSelectionTool.getView().getSelectionCount());



        TextFigure textFigure = new TextFigure();
        Component cursor = null;
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        };

       MouseEvent mouseEvent = new MouseEvent(cursor, 1, 1, 0, 20, 20,1,false);
       mouseAdapter.mouseClicked(mouseEvent);

       selectionTool.handleDoubleClick(mouseEvent);

   }
    */
}
