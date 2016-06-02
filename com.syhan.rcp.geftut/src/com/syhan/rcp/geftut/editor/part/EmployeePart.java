package com.syhan.rcp.geftut.editor.part;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;

import com.syhan.rcp.geftut.editor.figure.EmployeeFigure;
import com.syhan.rcp.geftut.editor.model.Employee;
import com.syhan.rcp.geftut.editor.model.Node;

public class EmployeePart extends AppAbstractEditPart {
	//
	
	@Override
	protected IFigure createFigure() {
		//
		IFigure figure = new EmployeeFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void refreshVisuals() {
		//
		EmployeeFigure figure = (EmployeeFigure) getFigure();
		Employee model = (Employee) getModel();
		
		figure.setName(model.getName());
		figure.setFirstName(model.getPrenom());
		figure.setLayout(model.getLayout());
	}
	
	@Override
	protected List<Node> getModelChildren() {
		//
		return new ArrayList<Node>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) {
			refreshVisuals();
		}
	}

	
	

}
