package Model;

import javax.swing.JOptionPane;

public class Estudiante 
{

    private String nombre;
    private String carnetNum;
    private String numeroCelular;

    public void imprimir() 
    {
        String mostrar = "\nNombre: " + nombre + "\nNumero de Carnet: " + carnetNum + "\nNumero Telefonico o Celular: " + numeroCelular;
        JOptionPane.showMessageDialog(null, mostrar);
    }

    public Estudiante(String nombre, String carnetNum, String numeroCelular) 
    {
        this.nombre = nombre;
        this.carnetNum = carnetNum;
        this.numeroCelular = numeroCelular;
    }

    public String getNombre() 
    {
        return this.nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getCarnetNum() 
    {
        return this.carnetNum;
    }

    public void setCarnetNum(String carnetNum) 
    {
        this.carnetNum = carnetNum;
    }

    public String getNumeroCelular() 
    {
        return this.numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) 
    {
        this.numeroCelular = numeroCelular;

    }

    @Override
    public String toString() 
    {
        return "\nNumero de Carnet: " + carnetNum + "\nNumero Telefonico o Celular: " + numeroCelular;
    }

}
