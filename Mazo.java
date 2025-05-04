import Carta.Bandera;
import Carta.Carta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private String figura;
    private ArrayList<Carta> cartas;
    private ArrayList<ImageIcon> iconos;
    public Mazo(String figura){
        this.figura = figura;
        cartas = new ArrayList<>();
        iconos = new ArrayList<>();
    }
    public void iniciarMazo(){
        switch (figura){
            case "Bandera":
                ArrayList<String> paises = new ArrayList<>();
                paises.add("Argentina");
                paises.add("Brasil");
                paises.add("Canada");
                paises.add("España");
                paises.add("Inglaterra");
                paises.add("Italia");
                paises.add("Mexico");
                paises.add("USA");
                paises.addAll(paises);
                for (int i = 0; i < 16; i++) {
                    String pais = paises.get(i);
                    ImageIcon iconoBandera = new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\Banderas\\" + pais + ".png");
                    JLabel imagen = new JLabel(iconoBandera);
                    Bandera carta = new Bandera(imagen, pais);
                    iconos.add(iconoBandera);
                    cartas.add(carta);
                }
                break;
        }
    }
    public void revolverMazo(){
        Collections.shuffle(iconos);
    }
    public ArrayList<ImageIcon> getIconos(){
        return iconos;
    }
}
