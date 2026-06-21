package com.collectionsystem.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author l3huz
 */
import com.collectionsystem.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilDAO {

    // Busca a lista de strings de Gêneros
    public List<String> listarGeneros() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nome FROM lista_generos ORDER BY nome";
        
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Busca a lista de strings de Mídias
    public List<String> listarMidias() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nome FROM lista_midias";
        
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
