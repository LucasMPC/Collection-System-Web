package com.collectionsystem.service;

import com.collectionsystem.dao.JogoDAO;
import com.collectionsystem.exception.RegraNegocioException;
import com.collectionsystem.model.Jogo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class JogoService {

    private final JogoDAO jogoDAO = new JogoDAO();

    public void cadastrarJogo(Jogo jogo) {

        if (jogo.getNome() == null || jogo.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do jogo é obrigatório.");
        }

        try {
            jogoDAO.cadastrar(jogo);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao cadastrar jogo: " + e.getMessage()
            );
        }
    }

    public List<Jogo> listarPorColecao(int idColecao) {

        try {
            return jogoDAO.listarPorColecao(idColecao);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao listar jogos: " + e.getMessage()
            );
        }
    }
    
    public void atualizarJogo(Jogo jogo) {

        try {
            jogoDAO.atualizar(jogo);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao atualizar jogo: " + e.getMessage()
            );
        }
    }

    public void excluirJogo(int id) {

        try {
            jogoDAO.excluir(id);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao excluir jogo: " + e.getMessage()
            );
        }
    }
    
    public int contarTotalJogosPorUsuario(int idUsuario) {
        try {
            return jogoDAO.contarTotalJogosPorUsuario(idUsuario);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao contar jogos: " + e.getMessage()
            );
        }
    }
    
    public Jogo buscarUltimoJogoPorUsuario(int idUsuario) {
        try {
            return jogoDAO.buscarUltimoJogoPorUsuario(idUsuario);
        } catch (SQLException e) {
            throw new RegraNegocioException("Erro ao buscar último jogo");
        }
    }
}