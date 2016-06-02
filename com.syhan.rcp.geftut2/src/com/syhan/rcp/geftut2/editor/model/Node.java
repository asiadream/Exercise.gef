package com.syhan.rcp.geftut2.editor.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class Node {
	//
	private PropertyChangeSupport listeners;
	public static final String PROPERTY_LAYOUT = "NodeLayout";
	public static final String PROPERTY_ADD = "NodeAddChild";
	public static final String PROPERTY_REMOVE = "NodeRemoveChild";
	
	private String name;
	private Rectangle layout;
	private List<Node> children;
	private Node parent;
	
	public Node() {
		//
		this.name = "Unknown";
		this.layout = new Rectangle(10, 10, 100, 100);
		this.children = new ArrayList<Node>();
		this.parent = null;
		
		this.listeners = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		//
		listeners.addPropertyChangeListener(listener);
	}
	
	public PropertyChangeSupport getListeners() {
		//
		return listeners;
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		//
		listeners.removePropertyChangeListener(listener);
	}
	
	public boolean addChild(Node child) {
		//
		boolean b = this.children.add(child);
		if (b) {
			child.setParent(this);
			getListeners().firePropertyChange(PROPERTY_ADD, null, child);
		}
		return b;
	}
	
	public boolean removeChild(Node child) {
		//
		boolean b = this.children.remove(child);
		if (b) {
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		}
		return b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rectangle getLayout() {
		return layout;
	}

	public void setLayout(Rectangle newLayout) {
		//
		Rectangle oldLayout = this.layout;
		this.layout = newLayout;
		getListeners().firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

}
