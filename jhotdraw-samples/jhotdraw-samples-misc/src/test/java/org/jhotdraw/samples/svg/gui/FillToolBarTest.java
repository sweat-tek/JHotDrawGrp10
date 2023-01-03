package org.jhotdraw.samples.svg.gui;

import org.junit.Test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static org.junit.Assert.*;

public class FillToolBarTest {

    @Test
    public void testCreateDisclosedComponent() {
        FillToolBar testClass = new FillToolBar();

        JComponent component1 = testClass.createDisclosedComponent(1);
        assertNotNull(component1);
        assertTrue(true);

        JComponent component2 = testClass.createDisclosedComponent(2);
        assertNotNull(component2);
        assertTrue(true);

    }


}