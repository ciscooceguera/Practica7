import Carta.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Memorama extends JFrame{
    private JTextArea turnoText, cartasVolteadas;
    private ArrayList<JButton> tarjetas;
    private int numJugadores, puntosMaximos, turno;
    private String figura;
    private ArrayList<JButton> tarjetasVolteadas;
    private HashMap<Integer, Integer> jugadores;
    private int[] cartasVolteadasConteo = new int[4];
    private int contadorCartasAdivinadas;
    private HashMap<Integer,Integer> numCartasVolteadas;
    private boolean bloqueo = false;
    private JButton salir;
    private JButton instrucciones;

    public Memorama(int numJugadores, int puntuacionMaxima, String figura) {
        super("Memorama");
        turno = 1;
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
        setLocationRelativeTo(null);
        turnoText.setText(turnoToString());
        cartasVolteadas.setText(getCartasVolteadas());
        contadorCartasAdivinadas = 0;



    }
    public void cambiarTurno(){
        turno = (turno%numJugadores)+1;
    }
    public void inicializarComponentes(){
        turnoText = new JTextArea(20,20);
        turnoText.setEditable(false);
        cartasVolteadas = new JTextArea(20,20);
        cartasVolteadas.setEditable(false);
        ArrayList<Carta> cartas = generarCartas();
        Collections.shuffle(cartas);
        for(int i=0; i<16; i++){
            JButton b = new JButton();
            b.setSize(150,150);
            b.setBackground(new Color(255, 152, 18));
            b.setFont(new Font("Arial",Font.BOLD,70));
            b.setForeground(Color.WHITE);
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
        for (int i = 1; i<= numJugadores; i++){
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
        instrucciones = new JButton("Instrucciones");
        instrucciones.setPreferredSize(new Dimension(200, 50));
        instrucciones.setBackground(new Color(70,130,180));
        instrucciones.setForeground(Color.WHITE);
        instrucciones.setFocusPainted(false);
        instrucciones.setFont(new Font("Arial",Font.BOLD,15));
        instrucciones.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }
    public String puntajesToString(){
        String mensajePuntajes = "";
        for (int i=1; i<= numJugadores; i++){
            mensajePuntajes += "Jugador "+ (i) + ": " + numCartasVolteadas.get(i)+"\n";
        }
        return mensajePuntajes;
    }
    public void seleccionarCarta(JButton boton){
        if (bloqueo || tarjetasVolteadas.contains(boton)){
            return;
        }
        Carta carta= (Carta) boton.getClientProperty("carta");
        if (figura == "Numero"){
            boton.setText(carta.obtenerDescripcion());
        }else{
            boton.setIcon(carta.obtenerIcono());
        }
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
                contadorCartasAdivinadas+=2;
                if (contadorCartasAdivinadas==16){
                    JOptionPane.showMessageDialog(null,mensajeGanador());
                    salir.setEnabled(true);
                }
            }else{
                cambiarTurno();
                turnoText.setText(turnoToString());

                // retraso
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        b1.setIcon(null);
                        b2.setIcon(null);
                        b1.setText("");
                        b2.setText("");
                        tarjetasVolteadas.clear();
                        bloqueo = false;
                    }
                });
                timer.setRepeats(false);
                timer.start();

            }
        }
    }
    public String mensajeGanador(){
        return "Ha ganado el jugador " + getGanador();
    }
    public String getGanador(){
        Integer posicion = 0;
        Integer max = 0;
        for (int i = 1 ; i<=numJugadores; i++){
            Integer valor = numCartasVolteadas.get(i);
            if (max<valor){
                max = valor;
            }
        }
        for (int i = 1 ; i<=numJugadores; i++){
            Integer valor = numCartasVolteadas.get(i);
            if (valor == max){
                posicion = i;
            }
        }
        return String.valueOf(posicion);
    }
    public String turnoToString(){
        String mensajeTurno = "-> Turno: \n   Jugador "+turno+"\n";
        return mensajeTurno;
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
                // Creando los numeros con random
                HashSet<Integer> numeros = new HashSet<>();
                Random rnd = new Random();
                int countPares = 0;
                int countImpares = 0;
                while (numeros.size()<16){
                    int num = rnd.nextInt(1,100)+1;
                    if (num%2 == 0){
                        if (countPares<8){
                            if (numeros.add(num)){
                                countPares++;
                            }
                        }
                    }else{
                        if (countImpares<8){
                            if (numeros.add(num)){
                                countImpares++;
                            }
                        }
                    }
                }
                for (Integer numero : numeros){
                    cartas.add(new Numero(numero));
                }
                /* Con imagenes
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
                cartas.add(new Numero(99));*/
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
        turnoText.setFont(new Font("Monospaced", Font.BOLD, 30));
        turnoText.setBackground(new Color(0, 255, 255));
        turnoText.setBorder(BorderFactory.createTitledBorder("Puntajes:"));
        cartasVolteadas.setFont(new Font("Monospaced", Font.PLAIN, 16));
        cartasVolteadas.setBackground(new Color(0, 255, 255));
        cartasVolteadas.setBorder(BorderFactory.createTitledBorder("Cartas Volteadas:"));
        cartasVolteadas.setText(puntajesToString());
        textos.add(turnoText);
        textos.add(cartasVolteadas);
        JPanel boton = new JPanel();
        boton.setLayout(new FlowLayout(FlowLayout.CENTER));
        // salir
        salir.addActionListener(e-> {
            this.dispose();
        });
        instrucciones.addActionListener(e->{
            switch(figura){
                case "Bandera":
                    JOptionPane.showMessageDialog(null,
                            "1. El juego se dispone de 16 cartas: 8 banderas y 8 escudos.\n" +
                                    "2. En cada turno, el jugador podrá voltear un máximo de 2 cartas.\n " +
                                    "   Si ambas cartas resultan par podrá repetir turno.\n" +
                                    "3. Se considera un par válido si:\n" +
                                    "   - Logras voltear una bandera y su respectivo escudo.\n" +
                                    "4. Si las cartas no resultan en un par se colocan boca abajo nuevamente.\n" +
                                    "5. El juego termina cuando todos los pares hayan sido encontrados.",
                            "Instrucciones",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Numero":
                    JOptionPane.showMessageDialog(null,
                            "1. El juego se dispone de 16 cartas.\n" +
                                    "2. En cada turno, el jugador podrá voltear un máximo de 2 cartas.\n " +
                                    "   Si ambas cartas resultan par podrá repetir turno.\n" +
                                    "3. Se considera un par válido cuando:\n" +
                                    "   - Ambas cartas volteadas son números par.\n" +
                                    "   - Ambas cartas volteadas son números impar.\n" +
                                    "4. Si las cartas no resultan en un par se colocan boca abajo nuevamente.\n" +
                                    "5. El juego termina cuando todos los pares hayan sido encontrados.",
                            "Instrucciones",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Raza de Dragon Ball":
                    JOptionPane.showMessageDialog(null,
                            "1. El juego se dispone de 16 cartas.\n" +
                                    "2. En cada turno, el jugador podrá voltear un máximo de 2 cartas.\n " +
                                    "   Si ambas cartas resultan par podrá repetir turno.\n" +
                                    "3. Se considera un par válido cuando:\n" +
                                    "   - Ambas cartas poseen personajes pertenecientes al mismo tipo de raza\n" +
                                    "4. Si las cartas no resultan en un par se colocan boca abajo nuevamente.\n" +
                                    "5. El juego termina cuando todos los pares hayan sido encontrados.",
                            "Instrucciones",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
            }

        });
        boton.setPreferredSize(new Dimension(660, 70));
        boton.add(instrucciones);
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


}