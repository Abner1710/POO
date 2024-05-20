package Model;

import javax.swing.JOptionPane;

public class Aranceles 
{
    private boolean pagoMatricula = false;
    private boolean pagoLaboratorio = false;
    private boolean pagoBiblioteca = false;
    

    public boolean isPagoMatricula() {
        return pagoMatricula;
    }

    public boolean isPagoLaboratorio() {
        return pagoLaboratorio;
    }

    public boolean isPagoBiblioteca() {
        return pagoBiblioteca;
    }

    public void pagarMatricula(Cuenta cuenta) 
    {
        double costo = 200;
        if (cuenta.getSaldo() >= costo && !pagoMatricula) 
        {
            cuenta.retirar(costo);
            pagoMatricula = true;
            JOptionPane.showMessageDialog(null, "Pago de matricula realizado. Saldo actual: C$ " + cuenta.getSaldo());
        } else if (pagoMatricula) 
        {
            JOptionPane.showMessageDialog(null, "El arancel de matricula ya fue pagado.");
        } else 
        {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para pagar la matricula.");
        }
    }

    public void pagarLaboratorio(Cuenta cuenta) 
    {
        double costo = 100;
        if (cuenta.getSaldo() >= costo && !pagoLaboratorio) 
        {
            cuenta.retirar(costo);
            pagoLaboratorio = true;
            JOptionPane.showMessageDialog(null, "Pago de laboratorio realizado. Saldo actual: C$ " + cuenta.getSaldo());
        } 
        else if (pagoLaboratorio) 
        {
            JOptionPane.showMessageDialog(null, "El arancel de laboratorio ya fue pagado.");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para pagar el laboratorio.");
        }
    }

    public void pagarBiblioteca(Cuenta cuenta) 
    {
        double costo = 50;
        if (cuenta.getSaldo() >= costo && !pagoBiblioteca) 
        {
            cuenta.retirar(costo);
            pagoBiblioteca = true;
            JOptionPane.showMessageDialog(null, "Pago de biblioteca realizado. Saldo actual: C$ " + cuenta.getSaldo());
        } 
        else if (pagoBiblioteca) 
        {
            JOptionPane.showMessageDialog(null, "El arancel de biblioteca ya fue pagado.");
        } else 
        {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para pagar la biblioteca.");
        }
    }
}
