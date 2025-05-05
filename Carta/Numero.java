package Carta;

import javax.swing.*;

public class Numero extends Carta {
    private int numero;
    public Numero(int numero) {
        this.numero = numero;
    }
    @Override
    public boolean esIgual(Carta otraCarta){
        boolean esPar;
        // esta carta es par
        if (numero%2 == 0){
            esPar = true;
        }else{
            esPar = false;
        }
        boolean carta2esPar;
        if (((Numero)otraCarta).numero%2 == 0){
            carta2esPar = true;
        }else{
            carta2esPar = false;
        }
        if (esPar == carta2esPar){
            return true;
        }
        return false;
    }
    @Override
    public Icon obtenerIcono(){
        return new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\Numeros\\num"+numero+".png");
    }

    @Override
    public String obtenerDescripcion() {
        return String.valueOf(numero);
    }
}
