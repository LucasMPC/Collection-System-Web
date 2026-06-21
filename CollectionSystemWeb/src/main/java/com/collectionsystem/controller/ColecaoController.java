package com.collectionsystem.controller;

import com.collectionsystem.model.Colecao;
import com.collectionsystem.service.ColecaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/colecoes")
@CrossOrigin(origins = "*")
public class ColecaoController {

    private final ColecaoService colecaoService;

    public ColecaoController(ColecaoService colecaoService) {
        this.colecaoService = colecaoService;
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Colecao>> listarColecoes(
            @PathVariable int idUsuario) {

        return ResponseEntity.ok(
                colecaoService.listarPorUsuario(idUsuario)
        );
    }
    
    @GetMapping("/usuario/{idUsuario}/recentes")
    public ResponseEntity<List<Colecao>> listarColecoesRecentes(
            @PathVariable int idUsuario) {

        return ResponseEntity.ok(
            colecaoService.listarRecentesPorUsuario(idUsuario, 2)
        );
    }
    
    @GetMapping("/total/usuario/{idUsuario}")
    public ResponseEntity<?> totalColecoes(
            @PathVariable int idUsuario) {

        return ResponseEntity.ok(
            Map.of(
                "total", colecaoService.contarPorUsuario(idUsuario)
            )
        );
    }

    @PostMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> cadastrarColecao(
            @PathVariable int idUsuario,
            @RequestBody Map<String, String> dados) {

        Colecao colecao = new Colecao();

        colecao.setNome(dados.get("nome"));
        colecao.setIcone(dados.getOrDefault("icone", "default"));

        colecaoService.cadastrarColecao(colecao, idUsuario);

        return ResponseEntity.ok(
                Map.of("mensagem", "Coleção criada com sucesso.")
        );
    }
}