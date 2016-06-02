package com.syhan.rcp.geftut.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;

import com.syhan.rcp.geftut.editor.model.Service;

// This commands will be called by EditPolicy.
public class ServiceChangeLayoutCommand extends AbstractLayoutCommand {
	//
	private Service model;
	private Rectangle layout;
	
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
	}

}
