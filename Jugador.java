package ProyectoFinal;

public class Jugador implements Reglas {
	
		//Declaramos los atributos
	    private String nombre;
	    private Color colorFichas;
	    private Roles rol;

	    //Creamos el constructor
	    public Jugador(String nombre, Color colorFichas, Roles rol) {
	        this.nombre = nombre;
	        this.colorFichas = colorFichas;
	        this.rol = rol;
	    }

	    // Getters y setters
		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Color getColorFichas() {
			return colorFichas;
		}

		public void setColorFichas(Color colorFichas) {
			this.colorFichas = colorFichas;
		}

		public Roles getRol() {
			return rol;
		}

		public void setRol(Roles rol) {
			this.rol = rol;
		}
		
		//ToString
		@Override
		public String toString() {
			return "Jugador --> Nombre=" + nombre + ", Color de las fichas=" + colorFichas + ", Rol=" + rol;
		}

		//Metodo movimiento valido
		public boolean movimientoValido(int fila, int columna, Color Color, Tablero tablero) {
			// Verificar que la casilla está vacía
		    if (tablero.casillas[fila][columna] != Color.VACIO) {
		        return false;
		    }

		    // Verificar que al menos una ficha del oponente es capturable
		    boolean alMenosUnaCapturable = false;
		    for (int filaDir = -1; filaDir <= 1; filaDir++) {
		        for (int colDir = -1; colDir <= 1; colDir++) {
		            // Saltarse la casilla actual
		            if (filaDir == 0 && colDir == 0) {
		                continue;
		            }

		            int filaActual = fila + filaDir;
		            int columnaActual = columna + colDir;
		            boolean hayFichasOponenteEnMedio = false;
		            
		            
		            while (filaActual >= 0 && filaActual < tablero.filas && columnaActual >= 0 && columnaActual < tablero.columnas) {
		            	
		            	//sacamos el color actual de la casilla
		                Color colorActual = tablero.casillas[filaActual][columnaActual];
		                if (colorActual == Color.VACIO) {
		                    // Si se encuentra una casilla vacía, se interrumpe la búsqueda en esa dirección
		                    break;
		                } else if (colorActual == Color) {
		                    // Si se encuentra una ficha del mismo color, se verifica si hay fichas del oponente en medio
		                    if (hayFichasOponenteEnMedio) {
		                        alMenosUnaCapturable = true;
		                    }
		                    break;
		                } else {
		                    // Si se encuentra una ficha del oponente, se marca que hay fichas del oponente en medio
		                    hayFichasOponenteEnMedio = true;  
		                }
		                filaActual += filaDir;
		                columnaActual += colDir;
		            }
		        }
		    }
		    return alMenosUnaCapturable;
		}
		
		//Metodo colocar ficha
		//Si devuelve false, se vuelve a pedir una fila y columna
		//Si es true se devuelve un mensaje y se muestra el tablero
		public boolean colocarFicha(Color color, int fila, int columna, Tablero tablero) {
			
		    if (!movimientoValido(fila, columna, color, tablero)) {
		    	//System.out.println("Movimiento no válido");
		        return false;
		    }
		    tablero.casillas[fila][columna] = color;
		    	return true;
		    	
		    	
		}


}
