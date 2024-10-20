package sintactico;

import java.util.Stack;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.map.HashTableMap;
import lexico.Alex;
import lexico.Pair;

public class Asin {

	// Pila que va construyendo el arbol
	private static Stack<String> pila = new Stack<>();

//		//Tabla ACCION
	private static HashTableMap<Pair<String, String>, Accion> ACCION = new HashTableMap<>();
	//
//		//Tabla GOTO
	private static HashTableMap<Pair<String, String>, Integer> GOTO = new HashTableMap<>();
	//
//		//Token que lee el Analizador Lexico
	private static String token;
	//Parse
	private static ArrayIndexedList<Integer> parse = new ArrayIndexedList<>();
	// Gramatica del analizador sintactico
	public final static Gramatica gram = new Gramatica();

	// Lista de estados
	public final static ColeccionCanonica c = new ColeccionCanonica();

	private static String impString(String[] s) {
		String[] p = s;
		String res = "";
		for (int i = 0; i < p.length; i++) {
			res += s[i];
		}
		return res;
	}

	private static void imprimirEstados() {
		for (int i = 0; i < c.getCc().size(); i++) {
			System.out.println("I" + i + ":");
			for (int j = 0; j < c.getCc().get(i).size(); j++) {
				Produccion p = c.getCc().get(i).getConjuntoProducciones().get(j);
				System.out.println(p.getAntecedente() + "->" + impString(p.getConsecuente())+"  PosPunto:"+ p.getPosPunto());
			}
		}
	}

	
	
	
	
	
	// Funcion que calcula el cierre de un conjunto
		public static IndexedList<Produccion> cierre(IndexedList<Produccion> lista) {
			if (lista == null)
				return null;
			return cierre(lista, 0);
//			// Aniadimos los items que ya tenemos
//			IndexedList<Produccion> c = new ArrayIndexedList<>();
//			IndexedList<String> anteProd = new ArrayIndexedList<>();
//			for (int i = 0; i < lista.size(); i++) {
//				c.add(c.size(), lista.get(i));
//				// anteProd.add(anteProd.size(),
//				// lista.get(i).getConsecuente()[lista.get(i).getPosPunto()]);
//			}
//			c = cierre(anteProd, c);
	//
//			return c;
		}

		private static IndexedList<Produccion> cierre(IndexedList<Produccion> lista, int pos) {

			// IndexedList<Produccion> aux = Asin.gram.getProduccionesConAntecedente(s);
			if (pos == lista.size())
				return lista;

			String cadena = lista.get(pos).getDespuesPunto();
			if (cadena != null && Asin.gram.noTerminales.contains(cadena)) {
				IndexedList<Produccion> aux = Asin.gram.getProduccionesConAntecedente(cadena);
				for (int j = 0; j < aux.size(); j++) {
					if (!contieneProduccion(aux.get(j), lista)) {
						lista.add(lista.size(), aux.get(j));
					}
				}

			}

			return cierre(lista, pos + 1);
		}
		
		
		// Funcion auxiliar que devuelve si una lista de producciones tiene una
		// produccion determinada.
		private static boolean contieneProduccion(Produccion e, IndexedList<Produccion> it) {
			boolean contiene = false;
			for (int i = 0; i < it.size() && !contiene; i++) {
				contiene = it.get(i).ProduccionesIguales(e);
			}
			return contiene;
		}

		
		
		
		
	public static void main(String[] args) {
//		String [] s = {"P"};
//		Produccion p = new Produccion("P_0", s);
//		IndexedList<Produccion> res = new ArrayIndexedList<>();
//		res.add(0, p);
//		IndexedList<Produccion> res2 = cierre(res);
//	
//			for (int j = 0; j < res2.size(); j++) {
//				Produccion o = res2.get(j);
//				System.out.println(o.getAntecedente() + "->" + impString(o.getConsecuente()));
//			}
//		
		
		imprimirEstados();
		//System.out.println("El numero de estados del automata es :" + c.getCc().size());

		// Aniadimos un simbolo de fondo de pila
//		pila.add("0");

//		// Algortimo del AL()
//		token = Alex.Alex();
//		// Se pide un token al analizador lexico
//
//		Accion acc = ACCION.get(new Pair<String, String>(pila.peek(), token));
//
//		if (acc.getQueAccion() == 'D') {
//			pila.add(token);
//			pila.add("" + acc.getNum());
//			token = Alex.Alex();
//		} else if (acc.getQueAccion() == 'R') {// REVISAR CUANDO ESTE HECHA LA TABLA GOTO
//			int n = acc.getNum() * 2;
//			for (int i = 1; i <= n; i++) {
//				pila.pop();
//			}
//			pila.add("" + acc.getNum());
//
//		} else if (acc.getQueAccion() == 'A') {
//			return;
//		} else { // error(); }
//		}
	}
}
