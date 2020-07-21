package model.operations;

import model.entity.GrammarTree;
import model.entity.MAryNode;

/**
 * @author Andrés Felipe Chaparro Rosas | Yohan Caro | Fabian Cristancho
 * @date 6/07/2020
 * @version 1.0
 *
 */
public class GrammarTreePrinter {
	
	/**
	 * Imprime un arbol con los niveles dados
	 * @param grammarTree
	 * @param limitLevel
	 */
	public static void printGrammarTree(String[] tree) {
		if (tree != null)
			for (int i = 0; i < tree.length; i++) {
				System.out.println(tree[i]);
			}
	}
	
	public static void printGrammarTree(GrammarTree tree) {
		String[] stringTree= getStringTree(tree);
		
		if (tree != null)
			for (int i = 0; i < stringTree.length; i++) {
				System.out.println(stringTree[i]);
			}
	}
	
	/**
	 * Genera un arbol con los niveles dados
	 * @param grammarTree
	 * @param limitLevel
	 */
	public static void generateTree(GrammarTree grammarTree, int limitLevel) {
		grammarTree.add(grammarTree.getProductions()[0], grammarTree.getRoot());
		grammarTree.addByLevels(grammarTree.getRoot(), 2, limitLevel);
	}
	
	/**
	 * Informacion de un arbol para imprimir en consola
	 * @param grammarTree
	 * @param limitLevels
	 * @return
	 */
	public static String[] getStringTree(GrammarTree grammarTree, int limitLevels) {
		String[] tree = new String[limitLevels];

		tree[0] = grammarTree.getRoot().getData();

		makeLevels(grammarTree.getRoot(), tree, 1);

		return tree;
	}
	
	public static String[] getStringTree(GrammarTree grammarTree) {
		
		String[] tree = new String[grammarTree.getMaxLevel()];

		tree[0] = grammarTree.getRoot().getData();

		makeLevels(grammarTree.getRoot(), tree, 1);

		return tree;
	}

	/**
	 * Construye los niveles para imprimir en consola
	 * @param node
	 * @param tree
	 * @param level
	 */
	private static void makeLevels(MAryNode<String> node, String[] tree, int level) {
		if (tree[level] == null)
			tree[level] = "";

		for (int i = 0; i < node.getChilds().size(); i++) {
			tree[level] += node.getChilds().get(i).getData() + " ";

			if (node.getChilds().get(i).getChilds().size() != 0)
				makeLevels(node.getChilds().get(i), tree, level + 1);
		}
	}
}
