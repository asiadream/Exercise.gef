package com.syhan.rcp.geftut6.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class MyEditorInput implements IEditorInput {
	//
	public String name;
	
	public MyEditorInput(String name) {
		//
		this.name = name;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		//
		return this.name != null;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		//
		return ImageDescriptor.getMissingImageDescriptor();
	}

	@Override
	public String getName() {
		//
		return this.name;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		//
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		//
		if (!(obj instanceof MyEditorInput)) {
			return false;
		}
		return ((MyEditorInput)obj).getName().equals(getName());
	}
	
	

}
