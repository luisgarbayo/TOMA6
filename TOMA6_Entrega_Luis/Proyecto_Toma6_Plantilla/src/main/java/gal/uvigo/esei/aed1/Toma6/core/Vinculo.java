/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed1.Toma6.core;

/**
 *
 * Esta clase se utiliza para establecer relacion entre 2 parametros de cualquier tipo
 * 
 * @param <E>
 * @param <T>
 */
public class Vinculo<E,T> {
    private E elemento1;
    private T elemento2;

    public Vinculo(E elemento1, T elemento2) {
        this.elemento1 = elemento1;
        this.elemento2 = elemento2;
    }

    public E getElemento1() {
        return elemento1;
    }

    public T getElemento2() {
        return elemento2;
    }
    
    
}
