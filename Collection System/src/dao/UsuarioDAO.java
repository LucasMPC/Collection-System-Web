package dao;

import model.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public void cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, username, senha, data_nascimento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getUsername());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getDataNascimento());
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }
    }

    public Usuario autenticar(String username, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE username = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setUsername(rs.getString("username"));
                    u.setDataNascimento(rs.getString("data_nascimento"));
                    return u;
                }
            }
        }
        return null;
    }

    public boolean existeUsername(String username) throws SQLException {
        String sql = "SELECT id FROM usuario WHERE username = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}