package org.jhotdraw.draw.figure;

import org.jhotdraw.geom.BezierPath;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.*;
import java.util.*;

import org.jhotdraw.draw.AttributeKey;
import static org.jhotdraw.draw.AttributeKeys.*;
import org.jhotdraw.geom.Geom;

public class BezierFigureTest {
    public boolean isClosed() {
        return get(PATH_CLOSED);
    }
    private HashMap<AttributeKey<?>, Object> attributes = new HashMap<>();
    public <T> T get(AttributeKey<T> key) {
        return key.get(attributes);
    }
    private transient BezierPath cappedPath;

    protected BezierPath getCappedPath(BezierFigure bezierFigure, BezierPath path) {
        if (cappedPath == null) {
            cappedPath = path.clone();
            if (isClosed()) {
                cappedPath.setClosed(true);
            } else {
                if (cappedPath.size() > 1) {
                    if (get(START_DECORATION) != null) {
                        BezierPath.Node p0 = cappedPath.get(0);
                        BezierPath.Node p1 = cappedPath.get(1);
                        Point2D.Double pp;
                        if ((p0.getMask() & BezierPath.C2_MASK) != 0) {
                            pp = p0.getControlPoint(2);
                        } else if ((p1.getMask() & BezierPath.C1_MASK) != 0) {
                            pp = p1.getControlPoint(1);
                        } else {
                            pp = p1.getControlPoint(0);
                        }
                        double radius = get(START_DECORATION).getDecorationRadius(bezierFigure);
                        double lineLength = Geom.length(p0.getControlPoint(0), pp);
                        cappedPath.set(0, 0, Geom.cap(pp, p0.getControlPoint(0), -Math.min(radius, lineLength)));
                    }
                    if (get(END_DECORATION) != null) {
                        BezierPath.Node p0 = cappedPath.get(cappedPath.size() - 1);
                        BezierPath.Node p1 = cappedPath.get(cappedPath.size() - 2);
                        Point2D.Double pp;
                        if ((p0.getMask() & BezierPath.C1_MASK) != 0) {
                            pp = p0.getControlPoint(1);
                        } else if ((p1.getMask() & BezierPath.C2_MASK) != 0) {
                            pp = p1.getControlPoint(2);
                        } else {
                            pp = p1.getControlPoint(0);
                        }
                        double radius = get(END_DECORATION).getDecorationRadius(bezierFigure);
                        double lineLength = Geom.length(p0.getControlPoint(0), pp);
                        cappedPath.set(cappedPath.size() - 1, 0, Geom.cap(pp, p0.getControlPoint(0), -Math.min(radius, lineLength)));
                    }
                    cappedPath.invalidatePath();
                }
            }
        }
        return cappedPath;
    }

    @Test
    public void getCappedPathTestAssertion(){
        BezierFigure bezierFigure = new BezierFigure();
        BezierPath path = bezierFigure.getCappedPath();
        BezierPath.Node node0 = new BezierPath.Node();
        BezierPath.Node node1 = new BezierPath.Node();

        node0.x[0] = 3.4;
        node0.x[1] = 2.3;
        node0.x[2] = 0.3;
        node0.y[0] = 3.2;
        node0.y[1] = 1.2;
        node0.y[2] = 0.3;

        node1.x[0] = 6.3;
        node1.x[1] = 3.1;
        node1.x[2] = 9.0;
        node1.y[0] = 3.2;
        node1.y[1] = 0.2;
        node1.y[2] = 3.2;

        path.add(node0);
        path.add(node1);
        System.out.println(path.get(0).x[0] + " " + path.get(0).y[0] + " " + path.get(1).x[0] + " " + path.get(1).y[0]);
        assertEquals(node0.x[0], path.get(0).x[0], 0.0);
        assertEquals(node0.x[1], path.get(0).x[1], 0.0);
        assertEquals(node0.x[2], path.get(0).x[2], 0.0);
        assertEquals(node0.y[0], path.get(0).y[0], 0.0);
        assertEquals(node0.y[1], path.get(0).y[1], 0.0);
        assertEquals(node0.y[2], path.get(0).y[2], 0.0);

        assertEquals(node1.x[0], path.get(1).x[0], 0.0);
        assertEquals(node1.x[1], path.get(1).x[1], 0.0);
        assertEquals(node1.x[2], path.get(1).x[2], 0.0);
        assertEquals(node1.y[0], path.get(1).y[0], 0.0);
        assertEquals(node1.y[1], path.get(1).y[1], 0.0);
        assertEquals(node1.y[2], path.get(1).y[2], 0.0);
    }

    @Test
    public void getCappedPathTest(){
        BezierFigure bezierFigure = new BezierFigure();
        BezierPath path = bezierFigure.getCappedPath();
        BezierPath.Node node0 = new BezierPath.Node();
        BezierPath.Node node1 = new BezierPath.Node();

        node0.x[0] = 3.4;
        node0.x[1] = 2.3;
        node0.x[2] = 0.3;
        node0.y[0] = 3.2;
        node0.y[1] = 1.2;
        node0.y[2] = 0.3;

        node1.x[0] = 6.3;
        node1.x[1] = 3.1;
        node1.x[2] = 9.0;
        node1.y[0] = 3.2;
        node1.y[1] = 0.2;
        node1.y[2] = 3.2;

        path.add(node0);
        path.add(node1);

        BezierPath testBezierPath = bezierFigure.getCappedPath();

        assertEquals(testBezierPath.getBounds2D(), this.getCappedPath(bezierFigure, path).getBounds2D());
        assertEquals(testBezierPath.get(0).x[0], this.getCappedPath(bezierFigure, path).get(0).x[0], 0.0);
        assert true;
    }

    @Test
    public void containsTest(){
        BezierFigure bezierFigure = new BezierFigure();
        BezierPath path = bezierFigure.getCappedPath();
        BezierPath.Node node0 = new BezierPath.Node();
        BezierPath.Node node1 = new BezierPath.Node();

        node0.x[0] = 3.4;
        node0.y[0] = 3.2;

        node1.x[0] = 6.3;
        node1.y[0] = 3.2;

        path.add(node0);
        path.add(node1);

        assertTrue(bezierFigure.contains(new Point2D.Double(3.4, 3.2)));
        assertFalse(bezierFigure.contains(new Point2D.Double(5.4, 5.2)));
        assert true;
    }
}
