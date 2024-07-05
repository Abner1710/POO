package frames;

import Model.Estudiante;
import View.Main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class InicioSesionFrame extends JFrame {
    
    private JTextField nombreField;
    private JTextField carnetField;
    private JTextField celularField;
    
    public InicioSesionFrame() {
        super("Registrar Estudiante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200); // Ajusta la altura del frame 
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // GridBagLayout para un mejor control del diseño
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets.left = 10;
        constraints.insets.right = 10;
        constraints.insets.top = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        JLabel nombreLabel = new JLabel("Nombre y Apellidos:");
        panel.add(nombreLabel, constraints);
        
        constraints.gridx = 1;
        nombreField = new JTextField(20);
        ((AbstractDocument) nombreField.getDocument()).setDocumentFilter(new LetterOnlyFilter());
        panel.add(nombreField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel carnetLabel = new JLabel("Número de Carnet:");
        panel.add(carnetLabel, constraints);
        
        constraints.gridx = 1;
        carnetField = new JTextField(20);
        panel.add(carnetField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel celularLabel = new JLabel("Número de Teléfono:");
        panel.add(celularLabel, constraints);
        
        constraints.gridx = 1;
        celularField = new JTextField(20);
        panel.add(celularField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JButton agregarButton = new JButton("Agregar Estudiante");
        agregarButton.addActionListener(e -> agregarEstudiante());
        panel.add(agregarButton, constraints);
        
        add(panel);
    }
    
    private void agregarEstudiante() {
        String nombre = nombreField.getText().trim();
        String carnetNum = carnetField.getText().trim();
        String numeroCelularStr = celularField.getText().trim();
        
        if (nombre.isEmpty() || carnetNum.isEmpty() || numeroCelularStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validarCarnet(carnetNum)) {
            JOptionPane.showMessageDialog(this, "Número de carnet inválido. Debe seguir el formato 20XX-XXXXU donde el año debe ser entre 2018 y 2024.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (numeroCelularStr.length() < 8) {
                JOptionPane.showMessageDialog(this, "El número de teléfono debe tener al menos 8 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numeroCelular = Integer.parseInt(numeroCelularStr);
            
            if (Main.ges.existeCarnet(carnetNum)) {
                JOptionPane.showMessageDialog(this, "El número de carnet ya está registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (Main.ges.existeNombre(nombre)) {
                JOptionPane.showMessageDialog(this, "El nombre ya está registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (Main.ges.existeNumeroCelular(numeroCelular)) {
                JOptionPane.showMessageDialog(this, "El número de celular ya está registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Estudiante estudiante = new Estudiante(nombre, carnetNum, numeroCelular);
            Main.ges.agregar(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante agregado con éxito");
            estudiante.imprimir();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número de teléfono inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCarnet(String carnetNum) {
        Pattern pattern = Pattern.compile("20[1-2][0-9]-[0-9]{4}U");
        if (!pattern.matcher(carnetNum).matches()) {
            return false;
        }

        int year = Integer.parseInt(carnetNum.substring(0, 4));
        return year >= 2018 && year <= 2024;
    }
    
    private class LetterOnlyFilter extends DocumentFilter {
        private final Pattern regex = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*");

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (regex.matcher(string).matches()) {
                super.insertString(fb, offset, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (regex.matcher(text).matches()) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
