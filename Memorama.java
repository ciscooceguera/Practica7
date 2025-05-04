import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Memorama extends JFrame{
    private JTextArea puntosJugadores, cartasVolteadas;
    private ArrayList<JButton> tarjetas;
    private int jugadores, puntosMaximos;
    private String figura;
    private ArrayList<JButton> tarjetasVolteadas;

    public Memorama(int numJugadores, int puntuacionMaxima, String figura) {
        super("Memorama");
        tarjetas = new ArrayList<>();
        this.jugadores = numJugadores;
        this.puntosMaximos = puntuacionMaxima;
        this.figura = figura;
        setLocationRelativeTo(null);
        inicializarComponentes();
        configurarVentana();
        this.setLocationRelativeTo(null);
        tarjetasVolteadas = new ArrayList<>();
    }
    public void iniciarMazo(){
        switch(figura){
            case "Carta":
                for (int i = 0; i < 16; i++) {

                }
        }
    }
    public void inicializarComponentes(){
        puntosJugadores = new JTextArea(20,20);
        puntosJugadores.setEditable(false);
        cartasVolteadas = new JTextArea(20,20);
        cartasVolteadas.setEditable(false);
        Mazo mazo = new Mazo(figura);
        mazo.iniciarMazo();
        mazo.revolverMazo();
        ArrayList<ImageIcon> imagenes = mazo.getIconos();
        for(int i=0; i<16; i++){
            JButton b = new JButton();
            b.setSize(150,150);
            b.setBackground(new Color(255, 152, 18));

            final int index = i;
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    b.setIcon(imagenes.get(index));
                    b.setDisabledIcon(imagenes.get(index));
                    b.setEnabled(false);
                }
            });
            tarjetas.add(b);
        }
        System.out.println(tarjetas.size());
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
        this.add(tablero, BorderLayout.NORTH);
        this.add(textos, BorderLayout.SOUTH);
        this.setSize(750, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}