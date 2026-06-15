package service;

import dao.UsuarioDAO;
import exception.RegraNegocioException;
import model.Usuario;
import java.sql.SQLException;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void registrarNovoUsuario(Usuario usuario) {
        if (usuario.getNome().trim().isEmpty() || usuario.getUsername().trim().isEmpty() || usuario.getSenha().trim().isEmpty()) {
            throw new RegraNegocioException("Erro de Validacao: Nome, Username e Senha sao campos obrigatorios.");
        }
        try {
            if (usuarioDAO.existeUsername(usuario.getUsername())) {
                throw new RegraNegocioException("Conflito de Regra: Este nome de utilizador já está a ser utilizado.");
            }
            usuarioDAO.cadastrar(usuario);
        } catch (SQLException e) {
            throw new RegraNegocioException("Erro crítico de infraestrutura ao salvar utilizador: " + e.getMessage());
        }
    }

    public Usuario autenticarUsuario(String username, String senha) {
        if (username.trim().isEmpty() || senha.trim().isEmpty()) {
            throw new RegraNegocioException("Erro de Validação: Informe o utilizador e a senha.");
        }
        try {
            Usuario usuario = usuarioDAO.autenticar(username, senha);
            if (usuario == null) {
                throw new RegraNegocioException("Autenticação Falhou: Utilizador ou senha inválidos.");
            }
            return usuario;
        } catch (SQLException e) {
            throw new RegraNegocioException("Erro de persistência na autenticação: " + e.getMessage());
        }
    }
}