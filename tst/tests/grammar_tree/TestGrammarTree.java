package tests.grammar_tree;

import model.entity.GrammarTree;
import model.entity.Production;
import model.operations.GrammarTreePrinter;

/**
 * @author Andrés Felipe Chaparro Rosas | Yohan Caro | Fabian Cristancho
 * @date 28/06/2020
 * @version 1.0
 *
 */
public class TestGrammarTree {

	public static void main(String[] args) {
		char initialSymbol = 'S';
		String[] terminalSymbols = new String[] {"a","b"};
		String[] noTerminalSymbols = new String[] {"S","A","T"};
		
		Production[] productions = new Production[] { 
				new Production('S', "A", "λ"),
				new Production('A', "aA","aT"),
				new Production('T', "b", "A")
				};
		GrammarTree grammarTree = new GrammarTree(terminalSymbols, noTerminalSymbols, initialSymbol, productions);
		
		System.out.println(grammarTree.wordBelongs("ab"));
		
		GrammarTreePrinter.printGrammarTree(grammarTree);
	}
}
