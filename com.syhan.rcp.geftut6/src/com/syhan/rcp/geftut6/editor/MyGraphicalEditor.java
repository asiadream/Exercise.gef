package com.syhan.rcp.geftut6.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.syhan.rcp.geftut6.editor.actions.RenameAction;
import com.syhan.rcp.geftut6.editor.model.Employee;
import com.syhan.rcp.geftut6.editor.model.Enterprise;
import com.syhan.rcp.geftut6.editor.model.Service;
import com.syhan.rcp.geftut6.editor.part.AppEditPartFactory;
import com.syhan.rcp.geftut6.editor.part.tree.AppTreeEditPartFactory;

public class MyGraphicalEditor extends GraphicalEditor {
	//
	public static final String ID = MyGraphicalEditor.class.getName();
	
	private Enterprise model;
	private KeyHandler keyHandler;

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
		configureContextMenu(viewer);
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
		this.keyHandler = createKeyHandler();
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), MouseWheelZoomHandler.SINGLETON);
		viewer.setKeyHandler(keyHandler);
	}
	
	private void configureContextMenu(GraphicalViewer viewer) {
		//
		ContextMenuProvider provider = new AppContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class type) {
		//
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getZoomManager();
		}
		
		if (type == IContentOutlinePage.class) {
			return new OutlinePage();
		}
		
		return super.getAdapter(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createActions() {
		// register user defined actions
		super.createActions();
		
		ActionRegistry registry = getActionRegistry();
		IAction action = new RenameAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	protected void initializeGraphicalViewer() {
		//
		GraphicalViewer viewer = getGraphicalViewer();
		this.model = createEnterprise();
		viewer.setContents(this.model);
	}
	
	private KeyHandler createKeyHandler() {
		//
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		return keyHandler;
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
	
	// ---------------------------
	protected class OutlinePage extends ContentOutlinePage {
		//
		private SashForm sash;
		
		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;
		
		public OutlinePage() {
			super(new TreeViewer());
		}

		@Override
		public void createControl(Composite parent) {
			//
			sash = new SashForm(parent, SWT.VERTICAL);
			getViewer().createControl(sash);
			
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			getViewer().setContents(model);
			
			getSelectionSynchronizer().addViewer(getViewer());
			
			// create miniature view
			createMiniatureView(sash);
		}

		private void createMiniatureView(Composite parent) {
			//
			Canvas canvas = new Canvas(parent, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);
			
			Viewport viewport = (Viewport) ((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getFigure();
			IFigure source = ((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getLayer(LayerConstants.PRINTABLE_LAYERS);
			thumbnail = new ScrollableThumbnail(viewport);
			thumbnail.setSource(source);
			lws.setContents(thumbnail);
			
			disposeListener = new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(disposeListener);
		}

		@Override
		public void init(IPageSite pageSite) {
			//
			super.init(pageSite);
			
			IActionBars bars = getSite().getActionBars();
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(), getActionRegistry().getAction(ActionFactory.UNDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(), getActionRegistry().getAction(ActionFactory.REDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
			bars.updateActionBars();
			
			getViewer().setKeyHandler(keyHandler);
			
			// for context menu
			ContextMenuProvider provider = new AppContextMenuProvider(getViewer(), getActionRegistry());
			getViewer().setContextMenu(provider);
		}
		
		@Override
		public Control getControl() {
			return sash;
		}
		
		public void dispose() {
			//
			getSelectionSynchronizer().removeViewer(getViewer());
			if (getGraphicalViewer().getControl() != null && !getGraphicalViewer().getControl().isDisposed()) {
				getGraphicalViewer().getControl().removeDisposeListener(disposeListener);
			}
			super.dispose();
		}
		
	}
	// ---------------------------
}
