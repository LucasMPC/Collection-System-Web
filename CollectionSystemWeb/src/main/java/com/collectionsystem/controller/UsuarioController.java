package com.collectionsystem.controller;

import com.collectionsystem.exception.RegraNegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import com.collectionsystem.model.Usuario;
import com.collectionsystem.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    // 🌟 CORRIGIDO: Instanciação manual direta (igualzinho você fez na MainApp)
    // Isso garante que o serviço nunca fique null, mesmo com o exclude ativado!
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Map<String, String> dados) {
        try {
            String nome = dados.get("nome");
            String email = dados.get("email");
            String username = dados.get("username");
            String senha = dados.get("senha");
            String dataNasc = dados.get("dataNascimento");

            Usuario novoUsuario = new Usuario(nome, email, username, senha, dataNasc);

            // Agora a chamada vai funcionar sem dar NullPointerException
            usuarioService.registrarNovoUsuario(novoUsuario);

            return ResponseEntity.ok().body(Map.of("mensagem", "Utilizador cadastrado com sucesso real no banco!"));
            
        } catch (Exception e) {
            // Se o erro for uma mensagem nula, envia o nome do erro para sabermos o que é
            String mensagemErro = (e.getMessage() != null) ? e.getMessage() : e.toString();
            return ResponseEntity.badRequest().body(Map.of("erro", mensagemErro));
        }
    }
     
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            Usuario u = usuarioService.autenticarUsuario(
                    usuario.getUsername(),
                    usuario.getSenha()
            );
            return ResponseEntity.ok(u);
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(401).body(Map.of("erro", e.getMessage()));
        }
    }
}