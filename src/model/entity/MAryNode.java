package model.entity;
/**
 * @author
 * @date 01/10/2019
 * @version 1.0
 *
 */

import java.util.ArrayList;
import java.util.List;

public class MAryNode<T> {
	protected int level;
	protected T data;
	protected MAryNode<T> parent;
	protected List<MAryNode<T>> childs;

	public MAryNode(T data, List<MAryNode<T>> nodes,int level) {
		this.data = data;
		this.parent = null;
		this.level = level;
		this.childs = new ArrayList<MAryNode<T>>();
		nodes.add(this);
	}

	public MAryNode(T data, MAryNode<T> parent, List<MAryNode<T>> nodes) {
		this.data = data;
		this.parent = parent;
		this.childs = new ArrayList<MAryNode<T>>();
		nodes.add(this);

		if (this.parent != null)
			this.level = this.parent.level + 1;
		else
			this.level = 0;
		
	}

	public T getData() {
		return this.data;
	}

	public List<MAryNode<T>> getChilds() {
		return this.childs;
	}

	public boolean isLeaf() {
		return this.childs.isEmpty();
	}

	public int getLevel() {
		return level;
	}
}
