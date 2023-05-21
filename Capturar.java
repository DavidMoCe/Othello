package ProyectoFinal;

import java.util.Random;

public class Capturar {

	//metodo para que la maquina ponga fichas en la primera posicion encontrada y válida
	public static boolean movimientoMaquina( Tablero tablero, Jugador turnoActual) {
		//variable para comprobar que el movimiento es correcto
		boolean correcto=false;
		//declaramos las variables para controlar si pueden hacer ese movimiento o pasa
		int pasa1=0,pasa2=0,pasa3=0,pasa4=0;
		int pasaTurno=0;
		boolean pasa=false;
		
		//variable para elegir la opcion, que se hara aleatoriamente
		int opcion=0;
		//mientras no sea true y pasaTurno no sea 4 se ejecuta el bucle do-while
		do {
			//elige una opcion aleatoriamente
			Random numero= new Random();
			opcion=(numero.nextInt(4)+1);
			
			//creamos un switch con 4 opciones que recorre el tablero en diferentes direcciones
			switch(opcion) {
			case 1:
				//recorre el tablero de izquierda a derecha y de arriba hacia abajo
				for(int fila=0;fila<tablero.getFilas();fila++){
					for(int columna=0;columna<tablero.getColumnas();columna++) {
						//comprobamos si podemos colocar la ficha
						if(turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero)) {
							//capturamos la ficha
							tablero.capturarFichas(fila, columna,tablero, turnoActual);
							 //sale del bucle podriamos salir tambien con un "break"
							 columna=tablero.getColumnas()-1;
							 fila=tablero.getFilas()-1;
							 //el movimiento a sido correcto asi que ponemos la variable correcto a true
							 correcto=true;
							 //si no se puede colocar la ficha entra en el else
						 } else {
							//la ariable pasa1 se pone a 1
							pasa1=1; 
						 }
					}	 
				}
				//salimos del stiwch
				break;
				
			case 2:
				//recorre el tablero de derecha a izquierda y de arriba hacia abajo
				for(int fila=0;fila<tablero.getFilas();fila++){
					for(int columna=tablero.getColumnas()-1;columna>-1;columna--) {
						//comprobamos si podemos colocar la ficha
						if(turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero)) {
							//capturamos la ficha
							 tablero.capturarFichas(fila, columna,tablero, turnoActual);
 
							//sale del bucle
							 columna=0;
							 fila=tablero.getFilas()-1;
							 //el movimiento a sido correcto asi que ponemos la variable correcto a true
							 correcto=true;
							 //si no se puede colocar la ficha entra en el else
						 }else {
							//la ariable pasa2 se pone a 1
							pasa2=1; 
						}
					}	 
				}
				//salimos del stiwch
				break;

			case 3:
				//recorre el tablero de izquierda a derecha y de abajo hacia arriba
				for(int fila=tablero.getFilas()-1;fila>-1;fila--){
					for(int columna=0;columna<tablero.getColumnas();columna++) {
						//comprobamos si podemos colocar la ficha
						if(turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero)) {
							//capturamos la ficha
							 tablero.capturarFichas(fila, columna,tablero, turnoActual);
							//sale del bucle
							 columna=tablero.getColumnas()-1;
							 fila=0;
							//el movimiento a sido correcto asi que ponemos la variable correcto a true
							 correcto=true;
							 //si no se puede colocar la ficha entra en el else
						 }else {
							//la ariable pasa3 se pone a 1
							pasa3=1; 
						}
					}	 
				}
				//salimos del stiwch
				break;

			case 4:
				//recorre el tablero de derecha a izquierda y de abajo hacia arriba
				for(int fila=tablero.getFilas()-1;fila>-1;fila--){
					for(int columna=tablero.getColumnas()-1;columna>-1;columna--) {
						//comprobamos si podemos colocar la ficha
						if(turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero)) {
							//capturamos la ficha
							 tablero.capturarFichas(fila, columna,tablero, turnoActual);
							//sale del bucle
							 columna=0;
							 fila=0;
							//el movimiento a sido correcto asi que ponemos la variable correcto a true
							 correcto=true;
							 //si no se puede colocar la ficha entra en el else
						 }else {
							//la ariable pasa4 se pone a 1
							pasa4=1; 
						 }
					}	 
				}
				//salimos del stiwch
				break;
			}
			//sumamos las variables
			pasaTurno=pasa1+pasa2+pasa3+pasa4;
		}while (!correcto && pasaTurno!=4);
		
		//si la variable pasaTurno es 4, la variable "pasa" se pone a true, si no se pone en false
		if(pasaTurno==4) {
			pasa=true;
		} else {
			pasa=false;
		}
		
		//devolvemos la variable pasa
		return pasa;
		
	}
	
	//movimiento maquina2, elige el mejor movimiento
	public static boolean movimientoMaquina2(Tablero tablero, Jugador turnoActual) {
		
		//Creamos una variable de tipo Movimiento que guarde la fila y la columna
		Movimiento mejorMovimiento=null;
		//variables para contar las fichas capturadas de un determinado movimiento
		int mejorCaptura = 0;
		int capturas;
		
		//estas variables controla si pasa turno
		int pasaTurno=0;
		boolean pasa=false;
		

		//recorre el tablero de izquierda a derecha y de arriba hacia abajo
		for(int fila=0;fila<tablero.getFilas();fila++){
			for(int columna=0;columna<tablero.getColumnas();columna++) {
						
				//si se puede colocar la ficha entra en el if
				boolean movimiento=turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero);
				if(movimiento==true) {
							
					//llamamos al metodo "contarFichasCapturadas" para que mire en todas las direcciones y cuente las fichas capturables posibles
					capturas = contarFichasCapturadas(fila, columna,turnoActual,tablero);			               
					//esto lo hacemos porque al llamar al metodo "colocarFicha" se pone una ficha y no puedo cambiarlo porque lo utilizo en otros sitio
					tablero.casillas[fila][columna]=Color.VACIO;
					
					//si el resultado del metodo es mayor a las capturas ya guardadas, se pone la cantidad de fichas de la mejor captura
					if (capturas > mejorCaptura) {
						mejorCaptura = capturas;
						
						//se guarda la casilla y volvemos a buscar otra casilla
						mejorMovimiento = new Movimiento(fila, columna);	
					} else if(mejorCaptura==0) {
						//se guarda la casilla y volvemos a buscar otra casilla
						mejorMovimiento = new Movimiento(fila, columna);
					}  
				}
			}	 
		}
		
		//recorre el tablero de derecha a izquierda y de arriba hacia abajo
		for(int fila=0;fila<tablero.getFilas();fila++){
			for(int columna=tablero.getColumnas()-1;columna>-1;columna--) {
				//si el movimiento es valido entra en el if
				boolean movimiento=turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero);
				if(movimiento==true) {
					//llamamos al metodo "contarFichasCapturadas" para que mire en todas las direcciones y cuente las fichas capturables posibles
					capturas = contarFichasCapturadas(fila, columna,turnoActual,tablero);
					
					//esto lo hacemos porque al llamar al metodo "colocarFicha" se pone una ficha y no puedo cambiarlo porque lo utilizo en otros sitio
					tablero.casillas[fila][columna]=Color.VACIO;
			                
					//si el resultado del metodo es mayor a las capturas ya guardadas, se pone la cantidad de fichas de la mejor captura
					if (capturas > mejorCaptura) {
						mejorCaptura = capturas;
			                    
						//se guarda la casilla y volvemos a buscar otra casilla
						mejorMovimiento = new Movimiento(fila, columna);
					} else if(mejorCaptura==0) {
						//se guarda la casilla y volvemos a buscar otra casilla
						mejorMovimiento = new Movimiento(fila, columna);
					}		
				}
			}	 
		}

		//recorre el tablero de izquierda a derecha y de abajo hacia arriba
		for(int fila=tablero.getFilas()-1;fila>-1;fila--){
			for(int columna=0;columna<tablero.getColumnas();columna++) {
				//si el movimiento es valido entra en el if
				boolean movimiento=turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero);
				if(movimiento==true) {
					//llamamos al metodo "contarFichasCapturadas" para que mire en todas las direcciones y cuente las fichas capturables posibles
					capturas = contarFichasCapturadas(fila, columna,turnoActual,tablero);
					
					//esto lo hacemos porque al llamar al metodo "colocarFicha" se pone una ficha y no puedo cambiarlo porque lo utilizo en otros sitio
					tablero.casillas[fila][columna]=Color.VACIO;				
					//si el resultado del metodo es mayor a las capturas ya guardadas, se pone la cantidad de fichas de la mejor captura
					if (capturas > mejorCaptura) {
						mejorCaptura = capturas;		                    
						//se guarda la casilla y volvemos a buscar otra casilla
						mejorMovimiento = new Movimiento(fila, columna);
					}				
				}
			}	 
		}

		//recorre el tablero de derecha a izquierda y de abajo hacia arriba
		for(int fila=tablero.getFilas()-1;fila>-1;fila--){
			for(int columna=tablero.getColumnas()-1;columna>-1;columna--) {
				//si el movimiento es correcto entra en el if				
				if(turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero)) {			
					//llamamos al metodo "contarFichasCapturadas" para que mire en todas las direcciones y cuente las fichas capturables posibles
					capturas = contarFichasCapturadas(fila, columna,turnoActual,tablero);
					//esto lo hacemos porque al llamar al metodo "colocarFicha" se pone una ficha y no puedo cambiarlo porque lo utilizo en otros sitio
					tablero.casillas[fila][columna]=Color.VACIO;
			                
					//si el resultado del metodo es mayor a las capturas ya guardadas, se pone la cantidad de fichas de la mejor captura
					if (capturas > mejorCaptura) {
						mejorCaptura = capturas;		                    
						//se guarda la casilla y volvemos a buscar otra casilla
						mejorMovimiento = new Movimiento(fila, columna);
					} else if(mejorCaptura==0) {
						//se guarda la casilla y volvemos a buscar otra casilla
						mejorMovimiento = new Movimiento(fila, columna);
					}			                
				}
			}	 
		}
				
		//una vez visto todos los movimientos posibles comprobamos si pasa o no
		if(mejorMovimiento!=null) {
			pasa=false;	
			//Prueba para ver si lo hace correctamente
			//System.out.println(mejorMovimiento.getFila()+","+mejorMovimiento.getColumna()+" mejor");
				
			//si no pasa significa que puede poner ficha, por lo que la ponemos y capturamos las fichas
			turnoActual.colocarFicha(turnoActual.getColorFichas(), mejorMovimiento.getFila(), mejorMovimiento.getColumna(), tablero);
			tablero.capturarFichas(mejorMovimiento.getFila(), mejorMovimiento.getColumna(),tablero, turnoActual);

		} else {
			pasa=true;			
		}	
		
		return pasa;	
	}
	
	//metodo para ver si los jugadores tienen movimientos posibles
	public static boolean movimientoPosible(Tablero tablero, Jugador turnoActual) {
		//declaramos la variable como false
		boolean posible=false;
		//recorre el tablero de izquierda a derecha y de arriba hacia abajo
		for(int fila=0;fila<tablero.getFilas();fila++){
			for(int columna=0;columna<tablero.getColumnas();columna++) {
				//si hay algun movimiento valido entra en el if
				if(turnoActual.movimientoValido(fila, columna, turnoActual.getColorFichas(), tablero)) {
					//si se puede colocar alguna ficha se sale del bucle y devolvemos true, si no devolvemos false
					//sale del bucle
					 posible= true;
					 columna=tablero.getColumnas()-1;
					 fila=tablero.getFilas()-1;
					
				 } else {
					posible= false; 
				 }
			}	 
		}
		//devolvemos la variable posible
		return posible;
	}
	
	//contar las fichas que se pueden capturar desde una determinada casilla
	public static int contarFichasCapturadas(int fila, int columna, Jugador turnoActual,Tablero tablero) {
	    int fichasCapturadas = 0;
	    
	  //Obtenemos el color del jugador que tiene el turno
	  Color colorActual=turnoActual.getColorFichas();
	  int repetidas=0;//variable para comprobar si hay dos fichas del mismo color juntas
	  //////////////////////////////////////////////////////////////////////////////////////////////
	  //Para cada casilla posible hacemos lo siguiente
	  //////////////////////////////////////////////////////////////////////////////////////////////
	  
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
		  if(tablero.obtenerFicha(fila,colum, tablero) !=colorActual && tablero.obtenerFicha(fila, colum, tablero)!=Color.VACIO) {
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
	  			
				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for (int c = colum; c < columCaptura; c++) {
						  fichasCapturadas=fichasCapturadas+1;
	  					
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
		  
		  boolean capturada=false;
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
			  if(tablero.obtenerFicha(fila, colum+1, tablero)==colorActual) {
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
				  
				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for (int c = colum; c > columCaptura; c--) {
						  fichasCapturadas=fichasCapturadas+1;
						  
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
		  
		  //si es del mismo color del turno, se suma 1 al contador y si hay dos del mismo color juntas, sale del bucle for
		  if(tablero.obtenerFicha(fil, columna, tablero)==colorActual) {
			  repetidas=repetidas+1;
		  }
		  if(repetidas>=2) {
			  break;
		  }
		  
		  //Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		  if (tablero.obtenerFicha(fil, columna, tablero) != colorActual && tablero.obtenerFicha(fil, columna, tablero) != Color.VACIO) {
		  
			  //si la ficha anterior es del color actual y no esta vacia, se sigue
			  if(tablero.obtenerFicha(fil-1, columna, tablero)==colorActual) {
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
				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for (int f = fil; f < filCaptura; f++) {
						  fichasCapturadas=fichasCapturadas+1;
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
		  
		  //si es del mismo color del turno, se suma 1 al contador y si hay dos del mismo color juntas, sale del bucle for
		  if(tablero.obtenerFicha(fil, columna, tablero)==colorActual) {
			  repetidas=repetidas+1;
		  }
		  if(repetidas>=2) {
			  break;
		  }
		  
		  //Si encontramos alguna ficha que no sea la del color actual y no esté vacia podremos capturar ficha
		  if (tablero.obtenerFicha(fil, columna, tablero) != colorActual && tablero.obtenerFicha(fil, columna, tablero) != Color.VACIO) {
			  
			  //si la ficha anterior es del color actual y no esta vacia, se sigue
			  if(tablero.obtenerFicha(fil+1, columna, tablero)==colorActual) {
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
				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for (int f = fil; f > filCaptura; f--) {
						  fichasCapturadas=fichasCapturadas+1;
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
		  
		  //si es del mismo color del turno, se suma 1 al contador y si hay dos del mismo color juntas, sale del bucle for
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

				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for(c=columnaDiagonal,f=filaDiagonal;c<columCapturaDi && f<filaCapturaDi;c++,f++) {
						  fichasCapturadas=fichasCapturadas+1;
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
		  
		  //si es del mismo color del turno, se suma 1 al contador y si hay dos del mismo color juntas, sale del bucle for
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

				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for(c=columnaDiagonal,f=filaDiagonal;c>columCapturaDi && f>filaCapturaDi;c--,f--) {
						  fichasCapturadas=fichasCapturadas+1;
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
		  
		  //si es del mismo color del turno, se suma 1 al contador y si hay dos del mismo color juntas, sale del bucle for
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
				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for(c=columnaDiagonal,f=filaDiagonal;c>columCapturaDi && f<filaCapturaDi;c--,f++) {
						  fichasCapturadas=fichasCapturadas+1;
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

	  //capturamos en diagonal (inferior izquierda a superior derecha)
	  //vamos a recorrer cada columna y cada fila pero hacia la izquierda desde la posicion de la ficha colocada
	  repetidas=0;//reiniciamos el contador
	  
	  for(columnaDiagonal=columna,filaDiagonal=fila;columnaDiagonal<tablero.getColumnas() && filaDiagonal>-1;columnaDiagonal++,filaDiagonal--) {
		  boolean capturada=false;//para salir del bucle si se capturan todas la fichas posibles
		  
		  //si es del mismo color del turno, se suma 1 al contador y si hay dos del mismo color juntas, sale del bucle for
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
				  //aqui vamos sumando las casillas desde donde hemos encontrado la ficha oponente hasta la otra ficha de nuestro color encontrada
				  if (capturaEncontrada) {
					  for(c=columnaDiagonal,f=filaDiagonal;c<columCapturaDi && f>filaCapturaDi;c++,f--) {
						  fichasCapturadas=fichasCapturadas+1;
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
	  
	  return fichasCapturadas;
	  
	}
	

}
