package frames;

import javax.swing.*;

import View.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuFrame extends JFrame {

    private JPanel contentPane;

    public MenuFrame() {
        setTitle("Menú de Opciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1062, 597); 

        BackgroundPanel backgroundPanel = new BackgroundPanel("/imagenes/UNIBANK.png");
        backgroundPanel.setLayout(null); 

        // Create the content panel for buttons
        contentPane = new JPanel();
        contentPane.setBounds(5, 115, 400, 400);
        contentPane.setOpaque(false); 
        contentPane.setLayout(new GridLayout(0, 1, 0, 10)); 

        // Add buttons to the content panel
        JButton btnRegistrarEstudiante = new JButton("Registrar Estudiante");
        btnRegistrarEstudiante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(1);
            }
        });
        contentPane.add(btnRegistrarEstudiante);

        JButton btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(2);
            }
        });
        contentPane.add(btnCrearCuenta);

        JButton btnListarCuentas = new JButton("Listar Cuentas");
        btnListarCuentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(3);
            }
        });
        contentPane.add(btnListarCuentas);

        JButton btnBuscarCuenta = new JButton("Buscar Cuenta");
        btnBuscarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(4);
            }
        });
        contentPane.add(btnBuscarCuenta);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(5);
            }
        });
        contentPane.add(btnDepositar);

        JButton btnRetirar = new JButton("Retirar");
        btnRetirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(6);
            }
        });
        contentPane.add(btnRetirar);

        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(7);
            }
        });
        contentPane.add(btnTransferir);

        JButton btnPagarAranceles = new JButton("Pagar Aranceles");
        btnPagarAranceles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(8);
            }
        });
        contentPane.add(btnPagarAranceles);

        JButton btnEliminarCuenta = new JButton("Eliminar Cuenta");
        btnEliminarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(9);
            }
        });
        contentPane.add(btnEliminarCuenta);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.menu(10);
            }
        });
        contentPane.add(btnSalir);

        // Add the content panel to the background panel
        backgroundPanel.add(contentPane);

        // Add the background panel to the frame
        setContentPane(backgroundPanel);
    }
}
