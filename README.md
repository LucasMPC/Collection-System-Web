# Collection System Web

## 🎯 O que o projeto faz?
O **Collection System Web** nasceu para resolver a vida de quem coleciona jogos de videogame. A ideia principal do sistema é permitir que o usuário cadastre e organize seus jogos em coleções personalizadas (ex: "Jogos de PC", "Favoritos de PS4"), vinculando informações fundamentais como gênero, tipo de mídia, descrição e a desenvolvedora do título. É o fim das planilhas manuais bagunçadas!

## 🔄 Mudando a dinâmica: De Desktop para Web (Versão 2.0.0)
Este repositório marca o início da **Versão 2.0.0** do nosso software. Como estamos na **Etapa 6 de 9** do projeto completo, o foco mudou totalmente: saímos de um sistema Desktop local e criamos um motor backend pronto para a internet.

Para fazer isso dar certo, aplicamos boas práticas de engenharia de software e limpamos o código antigo:
* **Tchau, Java Swing:** Arrancamos todas as telas (`javax.swing.*`) e os alertas visuais (`JOptionPane`) de dentro das classes de dados. Agora o motor roda limpo e sem travar.
* **Princípio da Responsabilidade Única (SOLID):** Separamos quem cuida de cada coisa. Os DAOs agora fazem apenas consultas SQL puras no banco de dados e repassam os erros para cima.
* **Nova Camada de Serviços (Service):** Tiramos as lógicas de validação (como checar campos vazios ou usuários duplicados) que ficavam escondidas atrás dos botões da tela e colocamos tudo em classes dedicadas a regras de negócio.
* **Mais Segurança na Web:** Eliminamos o uso de variáveis estáticas globais (`DadosTemporarios`) que guardavam o login na memória. Na Web isso misturaria os dados de usuários diferentes, então agora o contexto roda isolado por parâmetros.

## 🏗️ Como ficaram as pastas (Packages)
O projeto no NetBeans foi reestruturado de forma modularizada:

```text
src/
├── 📦 dao          -> Persistência Pura (Onde o JDBC e o MySQL conversam)
├── 📦 exception    -> Erros customizados para interceptar regras quebradas
├── 📦 main         -> Ponto de partida que roda os testes automáticos
├── 📦 model        -> Classes de objetos puras (Usuário, Jogo, Coleção, Dev)
└── 📦 service      -> Onde as validações e regras de negócio acontecem
