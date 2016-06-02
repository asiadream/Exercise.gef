package com.syhan.rcp.geftut5.editor.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.syhan.rcp.geftut5.editor.editpolicies.AppDeletePolicy;
import com.syhan.rcp.geftut5.editor.editpolicies.AppRenamePolicy;
import com.syhan.rcp.geftut5.editor.model.Node;
import com.syhan.rcp.geftut5.editor.model.Service;

public class ServiceTreeEditPart extends AppAbstractTreeEditPart {
	//
	
	@Override
	protected List<Node> getModelChildren() {
		//
		return ((Service)getModel()).getChildren();
	}
	
	@Override
	protected void createEditPolicies() {
		//
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}

	@Override
	protected void refreshVisuals() {
		//
		Service model = (Service) getModel();
		setWidgetText(model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
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
		
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) {
			refreshVisuals();
		}
	}


}
