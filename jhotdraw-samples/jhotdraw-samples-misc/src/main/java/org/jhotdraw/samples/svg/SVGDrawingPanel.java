/*
 * @(#)JSVGDrawingAppletPanel.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.svg;

import org.jhotdraw.api.app.Disposable;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.io.InputFormat;
import org.jhotdraw.draw.io.OutputFormat;
import org.jhotdraw.gui.ToolBarLayout;
import org.jhotdraw.gui.plaf.palette.PaletteLookAndFeel;
import org.jhotdraw.undo.UndoRedoManager;
import org.jhotdraw.util.ResourceBundleUtil;
import org.jhotdraw.util.prefs.PreferencesUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * JSVGDrawingAppletPanel.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class SVGDrawingPanel extends JPanel implements Disposable {

    private static final long serialVersionUID = 1L;
    private UndoRedoManager undoManager;
    private DrawingEditor editor;
    private ResourceBundleUtil labels;
    private Preferences prefs;
    private ContainerListener containerHandler;

    public UndoRedoManager getUndoRedoManager() {
        return undoManager;
    }

    public void setUndoRedoManager(UndoRedoManager undo) {
        if (undoManager != null && getView().getDrawing() != null) {
            getView().getDrawing().removeUndoableEditListener(undoManager);
        }
        undoManager = undo;
        if (undoManager != null && getView().getDrawing() != null) {
            getView().getDrawing().addUndoableEditListener(undoManager);
        }
    }

    private class ItemChangeHandler implements ItemListener {

        private JToolBar toolbar;
        private String prefkey;

        public ItemChangeHandler(JToolBar toolbar, String prefkey) {
            this.toolbar = toolbar;
            this.prefkey = prefkey;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            boolean b = e.getStateChange() == ItemEvent.SELECTED;
            toolbar.setVisible(b);
            prefs.putBoolean(prefkey, b);
            validate();
        }
    }

    /**
     * Creates new instance.
     */
    public SVGDrawingPanel() {
        labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels");
        try {
            prefs = PreferencesUtil.userNodeForPackage(getClass());
        } catch (SecurityException e) {
            // prefs is null, because we are not permitted to read preferences
        }
        initComponents();
        toolsPane.setLayout(new ToolBarLayout());
        toolsPane.setBackground(new Color(0xf0f0f0));
        toolsPane.setOpaque(true);
        viewToolBar.setView(view);
        undoManager = new UndoRedoManager();
        Drawing drawing = createDrawing();
        view.setDrawing(drawing);
        drawing.addUndoableEditListener(undoManager);
        // Try to install the DnDDrawingViewTransferHandler
        // Since this class only works on J2SE 6, we have to use reflection.
        try {
            view.setTransferHandler((TransferHandler) Class.forName("org.jhotdraw.draw.DnDDrawingViewTransferHandler").newInstance());
        } catch (Exception e) {
            // bail silently
        }
        // Sort the toolbars according to the user preferences
        ArrayList<JToolBar> sortme = new ArrayList<JToolBar>();
        for (Component c : toolsPane.getComponents()) {
            if (c instanceof JToolBar) {
                sortme.add((JToolBar) c);
            }
        }
        Collections.sort(sortme, new Comparator<JToolBar>() {
            @Override
            public int compare(JToolBar tb1, JToolBar tb2) {
                int i1 = prefs.getInt("toolBarIndex." + tb1.getName(), 0);
                int i2 = prefs.getInt("toolBarIndex." + tb2.getName(), 0);
                return i1 - i2;
            }
        });
        toolsPane.removeAll();
        for (JToolBar tb : sortme) {
            toolsPane.add(tb);
        }
        toolsPane.addContainerListener(containerHandler = new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {
                int i = 0;
                for (Component c : toolsPane.getComponents()) {
                    if (c instanceof JToolBar) {
                        JToolBar tb = (JToolBar) c;
                        prefs.putInt("toolBarIndex." + tb.getName(), i);
                        i++;
                    }
                }
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
            }
        });
        setEditor(new DefaultDrawingEditor());
    }

    @Override
    public void dispose() {
        toolsPane.removeContainerListener(containerHandler);
        containerHandler = null;
        setEditor(null);
        for (PropertyChangeListener pcl : view.getListeners(PropertyChangeListener.class)) {
            view.removePropertyChangeListener(pcl);
        }
        view.setDrawing(null);
        actionToolBar.dispose();
        alignToolBar.dispose();
        arrangeToolBar.dispose();
        canvasToolBar.dispose();
        creationToolBar.dispose();
        figureToolBar.dispose();
        fillToolBar.dispose();
        fontToolBar.dispose();
        linkToolBar.dispose();
        strokeToolBar.dispose();
        viewToolBar.dispose();
        removeAll();
    }

    /**
     * Creates a new Drawing object which can be used with this
     * {@code SVGDrawingPanel}.
     */
    public Drawing createDrawing() {
        Drawing drawing = new QuadTreeDrawing();
        drawing.setInputFormats(new ImageInputLoader().loadInput());
        drawing.setOutputFormats(new ImageOutputLoader().loadOutput());
        return drawing;
    }

    public void setDrawing(Drawing d) {
        undoManager.discardAllEdits();
        if (view.getDrawing() != null) {
            view.getDrawing().removeUndoableEditListener(undoManager);
        }
        view.setDrawing(d);
        d.addUndoableEditListener(undoManager);
    }

    public Drawing getDrawing() {
        return view.getDrawing();
    }

    public DrawingView getView() {
        return view;
    }

    public DrawingEditor getEditor() {
        return editor;
    }

    public void setEditor(DrawingEditor newValue) {
        DrawingEditor oldValue = editor;
        if (oldValue != null) {
            oldValue.remove(view);
        }
        editor = newValue;
        if (newValue != null) {
            newValue.add(view);
        }
        creationToolBar.setEditor(editor);
        fillToolBar.setEditor(editor);
        strokeToolBar.setEditor(editor);
        actionToolBar.setUndoManager(undoManager);
        actionToolBar.setEditor(editor);
        alignToolBar.setEditor(editor);
        arrangeToolBar.setEditor(editor);
        fontToolBar.setEditor(editor);
        figureToolBar.setEditor(editor);
        linkToolBar.setEditor(editor);
        DrawingView temp = (editor == null) ? null : editor.getActiveView();
        if (editor != null) {
            editor.setActiveView(view);
        }
        canvasToolBar.setEditor(editor);
        viewToolBar.setEditor(editor);
        if (editor != null) {
            editor.setActiveView(temp);
        }
    }

    /**
     * Reads a drawing from the specified file into the SVGDrawingPanel.
     * <p>
     * This method should be called from a worker thread.
     * Calling it from the Event Dispatcher Thread will block the user
     * interface, until the drawing is read.
     */
    public void read(URI f) throws IOException {
        // Create a new drawing object
        Drawing newDrawing = createDrawing();
        if (newDrawing.getInputFormats().size() == 0) {
            throw new InternalError("Drawing object has no input formats.");
        }
        // Try out all input formats until we succeed
        IOException firstIOException = null;
        for (InputFormat format : newDrawing.getInputFormats()) {
            try {
                format.read(f, newDrawing);
                final Drawing loadedDrawing = newDrawing;
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        // Set the drawing on the Event Dispatcher Thread
                        setDrawing(loadedDrawing);
                    }
                };
                if (SwingUtilities.isEventDispatchThread()) {
                    r.run();
                } else {
                    try {
                        SwingUtilities.invokeAndWait(r);
                    } catch (InterruptedException ex) {
                        // suppress silently
                    } catch (InvocationTargetException ex) {
                        InternalError ie = new InternalError("Error setting drawing.");
                        ie.initCause(ex);
                        throw ie;
                    }
                }
                // We get here if reading was successful.
                // We can return since we are done.
                return;
            } catch (IOException e) {
                // We get here if reading failed.
                // We only preserve the exception of the first input format,
                // because that's the one which is best suited for this drawing.
                if (firstIOException == null) {
                    firstIOException = e;
                }
            }
        }
        throw firstIOException;
    }

    /**
     * Reads a drawing from the specified file into the SVGDrawingPanel using
     * the specified input format.
     * <p>
     * This method should be called from a worker thread.
     * Calling it from the Event Dispatcher Thread will block the user
     * interface, until the drawing is read.
     */
    public void read(URI f, InputFormat format) throws IOException {
        if (format == null) {
            read(f);
            return;
        }
        // Create a new drawing object
        Drawing newDrawing = createDrawing();
        if (newDrawing.getInputFormats().size() == 0) {
            throw new InternalError("Drawing object has no input formats.");
        }
        format.read(f, newDrawing);
        final Drawing loadedDrawing = newDrawing;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // Set the drawing on the Event Dispatcher Thread
                setDrawing(loadedDrawing);
            }
        };
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(r);
            } catch (InterruptedException ex) {
                // suppress silently
            } catch (InvocationTargetException ex) {
                InternalError ie = new InternalError("Error setting drawing.");
                ie.initCause(ex);
                throw ie;
            }
        }
    }

    /**
     * Writes the drawing from the SVGDrawingPanel into a file.
     * <p>
     * This method should be called from a worker thread.
     * Calling it from the Event Dispatcher Thread will block the user
     * interface, until the drawing is written.
     */
    public void write(URI uri) throws IOException {
        // Defensively clone the drawing object, so that we are not
        // affected by changes of the drawing while we write it into the file.
        final Drawing[] helper = new Drawing[1];
        Runnable r = new Runnable() {
            @Override
            public void run() {
                helper[0] = (Drawing) getDrawing().clone();
            }
        };
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(r);
            } catch (InterruptedException ex) {
                // suppress silently
            } catch (InvocationTargetException ex) {
                InternalError ie = new InternalError("Error getting drawing.");
                ie.initCause(ex);
                throw ie;
            }
        }
        Drawing saveDrawing = helper[0];
        if (saveDrawing.getOutputFormats().size() == 0) {
            throw new InternalError("Drawing object has no output formats.");
        }
        // Try out all output formats until we find one which accepts the
        // filename entered by the user.
        File f = new File(uri);
        for (OutputFormat format : saveDrawing.getOutputFormats()) {
            if (format.getFileFilter().accept(f)) {
                format.write(uri, saveDrawing);
                // We get here if writing was successful.
                // We can return since we are done.
                return;
            }
        }
        throw new IOException("No output format for " + f.getName());
    }

    /**
     * Writes the drawing from the SVGDrawingPanel into a file using the
     * specified output format.
     * <p>
     * This method should be called from a worker thread.
     * Calling it from the Event Dispatcher Thread will block the user
     * interface, until the drawing is written.
     */
    public void write(URI f, OutputFormat format) throws IOException {
        if (format == null) {
            write(f);
            return;
        }
        // Defensively clone the drawing object, so that we are not
        // affected by changes of the drawing while we write it into the file.
        final Drawing[] helper = new Drawing[1];
        Runnable r = new Runnable() {
            @Override
            public void run() {
                helper[0] = (Drawing) getDrawing().clone();
            }
        };
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(r);
            } catch (InterruptedException ex) {
                // suppress silently
            } catch (InvocationTargetException ex) {
                InternalError ie = new InternalError("Error getting drawing.");
                ie.initCause(ex);
                throw ie;
            }
        }
        // Write drawing to file
        Drawing saveDrawing = helper[0];
        format.write(f, saveDrawing);
    }

    /**
     * Sets the actions for the "Action" popup menu in the toolbar.
     * <p>
     * This list may contain null items which are used to denote a
     * separator in the popup menu.
     * <p>
     * Set this to null to set the drop down menus to the default actions.
     */
    public void setPopupActions(List<Action> actions) {
        actionToolBar.setPopupActions(actions);
    }

    /**
     * Gets the actions of the "Action" popup menu in the toolbar.
     * This list may contain null items which are used to denote a
     * separator in the popup menu.
     *
     * @return An unmodifiable list with actions.
     */
    public List<Action> getPopupActions() {
        return actionToolBar.getPopupActions();
    }

    public JComponent getComponent() {
        return this;
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        toolButtonGroup = new javax.swing.ButtonGroup();
        scrollPane = new javax.swing.JScrollPane();
        view = new org.jhotdraw.draw.DefaultDrawingView();
        toolsPanel = new javax.swing.JPanel();
        toolsScrollPane = new javax.swing.JScrollPane();
        toolsPane = new javax.swing.JPanel();
        creationToolBar = new org.jhotdraw.samples.svg.gui.ToolsToolBar();
        actionToolBar = new org.jhotdraw.samples.svg.gui.ActionsToolBar();
        fillToolBar = new org.jhotdraw.samples.svg.gui.FillToolBar();
        strokeToolBar = new org.jhotdraw.samples.svg.gui.StrokeToolBar();
        fontToolBar = new org.jhotdraw.samples.svg.gui.FontToolBar();
        arrangeToolBar = new org.jhotdraw.samples.svg.gui.ArrangeToolBar();
        alignToolBar = new org.jhotdraw.samples.svg.gui.AlignToolBar();
        figureToolBar = new org.jhotdraw.samples.svg.gui.FigureToolBar();
        linkToolBar = new org.jhotdraw.samples.svg.gui.LinkToolBar();
        canvasToolBar = new org.jhotdraw.samples.svg.gui.CanvasToolBar();
        viewToolBar = new org.jhotdraw.samples.svg.gui.ViewToolBar();
        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());
        scrollPane.setBorder(null);
        scrollPane.setViewportView(view);
        add(scrollPane, java.awt.BorderLayout.CENTER);
        toolsPanel.setBackground(new java.awt.Color(255, 255, 255));
        toolsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        toolsPanel.setOpaque(true);
        toolsPanel.setLayout(new java.awt.GridBagLayout());
        toolsScrollPane.setBorder(PaletteLookAndFeel.getInstance().getBorder("Ribbon.border"));
        toolsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        toolsScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        toolsScrollPane.setMinimumSize(new java.awt.Dimension(0, 0));
        toolsPane.setForeground(new java.awt.Color(153, 153, 153));
        toolsPane.add(creationToolBar);
        toolsPane.add(actionToolBar);
        toolsPane.add(fillToolBar);
        strokeToolBar.setMargin(new java.awt.Insets(0, 10, 0, 0));
        toolsPane.add(strokeToolBar);
        toolsPane.add(fontToolBar);
        toolsPane.add(arrangeToolBar);
        toolsPane.add(alignToolBar);
        toolsPane.add(figureToolBar);
        toolsPane.add(linkToolBar);
        toolsPane.add(canvasToolBar);
        toolsPane.add(viewToolBar);
        toolsScrollPane.setViewportView(toolsPane);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        toolsPanel.add(toolsScrollPane, gridBagConstraints);
        add(toolsPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jhotdraw.samples.svg.gui.ActionsToolBar actionToolBar;
    private org.jhotdraw.samples.svg.gui.AlignToolBar alignToolBar;
    private org.jhotdraw.samples.svg.gui.ArrangeToolBar arrangeToolBar;
    private org.jhotdraw.samples.svg.gui.CanvasToolBar canvasToolBar;
    private org.jhotdraw.samples.svg.gui.ToolsToolBar creationToolBar;
    private org.jhotdraw.samples.svg.gui.FigureToolBar figureToolBar;
    private org.jhotdraw.samples.svg.gui.FillToolBar fillToolBar;
    private org.jhotdraw.samples.svg.gui.FontToolBar fontToolBar;
    private org.jhotdraw.samples.svg.gui.LinkToolBar linkToolBar;
    private javax.swing.JScrollPane scrollPane;
    private org.jhotdraw.samples.svg.gui.StrokeToolBar strokeToolBar;
    private javax.swing.ButtonGroup toolButtonGroup;
    private javax.swing.JPanel toolsPane;
    private javax.swing.JPanel toolsPanel;
    private javax.swing.JScrollPane toolsScrollPane;
    private org.jhotdraw.draw.DefaultDrawingView view;
    private org.jhotdraw.samples.svg.gui.ViewToolBar viewToolBar;
    // End of variables declaration//GEN-END:variables
}
