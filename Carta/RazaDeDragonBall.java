package Carta;

import javax.swing.*;
import java.awt.*;

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
        ImageIcon imagenDB = new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\RazaDragonBall\\"+raza+nombre+".png");
        Image imagen = imagenDB.getImage();
        int alturaNueva = 150;
        int anchoNuevo = 150;
        Image imagenEscalada = imagen.getScaledInstance(alturaNueva, anchoNuevo, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
    @Override
    public String obtenerDescripcion() {
        return raza;
    }
}
