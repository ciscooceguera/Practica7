import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Memorama");
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);

        ImageIcon pic = new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\Practica7\\ImageMain.png");
        JLabel image = new JLabel(pic);
        image.setBounds(0,0,500,500);
        ventana.setContentPane(image);

        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        panel.setBounds(0,0,500,500);

        JButton jugar = new JButton("Jugar");
        JButton creditos = new JButton("Creditos");
        JButton salir = new JButton("Salir");

        jugar.setBounds(100,300,100,50);
        creditos.setBounds(200,300,100,50);
        salir.setBounds(300,300,100,50);

        jugar.setBackground(new Color(70,130,180));
        jugar.setForeground(Color.WHITE);
        jugar.setFocusPainted(false);
        jugar.setFont(new Font("Arial", Font.BOLD,15));
        jugar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        creditos.setBackground(new Color(70,130,180));
        creditos.setForeground(Color.WHITE);
        creditos.setFocusPainted(false);
        creditos.setFont(new Font("Arial", Font.BOLD,15));
        creditos.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        salir.setBackground(new Color(70,130,180));
        salir.setForeground(Color.WHITE);
        salir.setFocusPainted(false);
        salir.setFont(new Font("Arial", Font.BOLD,15));
        salir.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

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



        ventana.add(jugar);
        ventana.add(creditos);
        ventana.add(salir);
        ventana.add(panel);

        ventana.setVisible(true);

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
