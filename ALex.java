
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

public class ALex {
	// Caracter que voy leyendo y proceso.
	private static char car;
	// Tabla que asocia un numero a las palabras reservadas.
	final static HashMap<String, Integer> PALRES = new HashMap<>();
	// Estado del automata en el que nos encontramos.
	private static int estado;
	// Accion que toca ejecutar.
	private static char accion;
	// Lista de tablas de simbolos publica para ser accesible desde todos los
	// modulos
	public static ArrayList<TablaSimbolos> tablaSimbolos = new ArrayList();
	// Rango enteros
	private static int maxInt = 32767;
	// Zona de declaracion
	private static boolean zonaDeclaracion = false;
	// Linea del fichero por la que va leyendo
	private static int linea = 1;
	private static String token;

	public static String Alex() {

		/*
		 * Tabla que contiene las palabras reservadas.
		 */

		PALRES.put("alert", 1);
		PALRES.put("boolean", 2);
		PALRES.put("do", 3);
		PALRES.put("function", 4);
		PALRES.put("if", 5);
		PALRES.put("input", 6);
		PALRES.put("let", 7);
		PALRES.put("number", 8);
		PALRES.put("return", 9);
		PALRES.put("string", 10);
		PALRES.put("while", 11);
		PALRES.put("false", 12);
		PALRES.put("true", 13);

		/*
		 * Matriz de dimensiones 13x11 que equivale al Automata Finito Determinista.
		 * Filas corresponden a estados. Columnas corresponden a un tipo de caracter:
		 * 0->l, 1->d, 2->&, 3->c', 4->/, 5->", 6->=, 7->', 8->\, 9-><eol>, 10->del Las
		 * transiciones se guardan en un par. El elemento de la izquierda es el estado
		 * al que transita, en el caso de ser -1, es porque no existe esa transicion. El
		 * derecho es la accion semantica que ejecuta, y en caso de ser 1, es un error
		 * lexico.
		 */
		Pair<Integer, Character>[][] mAFD = new Pair[13][11];

		mAFD[0][0] = new Pair<>(1, 'B');
		mAFD[0][1] = new Pair<>(3, 'D');
		mAFD[0][2] = new Pair<>(8, 'B');
		mAFD[0][3] = new Pair<>(5, 'G');
		mAFD[0][4] = new Pair<>(6, 'A');
		mAFD[0][5] = new Pair<>(11, 'J');
		mAFD[0][6] = new Pair<>(5, 'G');
		mAFD[0][7] = new Pair<>(-1, '1');
		mAFD[0][8] = new Pair<>(-1, '1');
		mAFD[0][9] = new Pair<>(0, 'A');
		mAFD[0][10] = new Pair<>(0, 'A');

		mAFD[1][0] = new Pair<>(1, 'B');
		mAFD[1][1] = new Pair<>(1, 'B');
		mAFD[1][2] = new Pair<>(2, 'C');
		mAFD[1][3] = new Pair<>(2, 'C');
		mAFD[1][4] = new Pair<>(2, 'C');
		mAFD[1][5] = new Pair<>(2, 'C');
		mAFD[1][6] = new Pair<>(2, 'C');
		mAFD[1][7] = new Pair<>(2, 'C');
		mAFD[1][8] = new Pair<>(2, 'C');
		mAFD[1][9] = new Pair<>(2, 'C');
		mAFD[1][10] = new Pair<>(2, 'C');

		mAFD[3][1] = new Pair<>(3, 'E');
		mAFD[3][0] = new Pair<>(4, 'F');
		mAFD[3][2] = new Pair<>(4, 'F');
		mAFD[3][3] = new Pair<>(4, 'F');
		mAFD[3][4] = new Pair<>(4, 'F');
		mAFD[3][5] = new Pair<>(4, 'F');
		mAFD[3][6] = new Pair<>(4, 'F');
		mAFD[3][7] = new Pair<>(4, 'F');
		mAFD[3][8] = new Pair<>(4, 'F');
		mAFD[3][9] = new Pair<>(4, 'F');
		mAFD[3][10] = new Pair<>(4, 'F');

		mAFD[6][0] = new Pair<>(-1, '1');
		mAFD[6][1] = new Pair<>(-1, '1');
		mAFD[6][2] = new Pair<>(-1, '1');
		mAFD[6][3] = new Pair<>(-1, '1');
		mAFD[6][4] = new Pair<>(7, 'A');
		mAFD[6][5] = new Pair<>(-1, '1');
		mAFD[6][6] = new Pair<>(-1, '1');
		mAFD[6][7] = new Pair<>(-1, '1');
		mAFD[6][8] = new Pair<>(-1, '1');
		mAFD[6][9] = new Pair<>(-1, '1');
		mAFD[6][10] = new Pair<>(-1, '1');

		mAFD[7][0] = new Pair<>(7, 'A');
		mAFD[7][1] = new Pair<>(7, 'A');
		mAFD[7][2] = new Pair<>(7, 'A');
		mAFD[7][3] = new Pair<>(7, 'A');
		mAFD[7][4] = new Pair<>(7, 'A');
		mAFD[7][5] = new Pair<>(7, 'A');
		mAFD[7][6] = new Pair<>(7, 'A');
		mAFD[7][7] = new Pair<>(7, 'A');
		mAFD[7][8] = new Pair<>(7, 'A');
		mAFD[7][9] = new Pair<>(0, 'A');
		mAFD[7][10] = new Pair<>(7, 'A');
		mAFD[8][0] = new Pair<>(-1, '1');
		mAFD[8][1] = new Pair<>(-1, '1');
		mAFD[8][2] = new Pair<>(9, 'H');
		mAFD[8][3] = new Pair<>(-1, '1');
		mAFD[8][4] = new Pair<>(-1, '1');
		mAFD[8][5] = new Pair<>(-1, '1');
		mAFD[8][6] = new Pair<>(10, 'I');
		mAFD[8][7] = new Pair<>(-1, '1');
		mAFD[8][8] = new Pair<>(-1, '1');
		mAFD[8][9] = new Pair<>(-1, '1');
		mAFD[8][10] = new Pair<>(-1, '1');

		mAFD[11][0] = new Pair<>(11, 'B');
		mAFD[11][1] = new Pair<>(11, 'B');
		mAFD[11][2] = new Pair<>(11, 'B');
		mAFD[11][3] = new Pair<>(11, 'B');
		mAFD[11][4] = new Pair<>(11, 'B');
		mAFD[11][5] = new Pair<>(13, 'K');
		mAFD[11][6] = new Pair<>(11, 'B');
		mAFD[11][7] = new Pair<>(11, 'B');
		mAFD[11][8] = new Pair<>(12, 'A');
		mAFD[11][9] = new Pair<>(11, 'B');
		mAFD[11][10] = new Pair<>(11, 'B');

		mAFD[12][1] = new Pair<>(-1, '1');
		mAFD[12][2] = new Pair<>(-1, '1');
		mAFD[12][3] = new Pair<>(-1, '1');
		mAFD[12][4] = new Pair<>(-1, '1');
		mAFD[12][5] = new Pair<>(11, 'B');
		mAFD[12][6] = new Pair<>(-1, '1');
		mAFD[12][7] = new Pair<>(11, 'B');
		mAFD[12][8] = new Pair<>(11, 'B');
		mAFD[12][9] = new Pair<>(-1, '1');
		mAFD[12][10] = new Pair<>(-1, '1');

		// Leer fichero
		try (FileReader fileReader = new FileReader("h.txt")) {
			int caracterLeido = fileReader.read();

			// Bucle que se ejecuta mientras haya caracteres que seguir leyendo.
			while (caracterLeido != -1) {

				// Inicializamos la tabla general y la aniadimos a la lista de tablas.
				TablaSimbolos tablaPrincipal = new TablaSimbolos(0);
				tablaSimbolos.add(tablaPrincipal);
				int tablaActual = 0;

				/*
				 * Empieza a recorrer el automata
				 */
				estado = 0;
				int n;
				int valor = 0;
				String lexema = "";
				int contCadena = 0;

				// System.out.println("...");

				// Bucle que se ejecuta mientras no se ha llegado a un estado final.
				while ((caracterLeido != -1) && (estado != 2) && (estado != 4) && (estado != 5) && (estado != 13)
						&& (estado != 9) && (estado != 10) && (estado != -1)) {
					// Leer caracter
					car = (char) caracterLeido;
					// Si se llega a un salto de linea pasamos a la siguiente.
					if (car == '\n')
						linea++;

					// Sacamos posicion en las columnas del AFD del caracter
					int pos = columna(car);
					// System.out.println(pos);

					// Si caracter es invalido, se lo salta CAMBIAR
					if (pos == -1) {
						error('1');
						caracterLeido = -1;
						caracterLeido = fileReader.read();
//						estado = 0;

					} else {
						// System.out.println(car);
						// System.out.println(pos);
						accion = mAFD[estado][pos].getDcha();
						estado = mAFD[estado][pos].getIzq();
						// System.out.println(estado + " " + accion);

						// Detecta error si el estado es -1 (no existe transicion).
						if (estado == -1) {
							error(accion);
							caracterLeido = fileReader.read();
//							estado = 0;
						} else {
							Integer p;

							switch (accion) {

							// Leer
							case 'A':
								car = (char) caracterLeido;
								caracterLeido = fileReader.read();
								break;

							// Leer y concatenar
							case 'B':
								car = (char) caracterLeido;
								lexema = lexema + car;
								contCadena++;
								caracterLeido = fileReader.read();
								break;

							// Generar palabra reservada o identificador
							case 'C':
								p = PALRES.get(lexema);
								if (p != null) { // esta en la lista de palabras reservadas
									if (p == 2 || p == 4 || p == 8 || (p == 10))
										zonaDeclaracion = true;
//									if(p==4) {
//										tablaActual++;
//										TSimbolos tablaSig= new TSimbolos(tablaActual);
//										tablaSimbolos.add(tablaSig);
//									};
									token = genToken("PalReservada", "" + p); // generamos token con la palabra
																				// reservada en esa
									// posicion

								} else {
									if (tablaSimbolos.get(tablaActual).buscarSimbolo(lexema) == -1) {// no esta aun en
																										// la tabla
										Simbolo simb = new Simbolo(lexema);// crea nuevo simbolo y lo inserta
										tablaSimbolos.get(tablaActual).insertarSimbolo(simb);
										token = genToken("Identificador", tablaSimbolos.get(tablaActual).size()); // generamos
																													// //
																													// token
										// identificador
									} else {// si ya esta en la tabla
											// genera token con su posicion en la TS
										token = genToken("Identificador",
												tablaSimbolos.get(tablaActual).buscarSimbolo(lexema));
									}

									// codigo con zona de declaracion para analizador semantico
//								if(zonaDeclaracion) {
//									if (tablaSimbolos.get(tablaActual).buscarSimbolo(lexema)!=-1) {
//										error('4');
//									} else {
//										Simbolo simb= new Simbolo(lexema);
//										tablaSimbolos.get(tablaActual).insertarSimbolo(simb);; // +1?
//										genToken("Identificador", tablaSimbolos.get(tablaActual).size()); // generamos token identificador
//									}
//								}
//								else {
//									if (tablaSimbolos.get(tablaActual).buscarSimbolo(lexema)!=-1) {
//										genToken("Identificador",tablaSimbolos.get(tablaActual).buscarSimbolo(lexema));
//									} else {
//										error('5');
//									}
//								}
								}
								break;

							// Leer numero y pasarlo a tipo entero
							case 'D':
								car = (char) caracterLeido;
								valor = Integer.parseInt("" + car);
								caracterLeido = fileReader.read();
								break;

							// Leer numero y concatenar
							case 'E':
								car = (char) caracterLeido;
								n = Integer.parseInt("" + car);
								;
								valor = valor * 10 + n;
								caracterLeido = fileReader.read();
								break;

							// Generar constante entera
							case 'F':
								if (valor > maxInt) {
									error('2');
								} else {
									token = genToken("ConstanteEntera", valor);
								}
								break;

							// Generar caracter especial
							case 'G':
								car = (char) caracterLeido;
								if (car == ';')
									zonaDeclaracion = false;
								token = genToken(car);
								caracterLeido = fileReader.read();
								break;

							// Generar &&
							case 'H':
								car = (char) caracterLeido;
								lexema = lexema + car;
								token = genToken(lexema);
								caracterLeido = fileReader.read();
								break;

							// Generar &=
							case 'I':
								car = (char) caracterLeido;
								lexema = lexema + car;
								token = genToken(lexema);
								caracterLeido = fileReader.read();
								break;

							// Leer y concatenar comillas
							case 'J':
								car = (char) caracterLeido;
								lexema = lexema + car;
								caracterLeido = fileReader.read();
								break;

							// Genera el token cadena si no tiene mas de 64 caracteres.
							case 'K':
								contCadena++;
								if (contCadena > 64) {
									error('3');
								} else {
									car = (char) caracterLeido;
									lexema = lexema + car;
									token = genToken("Cadena", lexema);
								}
								caracterLeido = fileReader.read();
								break;

							}
							// System.out.println(caracterLeido);

						}

					}
				}

			}

			// Imprime la tabla de simbolos
			tablaSimbolos.get(0).imprimirTabla();

		} catch (IOException e) {
			System.err.println("Error al leer el archivo");
			e.printStackTrace();
		}
		return token;

	}
	

	// columna correspondiente al char
	private static int columna(char c) {
		int pos = -1;
		int asci = c;
		if ((asci >= 65 && asci <= 90) || (asci >= 97 && asci <= 122))
			pos = 0;
		if (asci >= 48 && asci <= 57)
			pos = 1;
		if (c == '&')
			pos = 2;
		if (c == '/')
			pos = 4;
		if (c == '"')
			pos = 5;
		if (c == '=')
			pos = 6;
		if (c == '\'')
			pos = 7;// '
		if (c == '\\')
			pos = 8;// \
		if (c == '<' || c == ';' || c == ',' || c == '(' || c == ')' || c == '{' || c == '}' || c == '+')
			pos = 3;
		if ((c == '\n') || (asci == 13))// Si es salto de linea o retorno de carro.
			pos = 9;
		if (c == ' ')
			pos = 10;

		return pos;
	}

	/*
	 * Funciones con dos parametros que imprime un token
	 */

	// Imprime token cadena
	public static String genToken(String code, String atributo) {
		System.out.println(" < " + code + ", " + atributo + " > ");
		return code;
	}

	// Imprime token numero entero
	public static String genToken(String code, int atributo) {
		System.out.println(" < " + code + ", " + atributo + " > ");
		return code;
	}

	// Imprime caracter especial
	public static String genToken(char character) {
		String res = "";
		switch (character) {

		case '=':
			res = "=";
			System.out.println(" < Asignacion ,  > ");
			break;
		case ',':
			res = ",";
			System.out.println(" < Coma ,  > ");
			break;
		case ';':
			res = ";";
			System.out.println(" < PuntoYComa ,  > ");
			break;
		case '(':
			res = "(";
			System.out.println(" < AbreParentesis,  > ");
			break;
		case ')':
			res = ")";
			System.out.println(" < CierraParentesis,  > ");
			break;
		case '{':
			res = "{";
			System.out.println(" < AbreLlaves,  > ");
			break;
		case '}':
			res = "}";
			System.out.println(" < CierraLlaves,  > ");
			break;
		case '+':
			res = "+";
			System.out.println(" < OperadorSuma,  > ");
			break;
		case '<':
			res = "<";
			System.out.println(" < MenorQue,  > ");
			break;
		}
		return res;

	}

	// Imprime caracter especial
	public static String genToken(String codigo) {
		String res = "";
		if (codigo.equals("&&")) {
			res = "&&";
			System.out.println(" < AND ,  > ");
		}
		if (codigo.equals("&=")) {
			res = "&=";
			System.out.println(" < AsignacionAnd ,  > ");
		}
		return res;
	}

	// Metodo que imprime el error detectado por pantalla.
	public static void error(char c) {
		switch (c) {

		case '1':
			System.out.println("ERROR LÉXICO: caracter inválido en la linea " + linea);
			break;

		case '2':
			System.out.println("ERROR LÉXICO: constante entera fuera de rango (mayor que 32767) en la linea " + linea);
			break;

		case '3':
			System.out.println("ERROR LÉXICO: cadena de texto que supera los 64 caracteres en la linea " + linea);
			break;

		case '4':
			System.out.print("Identificador ya declarado en la linea " + linea);
			break;

		case '5':
			System.out.print("Identificador no declarado en la linea " + linea);
			break;

		}

	}
}
