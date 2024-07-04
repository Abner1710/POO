package View;

import Model.*;
import frames.CuentasFrame;
import frames.MenuFrame;
import frames.PagarArancelesFrame;
import BD.baseDeDatos;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import javax.swing.*;


public class Main {

    public static Gestion ges = new Gestion();
    public static Banco banco = new Banco();
    public static int numero = 0;
    public static baseDeDatos bd = new baseDeDatos();

    public static void main(String[] args) {
        bd.conectar();
        ges.cargarEstudiantes();
        banco.cargarCuentas();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        MenuFrame frame = new MenuFrame();
        frame.setVisible(true);
    }

    public static void menu(int opc) {

        switch (opc) {
            case 1:

                String nombre = JOptionPane.showInputDialog(null, "Digite sus nombres y apellidos\n");
                if (nombre != null && !nombre.isEmpty()) {
                    String carnetNum = JOptionPane.showInputDialog(null, "Digite su numero de carnet\n");
                    if (carnetNum != null && !carnetNum.isEmpty()) {
                        if (!ges.existeCarnet(carnetNum)) {
                            String numeroCelular = JOptionPane.showInputDialog(null, "Digite su numero de telefono\n");

                            if (numeroCelular != null && !numeroCelular.isEmpty()) {
                                Estudiante estudiante = new Estudiante(nombre, carnetNum, numeroCelular);
                                ges.agregar(estudiante);
                                JOptionPane.showMessageDialog(null, "Estudiante agregado con exito!");
                                estudiante.imprimir();
                            } else {
                                JOptionPane.showMessageDialog(null, "Digite su numero de telefono", "Atencion!", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El numero de carnet ya se encuentra registrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Digite su numero de carnet", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Digite sus nombres y apellidos", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }

                break;

            case 2:

                String carnetNum;
                if (!ges.getListaEstudiante().isEmpty()) {
                    carnetNum = JOptionPane.showInputDialog("Digite su numero de carnet: ");
                    if (carnetNum != null && !carnetNum.isEmpty()) {
                        if (ges.existeCarnet(carnetNum)) {
                            if (!banco.existeCuentaCarnet(carnetNum)) {
                                ges.verificarCarnet(carnetNum);
                                int esc = JOptionPane.showConfirmDialog(null, "¿Quiere crear una cuenta comun?");
                                if (esc == JOptionPane.YES_OPTION) {

                                    double saldo = 0;
                                    LocalDate data = LocalDate.now();
                                    Cuenta cuenta = new Cuenta(numero, saldo, data, ges.verificarCarnet(carnetNum));
                                    banco.agregar(cuenta);
                                    numero++;
                                    JOptionPane.showMessageDialog(null, "" + cuenta.toString());
                                } else if (esc == JOptionPane.NO_OPTION) {
                                    int esc2 = JOptionPane.showConfirmDialog(null, "¿Quiere crear una cuenta especial?");
                                    if (esc2 == JOptionPane.YES_OPTION) {

                                        double saldoE = 0;
                                        LocalDate dataE = LocalDate.now();
                                        CuentaEspecial cuentaE = new CuentaEspecial(numero, saldoE, dataE, ges.verificarCarnet(carnetNum));
                                        banco.agregarEsp(cuentaE);
                                        numero++;
                                        JOptionPane.showMessageDialog(null, cuentaE.toString());
                                    }
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Ya existe una cuenta con este numero de carnet registrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Numero de carnet no encontrado o registrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Introduzca un numero de carnet", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Registre un estudiante primero", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }

                break;

            case 3:
                if (!banco.getListaCuentasEsp().isEmpty() || !banco.getListaCuentas().isEmpty()) {
                    new CuentasFrame(banco);
                } else {
                    JOptionPane.showMessageDialog(null, "Registre una cuenta primero", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case 4:

                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) {
                    String carnet = JOptionPane.showInputDialog(null, "Ingrese el carnet del estudiante:");
                    if (banco.existeCuentaCarnet(carnet)) {
                        int numero = banco.getCuentaPorCarnet(carnet);
                        Cuenta cuenta = banco.buscar(numero);
                        if (cuenta != null) {
                            JOptionPane.showMessageDialog(null, cuenta);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Carnet no encontrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case 5:

                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) {
                    String carnet = JOptionPane.showInputDialog(null, "Ingrese el carnet del estudiante:");
                    if (banco.existeCuentaCarnet(carnet)) {
                        int numero = banco.getCuentaPorCarnet(carnet);
                        Cuenta ncuenta = banco.cuentaExiste(numero);
                        if (ncuenta != null) {
                            double cantidad = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el monto del depósito"));
                            ncuenta.depositar(cantidad);
                            JOptionPane.showMessageDialog(null, "Saldo actual de la cuenta " + ncuenta.getNumero() + ": \n" + "C$" + ncuenta.getSaldo());
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Carnet no encontrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case 6:

                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) {
                    String carnet = JOptionPane.showInputDialog(null, "Ingrese el carnet del estudiante:");
                    if (banco.existeCuentaCarnet(carnet)) {
                        int numero = banco.getCuentaPorCarnet(carnet);
                        Cuenta ncuenta = banco.cuentaExiste(numero);
                        if (ncuenta != null) {
                            double cantidad = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el monto del retiro"));
                            ncuenta.retirar(cantidad);
                            JOptionPane.showMessageDialog(null, "Saldo Actual de la Cuenta: " + "C$" + ncuenta.getSaldo());
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Carnet no encontrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case 7:

                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) {
                    String carnet = JOptionPane.showInputDialog(null, "Ingrese el carnet del estudiante que transferirá dinero:");
                    if (banco.existeCuentaCarnet(carnet)) {
                        int numero = banco.getCuentaPorCarnet(carnet);
                        Cuenta ncuenta = banco.cuentaExiste(numero);
                        if (ncuenta != null) {
                            carnet = JOptionPane.showInputDialog(null, "Ingrese el carnet del estudiante que recibirá la transferencia:");
                            if (banco.existeCuentaCarnet(carnet)) {
                                int numeroDestino = banco.getCuentaPorCarnet(carnet);
                                Cuenta destino = banco.cuentaExiste(numeroDestino);
                                if (destino != null) {
                                    double quantia = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el monto de la transferencia"));
                                    ncuenta.transferir(destino, quantia);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Carnet no encontrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Carnet no encontrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case 8:

                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) {
                    new PagarArancelesFrame(banco);
                } else {
                    JOptionPane.showMessageDialog(null, "Registre una cuenta primero", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case 9:

                if (!banco.getListaCuentas().isEmpty() || !banco.getListaCuentasEsp().isEmpty()) {
                    String carnet = JOptionPane.showInputDialog(null, "Ingrese el carnet del estudiante:");
                    if (banco.existeCuentaCarnet(carnet)) {
                        int numero = banco.getCuentaPorCarnet(carnet);
                        Cuenta cuenta = banco.getCuenta(numero);
                        if (cuenta != null) {
                            if (cuenta.getSaldo() == 0) {
                                banco.remover(numero);
                                JOptionPane.showMessageDialog(null, "Cuenta eliminada correctamente.");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se puede eliminar la cuenta porque tiene saldo disponible.", "Atención!", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Carnet no encontrado", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "REGISTRE UNA CUENTA PRIMERO", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
                break;


            case 10:
                JOptionPane.showMessageDialog(null, "GRACIAS, VUELVA PRONTO");
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INVALIDA", "Atencion!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
