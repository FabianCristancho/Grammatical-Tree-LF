package model.entity;

//TODO los simbolos T y NT no son utilizados
/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 21/04/2020
 * @version 1.0
 *
 */
public class GrammarTree extends MAryTree<String> {

	//private char[] terminalSymbols, noTerminalSymbols;
	private Production[] productions;
	private int levels;

	public GrammarTree(char[] terminalSymbols, char[] noTerminalSymbols, char initialSymbol, Production[] productions) {
		super(initialSymbol + "");
//		this.terminalSymbols = terminalSymbols;
//		this.noTerminalSymbols = noTerminalSymbols;
		this.productions = productions;
	}

	/**
	 * Añade desde el nodo entrante los niveles de limitLevel. Recursivo
	 * @param node nodo de referencia
	 * @param currentLevel valor de localizacion
	 * @param limitLevel limite de niveles a añadir
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
	 * @param p produccion a generar
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
	 * @param parent nodo de referencia
	 */
	public void add(MAryNode<String> parent) {
		for (int i = 0; i < productions.length; i++) {
			this.add(productions[i], parent);
		}
	}

	/**
	 * Evalua si la palabra existe en el arbol gramatical
	 * @param word
	 * @return
	 */
	public boolean wordBelongs(String word) {

		return false;
	}

	public int getLevels() {
		return levels;
	}

	public Production[] getProductions() {
		return productions;
	}
}
