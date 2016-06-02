package com.syhan.rcp.geftut.editor.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.syhan.rcp.geftut.editor.editpolicies.AppEditLayoutPolicy;
import com.syhan.rcp.geftut.editor.figure.EnterpriseFigure;
import com.syhan.rcp.geftut.editor.model.Enterprise;
import com.syhan.rcp.geftut.editor.model.Node;

public class EnterprisePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		//
		IFigure figure = new EnterpriseFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		//
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
	}

	@Override
	protected void refreshVisuals() {
		//
		EnterpriseFigure figure = (EnterpriseFigure) getFigure();
		Enterprise model = (Enterprise) getModel();
		
		figure.setName(model.getName());
		figure.setAddress(model.getAddress());
		figure.setCapital(model.getCapital());
	}

	@Override
	protected List<Node> getModelChildren() {
		//
		return ((Enterprise)getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) {
			refreshVisuals();
		}
			
	}
	
	

}
