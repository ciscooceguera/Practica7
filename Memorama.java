import Carta.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Memorama extends JFrame{
    private JTextArea puntosJugadores, cartasVolteadas;
    private ArrayList<JButton> tarjetas;
    private int numJugadores, puntosMaximos, turno;
    private String figura;
    private ArrayList<JButton> tarjetasVolteadas;
    private JButton primeraCarta = null;
    private JButton segundaCarta = null;
    private int[] indicesCartas = new int[16];
    private HashMap<Integer, Integer> jugadores;
    private int[] cartasVolteadasConteo = new int[4];
    private boolean bloqueo = false;
    private JButton salir;

    public Memorama(int numJugadores, int puntuacionMaxima, String figura) {
        super("Memorama");
        tarjetas = new ArrayList<>();
        this.numJugadores = numJugadores;
        this.puntosMaximos = puntuacionMaxima;
        this.figura = figura;
        this.setLocationRelativeTo(null);
        tarjetasVolteadas = new ArrayList<>();
        jugadores = new HashMap<>();
        inicializarJugadores();
        inicializarComponentes();
        configurarVentana();
        puntosJugadores.setText(getPuntajes());
        cartasVolteadas.setText(getCartasVolteadas());
        setLocationRelativeTo(null);
        turno = 1;
        mostrarTurno();
    }

    public void inicializarComponentes(){
        puntosJugadores = new JTextArea(20,20);
        puntosJugadores.setEditable(false);
        cartasVolteadas = new JTextArea(20,20);
        cartasVolteadas.setEditable(false);
        ArrayList<Carta> cartas = generarCartas();
        Collections.shuffle(cartas);
        for(int i=0; i<16; i++){
            JButton b = new JButton();
            b.setSize(150,150);
            b.setBackground(new Color(255, 152, 18));
            b.putClientProperty("carta",cartas.get(i));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    seleccionarCarta(b);
                }
            });
            b.setEnabled(true);
            tarjetas.add(b);
        }
        System.out.println(tarjetas.size());
        salir = new JButton("Salir");
        salir.setPreferredSize(new Dimension(200, 50));
        salir.setEnabled(false);
        salir.setBackground(new Color(70,130,180));
        salir.setForeground(Color.WHITE);
        salir.setFocusPainted(false);
        salir.setFont(new Font("Arial",Font.BOLD,15));
        salir.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }
    public void seleccionarCarta(JButton boton){
        if (bloqueo || tarjetasVolteadas.contains(boton)){
            return;
        }
        Carta carta= (Carta) boton.getClientProperty("carta");
        boton.setIcon(carta.obtenerIcono());
        tarjetasVolteadas.add(boton);

        if (tarjetasVolteadas.size() == 2 && tarjetasVolteadas.get(0)!=boton){
            bloqueo = true;
            JButton b1 = tarjetasVolteadas.get(0);
            JButton b2 = tarjetasVolteadas.get(1);
            Carta carta1 = (Carta) b1.getClientProperty("carta");
            Carta carta2 = (Carta) b2.getClientProperty("carta");

            if (carta1.esIgual(carta2)) {
                b1.setEnabled(false);
                b2.setEnabled(false);
                bloqueo = false;
                tarjetasVolteadas.clear();
            }else{
                // retraso
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        b1.setIcon(null);
                        b2.setIcon(null);
                        tarjetasVolteadas.clear();
                        bloqueo = false;
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }
    public ArrayList<Carta> generarCartas(){
        ArrayList<Carta> cartas = new ArrayList<>();
        switch(figura){
            case "Bandera":
                ArrayList<String> paises = new ArrayList<>();
                paises.add("Brasil");
                paises.add("Argentina");
                paises.add("Canada");
                paises.add("España");
                paises.add("Inglaterra");
                paises.add("Italia");
                paises.add("Mexico");
                paises.add("USA");
                for(String pais: paises){
                    cartas.add(new Bandera(pais,true));
                    cartas.add(new Bandera(pais,false));
                }
        }
        Collections.shuffle(cartas);
        return cartas;
    }

    public void configurarVentana(){
        this.setLayout(new BorderLayout());
        JPanel tablero = new JPanel();
        tablero.setPreferredSize(new Dimension(660, 660));
        tablero.setLayout(new GridLayout(4,4));
        JPanel textos = new JPanel();
        textos.setLayout(new GridLayout(1,2));
        textos.setPreferredSize(new Dimension(660, 155));
        for(int i=0; i<tarjetas.size(); i++){
            JButton b = tarjetas.get(i);
            tablero.add(b);
        }
        puntosJugadores.setFont(new Font("Monospaced", Font.PLAIN, 16));
        puntosJugadores.setBackground(new Color(0, 255, 255));
        puntosJugadores.setBorder(BorderFactory.createTitledBorder("Puntajes:"));
        cartasVolteadas.setFont(new Font("Monospaced", Font.PLAIN, 16));
        cartasVolteadas.setBackground(new Color(0, 255, 255));
        cartasVolteadas.setBorder(BorderFactory.createTitledBorder("Cartas Volteadas:"));
        textos.add(puntosJugadores);
        textos.add(cartasVolteadas);
        JPanel boton = new JPanel();
        boton.setLayout(new FlowLayout(FlowLayout.CENTER));
        boton.setPreferredSize(new Dimension(660, 70));
        boton.add(salir);
        this.add(tablero, BorderLayout.NORTH);
        this.add(textos, BorderLayout.CENTER);
        this.add(boton, BorderLayout.SOUTH);
        this.setSize(750, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public String getPuntajes(){
        String puntajes = "";
        for(int i=0; i<numJugadores; i++){
            puntajes += "Jugador "+(i+1)+": "+jugadores.get(i)+"\n";
        }
        return puntajes;
    }

    public void inicializarJugadores(){
        for(int i=0; i<numJugadores; i++){
            cartasVolteadasConteo[i] = 0;
            jugadores.put(i,0);
        }
    }

    public String getCartasVolteadas(){
        String cartasVolteadas = "";
        for(int i=0; i<numJugadores; i++){
            cartasVolteadas += "Jugador "+(i+1)+": "+cartasVolteadasConteo[i]+"\n";
        }
        return cartasVolteadas;
    }

    public void mostrarTurno(){
        JOptionPane.showMessageDialog(null,
                "Turno del jugador "+(turno),
                "Turno actual",
                JOptionPane.INFORMATION_MESSAGE);
    }
}