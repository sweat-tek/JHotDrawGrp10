package org.jhotdraw.draw.figure;

import org.jhotdraw.geom.BezierPath;
import org.junit.Test;

import static org.junit.Assert.*;

public class BezierFigureTest {


    @Test
    public void getCappedPathTest(){
        BezierFigure bezierFigure = new BezierFigure();
        BezierPath path = bezierFigure.getCappedPath();
        BezierPath.Node node0 = new BezierPath.Node();
        BezierPath.Node node1 = new BezierPath.Node();
        node0.x
        path.add(node0);
        path.add(node1);
        System.out.println(path.get(0).x[0] + " " + path.get(0).y[0] + " " + path.get(1).x[0] + " " + path.get(1).y[0]);

    }
}
