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
	protected T data;
	protected MAryNode<T> parent;
	protected List<MAryNode<T>> childs;

	public MAryNode(T data) {
		this(data, null);
	}

	public MAryNode(T data, MAryNode<T> parent) {
		this.data = data;
		this.parent = parent;
		this.childs = new ArrayList<MAryNode<T>>();
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
}
