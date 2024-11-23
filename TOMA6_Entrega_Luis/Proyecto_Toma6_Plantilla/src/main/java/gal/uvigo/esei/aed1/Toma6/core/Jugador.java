/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano (añadir la carta de foma que queden 
 * ordenadas de menor a mayor por su número), convertir a String el objeto Jugador (toString)
 */
package gal.uvigo.esei.aed1.Toma6.core;

import java.util.ArrayList;
import java.util.Stack;

public class Jugador {

    private String nombre;
    private ArrayList<Carta> mano;
    private Stack<Carta> monton;
    private int bueyesTotales;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.monton = new Stack<>();
        this.bueyesTotales = 0;
    }

    public boolean sinCartas() {
        return this.mano.isEmpty();
    }

    public int numCartasMano() {
        return this.mano.size();
    }

    /**
     * Las cartas en la mano del jugador se deben ir insertando de forma que se vayan ordenando de menor a mayor por número.
     */
    
    /**
     * Inserta las cartas en la mano de este jugador de forma ordenada
     *
     * @param cardToAdd: Carta a insertar.
     */
//    public void insertar(Carta cardToAdd) {
//        if (this.mano.isEmpty()) {
//            this.mano.add(cardToAdd);
//        } else {
//            for (int i = 0; i < this.mano.size(); i++) {
//                if (this.mano.get(i).getNumCarta() > cardToAdd.getNumCarta()) {
//                    this.mano.add(i, cardToAdd);
//                    return;
//                }
//
//            }
//            /* el return anterior evita llegar aqui, que seria el caso de una carta mayor que todas 
//            las que se tengan ya en mano*/
//
//            this.mano.add(cardToAdd);
//
//        }
//    }
    
    //METODO CORREGIDO USANDO UN WHILE
    public void insertar(Carta cardToAdd) {
        int i = 0; //posicion carta
        boolean added = false;
        if (this.mano.isEmpty()) {
            this.mano.add(cardToAdd);
        } else {
            while(i<this.mano.size() && !added){
                if(this.mano.get(i).getNumCarta() > cardToAdd.getNumCarta()){ //get para el numero de la carta
                    this.mano.add(i, cardToAdd);
                    added=true;
                }
                i++;
            }
            
            if(!added){
                this.mano.add(cardToAdd);
            }

        }
    }

    /**
     * Proporciona al jugador la selección de la carta a jugar en el turno
     * correspondiente
     *
     * @param index
     * @return Devuelve la carta elegida
     */
    public Carta devolverCarta(int index) {
        return this.mano.remove(index);
    }

    /**
     * Añade una carta al monton de este jugador
     *
     * @param c
     */
    public void addToMonton(Carta c) {
        this.monton.add(c);
    }

    /**
     * Reinicia el monton del jugador, vaciandolo
     */
    public void reinitMonton() {
        int acu = 0;
        for (Carta c : this.monton) {
            acu += c.getBueyes();
        }
        this.bueyesTotales += acu; //almacena el numero de bueyes para tenerlos en cuenta antes de vaciar el monton
        this.monton.clear();
    }

    /**
     * @return Suma del numero de bueyes acumulados mas el obtenido del monton
     * de cartas en el momento
     */
    public int numBueyesTotales() {
        int acu = 0;
        //Este metodo utiliza los puntos de las rondas anteriores
        for (Carta c : this.monton) {
            acu += c.getBueyes();
        }
        return this.bueyesTotales + acu;
    }

   

    /**
     *
     * Esta sobrecarga del metodo toString() de jugador existe para evitar el
     * uso de funciones como ".getNombre" o "mostarMano"
     *
     * @param s string para decidir el modo del metodo toString()
     * @return String compuesto por la mano o por el nombre, o el toString()
     * original en caso de un parametro "no valido"
     */
    public String toString(String s) {
        StringBuilder sb = new StringBuilder();

        if ("nombre".equals(s)) {
            sb.append(nombre);
        } else if ("mano".equals(s)) {
            sb.append("Mano: {");

            for (int i = 0; i < this.mano.size(); i++) {
                sb.append(i + 1);
                sb.append(" - ");
                sb.append(this.mano.get(i).toString());
                sb.append(", ");

            }

            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(" }");

        } else {

            return this.toString();
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ Nombre: ").append(nombre);
        sb.append(", Mano: [");
        for (Carta carta : mano) {
            sb.append(carta.toString());
        }
        sb.append(" ] }");
        return sb.toString();
    }

}
