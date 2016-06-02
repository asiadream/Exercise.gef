package com.syhan.rcp.geftut4.editor.part;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.syhan.rcp.geftut4.editor.model.Node;

public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	@Override
	public void activate() {
		//
		//System.out.println("activate...");
		super.activate();
		((Node)getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		//
		//System.out.println("deactivate...");
		super.deactivate();
		((Node)getModel()).removePropertyChangeListener(this);
	}
	
}
