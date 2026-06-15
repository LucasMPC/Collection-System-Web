package dao;

import model.Jogo;
import model.Desenvolvedora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}