package View;

import Model.*;
import Model.Cuenta;
import javax.swing.JOptionPane;
import java.time.LocalDate;

public class Main 
{

    public static Gestion ges = new Gestion();
    public static Banco banco = new Banco();
    public static int numero = 0;
    public static Aranceles aranceles = new Aranceles();
    
    public static void menu(int opc) 
    {

        switch (opc) 
        {
            case 1:

                String nombre = JOptionPane.showInputDialog(null, "Digite sus nombres y apellidos\n");
                if (nombre.isEmpty() == false) 
                {
                    String carnetNum = JOptionPane.showInputDialog(null, "Digite su numero de carnet\n");
                    if (carnetNum.isEmpty() == false) 
                    {
                        if (!ges.existeCarnet(carnetNum)) 
                        {
                            String numeroCelular = JOptionPane.showInputDialog(null, "Digite su numero de telefono\n");

                            if (numeroCelular.isEmpty() == false) 
                            {
                                Estudiante estudiante = new Estudiante(nombre, carnetNum, numeroCelular);
                                ges.agregar(estudiante);
                                JOptionPane.showMessageDialog(null, "Estudiante agregado con exito!");
                                estudiante.imprimir();
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(null, "Digite su numero de telefono", "Atencion!", JOptionPane.WARNING_MESSAGE);
                            }
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(null, "El numero de carnet ya se encuentra registrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                        }
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Digite su numero de carnet", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    }

                } 
                else
                {
                    JOptionPane.showMessageDialog(null, "Digite sus nombres y apellidos", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                
                break;

            case 2:
            	
                String carnetNum;
                if (ges.getListaEstudiante().isEmpty() == false) 
                {
                    carnetNum = JOptionPane.showInputDialog("Digite su numero de carnet: ");
                    if (carnetNum.isEmpty() == false) 
                    {
                        if (ges.existeCarnet(carnetNum) == true) 
                        {
                            if (banco.existeCuentaCarnet(carnetNum) == false) 
                            {
                                ges.verificarCarnet(carnetNum);//retornando um cliente do cpf diitado
                                int esc = JOptionPane.showConfirmDialog(null, "¿Quiere crear una cuenta comun?");
                                if (esc == JOptionPane.YES_OPTION) 
                                {

                                    double saldo = 0;
                                    LocalDate data = LocalDate.now();
                                    Cuenta cuenta = new Cuenta(numero, saldo, data, ges.verificarCarnet(carnetNum));
                                    banco.agregar(cuenta);
                                    numero++;
                                    JOptionPane.showMessageDialog(null, "" + cuenta.toString());

                                } 
                                else if (esc == JOptionPane.NO_OPTION) 
                                {
                                    int esc2 = JOptionPane.showConfirmDialog(null, "¿Quiere crear una cuenta especial?");
                                    if (esc2 == JOptionPane.YES_OPTION) 
                                    {

                                        double saldoE = 0;
                                        LocalDate dataE = LocalDate.now();
                                        CuentaEspecial contaE = new CuentaEspecial(numero, saldoE, dataE, ges.verificarCarnet(carnetNum));
                                        banco.agregarEsp(contaE);
                                        numero++;
                                        JOptionPane.showMessageDialog(null, contaE.toString());
                                    }
                                }
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(null, "Ya existe una cuenta con este numero de carnet registrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                            }
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(null, "Numero de carnet no encontrado o registrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                        }
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Introduzca un numero de carnet", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Registre un estudiante primero", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }

                break;

            case 3:
            	
                if (!banco.getListaCuentasEsp().isEmpty() || !banco.getListaCuentas().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, banco.listar());
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Registre una cuenta primero", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }

                break;
                
            case 4:
            	
                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) 
                {
                    int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta:"));
                    banco.getCuenta(numero);
                    if (banco.buscar(numero) != null) 
                    {
                        JOptionPane.showMessageDialog(null, banco.buscar(numero));
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }

                break;
                
            case 5:
            	
                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) 
                {
                    int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta:"));
                    Cuenta ncuenta;
                    Double cantidad;
                    ncuenta = banco.cuentaExiste(numero);
                    if (ncuenta != null) 
                    {
                        cantidad = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el monto del depósito"));

                        ncuenta.depositar(cantidad);
                        JOptionPane.showMessageDialog(null, "Saldo actual de la cuenta " + ncuenta.getNumero() + ": \n" + "C$" + ncuenta.getSaldo());
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);

                    }

                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }

                break;
                
            case 6:
            	
                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) 
                {
                    Cuenta ncuenta;
                    Double cantidad;
                    int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta:"));
                    ncuenta = banco.cuentaExiste(numero);
                    if (ncuenta != null) 
                    {
                        cantidad = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el monto del retiro"));
                        ncuenta.retirar(cantidad);
                        JOptionPane.showMessageDialog(null, "Saldo Actual de la Cuenta: " + "C$" + ncuenta.getSaldo());
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);

                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }

                break;
                
            case 7:
            	
                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) 
                {
                    Cuenta destino;
                    int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta a debitar:"));
                    Cuenta Nconta = banco.cuentaExiste(numero);
                    if (Nconta != null) 
                    {
                        numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta a acreditar:"));
                        destino = banco.cuentaExiste(numero);
                        if (destino != null) 
                        {
                            double quantia = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el monto de la transferencia"));
                            Nconta.transferir(destino, quantia);
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);

                        }
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);

                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                
                break;
                
            case 8:
                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) 
                {
                    int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta:"));
                    Cuenta cuenta = banco.cuentaExiste(numero);
                    if (cuenta != null) 
                    {
                        int feeOption = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el arancel a pagar:\n1. Matricula (200 C$)\n2. Laboratorio (100 C$)\n3. Biblioteca (50 C$)"));
                        switch (feeOption) 
                        {
	                        case 1:
	                            cuenta.pagarMatricula();
	                            break;
	                        case 2:
	                            cuenta.pagarLaboratorio();
	                            break;
	                        case 3:
	                            cuenta.pagarBiblioteca();
	                            break;
	                        default:
	                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                        }
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Registre una cuenta primero", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                break;
                
            case 9:
            	
                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) 
                {
                    int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de cuenta:"));
                    Cuenta Nconta = banco.cuentaExiste(numero);
                    if (Nconta != null) 
                    {
                        banco.remover(numero);
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);

                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                
                break;
                
            case 10:
            	
                JOptionPane.showMessageDialog(null, "FIN DEL PROGRAMA, GRACIAS VUELVA PRONTO");
                
                break;
                
            default:
            	
                JOptionPane.showMessageDialog(null, "Opcion invalida - Elija del 1 al 9", "ATENCION!", JOptionPane.WARNING_MESSAGE);

        }

    }

    public static void main(String[] args) 
    {
        int opc = 0;

        do 
        {
            try 
            {

                opc = Integer.parseInt(JOptionPane.showInputDialog(null, "----------Menu de UNIBank----------\n"
                        + "1 - Registrar Estudiante\n" + "2 - Registrar Cuenta\n" + "3 - Lista de Cuentas\n"
                        + "4 - Buscar Cuenta\n" + "5 - Depositar Dinero\n" + "6 - Retirar Dinero\n" + "7 - Transferir Dinero\n" + "8 - Pagar Aranceles\n"+ "9 - Eliminar Cuenta\n" + "10 - Salir\n"
                        + "Escoja una Opcion:\n"));

                menu(opc);
            }
            catch (NumberFormatException exception) 
            {

                JOptionPane.showMessageDialog(null, "INGRESE SÓLO NÚMEROS", "Atencion Usuario", JOptionPane.WARNING_MESSAGE);

            }

        } while (opc != 10);
    }

}