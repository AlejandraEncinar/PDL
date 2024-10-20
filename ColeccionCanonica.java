package sintactico;

import java.util.ArrayList;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;

public class ColeccionCanonica {
	private static IndexedList<Estado> cc = new ArrayIndexedList<>();

	public ColeccionCanonica() {
		IndexedList<Produccion> aux = new ArrayIndexedList<>();
		aux.add(0, Asin.gram.getProducciones().get(0));
		cc.add(0, new Estado(cierre(aux)));
		int i = 0;
		do {
			auxCC(i, Asin.gram.noTerminales);
			auxCC(i, Asin.gram.terminales);
			i++;
		} while (i <cc.size());
	}

	private void auxCC(int estado, ArrayList<String> t) {
		IndexedList<Produccion> res = new ArrayIndexedList<>();
		for (String s : t) {
			res = irA(estado, s);
			Estado e = new Estado(res);
			if (res != null && !res.isEmpty() && !ccContieneEstado(e) ) {
				System.out.println("Estado I"+cc.size()+" GOTO ( "+estado+" , "+s+" )");
				cc.add(cc.size(), e);
			}

		}
	}

	private boolean ccContieneEstado(Estado e) {
		boolean contiene = false;
		for (int i = 0; i < cc.size() && !contiene; i++) {
			contiene = cc.get(i).estadosIguales(e);
		}
		return contiene;
	}

	// Funcion que calcula el cierre de un conjunto
	public IndexedList<Produccion> cierre(IndexedList<Produccion> lista) {
		if (lista == null)
			return null;
		return cierre(lista, 0);
//		// Aniadimos los items que ya tenemos
//		IndexedList<Produccion> c = new ArrayIndexedList<>();
//		IndexedList<String> anteProd = new ArrayIndexedList<>();
//		for (int i = 0; i < lista.size(); i++) {
//			c.add(c.size(), lista.get(i));
//			// anteProd.add(anteProd.size(),
//			// lista.get(i).getConsecuente()[lista.get(i).getPosPunto()]);
//		}
//		c = cierre(anteProd, c);
//
//		return c;
	}

	private IndexedList<Produccion> cierre(IndexedList<Produccion> lista, int pos) {

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
	private boolean contieneProduccion(Produccion e, IndexedList<Produccion> it) {
		boolean contiene = false;
		for (int i = 0; i < it.size() && !contiene; i++) {
			contiene = it.get(i).ProduccionesIguales(e);
		}
		return contiene;
	}

	private IndexedList<String> antecedentesLista(IndexedList<Produccion> lista) {
		IndexedList<String> anteProd = new ArrayIndexedList<>();
		for (int i = 0; i < lista.size(); i++) {
			anteProd.add(anteProd.size(), lista.get(i).getConsecuente()[lista.get(i).getPosPunto()]);
		}
		return anteProd;
	}

	private boolean mismosAntecedentes(IndexedList<String> c1, IndexedList<String> c2) {
		if (c1.size() != c2.size())
			return false;
		boolean todos = true;
		for (int i = 0; i < c1.size() && todos; i++) {
			todos = contieneString(c2, c1.get(i));
		}
		return todos;
	}

	private boolean contieneString(IndexedList<String> c1, String s) {
		boolean alguno = false;
		for (int i = 0; i < c1.size() && !alguno; i++) {
			alguno = c1.get(i).equals(s);
		}
		return alguno;
	}

	// Hacer la funcion IrA
	public IndexedList<Produccion> irA(int numEstado, String simbolo) {
		IndexedList<Produccion> e = cc.get(numEstado).getConjuntoProducciones();
		IndexedList<Produccion> res = new ArrayIndexedList<>();
		for (int i = 0; i < e.size(); i++) {
			String s = e.get(i).getDespuesPunto();
			//System.out.println("simbolo despues del punto = "+ s);
			
			//if(s.equals("finConsecuente")) {
			if (!s.equals("finConsecuente") && (s.equals(simbolo))) {
				Produccion auxiliar=e.get(i).avanzaPunto();
				res.add(res.size(), auxiliar);
			}
		}

		return cierre(res);
	}

	public IndexedList<Estado> getCc() {
		return cc;
	}

}
