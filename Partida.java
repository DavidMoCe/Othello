package ProyectoFinal;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Partida {
 
	public static void main(String[] args) {
		//Aquí ejecutamos el programa

			//creamos los atributos necesarios
			Jugador jugador1 = null;
			Jugador jugador2 = null;
			Tablero tablero;
			Jugador turnoActual = null;
			TipoPartida tipoPartida= null;
						
			//creamos el tablero
			tablero=new Tablero(8, 8);
			
			
			//llamamos al método "menu" que a su vez llama a los demás métodos
			juego(menu(tipoPartida),tipoPartida,jugador1,jugador2,turnoActual,tablero);
	
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	//creamos los metodos
	
	//Metodo que muestra un menú para que pueda elegir el tipo de partida
	public static int menu(TipoPartida tipoPartida) {
		//Creamos el Scanner
		 Scanner leerDatos= new Scanner(System.in);
		 
		 //esta variable sirve para el bucle do-while que se repite mientras "error" sea verdadero
		 boolean error;
		 
		 //esta variable sirve para recoger la opción que elija el usuario
	     int opcion;
	     
	     //mostramos el menú
	    System.out.println("Elige el modo de partida");
		System.out.println("1) Jugador VS Jugador");
		System.out.println("2) Jugador VS Ordenador");
		System.out.println("3) Ordenador VS Ordenador");
		System.out.println("4) Salir del juego");
		
		
	    //aquí comienza el bucle do-while
	    do {
	    	//con un try-catch controlamos que se meta el valor correcto
	     	try {
	     		//ponemos la variable en false ya que se supone que el valor se va a meter correctamente
	     		error=false;
	     		
	     		//aquí creamos otro bucle do-while para que muestre "opcion:" hasta que se meta un valor correcto
	     		do {
	     			System.out.print("Opción: ");
	     			opcion= leerDatos.nextInt();
	     			
	     			//creamos un swtich para las diferentes opciones del menu
	     			switch (opcion){
	     			case 1:
	     				//tipoPartida=TipoPartida.JugadorJugador;
	     				return 1;
	     			
	     			case 2:
	     				//tipoPartida=TipoPartida.JugadorMaquina;
	     				return 2;
	     			
	     			case 3:
	     				//tipoPartida=TipoPartida.MaquinaMaquina;
	     				return 3;
	     			
	     			case 4:
	     				System.out.println("Fin del Programa");
	     				break;
	     			
	     			}
	     			//si la opcion es mayor que 4 y menor que 0
	     			if(opcion>4 || opcion<1) {
	     				System.out.println("Elige una de las opciones");
	     			}
	     			
	     		}while(opcion>4 || opcion<1);
	     		
	     		//el catch controla el error
	     	} catch (Exception e) {
	     		System.out.println("Introduce una opción válida");
	     		//si se mete un valor incorrecto se pone el error a true y se repite el bucle
	     		error=true;
	     		leerDatos.next();//limpiamos el buffer
	     	}
	     	
	    } while (error);
	    //por defecto retornamos el valor 0, ya que exige un valor de retorno si o si
	    return 0;
	}
	
		
	
	//Metodo para elegir la dificultad
	public static int escogerDificultad() {
		//creamos el Scanner
		Scanner leerDatos= new Scanner(System.in);
		
		//esta variable sirve para el bucle do-while que se repite mientras "error" sea verdadero
		 boolean error;
		 
		 //esta variable sirve para recoger la opción que elija el usuario
	     int opcion;
	    
	     //Mostramos las opciones
	    System.out.println("Elige la dificultad");
		System.out.println("1) Fácil");
		System.out.println("2) Difícil");
		 //El bucle do-while se repite mientras "error" sea true
	    do {
	    	//con un try-catch controlamos que se meta el valor correcto
	     	try {
	     		//ponemos la variable en false ya que se supone que el valor se va a meter correctamente
	     		error=false;
	     		
	     		//aquí creamos otro bucle do-while para que muestre "opcion:" hasta que se meta un valor correcto
	     		do {
	     			System.out.print("Opción: ");
	     			opcion= leerDatos.nextInt();
	     			
	     			//creamos un switch para las diferentes opciones
	     			switch (opcion){
	     				case 1:
	     					return 1;
	     			
	     				case 2:
	     					return 2;
	     			
	     				default:
	     					System.out.println("Elige una de las opciones");
	     			}
	     		}while(opcion>2 || opcion<1);
	     		//el catch controla los errores
	     	} catch (Exception e) {
	     		System.out.println("Introduce una opción válida");
	     		//ponemos "error" a true
	     		error=true;
	     		leerDatos.next();//limpiamos el buffer
	     	}
	     	
	    } while (error);
	    //por defecto retornamos el valor 0, ya que exige un valor de retorno si o si
	    return 0;
	}
	
	//Metodo juego, según el modo de la partida llama a un metodo o a otro
	public static void juego(int opcion, TipoPartida tipoPartida, Jugador jugador1,Jugador jugador2, Jugador turnoActual,Tablero tablero) {
		//si la opcion es 1 el tipo de partida es Jugador-Jugador
		if(opcion==1) {
			//asignamos el tipo de partida
			tipoPartida=TipoPartida.JugadorJugador;
			//creamos los jugadores
			jugador1= new Jugador("Jugador1",Color.BLANCA , Roles.JUGADOR1);
			jugador2= new Jugador("Jugador2",Color.NEGRA , Roles.JUGADOR2);
			
			//llamamos al metodo "elegirNombre"
			elegirNombre(jugador1,jugador2,tipoPartida);
			
			//llamamos al metodo "escogerColor"
			escogerColor(jugador1, jugador2);
			
			//Llamamos al metodo "sortearTurno" para decidir quien empieza
			turnoActual=sortearTurno(jugador1,jugador2);
			
			//segun el color del turno, mostramos un mensaje u otro
			if(turnoActual.getColorFichas()==Color.BLANCA) {
				System.out.println("Empiezan las blancas");
			} else {
				System.out.println("Empiezan las negras");
			}
			
			//variable que sirve para decir si el movimiento a efectuar es valido
			boolean movimiento=false;
			
			//variable para comprobar si pasan de turno
			int pasanTurno=0;
			//creamos la variable mal para poder controlar cuando mostrar el mensaje
			int mal=0;
			
			//creamos un bucle que se repita hasta que no se puedan capturar más fichas o el tablero este lleno
			while(!partidaFinalizada(tablero, pasanTurno)) {
				//mostramos el mensaje de turno actual cuando mal sea 0
				if(mal==0) {
					System.out.println("\nLe toca a "+tablero.NEGRITAS+tablero.BLUE+turnoActual.getNombre()+tablero.RESET+ ", Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET);
					//imprimimos el tablero
					tablero.imprimirTablero();
				}
				
				//creamos la variable posible que guarda true o false segun el resultado del metodo "movimientoPosible" de la clase capturar
				boolean posible=Capturar.movimientoPosible(tablero,turnoActual);
				
				//si hay movimiento posible entramos en el if, si no pasa turno
				if(posible==true) {
					
					//bucle que pide la casilla de nuevo si el movimiento no es válido
					do {
						//creamos el Scanner
						Scanner leerdatos= new Scanner(System.in);
						//pedimos los datos
						System.out.println("Dime la casilla donde quieres colocar la ficha");
						System.out.print(tablero.NEGRITAS+"Fila: "+tablero.RESET);
						 //creamos la variable para la fila
						 int fila=0;
						 
						//Controlamos la entrada por teclado para que se introduzca el caracter deaseado en la fila
						boolean error;
						//el do-while se repite mientras se meta un valor incorrecto
						do {
							//el try-catch comprueba que se meta el valor correcto
							try {
								fila=leerdatos.nextInt();
								//si se mete un valor correcto ponemos el error a false
								error=false;
								//si el valor es mayor que 7 o menor que 0, salta un error con el throw
								if(fila>=8 || fila < 0) {  
									throw new ArithmeticException("La fila debe ser menor a 8 y mayor o igual a 0");    
								}  
								//este catch controla el error de un numero incorrecto
							} catch (ArithmeticException e) {
								//mostramos el mensaje del throw
								System.out.println(e.getMessage());
								System.out.print("Introduce un valor correcto: ");
								//ponemos el error a true
								error=true;
								//este catch controla el error de un valor no valido
							}catch (InputMismatchException a) {
								System.out.print("Introduce un número: ");
								//ponemos el error a true
								error=true;
								leerdatos.next();//limpiamos el buffer
							}
						} while(error);
						 
						
						//pedimos la columna que es una letra
						 System.out.print(tablero.NEGRITAS+"Columna: "+tablero.RESET);
						 String letra0="a";//inicializamos la variable
						 
						//Controlamos la entrada por teclado para que se introduzca el caracter deaseado con un do-while
							do {
								//el try-catch comprueba que se meta el valor correcto
								try {
									//metemos la letra en la variable letra0
									 letra0=leerdatos.next();
									//si se mete un valor correcto ponemos el error a false
									 error=false;
									 //este while comprueba que no se meta una letra que no sea de la A a la H
									 while(letra0.matches("[I-Z]") || letra0.matches("[i-z]") || letra0.matches("ñ") || letra0.matches("Ñ") || letra0.matches("ç")|| letra0.matches("€")|| letra0.matches("_")) {  
										 System.out.print("Introduce una letra entre la A y la H: ");
										 letra0=leerdatos.next();
									 } 
									//este while comprueba que no se meta un numero
									 while(letra0.matches("[0-9]")) {
										//el do-while hace lo mismo que le while anterior, que no se meta una letra que no sea de la A a la H
										do {
											 System.out.print("No puedes introducir un numero, introduce una letra entre la A y la H: ");
											 letra0=leerdatos.next();
										 } while(letra0.matches("[I-Z]") || letra0.matches("[i-z]") || letra0.matches("ñ") || letra0.matches("Ñ") || letra0.matches("ç")|| letra0.matches("€")|| letra0.matches("_"));
									 }
									//el catch controla el error
								} catch (InputMismatchException e) {
									System.out.print("Introduce una letra: ");
									//ponemos el error a true
									error=true;
									leerdatos.next();//limpiamos el buffer
								} 
							} while(error);

						//sacamos la letra y la pasamos a char
						 char letra1=letra0.charAt(0);
						
						 //////////////////////////////////////////////////////////////////////////////////////////
						//esto sirve para pasar la letra introducida a numero
						int col;
						char letra;
						//pasamos la letra a mayuscula
						letra1=Character.toUpperCase(letra1);
						//este bucle es para ir contando y luego se guarda la posicion en la variable columna que coincida con la letra
					    for (letra = 'A',col=0; letra < letra1 && col < letra; ++letra,++col) {
		
					    }
					    int columna=col;
						////////////////////////////////////////////////////////////////////////////////////////
					    
						//si el movimiento es valido entra en el if
						 if(turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero)) {
							 //capturamos la ficha y cambiamos el turno
							 tablero.capturarFichas(fila, columna, tablero, turnoActual);
							
							 //Si se puede poner la ficha, ponemos la variable "pasanTurno" a 0
							 pasanTurno=0;
							 
							 //cambiamos de turno
							 turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);
							 
							 //ponemos "movimiento" a true para poder salir del do-while
							 movimiento=true;
							 //si el movimiento es correcto, se pone mal 0
							 mal=0;
							 //si el movimiento no es válido entramos en el else
						 } else {
							//mostramos el mensaje
							System.out.println("\n"+tablero.NEGRITAS+tablero.RED+"Movimiento no válido"+tablero.RESET+"\n");

							//pedimos que vuelva a introducir otra casilla
							System.out.println(tablero.RED+"Vuelve a introducir una casilla,"+tablero.RESET+" Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET+"\n");
							//si no es valido el movimiento, se pone mal a 1
							mal=1;
						 }
						 //si se introduce un movimiento no valido, se muestra el mensaje de error y el mensaje y tablero
						 if(mal==1) {
							 System.out.println("\nLe toca a "+tablero.NEGRITAS+tablero.BLUE+turnoActual.getNombre()+tablero.RESET+ ", Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET);
							 tablero.imprimirTablero();
						 }
					}while(movimiento==false);
						
					//si no hay movimiento posible, entramos en el else
				}  else if(posible==false) {
					//mostramos el mensaje
					System.out.println(tablero.NEGRITAS+tablero.RED+"No puedes capturar ficha, cambio de turno"+tablero.RESET+"\n");
					//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
					try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
					//si pasa turno, sumamos 1 a la variable "pasanTurno"
					pasanTurno++;
					//cambiamos de turno
					turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);
				}
				
				//Si finaliza la partida mostramos un mensaje y a continuacion el tablero
				if((partidaFinalizada(tablero, pasanTurno))) {
					System.out.println(tablero.NEGRITAS+tablero.GREEN+"¡¡PARTIDA FINALIZADA!!!"+tablero.RESET);
					tablero.imprimirTablero();
				}
				//ponemos la variable "movimiento" a false para que pueda repetirse correctamente el bucle
				movimiento=false;	
			};
			
			//mostramos el ganador
			tablero.ganador(tablero);
			
			//si la opcion es 2 el tipo de partida es Jugador-Maquina
		} else if(opcion==2) {
			//asignamos el tipo de partida
			tipoPartida=TipoPartida.JugadorMaquina;
			//creamos el jugador1 y el jugador2 (la maquina)
			jugador1= new Jugador("Jugador1",Color.BLANCA , Roles.JUGADOR1);
			jugador2= new Jugador("Máquina",Color.NEGRA , Roles.ORDENADOR);
			
			//llamamos al metodo "elegirNombre"
			elegirNombre(jugador1,jugador2,tipoPartida);
			
			//llamamos al metodo "escogerColor"
			escogerColor(jugador1, jugador2);
			
			//asignamos una dificultad llamando al metodo "escogerDificultad"
			int dificultad=escogerDificultad();
			
			//llamamos al metodo sortear turno para decidir quien empieza
			turnoActual=sortearTurno(jugador1,jugador2);
			//segun el color del turno, mostramos un mensaje u otro
			if(turnoActual.getColorFichas()==Color.BLANCA) {
				System.out.println("Empiezan las blancas");
			} else {
				System.out.println("Empiezan las negras");
			}
			
			//variable que sirve para decir si el movimiento a efectuar es valido
			boolean movimiento=false;
			
			//variable para comprobar si pasan de turno
			int pasanTurno=0;
			//creamos la variable mal para poder controlar cuando mostrar el mensaje
			int mal=0;
			//creamos un bucle que se repita hasta que no se puedan capturar más fichas o el tablero este lleno
			while(!partidaFinalizada(tablero,pasanTurno)) {
				//si le toca al jugador, pedimos la casilla
				if(turnoActual==jugador1) {
					//mostramos el mensaje de turno actual cuando mal sea 0
					if(mal==0) {
						System.out.println("\nLe toca a "+tablero.NEGRITAS+tablero.BLUE+turnoActual.getNombre()+tablero.RESET+ ", Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET);
						//imprimimos el tablero
						tablero.imprimirTablero();
					}
					//creamos la variable posible que guarda true o false segun el resultado del metodo "movimientoPosible" de la clase capturar
					boolean posible=Capturar.movimientoPosible(tablero,turnoActual);
					//si hay movimiento posible entramos en el if, si no pasa turno
					if(posible==true) {
						//bucle que pide la casilla de nuevo si el movimiento no es válido
						do {
							//creamos el Scanner
							Scanner leerdatos= new Scanner(System.in);
							//pedimos los datos
							System.out.println("\nDime la casilla donde quieres colocar la ficha");
							System.out.print(tablero.NEGRITAS+"Fila: "+tablero.RESET);
							//creamos la variable para la fila
							int fila=0;
							//Controlamos la entrada por teclado para que se introduzca el caracter deaseado en la fila
							boolean error;
							//el do-while se repite mientras se meta un valor incorrecto
							do {
								//el try-catch comprueba que se meta el valor correcto
								try {
									fila=leerdatos.nextInt();
									//si se mete un valor correcto ponemos el error a false
									error=false;
									//si el valor es mayor que 7 o menor que 0, salta un error con el throw
									if(fila>=8 || fila < 0) {  
										throw new ArithmeticException("La fila debe ser menor a 8 y mayor o igual a 0");    
									}  
									//este catch controla el error de un numero incorrecto
								} catch (ArithmeticException e) {
									//mostramos el mensaje del throw
									System.out.println(e.getMessage());
									System.out.print("Introduce un valor correcto: ");
									//ponemos el error a true
									error=true;
									//este catch controla el error de un valor no valido
								}catch (InputMismatchException a) {
									System.out.print("Introduce un número: ");
									//ponemos el error a true
									error=true;
									leerdatos.next();//limpiamos el buffer
								}
							} while(error);

							//pedimos la columna que es una letra
							System.out.print(tablero.NEGRITAS+"Columna: "+tablero.RESET);
							String letra0="a";//inicializamos la variable
								
							//Controlamos la entrada por teclado para que se introduzca el caracter deaseado con un do-while
							do {
								//el try-catch comprueba que se meta el valor correcto
								try {
									//metemos la letra en la variable letra0
									 letra0=leerdatos.next();
									//si se mete un valor correcto ponemos el error a false
									 error=false;
									 //este while comprueba que no se meta una letra que no sea de la A a la H
									 while(letra0.matches("[I-Z]") || letra0.matches("[i-z]") || letra0.matches("ñ") || letra0.matches("Ñ") || letra0.matches("ç")|| letra0.matches("€")|| letra0.matches("_")) {  
										 System.out.print("Introduce una letra entre la A y la H: ");
										 letra0=leerdatos.next();
									 } 

									//este while comprueba que no se meta un numero
									 while(letra0.matches("[0-9]")) {
										//el do-while hace lo mismo que le while anterior, que no se meta una letra que no sea de la A a la H
										do {
											 System.out.print("No puedes introducir un numero, introduce una letra entre la A y la H: ");
											 letra0=leerdatos.next();
										 } while(letra0.matches("[I-Z]") || letra0.matches("[i-z]") || letra0.matches("ñ") || letra0.matches("Ñ") || letra0.matches("ç")|| letra0.matches("€")|| letra0.matches("_"));
									 }
									//el catch controla el error
								} catch (InputMismatchException e) {
									System.out.print("Introduce una letra: ");
									//ponemos el error a true
									error=true;
									leerdatos.next();//limpiamos el buffer
								} 
							} while(error);
								
							//sacamos la letra y la pasamos a char
							char letra1=letra0.charAt(0);
							
							//////////////////////////////////////////////////////////////////////////////////////////
							//esto sirve para pasar la letra introducida a numero
							int col;
							char letra;
							//pasamos la letra a mayuscula
							letra1=Character.toUpperCase(letra1);
							//este bucle es para ir contando y luego se guarda la posicion en la variable columna que coincida con la letra
							for (letra = 'A',col=0; letra < letra1 && col < letra; ++letra,++col) {
							}
							int columna=col;
							////////////////////////////////////////////////////////////////////////////////////////
							
							//si el movimiento es valido entra en el if
							if(turnoActual.colocarFicha(turnoActual.getColorFichas(), fila, columna, tablero)) {
								//capturamos la ficha y cambiamos el turno
								tablero.capturarFichas(fila, columna, tablero, turnoActual);				 
								//Si pone pieza, ponemos el contador a 0
								//Si se puede poner la ficha, ponemos la variable "pasanTurno" a 0
								 pasanTurno=0;
								 
								 //cambiamos de turno
								 turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);
								//ponemos "movimiento" a true para poder salir del do-while
								 movimiento=true;
								 //si el movimiento es correcto, se pone mal 0
								 mal=0;
								 //si el movimiento no es válido entramos en el else
							 } else {
								//mostramos el mensaje
								 System.out.println(tablero.NEGRITAS+tablero.RED+"Movimiento no válido\n"+tablero.RESET);
								 
								//pedimos que vuelva a introducir otra casilla
								 System.out.println(tablero.RED+"Vuelve a introducir una casilla,"+tablero.RESET+" Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET+"\n");		 
								 //si no es valido el movimiento, se pone mal a 1
								 mal=1;
							 }
							//si se introduce un movimiento no valido, se muestra el mensaje de error y el mensaje y tablero
							if(mal==1) {
								System.out.println("\nLe toca a "+tablero.NEGRITAS+tablero.BLUE+turnoActual.getNombre()+tablero.RESET+ ", Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET);
								tablero.imprimirTablero();
							}
	 
						}while(movimiento==false);
						//si no hay movimiento posible, entramos en el else
					} else if(posible==false) {
						//mostramos el mensaje
						System.out.println(tablero.NEGRITAS+tablero.RED+"No puedes capturar ficha, cambio de turno"+tablero.RESET+"\n");
						//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
						try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
						//si pasa turno, sumamos 1 a la variable "pasanTurno"
						pasanTurno++;
						//cambiamos de turno
						turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);
					}
					//si el jugador actual es el jugador2 (Maquina), entra en el else if
				} else if(turnoActual==jugador2){
					//Mostramos el mensaje del turno actual
					System.out.println("\nLe toca a "+tablero.NEGRITAS+tablero.BLUE+turnoActual.getNombre()+tablero.RESET+ ", Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET);
					//imprimimos el tablero
					tablero.imprimirTablero();
						
					System.out.println("Colocando ficha....\n");
					//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
					try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
						
					//declaramos la valiable "pasa" para guardar cuando pase de turno
					boolean pasan;
					
					//dependiendo de la dificultad que haya elegido el usuario, llamara a un metodo o a otro
					if(dificultad==1) {
						pasan=Capturar.movimientoMaquina(tablero, turnoActual);
					} else {
						pasan=Capturar.movimientoMaquina2(tablero, turnoActual);
					}
					
					//si la maquina(jugador2) pasa, entra en el if 
					if(pasan==true) {
						//sumamos1 a la variable "pasanTurno"
						pasanTurno++;
						//mostramos el mensaje
						System.out.println(tablero.NEGRITAS+tablero.BLUE+ turnoActual.getNombre()+tablero.RED+ " pasa de turno\n"+tablero.RESET);
						//cambiamos de turno
						turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);
						
						//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
						try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
						//si no pasa de turno, entra en el else if	
					} else if(pasan==false)  {
						//si no pasa turno, capturamos la ficha, pasamos turno y ponemos el contador a 0
						pasanTurno=0;
						turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);		
					}
				}
					
				//Si finaliza la partida mostramos un mensaje y a continuacion el tablero
				if((partidaFinalizada(tablero, pasanTurno))) {
					System.out.println(tablero.NEGRITAS+tablero.GREEN+"¡¡PARTIDA FINALIZADA!!!"+tablero.RESET);
					tablero.imprimirTablero();
				}
		
			};
				
			//mostramos el ganador
			tablero.ganador(tablero);
			//si la opcion es 3 el tipo de partida es Maquina-Maquina	
		}else if(opcion==3) {
			//asignamos el tipo de partida
			tipoPartida=TipoPartida.MaquinaMaquina;
			//creamos el jugador1 y el jugador2 (la maquina)
			jugador1= new Jugador("Maquina1",Color.BLANCA , Roles.ORDENADOR);
			jugador2= new Jugador("Máquina2",Color.NEGRA , Roles.ORDENADOR);
			
			//asignamos una dificultad llamando al metodo "escogerDificultad"
			int dificultad=escogerDificultad();
			
			//llamamos al metodo sortear turno para decidir quien empieza
			turnoActual=sortearTurno(jugador1,jugador2);
			//segun el color del turno, mostramos un mensaje u otro
			if(turnoActual.getColorFichas()==Color.BLANCA) {
				System.out.println("Empiezan las blancas");
			} else {
				System.out.println("Empiezan las negras");
			}
			//imprimimos el tablero
			tablero.imprimirTablero();
			
			
			System.out.println("Colocando ficha....\n");
			//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
			try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
			//variable para comprobar si pasan de turno			
			int pasanTurno=0;
			//creamos un bucle que se repita hasta que no se puedan capturar más fichas o el tablero este lleno
			while(!partidaFinalizada(tablero, pasanTurno)) {
				
				//declaramos la valiable "pasa" para guardar cuando pase de turno
				boolean pasan;
				//dependiendo de la dificultad que haya elegido el usuario, llamara a un metodo o a otro
				if(dificultad==1) {
					pasan=Capturar.movimientoMaquina(tablero, turnoActual);
				} else {
					pasan=Capturar.movimientoMaquina2(tablero, turnoActual);
				}
				
				
				if(pasan==false) {
					//si no pasa turno ponemos el contador a 0
					if(pasanTurno>0) {
						pasanTurno=pasanTurno-1;
					}
					
					//cambiamos de turno
					turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);
					//si pasa de turno, entra en el else if	
				} else if(pasan==true) {
					//sumamos 1 a la variable "pasanTurno"
					pasanTurno=pasanTurno+1;
					//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
					try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
					//mostramos el mensaje
					System.out.println(tablero.RED+tablero.NEGRITAS+ turnoActual.getNombre()+ " pasa de turno\n"+tablero.RESET);
					//cambiamos de turno
					turnoActual=cambiarTurno(turnoActual, jugador1, jugador2);
				}

				//Si no finaliza la partida entra en el if
				if((!partidaFinalizada(tablero, pasanTurno))) {
					//mostramos el mensaje
					System.out.println("Le toca a "+tablero.NEGRITAS+tablero.BLUE+turnoActual.getNombre()+tablero.RESET+ ", Color de la ficha: "+tablero.NEGRITAS+turnoActual.getColorFichas()+tablero.RESET);
					//imprimimos el tablero
					tablero.imprimirTablero();
					
					System.out.println("Colocando ficha....\n");
					//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
					try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
					
					//si la partida finaliza entra en el else
				} else {
					//mostramos el mensaje
					System.out.println(tablero.NEGRITAS+tablero.GREEN+"¡¡PARTIDA FINALIZADA!!!"+tablero.RESET);
					//imprimimos el tablero
					tablero.imprimirTablero();
				}
			};
			
			//mostramos el ganador
			tablero.ganador(tablero);
			
		}
	}
	
	
	
	//metodo para sortear turno para ver que jugador empieza
	public static Jugador sortearTurno(Jugador jugador1,Jugador jugador2) {
		//creamos un atributo numero que es de tipo random
		Random numero= new Random();
		
		//creamos el atributo turno
		byte turno;
		
		//hacemos que salga un numero aleatorio entro 1 y 2 y se guarda en turno
		turno=(byte) (numero.nextInt(2)+1);	
		
		//si sale 1, empieza el jugador 1 y si sale 2 empieza el jugador 2
		if(turno==1) {
			return jugador1;
		}
		
		return jugador2;
		
	}
	
	
	
	//metodo de cambiar turno
	public static Jugador cambiarTurno(Jugador turnoActual,Jugador jugador1, Jugador jugador2) {
		
		//si el turno actual es del jugador 1, se cambia al jugador 2 y viceversa
		if(turnoActual==jugador1) {
			return turnoActual=jugador2;
		}
			return turnoActual=jugador1;
	}
	
	
	
	//metodo de partida finalizada que comprueba si el tablero esta lleno o si los dos jugadores han pasado turno y no han capturado ninguna ficha
	public static boolean partidaFinalizada(Tablero tablero,int pasanTurno) {
		
		//creamos el atributo "fin" y lo iniciamos en false
		boolean fin=false;
		
		//creamos la variable contador para contar las casillas libres del tablero, se inicializa en 0
		int contador=0;
		
		//Recorremos el tablero y comprobamos si hay hueco, si es así se suma 1 a contador
		for(int fila=0; fila<tablero.getFilas();fila++) {
			for(int col=0;col<tablero.getColumnas();col++) {
				if(tablero.casillas[fila][col]!=Color.VACIO) {
					contador++;
				}
			}
		}
		
		//sacamos todas las casillas del tablero
		int total=tablero.getFilas()*tablero.getColumnas();
		
		//si el contador no es igual que el total de las casillas del tablero y han pasado los dos jugadores, se termina la partida	
		if(contador!=total && pasanTurno==2) {
			fin= true;
			
			//si el contador es igual que el total de las casillas del tablero, se finaliza la partida
		}else if(contador==total){
			fin=true;
		}
		
		//devolvemos el valor de fin
		return fin;
		
	}
	
	
	//Metodo para que el jugador que empieza escoja el color que quiere
	public static void escogerColor(Jugador jugador1, Jugador jugador2) {
		//Creamos el Scanner
		Scanner leerDatos= new Scanner(System.in);
		
		//creamos la variable color donde se guardará el color que escriba el usuario
		String color;
		
		//esta variable sirve para el bucle do-while que se repite mientras "error" sea verdadero
		boolean error;
		
		//mostramos el texto
		System.out.println("\n"+Tablero.NEGRITAS+Tablero.BLUE+jugador1.getNombre()+Tablero.RESET+", que color de ficha quieres, blancas o negras??");
		
		//aquí comienza el bucle do-while que se repite mientras el valor metido sea incorrecto
		do {
			//el try-catch es para controlar el error
			try {
				//ponemos el error a false ya que se supone que se mete un valor correcto
				error=false;
				
				//pedimos el dato
				System.out.print("Introduce el color de las fichas: ");
				color=leerDatos.nextLine();
				
				//Creamos un bucle while que se repita si se introduce un color incorrecto
				while(!color.matches("^(BLANCAS|NEGRAS|Blancas|Negras|blancas|negras)$")) {
					System.out.print("Escoge un color de entre los posibles: ");
					color=leerDatos.next();
				}
				
				//si el jugador1 escoge las blancas, al jugador2 se le pone las negras y viceversa
				if(color.matches("^(BLANCAS|Blancas|blancas)$")) {
					jugador1.setColorFichas(Color.BLANCA);
					jugador2.setColorFichas(Color.NEGRA);
					System.out.println("Tu color de fichas es"+Tablero.NEGRITAS+" BLANCA\n"+Tablero.RESET);
					
				} else {
					jugador1.setColorFichas(Color.NEGRA);
					jugador2.setColorFichas(Color.BLANCA);
					System.out.println("Tu color de fichas es"+Tablero.NEGRITAS+" NEGRA\n"+Tablero.RESET);
				}
				
				//el catch controla que no se meta un valor incorrecto
			} catch (Exception e) {
				//si se mete un valor incorrecto se pone el error a true y se repite el bucle
	     		error=true;
	     		leerDatos.next();//limpiamos el buffer
			}
		} while(error);
	}
	
	//Metodo para que el jugador elija el nombre con el que se quiera llamar
	public static void elegirNombre(Jugador jugador1,Jugador jugador2,TipoPartida tipoPartida) {
		//creamos el Scanner
		Scanner leerDatos= new Scanner(System.in);
		
		//esta variable sirve para el bucle do-while que se repite mientras "error" sea verdadero
		 boolean error;
		 
		 //creamos las variables opcion y opcion2 para las dos preguntas que se realizan, inicializamos la opcion2 a 0 ya qu enos hace falta
		 //para el do-while de la segunda pregunta
	     int opcion,opcion2=0;
	     
	     //creamos por defecto los nombres de los jugadores
	     String nombreJugador1 = "Jugador1";
	     String nombreJugador2 = "Jugador2";
	   
	   //Si el modo de partida es jugador-jugador pedimos los dos nombres
	   if(tipoPartida==TipoPartida.JugadorJugador) {
		   
		 //Preguntamos por el nombre para el jugador1  
		    System.out.println("\n¿Quieres elegir el nombre del jugador1 para la partida?");
			System.out.println("1) Si");
			System.out.println("2) No");
			 //El bucle do-while controla que se meta el valor corecto, se repite mientras error sea true
		    do {
		    	
		    	//el try controla los errores al meter el valor incorrecto
		     	try {
		     		//ponemos la variable error a false ya que se supone que se mete un valor correcto
		     		error=false;
		     		
		     		//este do-while se repite mientras el valor sea distinto de 1 o 2
		     		do {
		     			System.out.print("Opción: ");
		     			opcion= leerDatos.nextInt();
		     			
		     			//creamos un switch para las opciones disponibles
		     			switch (opcion){
		     			
		     			//si elige la opcion1, se le pide que escriba un nombre
		     			case 1:
		     				//este do-while es para la segunda pregunta, se repite mientras la opcion2 sea igual a 2
		     				do {
		     					//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
		     					error=false;
		     					//pedimos el nombre
		     					System.out.print("\nEspecifica el nombre: ");	
		     					nombreJugador1=leerDatos.next();
		     					
		     					//preguntamos si está seguro de llamarse así
		     					System.out.println("\n¿Estás seguro de llamarte "+Tablero.NEGRITAS+Tablero.BLUE+nombreJugador1+Tablero.RESET+"?");
		     					System.out.println("1) Si");
		     					System.out.println("2) No");
		     					
		     					//este do-while controla que se metan valores correctos en la segunda pregunta
		     					do {
		     						//el try-catch controla que se meta el valor correcto
		     						try {
		     							//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
		     							error=false;
		     							//este do-while se repite mientras la opcion no sea 1 o 2
		     							do {
		     								System.out.print("Opción: ");
		     								opcion2= leerDatos.nextInt();
		     								
		     								//mostramos mensaje si no es 1 o 2
		     								if(opcion2>2 || opcion2<1) {
		     									System.out.println("Introduce 1 o 2");
		     								}
		     								
		    			     			}while(opcion2>2 || opcion2<1);
		     							//el catch controla el error
		     						} catch (Exception e) {
		     							System.out.println("Introduce una opción válida");
		     							//ponemos el valor de error a true ya que se a metido un valor incorrecto
		    				     		error=true;
		    				     		leerDatos.next();//limpìamos el bufer
		     						}
		    				     	
		     					} while (error);
		     				}while(opcion2==2);
		     				
		     				//una vez especificado el nombre, se le asigna al jugador 1
		     				jugador1.setNombre(nombreJugador1);
		     				System.out.println(Tablero.NEGRITAS+Tablero.BLUE+nombreJugador1+Tablero.RESET+" preparado para ganar\n");
		     				//salimos del siwtch
		     				break;
		     				
		     			//si la opcion es la 2, no quiere elegir nombre por lo que se le asigna uno por defecto
		     			case 2:
		     				jugador1.setNombre("Jugador1");
		     				System.out.println(Tablero.NEGRITAS+Tablero.BLUE+nombreJugador1+Tablero.RESET+" preparado para ganar\n");
		     				//salimos del switch
		     				break;
		     			}
		     			//mostramos mensaje si no es 1 o 2
						if(opcion2>2 || opcion2<1) {
							System.out.println("Introduce 1 o 2");
						}
		     		}while(opcion>2 || opcion<1);
		     		//el catch controla el error
		     	} catch (Exception e) {
		     		System.out.println("Introduce una opción válida");
		     		//ponemos el valor de error a true ya que se a metido un valor incorrecto
		     		error=true;
		     		leerDatos.next();//limpiamos el buffer
		     	}
		     	
		    } while (error);
		    
		    
		    //Pedimos el nombre para el jugador2
		    System.out.println("¿Quieres elegir el nombre del jugador2 para la partida?");
			System.out.println("1) Si");
			System.out.println("2) No");

			//este do-while controla que se metan valores correctos en la segunda pregunta
		    do {
		    	//el try-catch controla que se meta el valor correcto
		     	try {
		     		//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
					error=false;
					//este do-while se repite mientras la opcion no sea 1 o 2
		     		do {
		     			//pedimos la opcion
		     			System.out.print("Opción: ");
		     			opcion= leerDatos.nextInt();
		     			
		     			//creamos un switch para las opciones disponibles
		     			switch (opcion){
		     				//si elige la opcion1, se le pide que escriba un nombre
		     				case 1:
		     					//este do-while es para la segunda pregunta, se repite mientras la opcion2 sea igual a 2
		     					do {
		     						//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
			     					error=false;
			     					//pedimos el nombre
			     					System.out.print("\nEspecifica el nombre: ");	
		     						nombreJugador2=leerDatos.next();
		     						
		     						//preguntamos si está seguro de llamarse así
		     						System.out.println("\n¿Estás seguro de llamarte "+Tablero.NEGRITAS+Tablero.BLUE+nombreJugador2+Tablero.RESET+"?");
		     						System.out.println("1) Si");
		     						System.out.println("2) No");
		     						//este do-while controla que se metan valores correctos en la segunda pregunta
			     					do {
			     						//el try-catch controla que se meta el valor correcto
		     							try {
		     								//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
			     							error=false;
			     							//este do-while se repite mientras la opcion no sea 1 o 2
		     								do {
		     									System.out.print("Opción: ");
		     									opcion2= leerDatos.nextInt();
		     									
		     									//mostramos mensaje si no es 1 o 2
			     								if(opcion2>2 || opcion2<1) {
			     									System.out.println("Introduce 1 o 2");
			     								}
		     								}while(opcion2>2 || opcion2<1);
		     								//el catch controla el error
		     							} catch (Exception e) {
		     								System.out.println("Introduce una opción válida");
		     								//ponemos el valor de error a true ya que se a metido un valor incorrecto
			    				     		error=true;
			    				     		leerDatos.next();//limpìamos el bufer
		     							}
		    				     	
		     						} while (error);
		     					}while(opcion2==2);	
		     					//una vez especificado el nombre, se le asigna al jugador 2
		     					jugador2.setNombre(nombreJugador2);
		     					System.out.println(Tablero.NEGRITAS+Tablero.BLUE+nombreJugador2+Tablero.RESET+" preparado para ganar\n");
		     					//salimos del switch
		     					break;
		     					
		     				//si la opcion es la 2, no quiere elegir nombre por lo que se le asigna uno por defecto
		     				case 2:
		     					jugador2.setNombre("Jugador2");
		     					System.out.println(Tablero.NEGRITAS+Tablero.BLUE+nombreJugador2+Tablero.RESET+" preparado para ganar\n");
		     					//salimos del switch
		     					break;
		     			};	
		     			//mostramos mensaje si no es 1 o 2
						if(opcion>2 || opcion<1) {
							System.out.println("Introduce 1 o 2");
						}
		     		}while(opcion>2 || opcion<1);
		     		//el catch controla el error
		     	} catch (Exception e) {
		     		System.out.println("Introduce una opción válida");
		     		//ponemos el valor de error a true ya que se a metido un valor incorrecto
		     		error=true;
		     		leerDatos.next();//limpìamos el bufer
		     	}
		     	
		    } while (error);
	   }
	   
	   //Si el modo de partida es jugador-Maquina pedimos el nombre del primer jugador
	   if(tipoPartida==TipoPartida.JugadorMaquina) {
		 //Pedimos el nombre para el jugador1  
		    System.out.println("\n¿Quieres elegir el nombre del jugador1 para la partida?");
			System.out.println("1) Si");
			System.out.println("2) No");
			//este do-while controla que se metan valores correctos en la segunda pregunta
		    do {
		    	//el try-catch controla que se meta el valor correcto
		     	try {
		     		//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
					error=false;
					//este do-while se repite mientras la opcion no sea 1 o 2
					do {
						//pedimos la opcion
						System.out.print("Opción: ");
		     			opcion= leerDatos.nextInt();
		     			
		     			//creamos un switch para las opciones disponibles
		     			switch (opcion){
		     				//si elige la opcion1, se le pide que escriba un nombre
		     				case 1:
			     				//este do-while es para la segunda pregunta, se repite mientras la opcion2 sea igual a 2
			     				do {
			     					//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
			     					error=false;
			     					//pedimos el nombre
			     					System.out.print("\nEspecifica el nombre: ");	
			     					nombreJugador1=leerDatos.next();
			     					
			     					//preguntamos si está seguro de llamarse así
			     					System.out.println("\n¿Estás seguro de llamarte "+Tablero.NEGRITAS+Tablero.BLUE+nombreJugador1+Tablero.RESET+"?");
			     					System.out.println("1) Si");
			     					System.out.println("2) No");
			     					//este do-while controla que se metan valores correctos en la segunda pregunta
			     					do {
			     						//el try-catch controla que se meta el valor correcto
		     							try {
		     								//se pone el valor de error a false ya que se supone que se va a meter un valor correcto
			     							error=false;
			     							//este do-while se repite mientras la opcion no sea 1 o 2
			     							do {
			     								System.out.print("Opción: ");
			     								opcion2= leerDatos.nextInt();
			     								//mostramos mensaje si no es 1 o 2
			     								if(opcion2>2 || opcion2<1) {
			     									System.out.println("Introduce 1 o 2");
			     								}
			    			     			}while(opcion2>2 || opcion2<1);
			     							//el catch controla el error
			     						} catch (Exception e) {
			     							System.out.println("Introduce una opción válida");
			     							//ponemos el valor de error a true ya que se a metido un valor incorrecto
			    				     		error=true;
			    				     		leerDatos.next();//limpìamos el bufer
			     						}
			    				     	
			     					} while (error);
			     				}while(opcion2==2);
			     			
			     				//una vez especificado el nombre, se le asigna al jugador 1
			     				jugador1.setNombre(nombreJugador1);
			     				System.out.println(Tablero.NEGRITAS+Tablero.BLUE+nombreJugador1+Tablero.RESET+" preparado para ganar\n");
			     				//salimos del switch
			     				break;
			     				
			     			//si la opcion es la 2, no quiere elegir nombre por lo que se le asigna uno por defecto
			     			case 2:
			     				jugador1.setNombre("Jugador1");
			     				System.out.println(Tablero.NEGRITAS+Tablero.BLUE+nombreJugador1+Tablero.RESET+" preparado para ganar\n");
			     			
			     				//este codigo hace que tarde 1s en ejecutar el codigo, se mide en milisegundos
			     				try{ Thread.sleep(1000); } catch(InterruptedException e ) { System.out.println("Thread Interrupted"); }
			     				
			     				//mostramos el mensaje de la máquina
			     				System.out.println("La "+Tablero.NEGRITAS+Tablero.BLUE+jugador2.getNombre()+Tablero.RESET+" está preparada para ganar\n");
			     				
			     				//salimos del switch
			     				break;
		     			}
		     			//mostramos mensaje si no es 1 o 2
							if(opcion>2 || opcion<1) {
								System.out.println("Introduce 1 o 2");
							}
		     		}while(opcion>2 || opcion<1);
					//el catch controla el error
		     	} catch (Exception e) {
		     		System.out.println("Introduce una opción válida");
		     		//ponemos el valor de error a true ya que se a metido un valor incorrecto
		     		error=true;
		     		leerDatos.next();//limpìamos el bufer
		     	}
		     	
		    } while (error);
	   }
	}
}
