package service;

import exception.RegraNegocioException;
import model.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioServiceTest {

    @Test
    public void testRegistrarUsuarioComCamposVaziosDeveriaFalhar() {
        UsuarioService service = new UsuarioService();
        
        Usuario usuarioInvalido = new Usuario();
        usuarioInvalido.setNome(" ");
        usuarioInvalido.setUsername(" ");
        usuarioInvalido.setSenha(" ");
        
        try {
            service.registrarNovoUsuario(usuarioInvalido);
            fail("Deveria ter lançado uma RegraNegocioException por causa dos campos vazios.");
        } catch (RegraNegocioException e) {
            assertEquals("Erro de Validacao: Nome, Username e Senha sao campos obrigatorios.", e.getMessage());
        }
    }

    @Test
    public void testAutenticarUsuarioComCamposVaziosDeveriaFalhar() {
        UsuarioService service = new UsuarioService();
        
        try {
            service.autenticarUsuario("", " ");
            fail("Deveria ter lançado uma RegraNegocioException por falta de dados no login.");
        } catch (RegraNegocioException e) {
            assertEquals("Erro de Validação: Informe o utilizador e a senha.", e.getMessage());
        }
    }
}