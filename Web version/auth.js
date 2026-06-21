/**
 * auth.js
 * Lógica de Autenticação - Tela de Login (index.html)
 * Conecta com o método autenticarUsuario do seu UsuarioService.java
 */
function executarLogin(event) {
    debugger;
    event.preventDefault();
    const user = document.getElementById('login-username').value;
    const senha = document.getElementById('login-senha').value;
    const erroBox = document.getElementById('erro-auth');

    // Validação local rápida de campos vazios antes do envio ao servidor
    if (!user.trim() || !senha.trim()) {
        erroBox.textContent = "Erro de Validação: Informe o utilizador e a senha.";
        erroBox.style.display = 'block';
        return;
    }

    const payload = {
        username: user,
        senha: senha
    };

    // Dispara a requisição real para o endpoint do UsuarioController.java
    fetch('http://localhost:8080/api/usuarios/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })

        .then(async response => {
        let dadosServidor;

        try {
            dadosServidor = await response.json();
        } catch {
            dadosServidor = await response.text();
        }

        if (!response.ok) {
            erroBox.textContent = dadosServidor.erro || dadosServidor;
            erroBox.style.display = 'block';
        } else {
            erroBox.style.display = 'none';
            localStorage.setItem('usuarioId', dadosServidor.id);
            localStorage.setItem('usuarioLogado', dadosServidor.username);
            window.location.href = 'dashboard.html';
        }
    });

} // <<< FECHA executarLogin AQUI

function validarFormularioCadastro(event) {
        // Intercepta e suspende o recarregamento do formulário
        event.preventDefault();
        
        // Mapeamento dos elementos de entrada de dados (DOM)
        const inputNome = document.getElementById('cad-nome');
        const inputEmail = document.getElementById('cad-email');
        const inputUsername = document.getElementById('cad-username');
        const inputSenha = document.getElementById('cad-senha');
        const inputConfirmar = document.getElementById('cad-confirmar-senha');
        const inputData = document.getElementById('cad-data');

        // Mapeamento dos elementos de exibição de erro
        const erroNome = document.getElementById('erro-nome');
        const erroEmail = document.getElementById('erro-email');
        const erroUsername = document.getElementById('erro-username');
        const erroSenha = document.getElementById('erro-senha');
        const erroConfirmar = document.getElementById('erro-confirmar-senha');
        const erroData = document.getElementById('erro-data');

        let flagFormularioValido = true;

        // --- ETAPA 1: LIMPEZA DOS ALERTAS VISUAIS ANTERIORES ---
        const listaErros = [erroNome, erroEmail, erroUsername, erroSenha, erroConfirmar, erroData];
        listaErros.forEach(span => {
            if (span) span.style.display = 'none';
        });

        const listaInputs = [inputNome, inputEmail, inputUsername, inputSenha, inputConfirmar, inputData];
        listaInputs.forEach(input => {
            if (input) input.style.borderColor = 'var(--borda)';
        });

        // --- ETAPA 2: VERIFICAÇÃO DE OBRIGATORIEDADE INDIVIDUAL (CAMPOS EM BRANCO) ---
        if (!inputNome.value.trim()) {
            erroNome.style.display = 'block';
            inputNome.style.borderColor = 'var(--erro)';
            flagFormularioValido = false;
        }
        if (!inputEmail.value.trim()) {
            erroEmail.style.display = 'block';
            inputEmail.style.borderColor = 'var(--erro)';
            flagFormularioValido = false;
        }
        if (!inputUsername.value.trim()) {
            erroUsername.style.display = 'block';
            inputUsername.style.borderColor = 'var(--erro)';
            flagFormularioValido = false;
        }
        if (!inputSenha.value.trim()) {
            erroSenha.style.display = 'block';
            inputSenha.style.borderColor = 'var(--erro)';
            flagFormularioValido = false;
        }
        if (!inputConfirmar.value.trim()) {
            erroConfirmar.textContent = "* A Digitalização da confirmação de senha é obrigatória.";
            erroConfirmar.style.display = 'block';
            inputConfirmar.style.borderColor = 'var(--erro)';
            flagFormularioValido = false;
        }
        if (!inputData.value.trim()) {
            erroData.textContent = "* A Data de Nascimento é obrigatória.";
            erroData.style.display = 'block';
            inputData.style.borderColor = 'var(--erro)';
            flagFormularioValido = false;
        }

        // Se houver qualquer campo obrigatório em branco, interrompe o fluxo
        if (flagFormularioValido === false) {
            return;
        }

        // --- ETAPA 3: VERIFICAÇÃO SE OS DOIS CAMPOS DE SENHA COINCIDEM ---
        if (inputSenha.value !== inputConfirmar.value) {
            erroConfirmar.textContent = "* As senhas informadas não coincidem.";
            erroConfirmar.style.display = 'block';
            inputConfirmar.style.borderColor = 'var(--erro)';
            return; // Trava a execução do cadastro
        }

        // --- ETAPA 4: VERIFICAÇÃO DE INTEGRIDADE DA MÁSCARA DE DATA ---
        if (inputData.value.length < 10) {
            erroData.textContent = "* Informe uma data válida no formato DD/MM/AAAA.";
            erroData.style.display = 'block';
            inputData.style.borderColor = 'var(--erro)';
            return; // Trava a execução do cadastro
        }

        // --- ETAPA 5: INTEGRAÇÃO REAL COM O SERVIDOR (NOVO) ---
        const payload = {
            nome: inputNome.value,
            email: inputEmail.value,
            username: inputUsername.value,
            senha: inputSenha.value,
            dataNascimento: inputData.value
        };

        fetch('http://localhost:8080/api/usuarios/cadastro', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then(async response => {
            let dadosServidor;

            try {
                dadosServidor = await response.json();
            } catch {
                dadosServidor = await response.text();
            }

            if (!response.ok) {
                alert(dadosServidor.erro || dadosServidor);
            } else {
                alert(dadosServidor.mensagem || "Usuário cadastrado com sucesso!");
                window.location.href = 'index.html';
            }
        })
        .catch(error => {
            console.error(error);

            alert("Não foi possível conectar ao servidor.");
        });
}


function aplicarMascaraData(input) {
    let valor = input.value.replace(/\D/g, "");

    if (valor.length >= 3 && valor.length <= 4) {
        valor = valor.replace(/^(\d{2})(\d+)/, "$1/$2");
    } else if (valor.length >= 5) {
        valor = valor.replace(/^(\d{2})(\d{2})(\d+)/, "$1/$2/$3");
    }

    input.value = valor;
}
