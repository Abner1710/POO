package Model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Banco 
{

    private ArrayList<Cuenta> listaCuentas = new ArrayList<>();
    private ArrayList<CuentaEspecial> listaCuentasEsp = new ArrayList<>();

    public Banco() 
    {

    }

    public ArrayList<Cuenta> getListaCuentas() 
    {
        return listaCuentas;
    }

    public ArrayList<CuentaEspecial> getListaCuentasEsp() 
    {
        return listaCuentasEsp;
    }

    public void agregar(Cuenta cuenta) 
    {
        listaCuentas.add(cuenta);
        JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente");
    }

    public void agregarEsp(CuentaEspecial contaEsp) 
    {
        listaCuentasEsp.add(contaEsp);
        JOptionPane.showMessageDialog(null, "Cuenta especial agregada exitosamente");
    }

    public String listar() 
    {
        String lista1 = "";
        String lista2 = "";

        if (!listaCuentasEsp.isEmpty()) 
        {
            lista1 += "CUENTAS ESPECIALES\n";
            for (CuentaEspecial c : listaCuentasEsp) 
            {
                if (c != null) 
                {
                    lista1 += c.toString();
                }
            }
        } 
        else 
        {
            lista1 += "NO HAY CUENTAS ESPECIALES REGISTRADAS\n";

        }
        if (!listaCuentas.isEmpty()) 
        {
            lista2 += "\nCUENTAS COMUNES\n";

            for (Cuenta c1 : listaCuentas) 
            {
                //System.out.println(c);
                if (c1 != null) 
                {
                    lista2 += c1.toString();
                }
            }
        } 
        else 
        {
            lista2 += "NO HAY CUENTAS COMUNES REGISTRADAS\n";
        }
        return "LISTA DE CUENTAS\n" + lista1 + lista2;

    }

    public String buscar(int numero) 
    {

        for (Cuenta c : listaCuentas) 
        {
            if (c.getNumero() == numero) 
            {

                return "\nNumero de carnet: " + c.getEstudiante().getCarnetNum() + "\nNumero de Celular  o telefonico: " + c.getEstudiante().getNumeroCelular() + "\n" + c.toString();
            }
        }

        for (CuentaEspecial c1 : listaCuentasEsp) 
        {
            if (c1.getNumero() == numero) 
            {

                return "\nNumero de carnet: " + c1.getEstudiante().getCarnetNum() + "\nNumero de Celular  o telefonico: " + c1.getEstudiante().getNumeroCelular() + "\n" + c1.toString();
            }
        }
        return null;
    }

    public Cuenta getCuenta(int numero) 
    {
        for (Cuenta c : listaCuentas) 
        {
            if (c.getNumero() == numero) 
            {
                return c;
            }
        }
        for (Cuenta c1 : listaCuentasEsp) 
        {
            if (c1.getNumero() == numero) 
            {
                return c1;
            }
        }
        return null;
    }

    public Cuenta cuentaExiste(int num) 
    {
        for (Cuenta c : listaCuentas) 
        {

            if (c.getNumero() == num) 
            {
                return c;
            }
        }
        for (Cuenta c1 : listaCuentasEsp) 
        {
            if (c1.getNumero() == num) 
            {
                return c1;
            }
        }
        return null;
    }


    public boolean remover(int numero) 
    {
        for (Cuenta c : listaCuentas) 
        {
            if (c.getNumero() == numero) 
            {
                if (c.saldo == 0) 
                {
                    listaCuentas.remove(c);
                    JOptionPane.showMessageDialog(null, "CUENTA " + c.getNumero() + " REMOVIDA");
                    return true;
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "PARA QUE LA CUENTA SEA ELIMINADA, EL SALDO DEBE SER IGUAL A C$ 0\n");
                    return false;
                }
            }

        }
        for (CuentaEspecial c1 : listaCuentasEsp) 
        {
            if (c1.getNumero() == numero) 
            {
                if (c1.saldo == 0) 
                {
                    listaCuentasEsp.remove(c1);
                    JOptionPane.showMessageDialog(null, "CUENTA ESPECIAL " + c1.getNumero() + " REMOVIDA");
                    return true;
                } 
                else 
                {

                    JOptionPane.showMessageDialog(null, "PARA QUE LA CUENTA SEA ELIMINADA, EL SALDO DEBE SER IGUAL A C$ 0\n");
                    return false;
                }

            }
        }

        return false;
    }

    public boolean existeCuentaCarnet(String carnet) 
    {
        for (Cuenta c : listaCuentas) {
            if (c.getEstudiante().getCarnetNum().contains(carnet)) 
            {
                return true;
            }

        }
        for (CuentaEspecial c1 : listaCuentasEsp) {
            if (c1.getEstudiante().getCarnetNum().contains(carnet)) 
            {

                return true;
            }

        }
        return false;

    }
}