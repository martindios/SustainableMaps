package com.asiercl.server.dao;

import com.asiercl.server.models.Opinion;
import com.asiercl.server.models.Usuario;
import com.asiercl.server.models.Local;
import com.asiercl.server.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OpinionDAO {

    // Método para insertar una opinión en la base de datos
    public void insertarOpinion(Opinion opinion) {
        String sql = "INSERT INTO Opiniones (usuarioNickname, localId, fechaPublicacion, resenaTexto, ecosostenible, inclusionSocial, accesibilidad) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, opinion.getUsuario().getNickname());
            pstmt.setInt(2, opinion.getLocal().getId());
            pstmt.setTimestamp(3, opinion.getFechaPublicacion());
            pstmt.setString(4, opinion.getResenaTexto());
            pstmt.setInt(5, opinion.getEcosostenible());
            pstmt.setInt(6, opinion.getInclusionSocial());
            pstmt.setInt(7, opinion.getAccesibilidad());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener opinion en especifico
    public Opinion obtenerOpinionesPorId(int Id) {
        Opinion opinion = null;
        String sql = "SELECT * FROM Opiniones WHERE Id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                opinion = new Opinion(
                        rs.getInt("id"),
                        new Usuario(rs.getString("usuarioNickname"), "", "", "", null, false, null, new ArrayList<>()),
                        new Local(rs.getInt("localId"), "", "", null, "", "", 0, 0, 0),
                        rs.getTimestamp("fechaPublicacion"),
                        rs.getString("resenaTexto"),
                        rs.getInt("ecosostenible"),
                        rs.getInt("inclusionSocial"),
                        rs.getInt("accesibilidad"),
                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opinion;
    }

    // Método para obtener opiniones de un local específico
    public List<Opinion> obtenerOpinionesPorLocal(int localId) {
        List<Opinion> opiniones = new ArrayList<>();
        String sql = "SELECT * FROM Opiniones WHERE localId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, localId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Opinion opinion = new Opinion(
                        rs.getInt("id"),
                        new Usuario(rs.getString("usuarioNickname"), "", "", "", null, false, null, new ArrayList<>()),
                        new Local(rs.getInt("localId"), "", "", null, "", "", 0, 0, 0),
                        rs.getTimestamp("fechaPublicacion"),
                        rs.getString("resenaTexto"),
                        rs.getInt("ecosostenible"),
                        rs.getInt("inclusionSocial"),
                        rs.getInt("accesibilidad"),
                        new ArrayList<>()
                );
                opiniones.add(opinion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opiniones;
    }

    public List<Opinion> obtenerOpinionesPorUsuario(String usuarioNickname) {
        List<Opinion> opiniones = new ArrayList<>();
        String sql = "SELECT * FROM Opiniones WHERE usuarioNickname = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuarioNickname);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Opinion opinion = new Opinion(
                        rs.getInt("id"),
                        new Usuario(rs.getString("usuarioNickname"), "", "", "", null, false, null, new ArrayList<>()),
                        new Local(rs.getInt("localId"), "", "", null, "", "", 0, 0, 0),
                        rs.getTimestamp("fechaPublicacion"),
                        rs.getString("resenaTexto"),
                        rs.getInt("ecosostenible"),
                        rs.getInt("inclusionSocial"),
                        rs.getInt("accesibilidad"),
                        new ArrayList<>()
                );
                opiniones.add(opinion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opiniones;
    }


    public void actualizarOpinion(Opinion opinion) {
        String sql = "UPDATE Opiniones SET usuarioNickname = ?, localId = ?, resenaTexto = ?, ecosostenible = ?, inclusionSocial = ?, accesibilidad = ?, WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, opinion.getUsuario().getNickname());
            pstmt.setInt(2, opinion.getLocal().getId());
            pstmt.setString(3, opinion.getResenaTexto());
            pstmt.setInt(4, opinion.getEcosostenible());
            pstmt.setInt(5, opinion.getInclusionSocial());
            pstmt.setInt(6, opinion.getAccesibilidad());

            pstmt.setInt(7, opinion.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}