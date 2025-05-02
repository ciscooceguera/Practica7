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
