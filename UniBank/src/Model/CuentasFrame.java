package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CuentasFrame extends JFrame {
    private Banco banco;

    public CuentasFrame(Banco banco) {
        this.setBanco(banco);
        setTitle("Gestión de Cuentas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel para listar cuentas
        JPanel listPanel = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Tipo de Cuenta");
        model.addColumn("Estudiante");
        model.addColumn("Número de Cuenta");
        model.addColumn("Saldo");
        model.addColumn("Fecha de Creación");
        model.addColumn("Matricula");
        model.addColumn("Laboratorio");
        model.addColumn("Biblioteca");

        List<Cuenta> cuentas = banco.getListaCuentas();
        for (Cuenta cuenta : cuentas) {
            model.addRow(new Object[]{
                "Común",
                cuenta.getEstudiante().getNombre(),
                cuenta.getNumero(),
                "C$ " + cuenta.getSaldo(),
                cuenta.infOrden(),
                cuenta.isPagoMatricula() ? "Pagada" : "Sin Pagar",
                cuenta.isPagoLaboratorio() ? "Pagado" : "Sin Pagar",
                cuenta.isPagoBiblioteca() ? "Pagado" : "Sin Pagar"
            });
        }

        List<CuentaEspecial> cuentasEsp = banco.getListaCuentasEsp();
        for (CuentaEspecial cuenta : cuentasEsp) {
            model.addRow(new Object[]{
                "Especial",
                cuenta.getEstudiante().getNombre(),
                cuenta.getNumero(),
                "C$ " + cuenta.getSaldo(),
                cuenta.infOrden(),
                cuenta.isPagoMatricula() ? "Pagada" : "Sin Pagar",
                cuenta.isPagoLaboratorio() ? "Pagado" : "Sin Pagar",
                cuenta.isPagoBiblioteca() ? "Pagado" : "Sin Pagar"
            });
        }

        if (model.getRowCount() == 0) {
            JLabel noCuentasLabel = new JLabel("Registre una cuenta primero", JLabel.CENTER);
            noCuentasLabel.setForeground(Color.RED);
            listPanel.add(noCuentasLabel, BorderLayout.CENTER);
        } else {
            JScrollPane scrollPane = new JScrollPane(table);
            listPanel.add(scrollPane, BorderLayout.CENTER);
        }

        tabbedPane.addTab("Listado de Cuentas", listPanel);

        
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
}
