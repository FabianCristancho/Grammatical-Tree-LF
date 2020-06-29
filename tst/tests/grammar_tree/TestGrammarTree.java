package tests.grammar_tree;

import model.entity.GrammarTree;
import model.entity.MAryNode;
import model.entity.Production;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 28/06/2020
 * @version 1.0
 *
 */
public class TestGrammarTree {

	public static void main(String[] args) {
		char initialSymbol = 'S';
		char[] terminalSymbols = new char[] {'a','b'};
		char[] noTerminalSymbols = new char[] {'S','A','T'};
		
		Production[] productions = new Production[] { 
				new Production('S', "A", "λ"),
				new Production('A', "aA","aT"),
				new Production('T', "b", "A")
				};
		GrammarTree grammarTree = new GrammarTree(terminalSymbols, noTerminalSymbols, initialSymbol, productions);
		
		System.out.println(grammarTree.wordBelongs("λ"));
		
		
	}
	
	/**
	 * Imprime un arbol con los niveles dados
	 * @param grammarTree
	 * @param limitLevel
	 */
	public static void printGrammarTree(GrammarTree grammarTree, int limitLevel) {
		generateTree(grammarTree, limitLevel);

		String[] tree = getStringTree(grammarTree,limitLevel);

		if (tree != null)
			for (int i = 0; i < tree.length; i++) {
				System.out.println(tree[i]);
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
