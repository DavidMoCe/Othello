# 🖤🤍 Juego Othello en Java 🤍🖤

## 🌍 Choose Your Language / Elige tu idioma:
- [English](#english)
- [Español](#español)

---

## English

This project is an implementation of the **Othello** game (also known as Reversi) in **Java**. The game is played in the terminal and features the following game modes:

- **Player vs Player**: Two human players play against each other on the same device.
- **Player vs Machine**: A human player faces off against the machine. The machine has two difficulty levels: easy and hard.
- **Machine vs Machine**: Two machines play against each other.

## 📜 Game Rules

Othello is a strategy game where two players take turns placing pieces of their color (white or black) on a board. The rules are as follows:

1. The board has 8x8 squares.
2. The first player chooses a color: white or black.
3. On each turn, a player must place a piece on an empty square in such a way that at least one piece of the opponent is "captured."
4. Pieces are captured when they are in a horizontal, vertical, or diagonal line and are surrounded by the player's pieces who is placing the new piece.
5. The goal is to have more pieces of your color on the board at the end of the game.
6. The game ends when no more moves are possible for both players or when the board is full.

## 🕹️ Game Modes

### 👫 Player vs Player

This mode allows two players to play against each other on the same device. Each player chooses a color (white or black) at the start of the game, and they can enter their names.

### 🤖 Player vs Machine

In this mode, a human player plays against the machine. The player selects their color at the start and can choose from two difficulty levels:

- **Easy** 😌: The machine plays in a simple manner, making random or non-strategic decisions.
- **Hard** 😈: The machine uses a more complex strategy to make decisions, aiming to maximize its advantage.

### 🧠 Machine vs Machine

This mode allows two instances of the machine to play against each other. This mode is useful for testing and adjusting the AI of the game.

## 🚀 Instructions to Run the Game

1. **Clone the repository**:
   ```bash
      git clone https://github.com/DavidMoCe/othello.git

2. **Compile the code**: Open a terminal in the directory where you cloned the repository and run the following command:
   ```bash
      javac *.java

3. **Run the game**: To start the game, use the following command:
   ```bash
      java Othello
This will start the game in the terminal, where you can choose the game mode, difficulty level, and players' names.

## 🗂️ Project Structure

- **Capturar.java**: Class responsible for managing piece captures during the game.
- **Color.java**: Class that defines the colors of the pieces (white or black).
- **Estados.java**: Class that handles the different game states, such as in progress, finished, etc.
- **Jugador.java**: Class that represents a player (human or machine), including the option to enter names and choose colors.
- **Movimiento.java**: Class that manages the moves made by the players.
- **Partida.java**: Main class that controls the game flow and interaction between the players.
- **Reglas.java**: Class that implements the game rules, such as move validation and piece capturing.
- **Roles.java**: Class that defines the roles of players (player or machine).
- **Tablero.java**: Class that represents the 8x8 game board and related actions.

## 📋 Requirements

- Java 8 or higher.

## 📝 License

This project is licensed under the CC BY-NC 4.0 license. Please see the `LICENSE` file for more details.

## 🌟 Credits

Developed by David Moreno Cerezo.

***

## Español

Este proyecto es una implementación del juego **Othello** (también conocido como Reversi) en **Java**. El juego se juega en la terminal y cuenta con los siguientes modos de juego:

- **Jugador vs Jugador**: Dos jugadores humanos juegan uno contra el otro en el mismo dispositivo.
- **Jugador vs Máquina**: Un jugador humano se enfrenta a la máquina. La máquina tiene dos niveles de dificultad: fácil y difícil.
- **Máquina vs Máquina**: Dos máquinas juegan entre sí.

## 📜 Reglas del Juego

Othello es un juego de estrategia en el que dos jugadores se alternan para colocar fichas de su color (blanco o negro) sobre un tablero. Las reglas son las siguientes:

1. El tablero tiene 8x8 casillas.
2. El primer jugador elige un color: blanco o negro.
3. En cada turno, un jugador debe colocar una ficha en una casilla vacía de manera que se pueda "capturar" al menos una ficha del oponente.
4. Las fichas se capturan cuando están en una línea horizontal, vertical o diagonal y están rodeadas por fichas del jugador que está colocando la nueva ficha.
5. El objetivo es tener más fichas de tu color en el tablero al final del juego.
6. El juego termina cuando no hay más movimientos posibles para ambos jugadores o cuando el tablero está lleno.

## 🕹️ Modos de Juego

### 👫 Jugador vs Jugador

Este modo permite que dos jugadores jueguen entre sí en el mismo dispositivo. Cada jugador debe elegir un color (blanco o negro) al inicio del juego, y podrán ingresar sus nombres. 

### 🤖 Jugador vs Máquina

En este modo, un jugador humano juega contra la máquina. El jugador selecciona su color al inicio y puede elegir entre dos niveles de dificultad:

- **Fácil** 😌: La máquina juega de forma sencilla, tomando decisiones aleatorias o poco estratégicas.
- **Difícil** 😈: La máquina utiliza una estrategia más compleja para tomar decisiones, buscando maximizar su ventaja.

### 🧠 Máquina vs Máquina

Este modo permite que dos instancias de la máquina jueguen entre sí. Este modo es útil para probar y ajustar la inteligencia artificial del juego.

## 🚀 Instrucciones para Ejecutar el Juego

1. **Clona el repositorio**:
   ```bash
      git clone https://github.com/DavidMoCe/othello.git

2. **Compila el código**: Abre una terminal en el directorio donde clonaste el repositorio y ejecuta el siguiente comando:
   ```bash
      javac *.java

3. **Ejecuta el juego**: Para iniciar el juego, usa el siguiente comando:
   ```bash
      java Othello
Esto iniciará el juego en la terminal, donde podrás elegir el modo de juego, el nivel de dificultad y los nombres de los jugadores.

## 🗂️ Estructura del Proyecto

- **Capturar.java**: Clase encargada de gestionar las capturas de fichas durante el juego.
- **Color.java**: Clase que define los colores de las fichas (blanco o negro).
- **Estados.java**: Clase que maneja los diferentes estados del juego, como en curso, finalizado, etc.
- **Jugador.java**: Clase que representa a un jugador (humano o máquina), incluyendo la opción de ingresar nombres y elegir color.
- **Movimiento.java**: Clase que gestiona los movimientos realizados por los jugadores.
- **Partida.java**: Clase principal que controla el flujo del juego y la interacción entre los jugadores.
- **Reglas.java**: Clase que implementa las reglas del juego, como la validación de movimientos y la captura de fichas.
- **Roles.java**: Clase que define los roles de los jugadores (jugador o máquina).
- **Tablero.java**: Clase que representa el tablero de 8x8 y las acciones relacionadas con él.

## 📋 Requisitos

- Java 8 o superior.

## 📝 Licencia

Este proyecto está licenciado bajo la licencia **CC BY-NC 4.0**. Consulta el archivo `LICENSE` para más detalles.

## 🌟 Créditos

Desarrollado por **David Moreno Cerezo**.
