package com.syhan.rcp.geftut6.editor.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.syhan.rcp.geftut6.editor.editpolicies.AppDeletePolicy;
import com.syhan.rcp.geftut6.editor.model.Employee;
import com.syhan.rcp.geftut6.editor.model.Node;

public class EmployeeTreeEditPart extends AppAbstractTreeEditPart {
	//
	
	@Override
	protected List<Node> getModelChildren() {
		//
		return ((Employee)getModel()).getChildren();
	}

	@Override
	protected void createEditPolicies() {
		//
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}

	@Override
	protected void refreshVisuals() {
		//
		Employee model = (Employee) getModel();
		setWidgetText(model.getName() + " " + model.getPrenom());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW));
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
