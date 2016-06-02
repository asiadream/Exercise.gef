package com.syhan.rcp.geftut6.editor.editpolicies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.syhan.rcp.geftut6.editor.commands.RenameCommand;

public class AppRenamePolicy extends AbstractEditPolicy {
	//
	@Override
	public Command getCommand(Request request) {
		// see RenameAction.createRenameCommand
		if (request.getType().equals("rename")) {
			return createRenameCommand(request);
		}
		return null;
	}

	protected Command createRenameCommand(Request request) {
		//
		RenameCommand command = new RenameCommand();
		command.setModel(getHost().getModel());
		command.setNewName((String) request.getExtendedData().get("newName"));
		return command;
	}
}
