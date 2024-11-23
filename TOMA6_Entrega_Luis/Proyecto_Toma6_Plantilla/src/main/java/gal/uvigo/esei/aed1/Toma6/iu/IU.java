/**
 * Representa la interfaz del juego Toma 6, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.iu;

import gal.uvigo.esei.aed1.Toma6.core.Carta;
import gal.uvigo.esei.aed1.Toma6.core.Jugador;
import gal.uvigo.esei.aed1.Toma6.core.Vinculo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class IU {

    private final Scanner teclado;

    private static final int MIN_JUGADORES = 2;
    private static final int MAX_JUGADORES = 10;

    public IU() {
        teclado = new Scanner(System.in);

    }

    /**
     * Lee un número de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El numero como entero
     */
    public int leeNum(String msg) {
        boolean repite;
        int toret = 0;

        do {
            repite = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Lee un texto de teclado
     *
     * @param msg El mensaje a utilizar
     * @return El texto como String
     */
    public String leeString(String msg) {
        String toret;
        System.out.print(msg);
        toret = teclado.nextLine();
        return toret;
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param msg El mensaje a mostrar
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Preguntar cuántos jugadores/as van a jugar (entre 2 y 10) y, a continuación, 
     * crear los/as jugadores/as. Cada jugador/a se identifica por un nombre que se debe saber en el momento de crearlo.
     */
    
    /**
     * Solicita los nombres de los jugadores por teclado y los almacena en una
     * estructura de datos
     *
     * @return Los datos de los jugadores almacenados en la estructura de datos
     * correspondiente
     */
    public Collection<String> pedirNombresJugadores() {
        Collection<String> jugadores = new ArrayList<>();
        int numJugadores = 0;

        do {
            numJugadores = leeNum("Cuantos jugadores van a participar? (de "+MIN_JUGADORES
                    +" a " + MAX_JUGADORES +" jugadores)"+ "\nIntroduce el numero\n");

            //repite hasta que se introuduzca el numero correcto
            if ((numJugadores < MIN_JUGADORES) || (numJugadores > MAX_JUGADORES)) {
                System.out.println("Numero fuera de rango, introduce de nuevo el numero");
            }

        } while ((numJugadores < MIN_JUGADORES) || (numJugadores > MAX_JUGADORES));

        for (int i = 0; i < numJugadores; i++) {//pide los nombres de los jugadores
            jugadores.add(leeString("Introduce el nombre del jugador " + (i + 1) + ": "));
        }

        return jugadores;
    }

    /**
     * Hecho el reparto, para cada jugador/a se debe mostrar por pantalla su nombre y las cartas que le han tocado para jugar
     */
    
    /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param jugador Jugador para el cual se mostrarán los datos por pantalla
     */
    private void mostrarJugador(Jugador jugador) {
        System.out.println(jugador.toString());
    }

    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    public void mostrarJugadores(Collection<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            this.mostrarJugador(j);
        }
    }

    public int elegirCarta(Jugador j) {
        this.mostrarMensaje("Turno de " + j.toString("nombre"));
        int indiceCarta = -1;

        this.mostrarMensaje("Elige una carta de tu mano (introduce el indice de la carta)\n");
        do {
            this.mostrarMensaje(j.toString("mano"));

            indiceCarta = this.leeNum("");
            --indiceCarta;
            if (indiceCarta < 0 || indiceCarta >= j.numCartasMano()) {
                this.mostrarMensaje("Ese numero esta fuera de rango, vuelve a intentarlo");
            }
        } while (indiceCarta < 0 || indiceCarta >= j.numCartasMano());

        return indiceCarta;
    }

    
    public int elegirFila(Vinculo<Jugador, Carta> cartaJugador) {
        int filaElegida=-1;
        this.mostrarMensaje("Elige una de las filas para llevarte "
                + cartaJugador.getElemento1().toString("nombre") + " (Fx)\n");
        do {
            filaElegida = this.leeNum("");
            --filaElegida;
            if (filaElegida < 0 || filaElegida > 3) {
                this.mostrarMensaje("Esa fila no existe");
            }
        } while (filaElegida < 0 || filaElegida > 3);
        
        return filaElegida;
    }
}
