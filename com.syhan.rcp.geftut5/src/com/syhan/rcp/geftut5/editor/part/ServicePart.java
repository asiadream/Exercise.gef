package com.syhan.rcp.geftut5.editor.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.syhan.rcp.geftut5.editor.editpolicies.AppDeletePolicy;
import com.syhan.rcp.geftut5.editor.editpolicies.AppEditLayoutPolicy;
import com.syhan.rcp.geftut5.editor.editpolicies.AppRenamePolicy;
import com.syhan.rcp.geftut5.editor.figure.ServiceFigure;
import com.syhan.rcp.geftut5.editor.model.Node;
import com.syhan.rcp.geftut5.editor.model.Service;

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
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
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
		
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) {
			refreshChildren();
		}
		
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) {
			refreshChildren();
		}
		
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) {
			refreshVisuals();
		}
	}

}
