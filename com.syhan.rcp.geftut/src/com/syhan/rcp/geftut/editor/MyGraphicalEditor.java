package com.syhan.rcp.geftut.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditor;

import com.syhan.rcp.geftut.editor.model.Employee;
import com.syhan.rcp.geftut.editor.model.Enterprise;
import com.syhan.rcp.geftut.editor.model.Service;
import com.syhan.rcp.geftut.editor.part.AppEditPartFactory;

public class MyGraphicalEditor extends GraphicalEditor {
	//
	public static final String ID = MyGraphicalEditor.class.getName();

	public MyGraphicalEditor() {
		//
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void configureGraphicalViewer() {
		//
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());
	}

	@Override
	protected void initializeGraphicalViewer() {
		//
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(createEnterprise());
	}

	private Enterprise createEnterprise() {
		//
		Enterprise enterprise = new Enterprise();
		enterprise.setName("인천공항");
		enterprise.setAddress("인천");
		enterprise.setCapital(10000);

		Service comptaService = new Service();
		comptaService.setName("Compta");
		comptaService.setEtage(2);
		comptaService.setLayout(new Rectangle(30, 50, 250, 150));
		
		Employee employeCat = new Employee();
		employeCat.setName("Debroua");
		employeCat.setPrenom("Cat");
		employeCat.setLayout(new Rectangle(25, 40, 60, 40));
		comptaService.addChild(employeCat);
		
		Employee employeJyce = new Employee();
		employeJyce.setName("Psykokwak");
		employeJyce.setPrenom("Jyce");
		employeJyce.setLayout(new Rectangle(100, 60, 60, 40));
		comptaService.addChild(employeJyce);
		
		Employee employeEva = new Employee();
		employeEva.setName("Longoria");
		employeEva.setPrenom("Eva");
		employeEva.setLayout(new Rectangle(180, 90, 60, 40));
		comptaService.addChild(employeEva);
		
		enterprise.addChild(comptaService);
		
		Service rhService = new Service();
		rhService.setName("Ressources Humaine");
		rhService.setEtage(1);
		rhService.setLayout(new Rectangle(220, 230, 250, 150));
		
		Employee employePaul = new Employee();
		employePaul.setName("Dupond");
		employePaul.setPrenom("Paul");
		employePaul.setLayout(new Rectangle(40, 70, 60, 40));
		rhService.addChild(employePaul);
		
		Employee employeEric = new Employee();
		employeEric.setName("Durand");
		employeEric.setPrenom("Eric");
		employeEric.setLayout(new Rectangle(170, 100, 60, 40));
		rhService.addChild(employeEric);
		
		enterprise.addChild(rhService);

		return enterprise;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}
}
