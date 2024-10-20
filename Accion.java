package sintactico;

public class Accion {
	private static char queAccion;
	private static int num;
	
	public Accion(char accion) {
		num = -1;
		queAccion = accion;
	}
	public Accion(char accion, int n) {
		num = n;
		queAccion = accion;
	}
	public char getQueAccion(){
		return queAccion;
	}
	public int getNum(){
		return num;
	}

}
