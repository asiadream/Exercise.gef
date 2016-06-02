package com.syhan.rcp.geftut.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;

import com.syhan.rcp.geftut.editor.model.Employee;

// This commands will be called by EditPolicy.
public class EmployeeChangeLayoutCommand extends AbstractLayoutCommand {
	//
	private Employee model;
	private Rectangle layout;
	
	@Override
	public void execute() {
		// 레이아웃이 결정되었을 때 호출되어짐.(마우스 버튼이 떼어질 때)
		System.out.println("EmployChangeCommand Execute...");
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
	}

}
