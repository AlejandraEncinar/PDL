package sintactico;
import java.util.ArrayList;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;

public class Gramatica {
	
	// lista de terminales de la gramatica
	final ArrayList<String> terminales = new ArrayList<>();
			
	// lista de no terminales de la gramatica
	final ArrayList<String> noTerminales = new ArrayList<>();
		
	// Lista de producciones
	final static IndexedList<Produccion> producciones = new ArrayIndexedList<>();
	
	public Gramatica() {
		// Rellenamos terminales
				terminales.add("&&");
				terminales.add("<");
				terminales.add("+");
				terminales.add("id");
				terminales.add("(");
				terminales.add(")");
				terminales.add("entero");
				terminales.add("cadena");
				terminales.add("=");
				terminales.add("&=");
				terminales.add(";");
				terminales.add("lambda");
				terminales.add(",");
				terminales.add("alert");
				terminales.add("input");
				terminales.add("function");
				terminales.add("return");
				terminales.add("if");
				terminales.add("do");
				terminales.add("while");
				terminales.add("{");
				terminales.add("}");
				terminales.add("let");
				terminales.add("number");
				terminales.add("boolean");
				terminales.add("string");

				// Rellenamos noTerminales
				noTerminales.add("P_0");
				noTerminales.add("P");
				noTerminales.add("B");
				noTerminales.add("F");
				noTerminales.add("A");
				noTerminales.add("C");
				noTerminales.add("K");
				noTerminales.add("T");
				noTerminales.add("H");
				noTerminales.add("F_1");
				noTerminales.add("F_2");
				noTerminales.add("F_3");
				noTerminales.add("J");
				noTerminales.add("E");
				noTerminales.add("S");
				noTerminales.add("X");
				noTerminales.add("L");
				noTerminales.add("Q");
				noTerminales.add("U");
				noTerminales.add("V");
				noTerminales.add("R");
				
				
				//Rellenamos las producciones
				String[] derecho = { "P" };
				producciones.add(0, new Produccion("P_0", derecho));

				String[] derecho1 = { "B", "P" };
				producciones.add(1, new Produccion("P", derecho1));

				String[] derecho2 = { "F", "P" };
				producciones.add(2, new Produccion("P", derecho2));

				String[] derecho3 = { "lambda" };
				producciones.add(3, new Produccion("P", derecho3));

				String[] derecho4 = { "if", "(", "E", ")", "S" };
				producciones.add(4, new Produccion("B", derecho4));

				String[] derecho5 = { "if", "(", "E", ")", "{", "S", "}" };
				producciones.add(5, new Produccion("B", derecho5));

				String[] derecho6 = { "do", "{", "C", "}", "while", "(", "E", ")", };
				producciones.add(6, new Produccion("B", derecho6));

				String[] derecho7 = {  "let", "T", "id", ";" };
				producciones.add(7, new Produccion("B", derecho7));

				String[] derecho8 = { "let", "T", "id", "=", "J", ";" };
				producciones.add(8, new Produccion("B", derecho8));

				String[] derecho9 = { "S" };
				producciones.add(9, new Produccion("B", derecho9));

				String[] derecho10 = {  "F_1", "F_2", "F_3" };
				producciones.add(10, new Produccion("F", derecho10));

				String[] derecho11 = {  "function", "H", "id" };
				producciones.add(11, new Produccion("F_1", derecho11));

				String[] derecho12 = { "(", "A", ")" };
				producciones.add(12, new Produccion("F_2", derecho12));

				String[] derecho13 = { "{", "C", "}" };
				producciones.add(13, new Produccion("F_3", derecho13));

				String[] derecho14 = { "E", "&&", "R" };
				producciones.add(14, new Produccion("E", derecho14));

				String[] derecho15 = { "R" };
				producciones.add(15, new Produccion("E", derecho15));

				String[] d16 = { "id", "=", "E", ";" };
				producciones.add(16, new Produccion("S", d16));
				String[] d17 = {  "id", "&=", "E", ";" };
				producciones.add(17, new Produccion("S", d17));
				String[] d18 = { "id", "(", "L", ")", ";" };
				producciones.add(18, new Produccion("S", d18));
				String[] d19 = { "alert", "(", "E", ")", ";" };
				producciones.add(19, new Produccion("S", d19));
				String[] d20 = {  "input", "(", "id", ")", ";" };
				producciones.add(20, new Produccion("S", d20));
				String[] d21 = {  "return", "X", ";" };
				producciones.add(21, new Produccion("S", d21));
				String[] d22 = {  "B", "C" };
				producciones.add(22, new Produccion("C", d22));
				String[] d23 = { "lambda" };
				producciones.add(23, new Produccion("C", d23));
				String[] d24 = { "number" };
				producciones.add(24, new Produccion("T", d24));
				String[] d25 = {  "boolean" };
				producciones.add(25, new Produccion("T", d25));
				String[] d26 = { "string" };
				producciones.add(26, new Produccion("T", d26));
				String[] d27 = { "lambda" };
				producciones.add(27, new Produccion("J", d27));
				String[] d28 = {  "E" };
				producciones.add(28, new Produccion("J", d28));
				String[] d29 = { "T" };
				producciones.add(29, new Produccion("H", d29));
				String[] d30 = { "lambda" };
				producciones.add(30, new Produccion("H", d30));
				String[] d31 = { "T", "id", "K" };
				producciones.add(31, new Produccion("A", d31));
				String[] d32 = { "lambda" };
				producciones.add(32, new Produccion("A", d32));

				String s = "R";
				String[] c33 = {  "R", "<", "U" };
				String[] c34 = { "U" };
				producciones.add(33, new Produccion(s, c33));
				producciones.add(34, new Produccion(s, c34));
				s = "L";
				String[] c35 = {  "E", "Q" };
				String[] c36 = { "lambda" };
				producciones.add(35, new Produccion(s, c35));
				producciones.add(36, new Produccion(s, c36));
				s = "X";
				String[] c37 = {  "E" };
				String[] c38 = { "lambda" };
				producciones.add(37, new Produccion(s, c37));
				producciones.add(38, new Produccion(s, c38));
				s = "K";
				String[] c39 = {  ",", "T", "id", "K" };
				String[] c40 = { "lambda" };
				producciones.add(39, new Produccion(s, c39));
				producciones.add(40, new Produccion(s, c40));
				s = "U";
				String[] c41 = { "U", "+", "V" };
				String[] c42 = { "V" };
				producciones.add(41, new Produccion(s, c41));
				producciones.add(42, new Produccion(s, c42));
				s = "Q";
				String[] c43 = {  ",", "E", "Q" };
				String[] c44 = { "lambda" };
				producciones.add(43, new Produccion(s, c43));
				producciones.add(44, new Produccion(s, c44));
				s = "V";
				String[] c45 = {  "id" };
				String[] c46 = { "(", "E", ")" };
				String[] c47 = {  "id", "(", "L", ")" };
				String[] c48 = {  "entero" };
				String[] c49 = {  "cadena" };
				String[] c50 = {  "true" };
				String[] c51 = {  "false" };

				producciones.add(45, new Produccion(s, c45));
				producciones.add(46, new Produccion(s, c46));
				producciones.add(47, new Produccion(s, c47));
				producciones.add(48, new Produccion(s, c48));
				producciones.add(49, new Produccion(s, c49));
				producciones.add(50, new Produccion(s, c50));
				producciones.add(51, new Produccion(s, c51));
						
	}

	public ArrayList<String> getTerminales() {
		return terminales;
	}

	public  ArrayList<String> getNoterminales() {
		return noTerminales;
	}

	public IndexedList<Produccion> getProducciones() {
		return producciones;
	}
	
	public static boolean esProduccionDeG(Produccion p) {
		return producciones.indexOf(p)!=-1;
	}
	
	public IndexedList<Produccion> getProduccionesConAntecedente(String s) {
		IndexedList<Produccion> res = new ArrayIndexedList<>();
		for(int i = 0; i < producciones.size(); i++) {
			if(producciones.get(i).getAntecedente().equals(s)) {
				res.add(res.size(), producciones.get(i));
			}
		}
		return res;
	}
	
}
