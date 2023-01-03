package org.jhotdraw.samples.svg.jgiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.junit.Test;

public class GivenSelectFigureTest extends Stage<GivenSelectFigureTest>  {

        @ProvidedScenarioState
        private DrawingEditor editor;

        @ProvidedScenarioState
        private DefaultDrawingView view;

        @BeforeStage
        void before() {
            editor = new DefaultDrawingEditor();
            view = new DefaultDrawingView();
            view.setDrawing(new DefaultDrawing());
            editor.setActiveView(view);
        }

        @Test
        public GivenSelectFigureTest user_select_a_figure() {
            // Create figure
            Figure figure = new RectangleFigure();

            // Add figure to scene
            view.getDrawing().add(figure);
            view.addToSelection(figure);

            // Select the figure
            view.selectAll();
            return self();
        }

}
