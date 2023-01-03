/*
 * @(#)SelectionOpacityIcon.java
 *
 * Copyright (c) 2008 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.svg.gui;

import org.jhotdraw.draw.figure.Figure;
import java.awt.*;
import java.net.*;
import org.jhotdraw.draw.*;

/**
 * {@code SelectionOpacityIcon} visualizes an opacity attribute of the selected
 * {@code Figure}(s) in the active {@code DrawingView} of a {@code DrawingEditor}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class SelectionOpacityIcon extends javax.swing.ImageIcon {

    private static final long serialVersionUID = 1L;
    private DrawingEditor editor;
    private AttributeKey<Double> opacityKey;
    private AttributeKey<Color> fillColorKey;
    private AttributeKey<Color> strokeColorKey;
    private Shape fillShape;
    private Shape strokeShape;
    private Double opacity;


    /**
     * Creates a new instance.
     *
     * @param editor The drawing editor.
     * @param opacityKey The opacityKey of the default attribute
     * @param imageLocation the icon image
     * @param fillShape The shape to be drawn with the fillColor of the default
     * attribute.
     */
    public SelectionOpacityIcon(
            DrawingEditor editor,
            AttributeKey<Double> opacityKey,
            AttributeKey<Color> fillColorKey,
            AttributeKey<Color> strokeColorKey,
            URL imageLocation,
            Shape fillShape,
            Shape strokeShape) {
        super(imageLocation);
        this.editor = editor;
        this.opacityKey = opacityKey;
        this.fillColorKey = fillColorKey;
        this.strokeColorKey = strokeColorKey;
        this.fillShape = fillShape;
        this.strokeShape = strokeShape;
    }

    public SelectionOpacityIcon(
            DrawingEditor editor,
            AttributeKey<Double> opacityKey,
            AttributeKey<Color> fillColorKey,
            AttributeKey<Color> strokeColorKey,
            Image image,
            Shape fillShape,
            Shape strokeShape) {
        super(image);
        this.editor = editor;
        this.opacityKey = opacityKey;
        this.fillColorKey = fillColorKey;
        this.strokeColorKey = strokeColorKey;
        this.fillShape = fillShape;
        this.strokeShape = strokeShape;
    }

    @Override
    public void paintIcon(java.awt.Component c, java.awt.Graphics gr, int x, int y) {
        Graphics2D g = (Graphics2D) gr;
        super.paintIcon(c, g, x, y);
        Color fillColor;
        Color strokeColor;
        DrawingView view = (editor == null) ? null : editor.getActiveView();
        if (view != null && view.getSelectedFigures().size() == 1) {
            Figure f = view.getSelectedFigures().iterator().next();
            setFigureOpacity(f);
            fillColor = (fillColorKey == null) ? null : f.get(fillColorKey);
            strokeColor = (strokeColorKey == null) ? null : f.get(strokeColorKey);
        } else if (editor != null) {
            setDefaultOpacityAttributes();
            fillColor = (fillColorKey == null) ? null : fillColorKey.get(editor.getDefaultAttributes());
            strokeColor = (strokeColorKey == null) ? null : strokeColorKey.get(editor.getDefaultAttributes());
        } else {
            setDefaultOpacity();
            fillColor = (fillColorKey == null) ? null : fillColorKey.getDefaultValue();
            strokeColor = (strokeColorKey == null) ? null : strokeColorKey.getDefaultValue();
        }
        if (fillColorKey != null && fillShape != null) {
            if (opacity != null) {
                if (fillColor == null) {
                    fillColor = Color.BLACK;
                }
                g.setColor(new Color((((int) (opacity * 255)) << 24) | (fillColor.getRGB() & 0xffffff), true));
                g.translate(x, y);
                g.fill(fillShape);
                g.translate(-x, -y);
            }
        }
        if (strokeColorKey != null && strokeShape != null) {
            if (opacity != null) {
                if (strokeColor == null) {
                    strokeColor = Color.BLACK;
                }
                g.setColor(new Color((((int) (opacity * 255)) << 24) | (strokeColor.getRGB() & 0xffffff), true));
                g.translate(x, y);
                g.draw(strokeShape);
                g.translate(-x, -y);
            }
        }
    }

    public Double setFigureOpacity(Figure f){
        opacity = f.get(opacityKey);
        return opacity;
    }

    public Double setDefaultOpacityAttributes() {
        opacity = opacityKey.get(editor.getDefaultAttributes());
        return opacity;
    }

    public Double setDefaultOpacity(){
        opacity = opacityKey.getDefaultValue();
        return opacity;
    }
}
