package main;

import model.*;
import service.UsuarioService;
import dao.*;
import exception.RegraNegocioException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println(" INICIANDO TESTES DO MOTOR DE BACKEND REFATORADO ");
        System.out.println("=====================================================\n");

        UsuarioService usuarioService = new UsuarioService();
        ColecaoDAO colecaoDAO = new ColecaoDAO();
        DesenvolvedoraDAO devDAO = new DesenvolvedoraDAO();
        JogoDAO jogoDAO = new JogoDAO();

        // Gerar username aleatório para evitar colisões nos testes contínuos do main
        String uniqueUser = "tester_" + System.currentTimeMillis();

        try {
            // TESTE 1: Validação de Campo Obrigatório
            System.out.print("[TESTE 1] Validando rejeicao de campos vazios... ");
            try {
                usuarioService.registrarNovoUsuario(new Usuario("", "email@test.com", "", "", "10/10/1990"));
                System.out.println("❌ FALHOU (Aceitou registro inválido)");
            } catch (RegraNegocioException e) {
                System.out.println("✅ OK (" + e.getMessage() + ")");
            }

            // TESTE 2: Cadastro Válido de Usuário
            System.out.print("[TESTE 2] Registrando novo utilizador valido [" + uniqueUser + "]... ");
            Usuario novoUsuario = new Usuario("Lucas Camargo", "lucas@test.com", uniqueUser, "senha123", "25/12/1998");
            usuarioService.registrarNovoUsuario(novoUsuario);
            System.out.println("✅ OK (ID gerado no banco: " + novoUsuario.getId() + ")");

            // TESTE 3: Autenticação
            System.out.print("[TESTE 3] Validando fluxo de login do utilizador... ");
            Usuario logado = usuarioService.autenticarUsuario(uniqueUser, "senha123");
            System.out.println("✅ OK (Bem-vindo, " + logado.getNome() + "!)");

            // TESTE 4: Criação de Coleção Sem Janelas Swing
            System.out.print("[TESTE 4] Criando uma colecao para o utilizador logado... ");
            // Criamos a coleção usando os parâmetros que sua classe original aceita
            Colecao novaColecao = new Colecao("Jogos de PC", "PC_ICON"); 

            // Passamos a coleção E o ID do usuário logado separadamente para o DAO
            colecaoDAO.cadastrar(novaColecao, logado.getId()); 
            System.out.println("✅ OK (Colecao '" + novaColecao.getNome() + "' criada com ID: " + novaColecao.getId() + ")");
            
            // TESTE 5: Persistência de Desenvolvedora e Jogos
            System.out.print("[TESTE 5] Cadastrando Desenvolvedora de Jogos... ");
            Desenvolvedora valve = new Desenvolvedora(); 
            valve.setNome("Valve Software"); // Passamos apenas o nome, pois o banco só tem 'id' e 'nome'
            devDAO.cadastrar(valve);
            System.out.println("✅ OK (ID Dev: " + valve.getId() + ")");

            System.out.print("[TESTE 6] Vinculando e inserindo jogo na colecao... ");
            Jogo halfLife = new Jogo("Half-Life 2", "16/11/2004", "Clássico Sci-Fi FPS", "FPS", "Digital", novaColecao.getId(), valve);
            jogoDAO.cadastrar(halfLife);
            System.out.println("✅ OK (Jogo cadastrado com sucesso!)");

            // TESTE 6: Listagem Limpa via Terminal (Preparada para JSON/Web no futuro)
            System.out.println("\n-----------------------------------------------------");
            System.out.println(" RECUPERACAO DE DADOS (SIMULACAO DE RETORNO WEB):");
            List<Jogo> jogos = jogoDAO.listarPorColecao(novaColecao.getId());
            for (Jogo j : jogos) {
                System.out.println(" -> Jogo: " + j.getNome() + " | Genero: " + j.getGenero() + " | Desenvolvedora: " + (j.getDesenvolvedora() != null ? j.getDesenvolvedora().getNome() : "N/A"));
            }
            
            int total = jogoDAO.contarTotalJogosPorUsuario(logado.getId());
            System.out.println("Total de jogos do utilizador no sistema: " + total);
            System.out.println("-----------------------------------------------------");
            System.out.println("\nSUCESSO TOTAL: Todos os testes integrados rodaram sem acoplamento grafico!");

        } catch (Exception e) {
            System.out.println("\nCRÍTICO: O fluxo de teste quebrou inesperadamente!");
            e.printStackTrace();
        }
    }
}