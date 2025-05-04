package Carta;

import javax.swing.*;

public class Bandera extends Carta {
    private JLabel imagen;
    private String valor;
    private boolean estaVolteada;
    public Bandera(JLabel imagen, String valor){
        this.imagen = imagen;
        this.valor = valor;
        estaVolteada = false;
    }
    public JLabel getImagen(){
        return imagen;
    }
    @Override
    public boolean cartaEsIgualAOtraCarta(Carta carta){
        return false;
    }
}
