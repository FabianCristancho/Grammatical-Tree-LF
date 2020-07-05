package runner;

import java.util.Scanner;

import model.entity.GrammarTree;
import model.entity.Production;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 4/06/2020
 * @version 1.0
 *
 */
public class Runner {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.print("Introduzca los simbolos terminales. (ej: a,b,c,d) : ");
		String[] terminalSymbols = s.nextLine().split(",");

		System.out.print("Introduzca los simbolos no terminales. (ej: S,A) : ");
		String[] noTerminalSymbols = s.nextLine().split(",");

		System.out.print("Introduzca el simbolo inicial axiomatico: ");
		char initialSymbol = s.nextLine().charAt(0);

		System.out.println("Introduzca el numero producciones: ");
		int qProductions = s.nextInt();

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
	}
}
