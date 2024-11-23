
/*
 * Representa una carta, formada por un número y la cantidad de bueyes correspondiente
 */
package gal.uvigo.esei.aed1.Toma6.core;

public class Carta {

    private final int numCarta;
    private final int bueyes; //numero de bueyes

    public Carta(int num) {
        this.numCarta = num; //numero de carta (modulo para el numero en el q termina o es multiplo)

        //El número de bueyes de una carta depende del valor de la misma
        if (num % 11 == 0) { //multiplos de 11
            if (num % 5 == 0) { //terminadas en 5 (15,25...)
                this.bueyes = 7; //55
            } else {
                this.bueyes = 5;//multiplos 11
            }
        } else if (num % 10 == 0) {//multiplos 10
            this.bueyes = 3;
        } else if (num % 5 == 0) {
            this.bueyes = 2; //si no es 55, las terminadas en 5 tienen 2 bueyes
        } else {
            this.bueyes = 1; //resto de cartas
        }
        

    }

    public int getNumCarta() {
        return numCarta;
    }

    public int getBueyes() {
        return bueyes;
    }


    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" |_ ").append(String.format("%3s",numCarta )).append(":").append(String.format("%-3s", bueyes)).append("_| ");

        return sb.toString();
    }

}
