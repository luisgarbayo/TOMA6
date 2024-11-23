/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package gal.uvigo.esei.aed1.Toma6.core;

import gal.uvigo.esei.aed1.Toma6.iu.IU;
import java.util.ArrayList;
import java.util.Collection;

public class Juego {

    private final IU iu;
    private Collection<Jugador> jugadores;
    private Baraja baraja;
    private Mesa mesa;

    public Juego(IU iu) {
        this.iu = iu;
        //"crear baraja"
        this.baraja = new Baraja();
        this.jugadores = new ArrayList<>();
        //crear jugadores
        crearJugadores();
        this.baraja.barajar();
        //crear mesa
        this.mesa = new Mesa();

    }

    /**
     * Inicia el juego y lo mantiene en ejecución mientras todos los jugadores
     * tengan cartas en sus manos
     */
    public void jugar() {
        Carta cartaInit;

        this.repartir();

        //INICIALIZAR FILAS DE LA MESA        
        for (int i = 0; i < 4; i++) {
            cartaInit = this.baraja.quitarCarta();
            this.mesa.initFilas(cartaInit, i);
        }

        this.iu.mostrarJugadores(jugadores);

        while (!endRound()) {
            turno();
        }

        if (!this.gameEnded()) {
            this.baraja = new Baraja();
            this.baraja.barajar();
            this.mesa = new Mesa();

            for (Jugador j : this.jugadores) {
                j.reinitMonton();
            }

            this.iu.mostrarMensaje("FIN: Nadie ha ganado, a volver a empezar");
            this.jugar();
        }

    }

    /**
     * Crea los jugadores de la coleccion jugadores utilizando la coleccion de
     * nombres generada por el metodo pedirNombresJugadores()
     */
    private void crearJugadores() {
        Collection<String> nombres = this.iu.pedirNombresJugadores();
        for (int i = 0; i < nombres.size(); i++) {
            jugadores.add(new Jugador(nombres.toArray()[i].toString()));
        }

    }

    /**
     * Al finalizar la ronda (cada jugador ha colocado sus 10 cartas en la mesa), se muestra por pantalla el número de bueyes que lleva acumulado cada jugador.
     */
    
    /**
     * Las cartas en la mesa se van colocando por turnos, comenzando por el jugador 
     * que eligió la carta más pequeña, juegan en orden hasta que sea el turno del 
     * jugador que ha elegido la carta mayor. Cada vez que juega un jugador se muestra el estado de la mesa.
     */
    
    /**
     * Ejecuta las funcionalidades principales que se tienen que ejecutar por
     * cada turno del juego
     */
    public void turno() { //en cada turno cada jugador echa una carta
        this.mostrarBueyes();

        this.mesa.mostrarMesa();
        if (!this.endRound()) { //todos tienen cartas con las que jugar
            ArrayList<Vinculo<Jugador, Carta>> seleccionadas = new ArrayList<>();
            //elemento1 = jugador, elemento2 = carta

            for (Jugador j : this.jugadores) {
                int indiceCarta=-1;
                
                indiceCarta=this.iu.elegirCarta(j);

                this.insertarEnOrden(new Vinculo<>(j, j.devolverCarta(indiceCarta)), seleccionadas);
            }

            // las cartas ya estan seleccionadas, queda añadirlas a la mesa
            this.addCartasToMesa(seleccionadas);
        }

    }

    /**
     * Inserta una carta vinculada al jugador que la elige de forma
     * ordenada(segun los numeros de las cartas) en el array proporcionado
     *
     * @param carJu clase vinculo que contiene un jugador como primer elemento y
     * una carta como segundo elemento
     * @param toOrder array de las cartas que se estan eligiendo, pasado como
     * parametro para realizar las modificaciones dentro de esta funcion sin
     * afectar al turno de forma directa (fragmentar el codigo)
     */
    
//    private void insertarEnOrden(Vinculo<Jugador, Carta> carJu, ArrayList<Vinculo<Jugador, Carta>> toOrder) {
//
//        if (toOrder.isEmpty()) {
//            toOrder.add(carJu);
//        } else {
//            for (int i = 0; i < toOrder.size(); i++) {
//                if (toOrder.get(i).getElemento2().getNumCarta() > carJu.getElemento2().getNumCarta()) {
//                    toOrder.add(i, carJu);
//                    return;
//                }
//
//            }
//            toOrder.add(carJu);
//        }
//
//    }
    
    //METODO CORREGIDO USANDO UN WHILE
    private void insertarEnOrden(Vinculo<Jugador, Carta> carJu, ArrayList<Vinculo<Jugador, Carta>> toOrder) {
        int i = 0;
        boolean added = false;
        if (toOrder.isEmpty()) {
            toOrder.add(carJu);
        } else {
            while (i < toOrder.size() && !added) {
                if (toOrder.get(i).getElemento2().getNumCarta() > carJu.getElemento2().getNumCarta()) {
                    toOrder.add(i, carJu);
                    added = true;
                }
                i++;
            }
            if (!added) {
                toOrder.add(carJu);
            }
        }
    }

    /**
     * La colocación de la carta en la mesa consiste en seleccionar la fila de forma automática, 
     * siguiendo las instrucciones del juego “Cada carta debe ser colocada en 
     * aquella fila cuya última carta sea inferior a la carta a colocar y, además, 
     * la diferencia entre esa última carta de la fila y la carta a colocar sea la menor de todas las posibles”.
     */
    
    /**
     * Inserta una carta vinculada al jugador que la elige de forma
     * ordenada(segun los numeros de las cartas) en el array proporcionado
     *
     * @param seleccionadas array de las cartas ya elegidas, pasado como
     * parametro para realizar las modificaciones dentro de esta funcion sin
     * afectar al turno de forma directa (fragmentar el codigo)
     */
    private void addCartasToMesa(ArrayList<Vinculo<Jugador, Carta>> seleccionadas) {
        for (Vinculo<Jugador, Carta> cartaJugador : seleccionadas) {
            //elem1 = jugador, elem2 = carta a jugar
            Vinculo<Integer, Boolean> fila = this.mesa.comprobarAddCarta(cartaJugador.getElemento2());
            //elem1 = nFila, elem2 = isFilaFull

            // el condicional del if comprueba el booleano que dice si esta llena la fila o no
            if (!fila.getElemento2() && fila.getElemento1() >= 0) {

                //si la fila no esta llena, añadir carta
                this.mesa.addCarta(fila.getElemento1(), cartaJugador.getElemento2());

            } else if (fila.getElemento2() && fila.getElemento1() >= 0) {
                // ^ si la fila esta llena ^
                for (Carta carta : this.mesa.extraerFila(fila.getElemento1())) {
                    cartaJugador.getElemento1().addToMonton(carta); //añade al monton porq elemento 1 es el jugador
                }

                this.mesa.reinitFila(fila.getElemento1(), cartaJugador.getElemento2());

                this.iu.mostrarMensaje(cartaJugador.getElemento1().toString("nombre")
                        + " se lleva la fila " + (fila.getElemento1() + 1) + " porque esta llena ");

            } else if (fila.getElemento1() < 0) {
                // ^si no hay fila a la que añadir la carta^ (todas mayores q carta del jugador)

                int filaElegida = 0;
                this.mesa.mostrarMesa();
                
                filaElegida=this.iu.elegirFila(cartaJugador);
                
                for (Carta carta : this.mesa.extraerFila(filaElegida)) {
                    cartaJugador.getElemento1().addToMonton(carta);
                }

                this.mesa.reinitFila(filaElegida, cartaJugador.getElemento2());

            }
        }

    }

    /**
     * Asigna 10 cartas ordenadas de menor a mayor a la mano de cada jugador,
     * usando la funcion insertar carta de este
     */
    public void repartir() {
        for (Jugador j : this.jugadores) {
            for (int i = 0; i < 10; i++) {
                Carta cartaBaraja = this.baraja.quitarCarta();
                j.insertar(cartaBaraja); //insertar inserta por orden
            }
        }
    }

    /**
     * Se utiliza para indicar el fin de una ronda, entiéndese como ronda cuando todos los jugadores terminan sus cartas pero nadie ha ganado
     *
     * @return Devuelve true si aun hay un jugador con cartas, false en caso
     * contrario
     */
    public boolean endRound() {
        int jQuedan = this.jugadores.size();
        for (Jugador j : this.jugadores) {
            if (j.sinCartas()) {
                jQuedan--;
            }
        }
        if (jQuedan <= 0) {
            this.iu.mostrarMensaje("\n\nFin de la ronda");
            return true;
        }
        return false;

    }

    /**
     * Se utiliza para indicar el fin del juego A mayores utiliza la clase IU
     * para mostrar los ganadores o perdedores
     *
     * @return Devuelve true si hay un jugador con 66 o mas bueyes, false en
     * caso contrario
     */
    private void mostrarBueyes() {

        for (Jugador j : jugadores) {
            this.iu.mostrarMensaje("Bueyes de " + j.toString("nombre") + " : " + j.numBueyesTotales());
        }

    }

    /**
     * La partida termina cuando en la última ronda algún jugador alcanza o sobrepasa los 66 bueyes acumulados. En caso contrario, se repite todo el proceso desde el principio.
     * Al finalizar el juego se mostrará el ganador, que será aquel jugador con el menor número de bueyes acumulados. En caso de empate se mostrarán todos los ganadores.
     * @return si ha terminado o no el juego
     */
    public boolean gameEnded() {
        boolean loser = false;
        int bueyesLoser = 0;

        this.mostrarBueyes();
        
        for (Jugador j : jugadores) {
            if (j.numBueyesTotales() >= 66 && j.numBueyesTotales() > bueyesLoser) {
                loser = true;
                bueyesLoser = j.numBueyesTotales();
            }
        }


        if (loser) {
            int diferencia = bueyesLoser;
            Jugador ganador = null;
            for (Jugador j : this.jugadores) {
                if (j.numBueyesTotales() < diferencia) {
                    ganador = j;
                }
            }
            this.iu.mostrarMensaje("El jugador " + ganador.toString("nombre") + " ha ganado con " + ganador.numBueyesTotales() + " bueyes");

            return true;

        } else {
            return false;
        }

    }

}
