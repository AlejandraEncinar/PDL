package sintactico;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;

public class Estado {

	private IndexedList<Produccion> conjuntoProducciones = new ArrayIndexedList<>();

	public Estado() {
	}

	public Estado(IndexedList<Produccion> conjunto) {
		for (int i = 0; i < conjunto.size(); i++) {
			conjuntoProducciones.add(size(), conjunto.get(i));
		}
	}

	public IndexedList<Produccion> getConjuntoProducciones() {
		return conjuntoProducciones;
	}

	public int size() {
		return conjuntoProducciones.size();
	}

	public boolean estadosIguales(Estado s) {
		if(size() != s.size())
			return false;
		boolean iguales = true;
		for (int i = 0; i < s.size() && iguales; i++) {
			iguales = contieneProduccion(s.conjuntoProducciones.get(i));
		}
		return iguales;
	}

	public boolean contieneProduccion(Produccion e) {
		boolean contiene = false;
		for (int i = 0; i < this.size() && !contiene; i++) {
			contiene = conjuntoProducciones.get(i).ProduccionesIguales(e);
		}
		return contiene;
	}
	public boolean contieneConjuntoProducciones(IndexedList<Produccion> j) {
		boolean contieneLista= true;
		for(int i = 0; i < j.size()&&contieneLista; i++) {
			contieneLista = contieneProduccion(j.get(i));
		}
		return true;
		
	}

}
