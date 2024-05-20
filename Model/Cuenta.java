package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Cuenta 
{
    private int numero;
    private Estudiante estudiante;
    protected double saldo = 0;
    private LocalDate infoC;
    DateTimeFormatter infOrden = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Aranceles aranceles;
    
    public Cuenta(int numeroC, double saldo, LocalDate infoC, Estudiante estudiante) 
    { 
        this.saldo = saldo;
        this.numero = numeroC;
        this.infoC = infoC;
        this.estudiante = estudiante;
        this.aranceles = new Aranceles();
    }

    public Cuenta() 
    {
    }

    public LocalDate getInfoC() 
    {
        return infoC;
    }

    public void setInfoC(LocalDate infoC) 
    {
        this.infoC = infoC;
    }

    public String infOrden() 
    {
        return infoC.format(infOrden);
    }

    public int getNumero() 
    {
        return numero;
    }

    public void setNumero(int numero) 
    {
        this.numero = numero;
    }

    public double getSaldo() 
    {
        return saldo;
    }

    public void setSaldo(double saldo) 
    {
        this.saldo = saldo;
    }

    public Estudiante getEstudiante() 
    {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) 
    {
        this.estudiante = estudiante;
    }

    public void depositar(double cantidad) 
    {
        if (cantidad > 0) 
        {
            this.saldo += cantidad;
            JOptionPane.showMessageDialog(null, "Deposito Realizado");
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Introdujo una cantidad menor que 0", "Atencion!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean retirar(double cantidad) 
    {
        if (cantidad > 0) 
        {
            if (this.saldo >= cantidad) 
            {
                this.saldo -= cantidad;
                JOptionPane.showMessageDialog(null, "Retiro realizado con exito");
                return true;
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                return false;
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Ingresó una cantidad menor que 0", "Atencion!", JOptionPane.WARNING_MESSAGE);
            return false;
        }

    }

    public boolean transferir(Cuenta destino, double cantidad) 
    {
        if (this.saldo >= cantidad) 
        {
            this.saldo -= cantidad;
            destino.saldo += cantidad;
            JOptionPane.showMessageDialog(null, "Transferencia Exitosa!");
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Saldo Insuficiente", "Atencion!", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(null, "No fue posible realiar la transferencia", "Atencion!", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public void pagarMatricula() {
        aranceles.pagarMatricula(this);
    }

    public void pagarLaboratorio() {
        aranceles.pagarLaboratorio(this);
    }

    public void pagarBiblioteca() {
        aranceles.pagarBiblioteca(this);
    }

    public boolean isPagoMatricula() {
        return aranceles.isPagoMatricula();
    }

    public boolean isPagoLaboratorio() {
        return aranceles.isPagoLaboratorio();
    }

    public boolean isPagoBiblioteca() {
        return aranceles.isPagoBiblioteca();
    }

    public void imprimir() 
    {
        toString();
    }

    @Override
    public String toString() 
    {
        return "Estudiante: " + estudiante.getNombre() + "\nNumero de Cuenta: " + numero + "\nTipo de Cuenta: Comun" + "\nSaldo: C$ " + saldo + "\nFecha de creacion: " + infOrden() + "\nMatricula Pagada: " + (isPagoMatricula() ? "Si" : "No") + "\nLaboratorio Pagado: " + (isPagoLaboratorio() ? "Si" : "No") + "\nBiblioteca Pagada: " + (isPagoBiblioteca() ? "Si" : "No") + "\n----------------------------------------------\n";
    }
}
