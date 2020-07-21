package runner;

import java.util.Scanner;

import model.entity.GrammarTree;
import model.entity.Production;
import model.operations.GrammarTreePrinter;

/**
 * @author Andrés Felipe Chaparro Rosas | Yohan Caro | Fabian Cristancho
 * @date 4/06/2020
 * @version 1.0
 *
 */
public class Runner {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out
				.println("Como minimo debe ingresar dos simbolos terminales, tres no terminales y tres producciones.");

		String[] terminalSymbols;

		do {
			System.out.print("Introduzca al menos dos (2) simbolos terminales. (ej: a,b,c,d) : ");
			terminalSymbols = s.nextLine().split(",");
		} while (terminalSymbols.length < 2);

		String[] noTerminalSymbols;

		do {
			System.out.print("Introduzca al menos tres (3) simbolos no terminales. (ej: S,A) : ");
			noTerminalSymbols = s.nextLine().split(",");
		} while (noTerminalSymbols.length < 3);

		System.out.print("Introduzca el simbolo inicial axiomatico: ");
		char initialSymbol = s.nextLine().charAt(0);

		int qProductions;

		do {
			System.out.println("Introduzca el numero producciones (Minimo 3): ");
			qProductions = s.nextInt();
		} while (qProductions < 3);

		System.out.println("Seguido cada linea siguiente corresponde a una produccion.\n"
				+ "Debe seguir el siguiente formato: simbolo no terminal;produccion produccion produccion (ej. A;aA Ab c)");
		s.nextLine();

		Production[] productions = new Production[qProductions];

		String[] aux;
		for (int i = 0; i < productions.length; i++) {
			System.out.print(">>");
			aux = s.nextLine().split(";");
			productions[i] = new Production(aux[0], aux[1].split(" "));
		}

		System.out.print("Introduzca la palabra a evaluar en el arbol: ");
		String word = s.nextLine();

		s.close();

		GrammarTree grammarTree = new GrammarTree(terminalSymbols, noTerminalSymbols, initialSymbol, productions);

		System.out.println(grammarTree.wordBelongs(word));

		GrammarTreePrinter.printGrammarTree(grammarTree);
	}
}
