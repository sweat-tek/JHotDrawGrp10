/*
 * @(#)PropertyChangeEdit.java
 * 
 * Copyright (c) 2009 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 * 
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.beans;

import java.lang.reflect.Method;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;

/**
 * A {@code UndoableEdit} event which can undo a change of a JavaBeans property.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class PropertyChangeEdit extends AbstractUndoableEdit {

    /**
     * The object to be provided as the "source" of the JavaBeans property.
     */
    private Object source;
    /**
     * The name of the JavaBeans property.
     */
    private String propertyName;
    /**
     * The old value of the JavaBeans property.
     */
    private Object oldValue;
    /**
     * The new value of the JavaBeans property.
     */
    private Object newValue;
    /** The type of the property. */
    private Class type;

    /** Creates a new PropertyChangeEdit. */
    public <T> PropertyChangeEdit(Object source, String propertyName, Class<T> type, T oldValue, T newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /** Creates a new PropertyChangeEdit. */
    public PropertyChangeEdit(Object source, String propertyName, boolean oldValue, boolean newValue) {
        this(source, propertyName, Boolean.TYPE, oldValue, newValue);
    }

    /** Creates a new PropertyChangeEdit. */
    public PropertyChangeEdit(Object source, String propertyName, int oldValue, int newValue) {
        this(source, propertyName, Integer.TYPE, oldValue, newValue);
    }

    /** Creates a new PropertyChangeEdit. */
    public PropertyChangeEdit(Object source, String propertyName, long oldValue, long newValue) {
        this(source, propertyName, Long.TYPE, oldValue, newValue);
    }

    /** Creates a new PropertyChangeEdit. */
    public PropertyChangeEdit(Object source, String propertyName, float oldValue, float newValue) {
        this(source, propertyName, Float.TYPE, oldValue, newValue);
    }

    /** Creates a new PropertyChangeEdit. */
    public PropertyChangeEdit(Object source, String propertyName, double oldValue, double newValue) {
        this(source, propertyName, Double.TYPE, oldValue, newValue);
    }

    /** Creates a new PropertyChangeEdit. */
    public PropertyChangeEdit(Object source, String propertyName, char oldValue, char newValue) {
        this(source, propertyName, Character.TYPE, oldValue, newValue);
    }

    /** Creates a new PropertyChangeEdit. */
    public PropertyChangeEdit(Object source, String propertyName, String oldValue, String newValue) {
        this(source, propertyName, String.class, oldValue, newValue);
    }

    /**
     * Returns the setter for the property.
     *
     * @return the setter method.
     */
    protected Method getSetter() {
        try {
            return source.getClass().getMethod("set" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1), type);
        } catch (Exception e) {
            InternalError ie = new InternalError("Couldn't find setter for property \"" + propertyName + "\" in " + source);
            ie.initCause(e);
            throw ie;
        }
    }

    /** Undoes the change. */
    @Override
    public void undo() throws CannotRedoException {
        super.undo();
        try {
            getSetter().invoke(source, oldValue);
        } catch (Exception e) {
            InternalError ie = new InternalError("Couldn't invoke setter for property \"" + propertyName + "\" in " + source);
            ie.initCause(e);
            throw ie;
        }
    }

    /** Redoes the change. */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        try {
            getSetter().invoke(source, newValue);
        } catch (Exception e) {
            InternalError ie = new InternalError("Couldn't invoke setter for property \"" + propertyName + "\" in " + source);
            ie.initCause(e);
            throw ie;
        }
    }
}
