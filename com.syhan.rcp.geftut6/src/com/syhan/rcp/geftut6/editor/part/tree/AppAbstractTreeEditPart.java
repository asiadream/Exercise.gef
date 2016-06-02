package com.syhan.rcp.geftut6.editor.part.tree;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.syhan.rcp.geftut6.editor.model.Node;

public abstract class AppAbstractTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {

	@Override
	public void activate() {
		//
		super.activate();
		((Node)getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		//
		((Node)getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

}
