package com.collectionsystem.service;

import com.collectionsystem.dao.ColecaoDAO;
import com.collectionsystem.exception.RegraNegocioException;
import com.collectionsystem.model.Colecao;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ColecaoService {

    private final ColecaoDAO colecaoDAO = new ColecaoDAO();

    public void cadastrarColecao(Colecao colecao, int idUsuario) {

        if (colecao.getNome() == null || colecao.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da coleção é obrigatório.");
        }

        try {
            colecaoDAO.cadastrar(colecao, idUsuario);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao salvar coleção: " + e.getMessage()
            );
        }
    }

    public List<Colecao> listarPorUsuario(int idUsuario) {

        try {
            return colecaoDAO.listarPorUsuario(idUsuario);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao listar coleções: " + e.getMessage()
            );
        }
    }
    
    public List<Colecao> listarRecentesPorUsuario(int idUsuario, int limite) {
        try {
            return colecaoDAO.listarRecentesPorUsuario(idUsuario, limite);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao listar coleções recentes: " + e.getMessage()
            );
        }
    }
    
    public int contarPorUsuario(int idUsuario) {
        try {
            return colecaoDAO.contarPorUsuario(idUsuario);
        } catch (SQLException e) {
            throw new RegraNegocioException(
                "Erro ao contar coleções: " + e.getMessage()
            );
        }
    }
}