package Carta;

import javax.swing.*;

public class Numero extends Carta {
    private int numero;
    public Numero(int numero) {
        this.numero = numero;
    }
    @Override
    public boolean esIgual(Carta otraCarta){
        if (otraCarta instanceof Numero){
            return this.numero == ((Numero)otraCarta).numero;
        }
        return false;
    }
    @Override
    public Icon obtenerIcono(){
        return new ImageIcon("");
    }
}
