package com.syhan.rcp.geftut.editor.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class Node {
	//
	private PropertyChangeSupport listeners;
	public static final String PROPERTY_LAYOUT = "NodeLayout";
	
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
		child.setParent(this);
		return this.children.add(child);
	}
	
	public boolean removeChild(Node child) {
		//
		return this.children.remove(child);
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
