package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class CuentaEspecial extends Cuenta 
{
    DateTimeFormatter dataOrdem1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private double limite = 1000;

    public double getLimite() 
    {
        return limite;
    }

    public void setLimite(double limite) 
    {
        this.limite = limite;
    }

    public CuentaEspecial(int numeroC, double saldo, LocalDate infOrden, Estudiante estudiante) 
    {
        super(numeroC, saldo, infOrden, estudiante);
    }

    public double getSaldoContaEsp() 
    {
        return saldo;
    }

    public void setSaldoContaEsp(double saldo) 
    {
        //this.saldo = saldo;
        super.saldo = saldo;
    }

    @Override
    public boolean retirar(double cantidad) 
    {

        if (cantidad > 0) 
        {
            double a = this.saldo + limite;
            if (a > 0) 
            {
                double b = a -= cantidad;
                if (b >=0) {
                    this.saldo -= cantidad;
                    return true;

                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "SALDO INSUFICIENTE", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "LIMITE EXCEDIDO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                return false;

            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "NO ES POSIBLE RETIRAR UNA CANTIDAD NEGATIVA");
            return false;
        }
    }

    @Override
    public String toString() 
    {
        return "Estudiante: " + super.getEstudiante().getNombre() + "\nNumero de Cuenta: " + super.getNumero() + "\nTipo: Especial" + "\nSaldo: C$ " + super.saldo + "\nFecha de creacion: " + infOrden() + "\nLimite: C$ 1000" + "\nMatricula Pagada: " + (isPagoMatricula() ? "Si" : "No") + "\nLaboratorio Pagado: " + (isPagoLaboratorio() ? "Si" : "No") + "\nBiblioteca Pagada: " + (isPagoBiblioteca() ? "Si" : "No") + "\n----------------------------------------------\n";
    }

}
