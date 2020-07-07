package model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 21/04/2020
 * @version 1.0
 *
 */
public class GrammarTree extends MAryTree<String> {

	private String[]  terminalSymbols,noTerminalSymbols;
	private Production[] productions;
	private int currentLevel;

	public GrammarTree(String[] terminalSymbols, String[] noTerminalSymbols, char initialSymbol, Production[] productions) {
		super(initialSymbol + "");
		this.terminalSymbols = terminalSymbols;
		this.noTerminalSymbols = noTerminalSymbols;
		this.productions = productions;
	}

	/**
	 * Añade desde el nodo entrante los niveles de limitLevel. Recursivo
	 * 
	 * @param node         nodo de referencia
	 * @param currentLevel valor de localizacion
	 * @param limitLevel   limite de niveles a añadir
	 */
	public void addByLevels(MAryNode<String> node, int currentLevel, int limitLevel) {
		for (int i = 0; i < node.getChilds().size(); i++) {
			for (int j = 0; j < productions.length; j++) {
				this.add(productions[j], node.getChilds().get(i));
			}

			if (currentLevel < limitLevel - 1)
				this.addByLevels(node.getChilds().get(i), currentLevel + 1, limitLevel);
		}
	}

	/**
	 * Añade la produccion con el valor del nodo dado y lo coloca como su hoja
	 * 
	 * @param p      produccion a generar
	 * @param parent nodo de referencia
	 */
	public void add(Production p, MAryNode<String> parent) {
		String[] prods = p.product(parent.data);

		if (prods != null)
			for (int i = 0; i < prods.length; i++) {
				super.add(prods[i], parent);
			}
	}

	/**
	 * Añade todas las producciones del nodo de referencia
	 * 
	 * @param parent nodo de referencia
	 */
	public void add(MAryNode<String> parent) {
		for (int i = 0; i < productions.length; i++) {
			this.add(productions[i], parent);
		}
	}

	/**
	 * Evalua si la palabra existe en el arbol gramatical
	 * 
	 * @param word
	 * @return true si la palabra si esta dentro del arbol
	 */
	public boolean wordBelongs(String word) {
		if(!containsOnlyTerminalSymbol(word))
			return false;
		
		MAryNode<String> node = this.root;
		this.currentLevel = 1;

		if (!containsNoTerminalSymbol(node.data)) {
			if (node.data.length() == word.length())
				if (node.data.equals(word))
					return true;
		} else {
			this.currentLevel++;
			generateLevel(this.currentLevel);

			if (isWordInChilds(word, node, this.currentLevel))
				return true;
		}

		return false;
	}

	/**
	 * Metodo recursivo evalua si dentro del nodo y sus hijos existe posibilidad de que exista la palabra word
	 * @param word palabra a buscar
	 * @param node nodo al que sus hijos serán evaluados
	 * @param level nivel respecto al nodo
	 * @return
	 */
	public boolean isWordInChilds(String word, MAryNode<String> node, int level) {
		for (int i = 0; i < node.getChilds().size(); i++) {
			if (!containsNoTerminalSymbol(node.getChilds().get(i).data)) {
				if (node.getChilds().get(i).data.length() == word.length())
					if (node.getChilds().get(i).data.contentEquals(word))
						return true;
			}
		}

		if (this.currentLevel == level) {
			this.currentLevel++;
			generateLevel(currentLevel);
		}

		for (int i = 0; i < node.getChilds().size(); i++) {
			if (containsNoTerminalSymbol(node.getChilds().get(i).data)) {
				if (removeNoTerminalSymbols(node.getChilds().get(i).data).length() < word.length())
					if(isWordInChilds(word, node.getChilds().get(i), this.currentLevel))
						return true;
				
			}
		}

		return false;
	}

	/**
	 * Genera todas las producciones que puedan salir de una palabra ingresada
	 * @param word 
	 * @return Todas las producciones generadas
	 */
	public String[][] productAll(String word) {
		ArrayList<String[]> ps = new ArrayList<String[]>();

		String[] aux;
		for (int i = 0; i < productions.length; i++) {
			aux = this.productions[i].product(word);

			if (aux != null) {
				ps.add(aux);
			}
		}

		return ps.toArray(new String[0][0]);
	}

	/**
	 * Remueve de la palabra todos los simbolos no terminales
	 * @param word palabra a transformar
	 * @return nueva palabra
	 */
	public String removeNoTerminalSymbols(String word) {
		String w = word;

		for (int i = 0; i < noTerminalSymbols.length; i++) {
			w = w.replace(noTerminalSymbols[i] + "", "");
		}

		return w;
	}

	/**
	 * Evalua si contiene al menos un simbolo no terminal
	 * @param word palabra a evaluar
	 * @return true si contiene un simbolo no terminal
	 */
	public boolean containsNoTerminalSymbol(String word) {
		for (int i = 0; i < noTerminalSymbols.length; i++) {
			if (word.contains(noTerminalSymbols[i] + ""))
				return true;
		}

		return false;
	}
	
	
	/**
	 * Evalua si la palabra contiene unicamente simbolos terminales
	 * @param word palabra evaluada
	 * @return true si solo contiene simbolos terminales
	 */
	public boolean containsOnlyTerminalSymbol(String word) {
		String aux= word;
		
		for (int i = 0; i < terminalSymbols.length; i++) {
			aux=aux.replace(terminalSymbols[i]+"", "");
		}

		return aux.contentEquals("");
	}
	
	/**
	 * Genera un nivel más en el arbol a partir del parametro
	 * @param level nivel base
	 */
	public void generateLevel(int level) {
		List<MAryNode<String>> nodes = this.getAllLevelNodes(level - 1);

		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < productions.length; j++) {
				this.add(productions[j], nodes.get(i));
			}
		}
	}
	
	public int getMaxLevel() {
		return this.currentLevel;
	}

	public Production[] getProductions() {
		return productions;
	}
}
