# 🖤🤍 Juego Othello en Java 🤍🖤

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
   git clone https://github.com/tu-usuario/othello-java.git
