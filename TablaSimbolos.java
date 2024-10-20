
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Clase Tabla de Simbolos
 */

public class TablaSimbolos {

	// Atributo que identifica la tabla con un numero
	private int numTabla;

	// Atributo que guarda el siguiente byte libre para guardar un simbolo en la
	// tabla
	private int despl;

	// Arraylist que guarda la lista de simbolos(identificadores).
	private ArrayList<Simbolo> listaSimbolos;

	// Atributo que guarda el numero de simbolos que hay en la tabla.
	private int size;

	// Metodo constructor
	public TablaSimbolos(int numTabla) {

		this.numTabla = numTabla;
		this.despl = 0;
		this.listaSimbolos = new ArrayList<>();
		size = listaSimbolos.size();

	}

	// Metodo que busca si el simbolo esta en la tabla y devuelve la posicion donde
	// se encuentra si esta, o -1 si no esta.
	public int buscarSimbolo(String lexema) {
		int pos = -1;
		boolean esta = false;
		for (int i = 0; i < size && (!esta); i++) {
			esta = listaSimbolos.get(i).getNombre().equals(lexema);
			if (esta) {
				pos = i;
			}
		}
		return pos;
	}

	// Metodo que inserta el simbolo en la tabla si no esta.
	public void insertarSimbolo(Simbolo simbolo) {
		if (buscarSimbolo(simbolo.getNombre()) != -1) {
			return;
		}
		listaSimbolos.add(simbolo);
		size++;
	}

	// Metodo que imprime la tabla
	public void imprimirTabla() {
		// cabecera con el numero de la TS
		if (numTabla == 0) {
			System.out.println("TABLA GENERAL # " + (numTabla + 1) + ":");
		} else {
			System.out.println("TABLA de la FUNCION # " + (numTabla + 1) + ":");
		}

		for (int i = 0; i < size; i++) {
			// linea del lexema
			System.out.println("*LEXEMA: '" + listaSimbolos.get(i).getNombre() + "'");
			System.out.println("  ATRIBUTOS:");
			System.out.println("  + tipo: '" + listaSimbolos.get(i).getTipo() + "'");
			System.out.println("  + despl: '" + listaSimbolos.get(i).getDespl() + "'");
//			if (listaSimbolos.get(i).getNumParam() != 0) {
//				System.out.println("    + numParam: '" + listaSimbolos.get(i).getNumParam() + "'");
//				System.out.println("     + tipoParam: '" + listaSimbolos.get(i).getTipoParam() + "'");
//				System.out.println("     + modoParam: '" + listaSimbolos.get(i).getModoParam() + "'");
//				System.out.println("      + tipoRetorno: '" + listaSimbolos.get(i).getTipoRetorno() + "'");
//				System.out.println("      + etiqFuncion: '" + listaSimbolos.get(i).getEtiqFuncion() + "'");
//			}
		}

		System.out.println("--------------------------------");
	}

	// Metodo que devuelve el tamanio de la tabla.
	public int size() {
		return size;
	}

}