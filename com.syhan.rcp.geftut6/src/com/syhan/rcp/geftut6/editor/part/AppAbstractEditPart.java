package com.syhan.rcp.geftut6.editor.part;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.syhan.rcp.geftut6.editor.model.Node;

public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	@Override
	public void activate() {
		//
		super.activate();
		((Node)getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		//
		super.deactivate();
		((Node)getModel()).removePropertyChangeListener(this);
	}
	
}
