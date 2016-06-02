package com.syhan.rcp.geftut6.editor.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.syhan.rcp.geftut6.editor.model.Employee;
import com.syhan.rcp.geftut6.editor.model.Enterprise;
import com.syhan.rcp.geftut6.editor.model.Service;

public class AppEditPartFactory implements EditPartFactory {
	//
	
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		//
		AbstractGraphicalEditPart part = null;
		
		if (model instanceof Enterprise) {
			part = new EnterprisePart();
		} else if (model instanceof Service) {
			part = new ServicePart();
		} else if (model instanceof Employee) {
			part = new EmployeePart();
		}
		
		part.setModel(model);
		return part;
	}
	
}
