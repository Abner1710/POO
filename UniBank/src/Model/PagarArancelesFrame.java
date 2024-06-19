package Model;

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

        JLabel cuentaLabel = new JLabel("Número de Cuenta:");
        JTextField cuentaField = new JTextField();
        JLabel arancelLabel = new JLabel("Seleccione el arancel:");
        String[] aranceles = {"Matricula (200 C$)", "Laboratorio (100 C$)", "Biblioteca (50 C$)"};
        JComboBox<String> arancelComboBox = new JComboBox<>(aranceles);
        JButton pagarButton = new JButton("Pagar");

        payFeesPanel.add(cuentaLabel);
        payFeesPanel.add(cuentaField);
        payFeesPanel.add(arancelLabel);
        payFeesPanel.add(arancelComboBox);
        payFeesPanel.add(new JLabel());
        payFeesPanel.add(pagarButton);

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(cuentaField.getText());
                    Cuenta cuenta = banco.cuentaExiste(numero);
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de cuenta inválido", "Error", JOptionPane.ERROR_MESSAGE);
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