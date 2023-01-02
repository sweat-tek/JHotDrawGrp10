package org.jhotdraw.samples.svg.figures;


import org.jhotdraw.draw.figure.BezierFigure;
import org.jhotdraw.draw.handle.BezierNodeHandle;
import org.jhotdraw.draw.handle.Handle;
import org.jhotdraw.draw.handle.TransformHandleKit;
import org.jhotdraw.geom.BezierPath;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class SVGBezierFigureTest {

    private Collection<Handle> orginalMethod(SVGPathFigure pathFigure, int detailLevel, BezierPath path, BezierFigure figure){
        LinkedList<Handle> handles = new LinkedList<Handle>();

        switch (detailLevel % 2) {
            case 0:
                for (int i = 0, n = path.size(); i < n; i++) {
                    handles.add(new BezierNodeHandle(figure, i, pathFigure));
                }
                break;
            case 1:
                TransformHandleKit.addTransformHandles(figure, handles);
                break;
            default:
                break;
        }
        return handles;
    }

    @Test
    public void testValueCreateHandles(){
        SVGPathFigure svgPathFigure = new SVGPathFigure();
        SVGBezierFigure svgBezierFigure = new SVGBezierFigure();
        int detail = 1;
        LinkedList<Handle> handles = (LinkedList<Handle>) svgBezierFigure.createHandles(svgPathFigure, detail);
        assertNotEquals(0, handles.size());
    }

    @Test
    public void testCreateHandles(){
        BezierPath path = new BezierPath();
        SVGPathFigure svgPathFigure = new SVGPathFigure();
        SVGBezierFigure svgBezierFigure = new SVGBezierFigure();
        int detail = 1;
        LinkedList<Handle> handles = (LinkedList<Handle>) svgBezierFigure.createHandles(svgPathFigure, detail);
        LinkedList<Handle> handlesOld = (LinkedList<Handle>) orginalMethod(svgPathFigure, detail, path, svgBezierFigure);
        if (handles.size() == handlesOld.size()) {
            assert true;
        } else {
            assert false;
        }

        path = new BezierPath();
        svgPathFigure = new SVGPathFigure();
        svgBezierFigure = new SVGBezierFigure();
        detail = 2;
        handles = (LinkedList<Handle>) svgBezierFigure.createHandles(svgPathFigure, detail);
        handlesOld = (LinkedList<Handle>) orginalMethod(svgPathFigure, detail, path, svgBezierFigure);
        if (handles.size() == handlesOld.size()) {
            assert true;
        } else {
            assert false;
        }
    }
}
