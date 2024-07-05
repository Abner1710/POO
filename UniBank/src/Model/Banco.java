package Model;

import BD.CuentaDAO;
import BD.baseDeDatos;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Cuenta> listaCuentas = new ArrayList<>();
    private List<CuentaEspecial> listaCuentasEsp = new ArrayList<>();
    private Gestion ges = new Gestion();
    private baseDeDatos bd = new baseDeDatos(); // Instancia de la clase baseDeDatos para manejar conexiones

    public void agregar(Cuenta cuenta) {
        listaCuentas.add(cuenta); // Agrega una cuenta a ListaCuenta
        CuentaDAO.guardarCuenta(cuenta); // Utiliza el método estático de CuentaDAO
    }

    public void agregarEsp(CuentaEspecial cuenta) {
        listaCuentasEsp.add(cuenta);
        CuentaDAO.guardarCuentaEspecial(cuenta); // Utiliza el método estático de CuentaDAO
    }

    public boolean existeCuentaCarnet(String carnetNum) {
        return CuentaDAO.existeCuentaCarnet(carnetNum); // Verifica si existe una cuenta con X numero
    }
    
    public int getCuentaPorCarnet(String carnetNum) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getEstudiante().getCarnetNum().equals(carnetNum)) {
                return cuenta.getNumero();
            }
        }
        for (CuentaEspecial cuentaEsp : listaCuentasEsp) {
            if (cuentaEsp.getEstudiante().getCarnetNum().equals(carnetNum)) {
                return cuentaEsp.getNumero();
            }
        }
        return -1; 
    }


    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public List<CuentaEspecial> getListaCuentasEsp() {
        return listaCuentasEsp;
    }

    public Cuenta cuentaExiste(int numero) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumero() == numero) {
                return cuenta;
            }
        }
        for (CuentaEspecial cuenta : listaCuentasEsp) {
            if (cuenta.getNumero() == numero) {
                return cuenta;
            }
        }
        return null;
    }

    public Cuenta buscar(int numero) {
        return cuentaExiste(numero);
    }

    public void remover(int numero) {
        Cuenta cuenta = cuentaExiste(numero);
        if (cuenta != null) {
            if (cuenta.getSaldo() == 0) {
                listaCuentas.remove(cuenta);
                CuentaDAO.eliminarCuenta(numero); // Utiliza el método estático de CuentaDAO
            } else {
                System.out.println("No se puede eliminar la cuenta porque tiene saldo disponible.");
            }
        } else {
            System.out.println("La cuenta no existe.");
        }
    }

    public void cargarCuentas() {
        List<Cuenta> cuentas = CuentaDAO.cargarCuentas(); // Utiliza el método estático de CuentaDAO
        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaEspecial) {
                listaCuentasEsp.add((CuentaEspecial) cuenta);
            } else {
                listaCuentas.add(cuenta);
            }
        }
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuentas comunes:\n");
        for (Cuenta cuenta : listaCuentas) {
            sb.append(cuenta.toString()).append("\n");
        }
        sb.append("\nCuentas especiales:\n");
        for (CuentaEspecial cuenta : listaCuentasEsp) {
            sb.append(cuenta.toString()).append("\n");
        }
        return sb.toString();
    }

    public Cuenta getCuenta(int numero) {
        return cuentaExiste(numero);
    }
}
