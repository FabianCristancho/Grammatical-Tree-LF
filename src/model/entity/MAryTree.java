package model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 01/10/2019
 * @version 1.0
 *
 */
public class MAryTree<T> {
	protected MAryNode<T> root;
	protected List<MAryNode<T>> nodes;

	public MAryTree(T data) {
		this.nodes = new ArrayList<>();
		this.root = new MAryNode<T>(data, this.nodes, 1);
	}

	/**
	 * Agrega un valor T al arbol M-ario y lo coloca como rama de parent
	 * 
	 * @param data   informacion nueva
	 * @param parent padre rama de mayor nivel de la que entra
	 * @return true (as specified by Collection.add)
	 */
	public boolean add(T data, MAryNode<T> parent) {
		return parent.childs.add(new MAryNode<T>(data, parent, nodes));
	}

	/**
	 * Remueve un valor T del arbol, quita el nodo y sus ramas
	 * 
	 * @param node   nodo a eliminar
	 * @param parent referencia para eliminar
	 * @return true si sí contiene el elemento
	 */
	public boolean remove(MAryNode<T> node, MAryNode<T> parent) {
		return parent.childs.remove(node);
	}

	/**
	 * Retorna todos los nodos de un nivel
	 * @param level nivel de referencia
	 * @return nodos
	 */
	public List<MAryNode<T>> getAllLevelNodes(int level) {
		List<MAryNode<T>> nodes = new ArrayList<MAryNode<T>>();

		for (int i = 0; i < this.nodes.size(); i++) {
			if (this.nodes.get(i).getLevel() == level)
				nodes.add(this.nodes.get(i));
		}

		return nodes;
	}

	public MAryNode<T> getRoot() {
		return root;
	}
}
