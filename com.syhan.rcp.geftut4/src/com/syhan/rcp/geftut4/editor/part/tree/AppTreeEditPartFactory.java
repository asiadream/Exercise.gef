package com.syhan.rcp.geftut4.editor.part.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.syhan.rcp.geftut4.editor.model.Employee;
import com.syhan.rcp.geftut4.editor.model.Enterprise;
import com.syhan.rcp.geftut4.editor.model.Service;

public class AppTreeEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		//
		EditPart part = null;
		
		if (model instanceof Enterprise) {
			part = new EnterpriseTreeEditPart();
		} else if (model instanceof Service) {
			part = new ServiceTreeEditPart();
		} else if (model instanceof Employee) {
			part = new EmployeeTreeEditPart();
		}
		
		if (part != null) {
			part.setModel(model);
		}
		
		return part;
	}

}
