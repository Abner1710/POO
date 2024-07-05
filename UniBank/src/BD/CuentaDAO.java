package BD;

import Model.Cuenta;
import Model.CuentaEspecial;
import Model.Gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {
    private static Gestion gestion = new Gestion();
    private static baseDeDatos bd = new baseDeDatos();

    public static void guardarCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO Cuenta(numero, saldo, fecha, carnetNum, tipo, matri, biblio, lab) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = bd.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cuenta.getNumero());
            pstmt.setDouble(2, cuenta.getSaldo());
            pstmt.setString(3, cuenta.getInfoC().toString());
            pstmt.setString(4, cuenta.getEstudiante().getCarnetNum());
            pstmt.setString(5, "comun");
            pstmt.setString(6, cuenta.isPagoMatricula() ? "Pagado" : "Sin Pagar");
            pstmt.setString(7, cuenta.isPagoBiblioteca() ? "Pagado" : "Sin Pagar");
            pstmt.setString(8, cuenta.isPagoLaboratorio() ? "Pagado" : "Sin Pagar");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void guardarCuentaEspecial(CuentaEspecial cuentaEspecial) {
        String sql = "INSERT INTO Cuenta(numero, saldo, fecha, carnetNum, tipo, matri, biblio, lab) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = bd.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cuentaEspecial.getNumero());
            pstmt.setDouble(2, cuentaEspecial.getSaldo());
            pstmt.setString(3, cuentaEspecial.getInfoC().toString());
            pstmt.setString(4, cuentaEspecial.getEstudiante().getCarnetNum());
            pstmt.setString(5, "especial");
            pstmt.setString(6, cuentaEspecial.isPagoMatricula() ? "Pagado" : "Sin Pagar");
            pstmt.setString(7, cuentaEspecial.isPagoBiblioteca() ? "Pagado" : "Sin Pagar");
            pstmt.setString(8, cuentaEspecial.isPagoLaboratorio() ? "Pagado" : "Sin Pagar");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Cuenta> cargarCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM Cuenta";
        try (Connection conn = bd.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Cuenta cuenta;
                if (tipo.equals("comun")) {
                    cuenta = new Cuenta(rs.getInt("numero"), rs.getDouble("saldo"), fecha, gestion.verificarCarnet(rs.getString("carnetNum")));
                } else {
                    cuenta = new CuentaEspecial(rs.getInt("numero"), rs.getDouble("saldo"), fecha, gestion.verificarCarnet(rs.getString("carnetNum")));
                }
                // Establecer los estados de los aranceles
                cuenta.getArancelMatricula().setPagado("Pagado".equals(rs.getString("matri")));
                cuenta.getArancelLaboratorio().setPagado("Pagado".equals(rs.getString("lab")));
                cuenta.getArancelBiblioteca().setPagado("Pagado".equals(rs.getString("biblio")));
                
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    public static boolean existeCuentaCarnet(String carnetNum) {
        String sql = "SELECT * FROM Cuenta WHERE carnetNum = ?";
        try (Connection conn = bd.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carnetNum);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void eliminarCuenta(int numero) {
        String sql = "DELETE FROM Cuenta WHERE numero = ?";
        try (Connection conn = bd.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numero);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarSaldo(Cuenta cuenta) {
        String sql = "UPDATE Cuenta SET saldo = ? WHERE numero = ?";
        try (Connection conn = bd.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, cuenta.getSaldo());
            pstmt.setInt(2, cuenta.getNumero());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void actualizarArancel(Cuenta cuenta, String arancel, boolean pagado) {
        String sql = "UPDATE Cuenta SET " + arancel + " = ? WHERE numero = ?";
        try (Connection conn = bd.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pagado ? "Pagado" : "Sin Pagar");
            pstmt.setInt(2, cuenta.getNumero());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
