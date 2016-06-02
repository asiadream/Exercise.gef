package com.syhan.rcp.geftut4.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;

import com.syhan.rcp.geftut4.editor.model.Employee;

// This commands will be called by EditPolicy.
public class EmployeeChangeLayoutCommand extends AbstractLayoutCommand {
	//
	private Employee model;
	private Rectangle layout;
	
	private Rectangle oldLayout;
	
	@Override
	public void execute() {
		// 레이아웃이 결정되었을 때 호출되어짐.(마우스 버튼이 떼어질 때)
		//System.out.println("EmployChangeCommand Execute...");
		model.setLayout(layout);
	}
	
	@Override
	public void setConstraint(Rectangle rect) {
		//
		this.layout = rect;
	}

	@Override
	public void setModel(Object model) {
		//
		this.model = (Employee) model;
		
		this.oldLayout = ((Employee) model).getLayout();
	}

	@Override
	public void undo() {
		//
		this.model.setLayout(this.oldLayout);
	}

}
