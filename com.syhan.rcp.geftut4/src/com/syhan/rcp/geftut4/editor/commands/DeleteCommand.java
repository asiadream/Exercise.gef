package com.syhan.rcp.geftut4.editor.commands;

import org.eclipse.gef.commands.Command;

import com.syhan.rcp.geftut4.editor.model.Node;

public class DeleteCommand extends Command {
	//
	private Node model;
	private Node parentModel;
	
	@Override
	public void execute() {
		//
		this.parentModel.removeChild(model);
	}

	public void setModel(Object model) {
		this.model = (Node)model;
	}

	public void setParentModel(Object parentModel) {
		this.parentModel = (Node)parentModel;
	}

	@Override
	public void undo() {
		//
		this.parentModel.addChild(model);
	}
	
	
}
