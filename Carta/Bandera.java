package Carta;

import javax.swing.*;

public class Bandera extends Carta {
    private String bandera;
    private boolean esBandera;
    public Bandera(String bandera, boolean esBandera) {
        this.bandera = bandera;
        this.esBandera = esBandera;
    }
    @Override
    public boolean esIgual(Carta otraCarta){
        if (otraCarta instanceof Bandera){
            return this.bandera .equals( ((Bandera)otraCarta).bandera);
        }
        return false;
    }
    @Override
    public Icon obtenerIcono(){
        if (esBandera) {
            return new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\Banderas\\" + bandera + ".png");
        }else{
            return new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\Banderas\\" + bandera + "Escudo.png");
        }
    }
    @Override
    public String obtenerDescripcion() {
        return bandera;
    }
}
