/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.LinkedList;

public class Mesa {

    private LinkedList<Carta>[] mesa;
    //listas enlazadas para poder usar el metodo getLast()
    private static final int NUM_FILAS = 4;

    /**
     * Crear la mesa de juego, inicialmente vacía.
     */
    public Mesa() {
        this.mesa = new LinkedList[NUM_FILAS];

        //Colocar 4 cartas en la mesa para el inicio de la partida.
        for (int i = 0; i < NUM_FILAS; i++) {
            this.mesa[i] = new LinkedList<>();

        }
    }

    /**
     * Inserta las cartas iniciales en las "filas"(miembros de mesa)
     * @param c carta pasada como parametro la cual se saca de la baraja e 
     * inserta cada fila de la mesa
     * @param fila int pasado como param el cual indica el numero de la fila 
     * en el que se insertará la carta
     */
    public void initFilas(Carta c, int fila) {
        this.mesa[fila].add(c);       

    }

    /**
     * Si la fila seleccionada para colocar la carta ya tiene 5 cartas, se muestra un mensaje indicando que el jugador activo no puede colocar 
     * la carta en la mesa porque la fila que le corresponde está llena, y por lo tanto, la carta se devuelve a la baraja. A continuación, pasa el turno al siguiente jugador.
     * 
     * Si la carta elegida del jugador activo es menor que la última de todas las filas, se muestra un mensaje indicando que dicho jugador no la puede colocar 
     * en la mesa por ser todas las cartas mayores, y por lo tanto, se devuelve a la baraja. A continuación, pasa el turno al siguiente jugador.
     * 
     * Si la fila seleccionada de forma automática para colocar la carta ya tiene 5 cartas, el jugador las retira de la fila, las añade a su montón y coloca 
     * la carta como primera de esa fila en la mesa. A continuación, se pasa el turno al siguiente jugador.
     * 
     * Si la carta elegida del jugador activo es menor que la última carta de todas las filas, el jugador selecciona una fila donde la quiere colocar. 
     * Retira todas las cartas de esa fila, las añade a su montón y coloca su carta como primera de esa fila en la mesa. A continuación, se pasa el turno al siguiente jugador.
     */
    
    /**
     * Comprueba si se puede añadir una carta a alguna fila
     *
     * @param cartaInsertar carta que se comprobará si puede ser insertada en
     * alguna fila
     * @return devuelve una clase vinculo(conjunto de dos elementos), en este
     * caso elemento1 = int, elemento2 = boolean
     * fila a la q se va puede insertar la carta
     */
    public Vinculo<Integer, Boolean> comprobarAddCarta(Carta cartaInsertar) {
        int nFila = -1, diferencia = 104;
        boolean isFilaFull = false;
        for (int i = 0; i < NUM_FILAS; i++) {
            if (cartaInsertar.getNumCarta() > mesa[i].getLast().getNumCarta()
                    && (cartaInsertar.getNumCarta() - mesa[i].getLast().getNumCarta()) < diferencia) {
                diferencia = cartaInsertar.getNumCarta() - mesa[i].getLast().getNumCarta();
                nFila = i;

            }
        }

        //replantear si mantener la condicion de fila llena como >=5 o pasarla a ==5
        if (nFila >= 0 && nFila < mesa.length && mesa[nFila].size() >= 5) {
            isFilaFull = true;

        }
        return new Vinculo<>(nFila, isFilaFull);
    }

    /**
     * Inserta las cartas en las "filas"(miembros de mesa) que le
     * corresponda
     *
     * @param nFila indice de la fila en la que se añadira la carta c
     * @param c carta que ha de ser añadida
     */
    public void addCarta(int nFila, Carta c) {
        this.mesa[nFila].add(c);
    }

    /**
     * Extrae las cartas de una fila para que sean devueltas, el codigo es adaptado
     * a las reglas del juego, de tener que usarse de otra manera se podria 
     * dividr en 2 metodos, borrarFila//vaciarFila y extraerFila//copiarFila
     *
     * @param nFila indice de la fila que se extraera
     * @return Devuelvela la fila especificada
     */
    public ArrayList<Carta> extraerFila(int nFila) {
        ArrayList<Carta> cartasDevolucion = new ArrayList<>();
        while (!(this.mesa[nFila].isEmpty())) {
            cartasDevolucion.add(mesa[nFila].pop());
        }
        return cartasDevolucion;

    }

    /**
     * Reinicia una fila con la carta para ello
     * 
     * @param nFila indica la fila a reiniciar
     * @param nueva la carta que reinicializara la fila
     */
    public void reinitFila(int nFila, Carta nueva) {
        this.mesa[nFila].add(nueva);
    }

    /**
     * Muestra las cartas presentes en la mesa utilizanda el metodo .toString()
     * de la clase carta
     */
    public void mostrarMesa() {
        System.out.println("\nMESA");

        for (int i = 0; i < NUM_FILAS; i++) {
            System.out.println("\n------------------------------------------------");
            System.out.print("F" + (i + 1) + "    ");

            for (Carta carta : mesa[i]) {
                System.out.print(carta.toString());
            }

        }
        System.out.println("\n------------------------------------------------");

    }

}
