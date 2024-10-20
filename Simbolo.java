
/*
 * Clase Simbolo que asocia a cada identificador(simbolo de la tabla) sus respectivos atributos.
 */
public class Simbolo {

	// Lexema que identifica al simbolo.
	private String nombre;

	// Tipo del identificador.
	private String tipo;

	// La direccion relativa de cada variable.
	private int despl;

	// Numero de parametros que tiene un identificador en caso de ser de tipo
	// funcion(subprograma)
	private int numParam;

	// Representa el tipo de parametro de la funcion.
	private String tipoParam;

	// Representa el modo de paso del parametro(por variable o referencia).
	private int modoParam;

	// Representa el tipo que devuelve un identificador funcion.
	private String tipoRetorno;

	// Representa la etiqueta que se asocia a un identificador de tipo funcion.
	private String etiqFuncion;

	// Numero de parametros
	private int param;

	public Simbolo(String nombre) {

		this.nombre = nombre;

	}
	
//PARA EL ANALIZADOR SINTACTICO
//	public void rellenar(String tipo, int despl, int numParam, String tipoParam, int modoParam,
//			String tipoRetorno, String etiqFuncion, int param) {
//
//		this.tipo = tipo;
//		this.despl = despl;
//		this.numParam = numParam;
//		this.tipoParam = tipoParam;
//		this.modoParam = modoParam;
//		this.tipoRetorno = tipoRetorno;
//		this.etiqFuncion = etiqFuncion;
//		this.param = param;
//	}
//
	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

//	public void setTipo(String tipo) {
//		this.tipo = tipo;
////	}
//
	public int getDespl() {
		return despl;
	}

//	public void setDespl(int despl) {
//		this.despl = despl;
//	}
//
//	public int getNumParam() {
//		return numParam;
//	}
//
//	public void setNumParam(int numParam) {
//		this.numParam = numParam;
//	}
//
//	public String getTipoParam() {
//		return tipoParam;
//	}
//
//	public void setTipoParam(String tipoParam) {
//		this.tipoParam = tipoParam;
//	}
//
//	public int getModoParam() {
//		return modoParam;
//	}
//
//	public void setModoParam(int modoParam) {
//		this.modoParam = modoParam;
//	}
//
//	public String getTipoRetorno() {
//		return tipoRetorno;
//	}
//
//	public void setTipoRetorno(String tipoRetorno) {
//		this.tipoRetorno = tipoRetorno;
//	}
//
//	public String getEtiqFuncion() {
//		return etiqFuncion;
//	}
//
//	public void setEtiqFuncion(String etiqFuncion) {
//		this.etiqFuncion = etiqFuncion;
//	}
//
//	public int getParam() {
//		return param;
//	}
//
//	public void setParam(int param) {
//		this.param = param;
//	}
//
}