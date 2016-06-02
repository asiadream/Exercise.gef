package com.syhan.rcp.geftut6.editor.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import com.syhan.rcp.geftut6.editor.model.Enterprise;
import com.syhan.rcp.geftut6.editor.model.Node;

public class EnterpriseTreeEditPart extends AppAbstractTreeEditPart {
	//
	
	@Override
	protected List<Node> getModelChildren() {
		//
		return ((Enterprise)getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) {
			refreshChildren();
		}
		
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) {
			refreshChildren();
		}
	}

}
