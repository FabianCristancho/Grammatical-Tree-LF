package model.entity;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 25/06/2020
 * @version 1.0
 *
 */
public class Production {
	protected String noTerminalSymbol;
	protected String[] productions;

	public Production(char noTerminalSymbol, String... productions) {
		this.noTerminalSymbol = noTerminalSymbol+"";
		this.productions = productions;
	}
	
	public Production(String noTerminalSymbol, String... productions) {
		this.noTerminalSymbol = noTerminalSymbol;
		this.productions = productions;
	}

	/**
	 * Produce las distintas palabras dependiendo de las producciones internas
	 * @param word palabra a evaluar
	 * @return grupo de palabras producidas
	 */
	public String[] product(String word) {
		if (word.contains(noTerminalSymbol + "")) {
			String[] prod = new String[this.productions.length];
			
			for (int i = 0; i < prod.length; i++) {
				prod[i] = word.replaceAll(noTerminalSymbol + "", productions[i]);
			}
			
			return prod;
		}

		return null;
	}
	
	public String getNoTerminalSymbol() {
		return noTerminalSymbol;
	}
	
	public String[] getProductions() {
		return productions;
	}
}
