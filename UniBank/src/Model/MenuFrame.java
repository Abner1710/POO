package Model;

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
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));

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
    }
}
