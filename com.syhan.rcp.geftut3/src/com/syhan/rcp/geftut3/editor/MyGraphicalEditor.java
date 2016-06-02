package com.syhan.rcp.geftut3.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.actions.ActionFactory;

import com.syhan.rcp.geftut3.editor.model.Employee;
import com.syhan.rcp.geftut3.editor.model.Enterprise;
import com.syhan.rcp.geftut3.editor.model.Service;
import com.syhan.rcp.geftut3.editor.part.AppEditPartFactory;

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
		configureEditPartFactory(viewer);
		configureRootEditPart(viewer);
		configureKeyHandler(viewer);
	}
	

	private void configureEditPartFactory(GraphicalViewer viewer) {
		//
		viewer.setEditPartFactory(new AppEditPartFactory());
	}

	private void configureRootEditPart(GraphicalViewer viewer) {
		// for zoom
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		
		double[] zoomLevels = new double[]{0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0, 20.0};
		manager.setZoomLevels(zoomLevels);
		
		List<String> zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);
	}
	
	private void configureKeyHandler(GraphicalViewer viewer) {
		//
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), MouseWheelZoomHandler.SINGLETON);
		
		viewer.setKeyHandler(keyHandler);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class type) {
		//
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getZoomManager();
		}
		return super.getAdapter(type);
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
