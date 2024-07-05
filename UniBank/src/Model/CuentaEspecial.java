package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class CuentaEspecial extends Cuenta {
    private double limite = 1000;

    public CuentaEspecial(int numeroC, double saldo, LocalDate infoC, Estudiante estudiante) {
        super(numeroC, saldo, infoC, estudiante);
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0) {
            if (this.saldo + limite >= cantidad) {
                double nuevoSaldo = this.saldo - cantidad;
                if (nuevoSaldo >= -limite) {
                    this.saldo = nuevoSaldo;
                    JOptionPane.showMessageDialog(null, "Retiro realizado con éxito");
                    actualizarSaldoEnBaseDeDatos();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Límite de retiro excedido", "Atención!", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Atención!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No es posible retirar una cantidad negativa", "Atención!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    @Override
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            if (this.saldo + cantidad <= limite) {
                this.saldo += cantidad;
                JOptionPane.showMessageDialog(null, "Depósito realizado con éxito");
                actualizarSaldoEnBaseDeDatos();
            } else {
                JOptionPane.showMessageDialog(null, "Límite de depósito excedido", "Atención!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No es posible depositar una cantidad negativa", "Atención!", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public boolean transferir(Cuenta destino, double cantidad) {
        if (cantidad > 0) {
            if (this.saldo >= cantidad && destino != null) {
                if (destino instanceof CuentaEspecial) {
                    CuentaEspecial cuentaDestino = (CuentaEspecial) destino;
                    if (this.saldo + cuentaDestino.saldo + cantidad <= limite) {
                        this.saldo -= cantidad;
                        destino.saldo += cantidad;
                        JOptionPane.showMessageDialog(null, "Transferencia realizada con éxito");
                        actualizarSaldoEnBaseDeDatos();
                        destino.actualizarSaldoEnBaseDeDatos();
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Límite de transferencia excedido", "Atención!", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede transferir a una cuenta diferente de CuentaEspecial", "Atención!", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente para transferir", "Atención!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No es posible transferir una cantidad negativa", "Atención!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Estudiante: " + super.getEstudiante().getNombre() +
                "\nNúmero de Cuenta: " + super.getNumero() +
                "\nTipo de Cuenta: Especial" +
                "\nSaldo: C$ " + super.getSaldo() +
                "\nFecha de creación: " + super.infOrden() +
                "\nLimite: C$ " + limite +
                "\nArancel de Matrícula: " + (super.isPagoMatricula() ? "Pagado" : "Sin Pagar") +
                "\nArancel de Laboratorio: " + (super.isPagoLaboratorio() ? "Pagado" : "Sin Pagar") +
                "\nArancel de Biblioteca: " + (super.isPagoBiblioteca() ? "Pagado" : "Sin Pagar") +
                "\n----------------------------------------------\n";
    }
}
