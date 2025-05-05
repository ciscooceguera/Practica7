package Carta;
import javax.swing.*;

public abstract class Carta {
    private boolean volteada = false;
    private boolean encontrada = false;
    public void voltear(){
        volteada = !volteada;
    }
    public boolean estaVolteada(){
        return volteada;
    }
    public void fueEncontrada(){
        encontrada = true;
    }
    public abstract boolean esIgual(Carta otraCarta);
    public abstract Icon obtenerIcono();
}
