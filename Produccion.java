package sintactico;

/*
public class Produccion {
	private String antecedente;
	private String[] consecuente;
	public Produccion(String antecedente, String[] consecuente) {
		this.antecedente = antecedente;
		this.consecuente = consecuente;
	}

	public String getAntecedente() {
		return antecedente;
	}

	public String[] getConsecuente() {
		return consecuente;
	}
}*/
public class Produccion {
	private String antecedente;
	private int posPunto;
	private String[] consecuente;

	public Produccion(String antecedente, String[] consecuente) {
		// boolean noTienePunto = true;
		this.antecedente = antecedente;
		this.consecuente = consecuente;
		this.posPunto = 0;
//		for (int i = 0; i < consecuente.length && noTienePunto; i++) {
//			if (consecuente[i].equals(".")) {
//				posPunto = i;
//				noTienePunto = false;
//			}
//		}
//
//		if (noTienePunto) {
//			throw new Error("Formato de producción no válida, introduzca un punto al principio.");
//		}
	}

	public String getAntecedente() {
		return antecedente;
	}

	public String[] getPrefijoViable() {
		String[] prefijo = new String[posPunto];
		for (int i = 0; i < posPunto; i++) {
			prefijo[i] = consecuente[i];
		}
		return prefijo;
	}

//	public String getProduccion() {
//		return consecuente[posPunto];
//	}

	public String[] getConsecuente() {
		return consecuente;
	}

	public int getPosPunto() {
		return posPunto;
	}

	public String getDespuesPunto() {
		if(posPunto == consecuente.length)
			return "finConsecuente";
		
		return consecuente[posPunto];
	}

	public boolean consecuentesIguales(String[] con) {
		if (con.length != consecuente.length)
			return false;
		boolean iguales = true;
		for (int i = 0; i < consecuente.length && iguales; i++) {
			iguales = consecuente[i].equals(con[i]);
		}
		return iguales;
	}

	public boolean ProduccionesIguales(Produccion e) {
		if ((posPunto != e.getPosPunto())||(!this.getAntecedente().equals(e.getAntecedente())))
			return false;
		return this.consecuentesIguales(e.getConsecuente());
	}

	public Produccion avanzaPunto() {
		Produccion res= null;
		if (posPunto < consecuente.length) {
			int punto=posPunto +1;
			String ant= new String(this.antecedente);
			String[] cons= new String [consecuente.length];
			for(int i =0;i<cons.length;i++) {
				cons[i]=new String(consecuente[i]);
			}
			res= new Produccion(ant, cons);
			res.posPunto=posPunto+1;
		}
		return res;	

	}
}