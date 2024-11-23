/**
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.Collections;
import java.util.Stack;

public class Baraja {

    private Stack<Carta> stackBaraja;

    /**
     * Crear la baraja de 104 cartas. Estas cartas se encuentran numeradas (del 1 al 104) 
     * y son únicas (no hay cartas repetidas). Además, cada carta posee un número de 
     * cabezas de buey que representan el número de puntos negativos (bueyes).
    */
    public Baraja() {
        stackBaraja = new Stack<Carta>();
        for (int i = 1; i <= 104; i++) {
            this.stackBaraja.push(new Carta(i));
        }
    }
    
    public void insertarEnBaraja(Carta c){
        this.stackBaraja.add(c);
    }

    /**
     * Una vez que ya se dispone de la baraja de cartas y los/as jugadores/as, 
     * la siguiente tarea es barajar y repartir 10 cartas entre los/as jugadores/as.
     */
    
    /**
     * Baraja las cartas dentro de la propia baraja.
     * Si la baraja está vacía, lanza la excepción correspondiente.
     */
    public void barajar() {
        //preguntar si  hace falta la excepcion
        // fuera el error de nulo
        Collections.shuffle(stackBaraja);

    }

    /**
     * Retira una carta del stack empleado.
     * Si la baraja está vacía, lanza la excepción correspondiente.
     */
    public Carta quitarCarta() {
        if (this.stackBaraja.isEmpty()) {
            throw new NullPointerException("La baraja esta vacia");
        } else {
            return this.stackBaraja.pop();
        }

    }

    
}
