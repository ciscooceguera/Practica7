package Carta;

import javax.swing.*;

public class RazaDeDragonBall extends Carta {
    @Override
    public boolean esIgual(Carta otraCarta){
        if (otraCarta instanceof Numero){
         //   return this.bandera .equals( ((Bandera)otraCarta).bandera);
        }
        return false;
    }
    @Override
    public Icon obtenerIcono(){
        return new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\Banderas\\"+""+".png");
    }
}
