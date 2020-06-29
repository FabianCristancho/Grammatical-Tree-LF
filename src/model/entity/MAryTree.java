package model.entity;

/**
 * @author
 * @date 01/10/2019
 * @version 1.0
 *
 */
public class MAryTree<T> {
	protected MAryNode<T> root;
	
	public MAryTree(T data) {
		this.root = new MAryNode<T>(data);
	}
	
	/**
	 * Agrega un valor T al arbol M-ario y lo coloca como rama de parent
	 * @param data informacion nueva
	 * @param parent padre rama de mayor nivel de la que entra
	 * @return true (as specified by Collection.add)
	 */
	public boolean add(T data, MAryNode<T> parent) {
		return parent.childs.add(new MAryNode<T>(data, parent));
	}
	
	/**
	 * Remueve un valor T del arbol, quita el nodo y sus ramas
	 * @param node nodo a eliminar
	 * @param parent referencia para eliminar
	 * @return true si sí contiene el elemento 
	 */
	public boolean remove(MAryNode<T> node,MAryNode<T> parent) {
		return parent.childs.remove(node);
	}
	
	public MAryNode<T> getRoot() {
		return root;
	}
}
