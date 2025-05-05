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
    private HashMap<Integer, Integer> jugadores;
    private int[] cartasVolteadasConteo = new int[4];
    private int jugadores, puntosMaximos, contadorCartasAdivinadas;
    private HashMap<Integer,Integer> numCartasVolteadas;
    private boolean bloqueo = false;
    private JButton salir;

    public Memorama(int numJugadores, int puntuacionMaxima, String figura) {
        super("Memorama");
        tarjetas = new ArrayList<>();
        this.numJugadores = numJugadores;
        this.puntosMaximos = puntuacionMaxima;
        this.figura = figura;
        this.setLocationRelativeTo(null);
        numCartasVolteadas = new HashMap<>();
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
   
        inicializarComponentes();
        configurarVentana();
        setLocationRelativeTo(null);
    }
    public void cambiarTurno(){
        turno = (turno%jugadores)+1;
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
        for (int i = 1; i<= jugadores; i++){
            numCartasVolteadas.put(i,0);
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
    public String puntajesToString(){
        String mensajePuntajes = "";
        for (int i=1; i<= jugadores; i++){
            mensajePuntajes += "Jugador "+ (i) + ": " + numCartasVolteadas.get(i)+"\n";
        }
        return mensajePuntajes;
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
                int numCartasAtinadas = numCartasVolteadas.get(turno);
                numCartasVolteadas.remove(turno);
                numCartasVolteadas.put(turno, numCartasAtinadas+1);
                tarjetasVolteadas.clear();
                cartasVolteadas.setText(puntajesToString());
                contadorCartasAdivinadas+=1;
            }else{
                // retraso
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        b1.setIcon(null);
                        b2.setIcon(null);
                        tarjetasVolteadas.clear();
                        bloqueo = false;
                        cambiarTurno();
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
                break;
            case "Numero":
                cartas.add(new Numero(1));
                cartas.add(new Numero(6));
                cartas.add(new Numero(7));
                cartas.add(new Numero(8));
                cartas.add(new Numero(9));
                cartas.add(new Numero(10));
                cartas.add(new Numero(13));
                cartas.add(new Numero(14));
                cartas.add(new Numero(22));
                cartas.add(new Numero(27));
                cartas.add(new Numero(41));
                cartas.add(new Numero(50));
                cartas.add(new Numero(77));
                cartas.add(new Numero(78));
                cartas.add(new Numero(88));
                cartas.add(new Numero(99));
                break;
            case "Raza de Dragon Ball":
                String[] razas = {"Angel","Buu","DemonioDelFrio","Dios","Humano","Kaio","Namek","Saiyan"};
                String[] nombresPersonajes = {"Vados","Whis","Gordo","Kid","Freezer","KingCold","Bills","Champa","Krillin","Yamcha",
                "Gordo","Shin","Dende","Picollo","Goku","Vegeta"};
                int count = 0;
                for (String raza: razas){
                    cartas.add(new RazaDeDragonBall(raza,nombresPersonajes[count]));
                    count++;
                    cartas.add(new RazaDeDragonBall(raza,nombresPersonajes[count]));
                    count++;
                }
                break;
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
        cartasVolteadas.setText(puntajesToString());
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