package com.syhan.rcp.geftut.editor.editpolicies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.syhan.rcp.geftut.editor.commands.AbstractLayoutCommand;
import com.syhan.rcp.geftut.editor.commands.EmployeeChangeLayoutCommand;
import com.syhan.rcp.geftut.editor.commands.ServiceChangeLayoutCommand;
import com.syhan.rcp.geftut.editor.part.EmployeePart;
import com.syhan.rcp.geftut.editor.part.ServicePart;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {
	//
	
	@Override
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		// 이 메소드는 마우스 이벤트 발생시마다 계속 호출됨.
		// System.out.println("Policy ... make command...");
		AbstractLayoutCommand command = null;
		
		if (child instanceof EmployeePart) {
			command = new EmployeeChangeLayoutCommand();
		} else if (child instanceof ServicePart) {
			command = new ServiceChangeLayoutCommand();
		}
		
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
