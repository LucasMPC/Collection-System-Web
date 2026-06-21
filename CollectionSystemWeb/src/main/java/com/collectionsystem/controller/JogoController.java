package com.collectionsystem.controller;

import com.collectionsystem.model.Jogo;
import com.collectionsystem.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jogos")
@CrossOrigin(origins = "*")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("/colecao/{idColecao}")
    public ResponseEntity<List<Jogo>> listarPorColecao(
            @PathVariable int idColecao) {

        return ResponseEntity.ok(
            jogoService.listarPorColecao(idColecao)
        );
    }
    
    @GetMapping("/total/usuario/{idUsuario}")
    public ResponseEntity<?> totalJogos(
            @PathVariable int idUsuario) {

        return ResponseEntity.ok(
            Map.of(
                "total", jogoService.contarTotalJogosPorUsuario(idUsuario)
            )
        );
    }
    
    @GetMapping("/ultimo/usuario/{idUsuario}")
        public ResponseEntity<?> ultimoJogo(@PathVariable int idUsuario) {

            return ResponseEntity.ok(
                jogoService.buscarUltimoJogoPorUsuario(idUsuario)
            );
        }

    @PostMapping
    public ResponseEntity<?> cadastrarJogo(
            @RequestBody Jogo jogo) {

        jogoService.cadastrarJogo(jogo);

        return ResponseEntity.ok(
            Map.of("mensagem", "Jogo cadastrado com sucesso.")
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarJogo(
            @PathVariable int id,
            @RequestBody Jogo jogo) {

        jogo.setId(id);

        jogoService.atualizarJogo(jogo);

        return ResponseEntity.ok(
            Map.of("mensagem", "Jogo atualizado com sucesso.")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirJogo(
            @PathVariable int id) {

        jogoService.excluirJogo(id);

        return ResponseEntity.ok(
            Map.of("mensagem", "Jogo excluído com sucesso.")
        );
    }
}