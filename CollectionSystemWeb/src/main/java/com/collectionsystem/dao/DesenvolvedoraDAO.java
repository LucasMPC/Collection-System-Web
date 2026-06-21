package com.collectionsystem.dao;

import com.collectionsystem.dao.ConnectionFactory;
import com.collectionsystem.model.Desenvolvedora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesenvolvedoraDAO {

    public void cadastrar(Desenvolvedora dev) throws SQLException {
        // SQL focado apenas na coluna existente: nome
        String sql = "INSERT INTO desenvolvedora (nome) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dev.getNome());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) dev.setId(rs.getInt(1));
            }
        }
    }

    public List<Desenvolvedora> listarTodas() throws SQLException {
     List<Desenvolvedora> lista = new ArrayList<>();
     String sql = "SELECT id, nome FROM desenvolvedora ORDER BY nome";

     try (Connection conn = ConnectionFactory.getConexao();
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery()) { // <--- LINHA CORRIGIDA AQUI!

         while (rs.next()) {
             Desenvolvedora dev = new Desenvolvedora();
             dev.setId(rs.getInt("id"));
             dev.setNome(rs.getString("nome"));
             lista.add(dev);
         }
     }
     return lista;
    }
}