package com.collectionsystem.dao;

import com.collectionsystem.dao.ConnectionFactory;
import com.collectionsystem.model.Jogo;
import com.collectionsystem.model.Desenvolvedora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class JogoDAO {

    public void cadastrar(Jogo jogo) throws SQLException {
        String sql = "INSERT INTO jogo (nome, data_lancamento, descricao, genero, tipo_midia, id_colecao, id_desenvolvedora) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, jogo.getNome());
            ps.setString(2, jogo.getDataLancamento());
            ps.setString(3, jogo.getDescricao());
            ps.setString(4, jogo.getGenero());
            ps.setString(5, jogo.getTipoMidia());
            ps.setInt(6, jogo.getIdColecao());
            
            if (jogo.getDesenvolvedora() != null) {
                ps.setInt(7, jogo.getDesenvolvedora().getId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }
            
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) jogo.setId(rs.getInt(1));
            }
        }
    }

    public List<Jogo> listarPorColecao(int idColecao) throws SQLException {
        List<Jogo> lista = new ArrayList<>();
        String sql = "SELECT j.*, d.nome as dev_nome FROM jogo j " +
                     "LEFT JOIN desenvolvedora d ON j.id_desenvolvedora = d.id " +
                     "WHERE j.id_colecao = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idColecao);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Jogo j = new Jogo();
                    j.setId(rs.getInt("id"));
                    j.setNome(rs.getString("nome"));
                    j.setDataLancamento(rs.getString("data_lancamento"));
                    j.setDescricao(rs.getString("descricao"));
                    j.setGenero(rs.getString("genero"));
                    j.setTipoMidia(rs.getString("tipo_midia"));
                    j.setIdColecao(rs.getInt("id_colecao"));
                    
                    int devId = rs.getInt("id_desenvolvedora");
                    if (!rs.wasNull()) {
                        j.setDesenvolvedora(new Desenvolvedora(devId, rs.getString("dev_nome")));
                    }
                    lista.add(j);
                }
            }
        }
        return lista;
    }

    public int contarTotalJogosPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM jogo j JOIN colecao c ON j.id_colecao = c.id WHERE c.id_usuario = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }
    
    public void atualizar(Jogo jogo) throws SQLException {

        String sql = """
            UPDATE jogo
            SET nome = ?,
                data_lancamento = ?,
                genero = ?,
                tipo_midia = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jogo.getNome());
            ps.setString(2, jogo.getDataLancamento());
            ps.setString(3, jogo.getGenero());
            ps.setString(4, jogo.getTipoMidia());
            ps.setInt(5, jogo.getId());

            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM jogo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
    
    public Jogo buscarUltimoJogoPorUsuario(int idUsuario) throws SQLException {
        String sql = """
            SELECT j.*
            FROM jogo j
            JOIN colecao c ON j.id_colecao = c.id
            WHERE c.id_usuario = ?
            ORDER BY j.id DESC
            LIMIT 1
        """;

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Jogo j = new Jogo();
                    j.setId(rs.getInt("id"));
                    j.setNome(rs.getString("nome"));
                    return j;
                }
            }
        }

        return null;
    }
}