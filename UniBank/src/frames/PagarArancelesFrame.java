package frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Banco;
import Model.Cuenta;

public class PagarArancelesFrame extends JFrame {
    private Banco banco;

    public PagarArancelesFrame(Banco banco) {
        this.setBanco(banco);
        setTitle("Pagar Aranceles");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel payFeesPanel = new JPanel();
        payFeesPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel carnetLabel = new JLabel("Número de Carnet:");
        JTextField carnetField = new JTextField();
        JLabel arancelLabel = new JLabel("Seleccione el arancel:");
        String[] aranceles = {"Matricula (200 C$)", "Laboratorio (100 C$)", "Biblioteca (50 C$)"};
        JComboBox<String> arancelComboBox = new JComboBox<>(aranceles);
        JButton pagarButton = new JButton("Pagar");

        payFeesPanel.add(carnetLabel);
        payFeesPanel.add(carnetField);
        payFeesPanel.add(arancelLabel);
        payFeesPanel.add(arancelComboBox);
        payFeesPanel.add(new JLabel());
        payFeesPanel.add(pagarButton);

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String carnet = carnetField.getText();
                    if (banco.existeCuentaCarnet(carnet)) {
                        int numeroCuenta = banco.getCuentaPorCarnet(carnet);
                        Cuenta cuenta = banco.cuentaExiste(numeroCuenta);
                        if (cuenta != null) {
                            int feeOption = arancelComboBox.getSelectedIndex();
                            switch (feeOption) {
                                case 0:
                                    cuenta.pagarMatricula();
                                    break;
                                case 1:
                                    cuenta.pagarLaboratorio();
                                    break;
                                case 2:
                                    cuenta.pagarBiblioteca();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atención!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atención!", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Número de carnet inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(payFeesPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}