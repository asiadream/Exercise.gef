package com.syhan.rcp.geftut6.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;

import com.syhan.rcp.geftut6.editor.model.Service;

// This commands will be called by EditPolicy.
public class ServiceChangeLayoutCommand extends AbstractLayoutCommand {
	//
	private Service model;
	private Rectangle layout;
	
	private Rectangle oldLayout;
	
	@Override
	public void execute() {
		//
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
		this.model = (Service) model;
		
		this.oldLayout = ((Service)model).getLayout();
	}

	@Override
	public void undo() {
		//
		this.model.setLayout(this.oldLayout);
	}

}
