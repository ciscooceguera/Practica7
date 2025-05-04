import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Memorama");
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        JButton jugar = new JButton("Jugar");
        JButton creditos = new JButton("Creditos");
        JButton salir = new JButton("Salir");

        jugar.setBounds(100,300,100,50);
        creditos.setBounds(200,300,100,50);
        salir.setBounds(300,300,100,50);

        ventana.add(jugar);
        ventana.add(creditos);
        ventana.add(salir);
        ventana.setLayout(null);
        ventana.setVisible(true);

        ImageIcon pic = new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\FondoMain.png");
        JLabel image = new JLabel(pic);
        image.setBounds(0,0,pic.getIconWidth(),pic.getIconHeight());
        ventana.add(image);

        //Boton Jugar

        jugar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               int numJugadores = 0;
               while (numJugadores < 2 || numJugadores > 4) {
                   String numJugadoresStr = JOptionPane.showInputDialog(ventana,
                           "Numero de jugadores",
                           "Jugadores",
                           JOptionPane.QUESTION_MESSAGE);
                   numJugadores = Integer.parseInt(numJugadoresStr);
                   Object[] botones = {"5", "10","25", "Todos"};
                   int modalidadOpcion = JOptionPane.showOptionDialog(ventana,
                           "Elige modalidad",
                           "Modalidad",
                           JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                           null,
                           botones,
                           botones[0]);
                   int puntuacionMaxima = 0;
                   switch (modalidadOpcion) {
                       case 0:
                           puntuacionMaxima = 5;
                           break;
                       case 1:
                           puntuacionMaxima = 10;
                           break;
                       case 2:
                           puntuacionMaxima = 25;
                           break;
                   }
                   Object[] botones2 = {"Bandera", "Numero", "Raza de Dragon Ball"};
                   int figuraMemorama = JOptionPane.showOptionDialog(ventana,
                           "Elige modalidad",
                           "Modalidad",
                           JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                           null,
                           botones2,
                           botones2[0]);
                   String figura = "";
                   switch (figuraMemorama) {
                       case 0:
                           figura = "Bandera";
                           break;
                       case 1:
                           figura = "Numero";
                           break;
                       case 2:
                           figura = "Raza de Dragon Ball";
                           break;
                   }
                   Memorama juego = new Memorama(numJugadores, puntuacionMaxima, figura);
               }
           }
        });

        // boton creditos
        creditos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ventana, "Realizado por: \n" +
                                "Francisco Javier Oceguera Gutierrez" +
                                "\n José Ramón Suffo Peimbert",
                        "Créditos", JOptionPane.DEFAULT_OPTION);
            }
        });
        // boton salir
        salir.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}