package com.syhan.rcp.geftut6;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {
	private static final String ID_TABS_FOLDER = "TabsFolder";

	public void createInitialLayout(IPageLayout layout) {
		//
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		
		IFolderLayout tabs = layout.createFolder(ID_TABS_FOLDER, IPageLayout.LEFT, 0.3f, editorArea);
		tabs.addView(IPageLayout.ID_OUTLINE);
		
		IFolderLayout tabs2 = layout.createFolder(ID_TABS_FOLDER + "2", IPageLayout.BOTTOM, 0.5f, ID_TABS_FOLDER);
		tabs2.addView(IPageLayout.ID_PROP_SHEET);
	}
}
