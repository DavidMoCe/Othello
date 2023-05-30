package ProyectoFinal;

public class Tablero {
	//creamos los atributos para el tablero
	protected int filas;
	protected int columnas;
    protected Color[][] casillas;
    
    //constructor del tablero
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.casillas = new Color[filas][columnas];
        
        // Inicializar todas las casillas como vacías
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.casillas[i][j] = Color.VACIO;
            }
        }
        // Colocar las cuatro fichas iniciales en el centro del tablero
        int centroFila = filas / 2;
        int centroColumna = columnas / 2;
        this.casillas[centroFila - 1][centroColumna - 1] = Color.NEGRA;
        this.casillas[centroFila - 1][centroColumna] = Color.BLANCA;
        this.casillas[centroFila][centroColumna - 1] = Color.BLANCA;
        this.casillas[centroFila][centroColumna] = Color.NEGRA;
    }
    
    // Métodos getter y setter para filas y columnas
    public int getFilas() {
        return filas;
    }
    
    public int getColumnas() {
        return columnas;
    }
    
    public void setFilas(int filas) {
        this.filas = filas;
    }
    
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    
    //colores para el tablero
    public static final String BLACK = "\u001B[30m";
	public static final String WHITE = "\u001B[97m";
	public static final String yellow=("\u001B[33m");
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	
	//palabras en negritas
	public static final String NEGRITAS="\033[0;1m";
	
	//colores de fondo para el tablero
	public static final String BLACK_BACKGROUND = "\u001B[40m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";
	public static final String RED_BACKGROUND = "\u001B[41m";
	public static final String GREEN_BACKGROUND = "\u001B[42m";
	public static final String YELLOW_BACKGROUND = "\u001B[43m";
	public static final String BLUE_BACKGROUND = "\u001B[44m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static final String CYAN_BACKGROUND = "\u001B[46m";
	public static final String GRIS_BACKGROUND= "\u001B[47m";
	
    //Metodo para imprimir el tablero
    public void imprimirTablero() {
        System.out.print("  ");
        //imprime las letras de las columnas del tablero
        char c;
        int col;
        for (c = 'A',col=0; c <= 'Z'&& col < columnas; ++c,col++) {
        	
            System.out.print("    "+NEGRITAS+GREEN+c + "    "+RESET);
        }
        System.out.println();
        
        //imprime los numeros de las filas del tablero
        for (int fila = 0; fila < filas; fila++) {
        	//imprime los numeros del tablero
        	System.out.print(NEGRITAS+GREEN+fila+" "+RESET);
        	
        	//imprime las casillas del tablero
            for ( col = 0; col < columnas; col++) {
            	//con un siwtch ponemos los colores a las casillas dependiendo de la opcion que sea
                switch (casillas[fila][col]) {
               
                    case NEGRA:
                    	 System.out.print("|"+BLACK_BACKGROUND + WHITE+" "+casillas[fila][col]+"  "+RESET);
                        break;
                    case BLANCA:
                    	System.out.print("|"+YELLOW_BACKGROUND+ BLACK+" "+casillas[fila][col]+" "+RESET);
                        break;
                    default:
                        System.out.print("|"+GRIS_BACKGROUND+" VACIO  "+RESET);
                        break;
                }
            }
            System.out.println("|\n");
        }
        System.out.println();
    }

    //metodo para obtener el color de la ficha de una determinada casilla
    public Color obtenerFicha(int fila, int columna,Tablero tablero) {

    	return tablero.casillas[fila][columna];
    	
    }
    
    
    //metodo para capturar ficha
    public void capturarFichas(int fila, int columna, Tablero tablero, Jugador turnoActual) {
		//Obtenemos el color del jugador que tiene el turno
		Color colorActual=turnoActual.getColorFichas();
		int repetidas=0;//variable para comprobar si hay dos fichas del mismo color juntas
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Misma fila
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		//Capturamos en la misma fila (De izquierda a derecha)
		//recorremos todas las columnas pero en la misma fila y hacia la derecha
		for(int colum=columna;colum<tablero.getColumnas();colum++) {
			boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
			
			//si es del mismo color del turno, se suma 1 al contador y si hay dos del mismo color juntas, sale del bucle for
			if(tablero.obtenerFicha(fila, colum, tablero)==colorActual) {
				repetidas=repetidas+1;
			}
			if(repetidas>=2) {
				break;
			}
			//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
			if(tablero.obtenerFicha(fila,colum, tablero) !=colorActual) {
				//si la ficha anterior es del color actual y no esta vacia, se sigue
				if(tablero.obtenerFicha(fila, colum-1, tablero)==colorActual) {
					//se puede capturar una ficha y guardamos la columna
					boolean capturaEncontrada= true;
					int columCaptura= colum;
					//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
					for (int c = columCaptura; c < tablero.getColumnas(); c++) {
						if (tablero.obtenerFicha(fila, c, tablero) == colorActual) {
							//guardamos la columna en la que esta la ficha de nuestro color
							columCaptura = c;
							break;
							 //si encuentra una casilla vacia, se sale del bucle
						} else if (tablero.obtenerFicha(fila, c, tablero) == Color.VACIO){
							break;
						}
			                
					}
						
					//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
					if (capturaEncontrada) {
						for (int c = colum; c < columCaptura; c++) {
				             tablero.casillas[fila][c]=colorActual;			                   
				         }
				         //salimos del bucle ya que no se puede capturar mas fichas
					     capturada=true;
				     }
				}
				if(capturada) {
					break;//salimos del bucle principal para que no continue
				}
			}
			//si encuentra una casilla vacia, se para
			if(tablero.casillas[fila][colum]==Color.VACIO){
				break;
			}
		}

		
		//Capturamos en la misma fila (De derecha a izquierda)
		//recorremos todas las columnas pero en la misma fila y hacia la izquierda
		repetidas=0;//reiniciamos el contador
		
		for(int colum=columna;colum>-1;colum--) {
			boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
			
			//si es del mismo color del turno, se suma 1 al contador y si hay dos, sale del bucle for
			if(tablero.obtenerFicha(fila, colum, tablero)==colorActual) {
				repetidas=repetidas+1;
			}
			if(repetidas>=2) {
				break;
			}
			//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
			if(tablero.obtenerFicha(fila,colum, tablero) !=colorActual && tablero.obtenerFicha(fila, colum, tablero)!=Color.VACIO) {
				//si la ficha anterior es del color actual y no esta vacia, se sigue
				if(tablero.obtenerFicha(fila, colum+1, tablero)==colorActual && tablero.obtenerFicha(fila,colum, tablero) !=colorActual) {
					//se puede capturar una ficha y guardamos la columna
					boolean capturaEncontrada= true;
					int columCaptura= colum;
					//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
					for (int c = columCaptura; c >-1; c--) {

				        if (tablero.obtenerFicha(fila, c, tablero) == colorActual) {
				            //guardamos la columna en la que esta la ficha de nuestro color
					        columCaptura = c;
				            break;
				            //si encuentra una casilla vacia, se sale del bucle
			        	 } else if (tablero.obtenerFicha(fila, c, tablero) == Color.VACIO){ 
			        	   	break;
			        	 }
			                
			        }
						
					//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
					 if (capturaEncontrada) {
				         for (int c = colum; c > columCaptura; c--) {
				             tablero.casillas[fila][c]=colorActual;
				                  
				         }
				         //salimos del bucle ya que no se puede capturar mas fichas
				         capturada=true;				         
					 }
				}
				if(capturada) {
					break;//salimos del bucle principal para que no continue
				}
			}
			//si encuentra una casilla vacia, se para
			if(tablero.casillas[fila][colum]==Color.VACIO){
				break;
			}		
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Misma columna
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		//Capturamos en la misma columna (De arriba hacia abajo)
		//recorremos todas las filas pero en la misma columna direccion negativa
		repetidas=0;//reiniciamos el contador
		
		for (int fil = fila; fil < tablero.getFilas(); fil++) {
			boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
			
			//si es del mismo color del turno, se suma 1 al contador y si hay dos, sale del bucle for
			if(tablero.obtenerFicha(fil, columna, tablero)==colorActual) {
				repetidas=repetidas+1;
			}
			if(repetidas>=2) {
				break;
			}

			//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		    if (tablero.obtenerFicha(fil, columna, tablero) != colorActual && tablero.obtenerFicha(fil, columna, tablero) != Color.VACIO) {
	
		       	//si la ficha anterior es del color actual y no esta vacia, se sigue
		       	if(tablero.obtenerFicha(fil-1, columna, tablero)==colorActual && tablero.obtenerFicha(fil, columna, tablero) != colorActual) {
		       		//se puede capturar una ficha y guardamos la fila
		       		boolean capturaEncontrada = true;
		       		int filCaptura = fil;
			           
			       	//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
			        for (int f = filCaptura; f < tablero.getFilas(); f++) {
			           	if (tablero.obtenerFicha(f, columna, tablero) == colorActual) {
			           		//guardamos la fila en la que esta la ficha de nuestro color
			              	filCaptura = f;
			               	break;
			                //si encuentra una casilla vacia, se sale del bucle
			           	} else if (tablero.obtenerFicha(f, columna, tablero) == Color.VACIO) {
			           		break;
			           	}
			         		
			        }
			      	//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
			        if (capturaEncontrada) {
			           	for (int f = fil; f < filCaptura; f++) {
			           		tablero.casillas[f][columna]=colorActual;
			           	}
			           	//salimos del bucle ya que no se puede capturar mas fichas
			           	capturada=true;
			        }
		        }
		       	if(capturada) {
					break;//salimos del bucle principal para que no continue
				}
			}
			//si encuentra una casilla vacia, se para
			if(tablero.casillas[fil][columna]==Color.VACIO){
				break;
			}	
		}
		
		//Capturamos en la misma columna (De abajo hacia arriba)
		//recorremos todas las filas pero en la misma columna direccion positiva
		repetidas=0;//reiniciamos el contador
		
		for (int fil = fila; fil >-1; fil--) {
			boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
			
			//si es del mismo color del turno, se suma 1 al contador y si hay dos, sale del bucle for
			if(tablero.obtenerFicha(fil, columna, tablero)==colorActual) {
				repetidas=repetidas+1;
			}
			if(repetidas>=2) {
				break;
			}

			//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		    if (tablero.obtenerFicha(fil, columna, tablero) != colorActual && tablero.obtenerFicha(fil, columna, tablero) != Color.VACIO) {
 	
		       	//si la ficha anterior es del color actual y no esta vacia, se sigue
		       	if(tablero.obtenerFicha(fil+1, columna, tablero)==colorActual && tablero.obtenerFicha(fil, columna, tablero) != colorActual) {
		       	//se puede capturar una ficha y guardamos la fila
		       		boolean capturaEncontrada = true;
		       		int filCaptura = fil;
			           
			       	//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
			        for (int f = filCaptura; f >-1; f--) {
			           	if (tablero.obtenerFicha(f, columna, tablero) == colorActual) {
			           		//guardamos la fila en la que esta la ficha de nuestro color
			              	filCaptura = f;
			               	break;
			               	//si encuentra una casilla vacia, se sale del bucle
			           	} else if (tablero.obtenerFicha(f, columna, tablero) == Color.VACIO) {
			           		break;
			           	}
			         		
			        }
			      	//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
			        if (capturaEncontrada) {
			           	for (int f = fil; f > filCaptura; f--) {
			           		tablero.casillas[f][columna]=colorActual;
			           	}
			           	//salimos del bucle ya que no se puede capturar mas fichas
				        capturada=true;
			        }
		        }
		       	if(capturada) {
					break;//salimos del bucle principal para que no continue
				}	
			} 		
			//si encuentra una casilla vacia, se para
			if(tablero.casillas[fil][columna]==Color.VACIO){
				break;
			}
			
		}
	
	///////////////////////////////       DIAGONAL 1 --->    //////////////////////////////////////////////////////	
	int columnaDiagonal,filaDiagonal;
	//capturamos en diagonal (superior izquierda a inferior derecha)
	//vamos a recorrer cada columna y cada fila pero hacia la derecha desde la posicion de la ficha colocada
	repetidas=0;//reiniciamos el contador
	
	for(columnaDiagonal=columna,filaDiagonal=fila;columnaDiagonal<tablero.getColumnas() && filaDiagonal<tablero.getFilas();columnaDiagonal++, filaDiagonal++) {
		boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
		
		//si es del mismo color del turno, se suma 1 al contador y si hay dos, sale del bucle for
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) ==colorActual) {
			repetidas=repetidas+1;
		}
		if(repetidas>=2) {
			break;
		}

		//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) !=colorActual && tablero.obtenerFicha(filaDiagonal, columnaDiagonal, tablero)!=Color.VACIO) {
			//si la ficha anterior es del color actual y no esta vacia, se sigue
			if(tablero.obtenerFicha(filaDiagonal-1, columnaDiagonal-1, tablero)==colorActual) {
				//se puede capturar una ficha y guardamos la posicion(columna y fila)
				boolean capturaEncontrada= true;
				int columCapturaDi=columnaDiagonal;
				int filaCapturaDi=filaDiagonal;
				
				//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
				int c,f;
				for(c=columCapturaDi,f=filaCapturaDi;c<tablero.getColumnas() && f<tablero.getFilas();c++,f++) {
					if (tablero.obtenerFicha(f, c, tablero) == colorActual) {
						//guardamos la columna en la que esta la ficha de nuestro color
		                columCapturaDi = c;
		                filaCapturaDi=f;
	                    break;
	                    //si encuentra una casilla vacia, se sale del bucle
        	        } else if(tablero.obtenerFicha(f, c, tablero) == Color.VACIO){
        	        	break;
        	        }		 
				}
				
				//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				if (capturaEncontrada) {
		          for(c=columnaDiagonal,f=filaDiagonal;c<columCapturaDi && f<filaCapturaDi;c++,f++) {  
		        	  tablero.casillas[f][c]=colorActual;
		          }
		          //salimos del bucle ya que no se puede capturar mas fichas
		          capturada=true;
				}
			}
			if(capturada) {
				break;//salimos del bucle principal para que no continue
			}
		} 
		//si encuentra una casilla vacia, se para
		if(tablero.casillas[filaDiagonal][columnaDiagonal]==Color.VACIO){
			break;
		}	
	}
	
	//capturamos en diagonal (inferior derecha a superior izquierda)
	//vamos a recorrer cada columna y cada fila pero hacia la derecha desde la posicion de la ficha colocada
	
	repetidas=0;//reiniciamos el contador
	for(columnaDiagonal=columna,filaDiagonal=fila;columnaDiagonal>-1 && filaDiagonal>-1;columnaDiagonal--, filaDiagonal--) {
		boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
		//si es del mismo color del turno, se suma 1 al contador y si hay dos, sale del bucle for
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) ==colorActual) {
			repetidas=repetidas+1;
		}
		if(repetidas>=2) {
			break;
		}

		//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) !=colorActual && tablero.obtenerFicha(filaDiagonal, columnaDiagonal, tablero)!=Color.VACIO) {
			//si la ficha anterior es del color actual y no esta vacia, se sigue
			if(tablero.obtenerFicha(filaDiagonal+1, columnaDiagonal+1, tablero)==colorActual) {
				//se puede capturar una ficha y guardamos la posicion(columna y fila)
				boolean capturaEncontrada= true;
				int columCapturaDi=columnaDiagonal;
				int filaCapturaDi=filaDiagonal;
					
				//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
				int c,f;
				for(c=columCapturaDi,f=filaCapturaDi;c>-1 && f>-1;c--,f--) {
					if (tablero.obtenerFicha(f, c, tablero) == colorActual) {
						//guardamos la columna en la que esta la ficha de nuestro color
						columCapturaDi = c;
						filaCapturaDi=f;
						break;
						//si encuentra una casilla vacia, se sale del bucle
					} else if(tablero.obtenerFicha(f, c, tablero) == Color.VACIO){
						break;
					}		 
				}
					
				//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				if (capturaEncontrada) {
					for(c=columnaDiagonal,f=filaDiagonal;c>columCapturaDi && f>filaCapturaDi;c--,f--) {
						tablero.casillas[f][c]=colorActual;
					}
					//salimos del bucle ya que no se puede capturar mas fichas
					capturada=true;
				}			
			}
			if(capturada) {
				break;//salimos del bucle principal para que no continue
			}
		}		
		//si encuentra una casilla vacia, se para
		if(tablero.casillas[filaDiagonal][columnaDiagonal]==Color.VACIO){
			break;
		}		
	}

	
	///////////////////////////////       DIAGONAL 2 <----    //////////////////////////////////////////////////////
	//capturamos en diagonal (superior derecha a inferior izquierda)
	//vamos a recorrer cada columna y cada fila pero hacia la izquierda desde la posicion de la ficha colocada
	repetidas=0;//reiniciamos el contador
		
	for(columnaDiagonal=columna,filaDiagonal=fila;columnaDiagonal>-1 && filaDiagonal<tablero.getFilas();columnaDiagonal--,filaDiagonal++) {
		boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
			
		//si es del mismo color del turno, se suma 1 al contador y si hay dos, sale del bucle for
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) ==colorActual) {
			repetidas=repetidas+1;
		}
		if(repetidas>=2) {
			break;
		}
		//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) !=colorActual && tablero.obtenerFicha(filaDiagonal, columnaDiagonal, tablero)!=Color.VACIO) {
			//si la ficha anterior es del color actual y no esta vacia, se sigue
			if(tablero.obtenerFicha(filaDiagonal-1, columnaDiagonal+1, tablero)==colorActual) {
				//se puede capturar una ficha y guardamos la posicion(columna y fila)
				boolean capturaEncontrada= true;
				int columCapturaDi=columnaDiagonal;
				int filaCapturaDi=filaDiagonal;
				//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
				int c,f;
				for(c=columCapturaDi,f=filaCapturaDi;c>-1 && f<tablero.getFilas();c--,f++) {
					if (tablero.obtenerFicha(f, c, tablero) == colorActual ) {
						//guardamos la columna en la que esta la ficha de nuestro color
		                columCapturaDi = c;
		                filaCapturaDi=f;
	                    break;
	                    //si encuentra una casilla vacia, se sale del bucle
        	        } else if(tablero.obtenerFicha(f, c, tablero) == Color.VACIO) {
        	        	break;
        	        }
				}
				//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				if (capturaEncontrada) {
					 for(c=columnaDiagonal,f=filaDiagonal;c>columCapturaDi && f<filaCapturaDi;c--,f++) {
			        	  tablero.casillas[f][c]=colorActual;
			          }
					//salimos del bucle ya que no se puede capturar mas fichas
			          capturada=false;
				}
			}
			if(capturada) {
				break;//salimos del bucle principal para que no continue
			}	
		}
		//si encuentra una casilla vacia, se para
		if(tablero.casillas[filaDiagonal][columnaDiagonal]==Color.VACIO){
			break;
		}	
	}
	
	//capturamos en diagonal (inferior izquierda a superior derecha)
	//vamos a recorrer cada columna y cada fila pero hacia la izquierda desde la posicion de la ficha colocada
	repetidas=0;//reiniciamos el contador
	
	for(columnaDiagonal=columna,filaDiagonal=fila;columnaDiagonal<tablero.getColumnas() && filaDiagonal>-1;columnaDiagonal++,filaDiagonal--) {
		boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
		
		//si es del mismo color del turno, se suma 1 al contador y si hay dos, sale del bucle for
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) ==colorActual) {
			repetidas=repetidas+1;
		}
		if(repetidas>=2) {
			break;
		}	
		//Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		if(tablero.obtenerFicha(filaDiagonal,columnaDiagonal, tablero) !=colorActual && tablero.obtenerFicha(filaDiagonal, columnaDiagonal, tablero)!=Color.VACIO) {
			//si la ficha anterior es del color actual y no esta vacia, se sigue
			if(tablero.obtenerFicha(filaDiagonal+1, columnaDiagonal-1, tablero)==colorActual) {
				//se puede capturar una ficha y guardamos la posicion(columna y fila)
				boolean capturaEncontrada= true;
				int columCapturaDi=columnaDiagonal;
				int filaCapturaDi=filaDiagonal;
				//sigue buscando hasta que encuentre la ficha del color del turno actual y la guarda
				int c,f;
				for(c=columCapturaDi,f=filaCapturaDi;c<tablero.getColumnas() && f>-1;c++,f--) {
					if (tablero.obtenerFicha(f, c, tablero) == colorActual ) {
						//guardamos la columna en la que esta la ficha de nuestro color
			            columCapturaDi = c;
			            filaCapturaDi=f;
		                break;
	        	    } else if(tablero.obtenerFicha(f, c, tablero) == Color.VACIO) {
	        	      	break;
	        	    }
								
				}
				//aqui vamos cambiando los colores de las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				if (capturaEncontrada) {
					 for(c=columnaDiagonal,f=filaDiagonal;c<columCapturaDi && f>filaCapturaDi;c++,f--) {
				       	  tablero.casillas[f][c]=colorActual;
				     }
					 //salimos del bucle ya que no se puede capturar mas fichas
					 capturada=true;
				}
			}
			if(capturada) {
				break;//salimos del bucle principal para que no continue
			}
		}
		//si encuentra una casilla vacia, se para
		if(tablero.casillas[filaDiagonal][columnaDiagonal]==Color.VACIO){
			break;
		}
	}
	
				
		
}
    
   
    //metodo para obtener ganador   
    public static void ganador(Tablero tablero) {
    	//Iniciamos dos variables para contar las fichas
    	int blancas=0,negras=0;
    	
    	//hacemos un for para recorrer las filas
    	for(int f=0;f<tablero.getFilas();f++) {
    		
    		//este for recorre las columnas
    		for(int c=0;c<tablero.getColumnas();c++) {
    			
    			//Dependiendo del color de la casilla, se suma 1 a blancas o negras
    			if(tablero.casillas[f][c]==Color.BLANCA) {
    				blancas++;
    			} else if(tablero.casillas[f][c]==Color.NEGRA){
    				negras++;
    			}
    		}
    	}
    	
    	//Comparamos las dos variables y mostramos el mensaje del ganador que es el que tiene más fichas
    	if(blancas>negras) {
    		System.out.println(tablero.NEGRITAS+tablero.GREEN+"GANAN LAS BLANCAS"+tablero.RESET);
    	} else if(blancas<negras) {
    		System.out.println(tablero.NEGRITAS+tablero.GREEN+"GANAN LAS NEGRAS"+tablero.RESET);
    	} else {
    		System.out.println(tablero.NEGRITAS+tablero.GREEN+"HA SIDO UN EMPATE"+tablero.RESET);
    	}
    	//mostramos la puntuacion
    	System.out.println("-----Puntuación-----");
    	System.out.println("Fichas negras: "+ negras);
    	System.out.println("Fichas blancas: "+blancas);
    	
    }
    
	
}
