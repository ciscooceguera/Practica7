import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase main
public class Main {
    // metodo main
    public static void main(String[] args) {
        // ventana
        JFrame ventana = new JFrame("Memorama");
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.setVisible(true);

        // imagen
        ImageIcon pic = new ImageIcon("C:\\Users\\joser\\IdeaProjects\\Practica 7\\ImageMain.png");
        JLabel image = new JLabel(pic);
        image.setBounds(0,0,500,500);
        ventana.setContentPane(image);
        // panel para la imagen
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        panel.setBounds(0,0,500,500);
        // botones
        JButton jugar = new JButton("Jugar");
        JButton creditos = new JButton("Creditos");
        JButton salir = new JButton("Salir");
        // acomodo de botones
        jugar.setBounds(100,300,100,50);
        creditos.setBounds(200,300,100,50);
        salir.setBounds(300,300,100,50);
        // boton jugar
        jugar.setBackground(new Color(70,130,180));// fondo
        jugar.setForeground(Color.WHITE);// color letra
        jugar.setFocusPainted(false);// se quita el cuadrito de la opcion seleccionada en auto
        jugar.setFont(new Font("Arial", Font.BOLD,15)); // tipo y tamano de la letra en el boton
        jugar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // color del borde
        // boton creditos
        creditos.setBackground(new Color(70,130,180));
        creditos.setForeground(Color.WHITE);
        creditos.setFocusPainted(false);
        creditos.setFont(new Font("Arial", Font.BOLD,15));
        creditos.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        // boton salir
        salir.setBackground(new Color(70,130,180));
        salir.setForeground(Color.WHITE);
        salir.setFocusPainted(false);
        salir.setFont(new Font("Arial", Font.BOLD,15));
        salir.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        // efecto para cuando el mouse pase por encima del boton
        jugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jugar.setBackground(new Color(100, 149, 237)); // Cambia a un azul más claro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jugar.setBackground(new Color(70, 130, 180));  // Vuelve al original
            }
        });
        creditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                creditos.setBackground(new Color(100, 149, 237)); // Cambia a un azul más claro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                creditos.setBackground(new Color(70, 130, 180));  // Vuelve al original
            }
        });
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salir.setBackground(new Color(100, 149, 237)); // Cambia a un azul más claro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salir.setBackground(new Color(70, 130, 180));  // Vuelve al original
            }
        });
        // agrego componentes a la ventana
        ventana.add(jugar);
        ventana.add(creditos);
        ventana.add(salir);
        ventana.add(panel);
        // ventana visible
        ventana.setVisible(true);
        // acciones
        // boton jugar
        jugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
                    Object[] botones = {"Bandera", "Numero", "Raza de Dragon Ball"};
                    int figuraMemorama = JOptionPane.showOptionDialog(ventana,
                            "Elige modalidad",
                            "Modalidad",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            botones,
                            botones[0]);
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
                    Memorama juego = new Memorama(numJugadores, figura);
                }
            }
        });
    }
}

