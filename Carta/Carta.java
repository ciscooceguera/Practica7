package Carta;
import javax.swing.*;

public abstract class Carta {
    public abstract boolean esIgual(Carta otraCarta);
    public abstract Icon obtenerIcono();
    public abstract String obtenerDescripcion();
}
