package Model;

import java.util.ArrayList;

public class Gestion 
{

    int i = 0;
    private ArrayList<Estudiante> listaEstudiante = new ArrayList<>();

    public ArrayList<Estudiante> getListaEstudiante() 
    {
        return listaEstudiante;
    }

    public void setListaEstudiante(ArrayList<Estudiante> listaEstudiante) 
    {
        this.listaEstudiante = listaEstudiante;
    }

    public void agregar(Estudiante estudiante) 
    {
        listaEstudiante.add(estudiante);
    }

    public String listar() 
    {
        String lista = "";
        for (Estudiante c : listaEstudiante) 
        {
            
            lista += c.toString();
        }
        return lista;

    }

    public Estudiante verificarCarnet(String a) 
    {
        for (Estudiante c1 : listaEstudiante) 
        {

            if (c1.getCarnetNum().contains(a)) 
            {

                return c1;
            }

        }
        return null;
    }

    public boolean existeCarnet(String carnet) 
    {
        for (Estudiante umcliente : listaEstudiante) 
        {
            if (umcliente.getCarnetNum().contains(carnet)) 
            {
                return true;
            }
        }
        return false;
    }

}
