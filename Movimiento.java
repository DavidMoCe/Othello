package ProyectoFinal;

public class Movimiento {
	
	//creamos los atributos
	private int fila;
	private int columna;
	
	//creamos el constructor y los get y set
	public Movimiento(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
	

}
