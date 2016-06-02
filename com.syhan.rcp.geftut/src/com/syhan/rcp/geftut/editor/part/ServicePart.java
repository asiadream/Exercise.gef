package com.syhan.rcp.geftut.editor.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.syhan.rcp.geftut.editor.editpolicies.AppEditLayoutPolicy;
import com.syhan.rcp.geftut.editor.figure.ServiceFigure;
import com.syhan.rcp.geftut.editor.model.Node;
import com.syhan.rcp.geftut.editor.model.Service;

public class ServicePart extends AppAbstractEditPart {
	//
	
	@Override
	protected IFigure createFigure() {
		//
		IFigure figure = new ServiceFigure();
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
		ServiceFigure figure = (ServiceFigure) getFigure();
		Service model = (Service) getModel();
		figure.setName(model.getName());
		figure.setEtage(model.getEtage());
		figure.setLayout(model.getLayout());
	}

	@Override
	protected List<Node> getModelChildren() {
		//
		return ((Service)getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) {
			refreshVisuals();
		}
	}

}
