package com.collectionsystem.dao;

import com.collectionsystem.model.Colecao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ColecaoDAO {

    // Adicionamos o "int idUsuario" como parâmetro do método
    public void cadastrar(Colecao colecao, int idUsuario) throws SQLException {
        String sql = "INSERT INTO colecao (nome, icone, id_usuario) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, colecao.getNome());
            ps.setString(2, colecao.getIcone());
            ps.setInt(3, idUsuario); // <--- Usa o ID que veio do parâmetro

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    colecao.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Colecao> listarPorUsuario(int idUsuario) throws SQLException {
        List<Colecao> lista = new ArrayList<>();
        String sql = "SELECT * FROM colecao WHERE id_usuario = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Colecao c = new Colecao();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setIcone(rs.getString("icone"));
                    lista.add(c);
                }
            }
        }
        return lista;
    }
    
    public List<Colecao> listarRecentesPorUsuario(int idUsuario, int limite)
        throws SQLException {

        List<Colecao> lista = new ArrayList<>();

        String sql = """
            SELECT *
            FROM colecao
            WHERE id_usuario = ?
            ORDER BY id DESC
            LIMIT ?
        """;

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, limite);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Colecao c = new Colecao();

                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setIcone(rs.getString("icone"));

                    lista.add(c);
                }
            }
        }

        return lista;
    }
    
    public int contarPorUsuario(int idUsuario) throws SQLException {
        String sql = """
            SELECT COUNT(*)
            FROM colecao
            WHERE id_usuario = ?
        """;

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }
}