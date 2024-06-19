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
        try (Connection con = bd.conectar()) {
            String sql = "INSERT INTO Estudiantes (nombre, carnetNum, numeroCelular) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getCarnetNum());
            pstmt.setString(3, estudiante.getNumeroCelular());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeCarnet(String carnetNum) {
        try (Connection con = bd.conectar()) {
            String sql = "SELECT * FROM Estudiantes WHERE carnetNum = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, carnetNum);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Estudiante verificarCarnet(String carnetNum) {
        try (Connection con = bd.conectar()) {
            String sql = "SELECT * FROM Estudiantes WHERE carnetNum = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, carnetNum);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(rs.getString("nombre"), rs.getString("carnetNum"), rs.getString("numeroCelular"));
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
        try (Connection con = bd.conectar()) {
            String sql = "SELECT * FROM Estudiantes";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(rs.getString("nombre"), rs.getString("carnetNum"), rs.getString("numeroCelular"));
                listaEstudiante.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
