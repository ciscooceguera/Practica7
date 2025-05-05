package Carta;

import javax.swing.*;

public class RazaDeDragonBall extends Carta {
    private String raza,nombre;
    public RazaDeDragonBall(String raza, String nombre){
        this.raza = raza;
        this.nombre = nombre;
    }
    @Override
    public boolean esIgual(Carta otraCarta){
        String raza2 = ((RazaDeDragonBall)otraCarta).raza;
        return raza.equals(raza2);
    }
    @Override
    public Icon obtenerIcono(){
        return new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\RazaDragonBall\\"+raza+nombre+".png");
    }
}
