# 🎮 Collection System Web

O Collection System Web nasceu para resolver a vida de quem coleciona jogos de videogame. A ideia principal do sistema é permitir que o usuário cadastre e organize seus jogos em coleções personalizadas, vinculando informações fundamentais como gênero, tipo de mídia, descrição e a desenvolvedora do título. É o fim das planilhas manuais bagunçadas!

---

### ⏳ Linha do Tempo e Evolução do Projeto

O desenvolvimento deste ecossistema foi estruturado seguindo um ciclo incremental de engenharia de software, dividido em três grandes marcos de evolução:

1. 💻 Fase Desktop (Versão 1.0.0 - Swing): O projeto nasceu como uma aplicação desktop local baseada em Java Swing. Embora funcional para um único usuário local, a interface dependia de janelas pesadas do sistema operacional e possuía acoplamento rígido entre as regras visuais e o acesso ao banco de dados.
2. ⚙️ Fase Backend & Desacoplamento (Versão 1.5.0): Realizamos uma refatoração arquitetural profunda. Removemos completamente a interface Swing para limpar o motor do sistema. Isolamos o acesso ao MySQL via DAOs e criamos uma camada de Serviços (Service) isolada para centralizar as regras de negócio e validações de segurança sob os princípios do SOLID.
3. 🌐 Fase Web & Interface Dinâmica (Versão 2.0.0 - Atual): Consolidamos a transição para o ambiente web (Etapa 8 de 9). O sistema agora opera com uma interface moderna em formato Single-Page Experience (através de modais) e páginas físicas dedicadas, portando uma camada de comportamento em JavaScript puro ligada diretamente ao cliente.

---

### 🔄 Mudando a dinâmica: De Desktop para Web (Versão 2.0.0)

Como estamos na Etapa 8 de 9 do projeto completo, o foco mudou totalmente: saímos de um sistema Desktop local e consolidamos a nossa arquitetura para a internet.

Para fazer isso dar certo, aplicamos boas práticas de engenharia de software e limpamos o código antigo:

* 🚫 Tchau, Java Swing: Arrancamos todas as telas e os alertas visuais de dentro das classes de dados. Agora o motor roda limpo e sem travar.
* 🧩 Princípio da Responsabilidade Única (SOLID): Separamos quem cuida de cada coisa. Os DAOs agora fazem apenas consultas SQL puras no banco de dados e repassam os erros para cima.
* 🛡️ Nova Camada de Serviços (Service): Tiramos as lógicas de validação (como checar campos vazios ou usuários duplicados) que ficavam escondidas atrás dos botões da tela e colocamos tudo em classes dedicadas a regras de negócio.
* 🔒 Mais Segurança na Web: Eliminamos o uso de variáveis estáticas globais que guardavam o login na memória. Na Web isso misturaria os dados de usuários diferentes, então agora o contexto roda isolado por parâmetros.

---

### 🎨 Engenharia da Interface e Recursos Implementados

A nova camada Front-end foi projetada com foco em fidelidade visual à identidade original e usabilidade moderna:

* 🟢 Identidade Cromática: Tema Dark Mode corporativo e imersivo com elementos de destaque e botões de ação utilizando o verde vibrante original do protótipo (#2ecc71).
* 👤 Barra Lateral Unificada com Avatar: Menu de navegação padronizado contendo espaço exclusivo para a Logo/Avatar do usuário logado.
* 🚨 Validação Crítica com Alertas Individuais: O formulário de cadastro possui tags ocultas de erro que acendem em vermelho logo abaixo de cada input específico caso o usuário deixe campos obrigatórios vazios, alterando também a cor da borda do componente.
* 🔑 Trava de Segurança de Senhas: Validador lógico que impede o envio do formulário de cadastro de usuário caso a senha informada e a confirmação de senha não sejam matematicamente idênticas.
* 📅 Máscaras de Entrada de Dados: Algoritmo nativo em JavaScript que intercepta a digitação e formatar automaticamente strings numéricas no padrão cronológico DD/MM/AAAA (utilizado na data de nascimento do usuário e na data de lançamento dos jogos).
* 📊 Painel de Controle e Repositórios: O Dashboard centraliza estatísticas gerais e uma área de acesso rápido limitada aos 2 últimos repositórios acessados. A aba "Coleções" gerencia e lista a grade completa de catálogos do usuário.
* 🔍 Gerenciador de Títulos Responsivo: A tabela de listagem de jogos agora conta com filtro de pesquisa textual em tempo real, coluna dedicada para o tipo de mídia (Física/Digital) e botões de ação para Editar e Deletar registros através de modais fluidos.

---

### 🏗️ Estrutura Unificada de Diretórios do Projeto

A árvore de arquivos foi mapeada de forma idêntica ao ambiente físico do repositório, exibindo todos os recursos front-end planos na pasta principal ao lado do ecossistema backend:

📁 Collection System/               --> Módulos desacoplados e motores do servidor Java
    └── src/
        ├── 📦 dao                --> Camada pura de persistência JDBC e queries MySQL
        ├── 📦 exception          --> Exceções de tratamento de regras de negócio
        ├── 📦 main               --> Inicializadores e rotinas automatizadas de testes
        ├── 📦 model              --> Entidades estruturais do sistema (Usuário, Jogo, etc)
        └── 📦 service            --> Motor centralizado de validações lógicas e SOLID

📁 Web version/                       --> Diretório raiz plano da aplicação web
│
├── 📄 style.css                  --> Folha de estilos unificada (Dark Mode / Verde)
├── 📄 auth.js                    --> Script comportamental de validações e máscaras
├── 📄 index.html                 --> Tela de Login (Ponto de entrada do sistema)
├── 📄 cadastro-usuario.html      --> Formulário estruturado de cadastro de usuários
├── 📄 dashboard.html             --> Painel inicial com widgets e acesso rápido de 2 cards
├── 📄 colecoes.html              --> Tela de listagem geral de coleções cadastradas
└── 📄 colecao-detalhes.html      --> Tabela de jogos com barra de busca, mídias e botões