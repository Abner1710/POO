package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BD.baseDeDatos;

public class Gestion {
    private List<Estudiante> listaEstudiante = new ArrayList<>();
    private baseDeDatos bd = new baseDeDatos();

    public void agregar(Estudiante estudiante) {
        listaEstudiante.add(estudiante);
        String sql = "INSERT INTO Estudiantes (nombre, carnetNum, numeroCelular) VALUES (?, ?, ?)";
        Connection con = null;
        try {
            con = bd.conectar();
            con.setAutoCommit(false);

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, estudiante.getNombre());
                pstmt.setString(2, estudiante.getCarnetNum());
                pstmt.setInt(3, estudiante.getNumeroCelular());
                pstmt.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bd.desconectar();
            }
        }
    }

    public boolean existeCarnet(String carnetNum) {
        String sql = "SELECT * FROM Estudiantes WHERE carnetNum = ?";
        try (Connection con = bd.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, carnetNum);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeNombre(String nombre) {
        String sql = "SELECT * FROM Estudiantes WHERE nombre = ?";
        try (Connection con = bd.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeNumeroCelular(int numeroCelular) {
        String sql = "SELECT * FROM Estudiantes WHERE numeroCelular = ?";
        try (Connection con = bd.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, numeroCelular);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Estudiante verificarCarnet(String carnetNum) {
        String sql = "SELECT * FROM Estudiantes WHERE carnetNum = ?";
        try (Connection con = bd.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, carnetNum);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(rs.getString("nombre"), rs.getString("carnetNum"), rs.getInt("numeroCelular"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Estudiante> getListaEstudiante() {
        return listaEstudiante;
    }

    public void cargarEstudiantes() {
        listaEstudiante.clear();
        String sql = "SELECT * FROM Estudiantes";

        try (Connection con = bd.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante(rs.getString("nombre"), rs.getString("carnetNum"), rs.getInt("numeroCelular"));
                listaEstudiante.add(estudiante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
