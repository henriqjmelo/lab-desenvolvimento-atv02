// =====================================
// SUBSISTEMA 1: GESTAO DE PEDIDOS E CONTRATOS
// =====================================

let clienteCadastrado = null;
let clienteLogado = null;
let pedidos = [];
let contadorPedidos = 1;
let pedidoEmEdicao = null;

// Formularios
const formLogin = document.getElementById("formLogin");
const formCadastro = document.getElementById("formCadastro");
const formPedido = document.getElementById("formPedido");
const formAgente = document.getElementById("formAgente");

// Mensagens
const mensagemLogin = document.getElementById("mensagemLogin");
const mensagemCadastro = document.getElementById("mensagemCadastro");
const mensagemPedido = document.getElementById("mensagemPedido");
const mensagemAgente = document.getElementById("mensagemAgente");

// Areas dinamicas
const listaPedidos = document.getElementById("listaPedidos");
const pedidoSelecionado = document.getElementById("pedidoSelecionado");

// Cadastro
formCadastro.addEventListener("submit", function (e) {
  e.preventDefault();

  clienteCadastrado = {
    nome: document.getElementById("nome").value.trim(),
    cpf: document.getElementById("cpf").value.trim(),
    rg: document.getElementById("rg").value.trim(),
    endereco: document.getElementById("endereco").value.trim(),
    profissao: document.getElementById("profissao").value.trim(),
    rendas: [
      {
        empregador: document.getElementById("empregador1").value.trim(),
        renda: document.getElementById("renda1").value.trim()
      },
      {
        empregador: document.getElementById("empregador2").value.trim(),
        renda: document.getElementById("renda2").value.trim()
      },
      {
        empregador: document.getElementById("empregador3").value.trim(),
        renda: document.getElementById("renda3").value.trim()
      }
    ]
  };

  clienteLogado = null;
  mensagemCadastro.style.color = "#34d399";
  mensagemCadastro.textContent = `Cliente ${clienteCadastrado.nome} cadastrado com sucesso. Agora faca o login.`;
  formCadastro.reset();
});

// Login
formLogin.addEventListener("submit", function (e) {
  e.preventDefault();

  const loginCpf = document.getElementById("loginCpf").value.trim();
  const loginNome = document.getElementById("loginNome").value.trim().toLowerCase();

  if (!clienteCadastrado) {
    mensagemLogin.style.color = "#f87171";
    mensagemLogin.textContent = "Nenhum cliente cadastrado. Faca o cadastro primeiro.";
    return;
  }

  if (
    clienteCadastrado.cpf === loginCpf &&
    clienteCadastrado.nome.toLowerCase() === loginNome
  ) {
    clienteLogado = clienteCadastrado;
    mensagemLogin.style.color = "#34d399";
    mensagemLogin.textContent = `Login realizado com sucesso. Bem-vindo(a), ${clienteLogado.nome}.`;
    formLogin.reset();
  } else {
    mensagemLogin.style.color = "#f87171";
    mensagemLogin.textContent = "Dados de login invalidos.";
  }
});

// Pedido
formPedido.addEventListener("submit", function (e) {
  e.preventDefault();

  if (!clienteLogado) {
    mensagemPedido.style.color = "#f87171";
    mensagemPedido.textContent = "E necessario fazer login para registrar um pedido.";
    return;
  }

  const temCredito = document.getElementById("temCredito").value;
  const bancoAgente = document.getElementById("bancoAgente").value.trim();

  if (temCredito === "Sim" && bancoAgente === "") {
    mensagemPedido.style.color = "#f87171";
    mensagemPedido.textContent = "Informe o banco agente quando houver contrato de credito.";
    return;
  }

  const dadosPedido = {
    cliente: clienteLogado.nome,
    matricula: document.getElementById("matricula").value.trim(),
    ano: document.getElementById("ano").value.trim(),
    marca: document.getElementById("marca").value.trim(),
    modelo: document.getElementById("modelo").value.trim(),
    placa: document.getElementById("placa").value.trim(),
    tipoProprietario: document.getElementById("tipoProprietario").value,
    temCredito: temCredito,
    bancoAgente: bancoAgente || "Nao se aplica",
    status: "Em analise financeira",
    observacao: "Pedido criado pelo cliente"
  };

  if (pedidoEmEdicao) {
    const pedido = pedidos.find((p) => p.id === pedidoEmEdicao);

    if (pedido) {
      pedido.matricula = dadosPedido.matricula;
      pedido.ano = dadosPedido.ano;
      pedido.marca = dadosPedido.marca;
      pedido.modelo = dadosPedido.modelo;
      pedido.placa = dadosPedido.placa;
      pedido.tipoProprietario = dadosPedido.tipoProprietario;
      pedido.temCredito = dadosPedido.temCredito;
      pedido.bancoAgente = dadosPedido.bancoAgente;
      pedido.observacao = "Pedido modificado pelo cliente";
    }

    mensagemPedido.style.color = "#34d399";
    mensagemPedido.textContent = `Pedido #${pedidoEmEdicao} modificado com sucesso.`;
    pedidoEmEdicao = null;
  } else {
    const novoPedido = {
      id: contadorPedidos++,
      ...dadosPedido
    };

    pedidos.push(novoPedido);
    mensagemPedido.style.color = "#34d399";
    mensagemPedido.textContent = `Pedido #${novoPedido.id} registrado com sucesso.`;
  }

  formPedido.reset();
  atualizarPedidos();
});

// Atualizacao por agentes
formAgente.addEventListener("submit", function (e) {
  e.preventDefault();

  const idPedido = Number(document.getElementById("pedidoSelecionado").value);
  const novoStatus = document.getElementById("novoStatus").value;
  const observacaoAgente = document.getElementById("observacaoAgente").value.trim();

  const pedido = pedidos.find((p) => p.id === idPedido);

  if (!pedido) {
    mensagemAgente.style.color = "#f87171";
    mensagemAgente.textContent = "Pedido nao encontrado.";
    return;
  }

  pedido.status = novoStatus;

  if (observacaoAgente !== "") {
    pedido.observacao = observacaoAgente;
  } else {
    pedido.observacao = "Pedido atualizado pelo agente";
  }

  mensagemAgente.style.color = "#34d399";
  mensagemAgente.textContent = `Pedido #${pedido.id} atualizado com sucesso pelo agente.`;

  formAgente.reset();
  atualizarPedidos();
});

// =====================================
// SUBSISTEMA 2: CONSTRUCAO DINAMICA DAS PAGINAS WEB
// =====================================

function atualizarPedidos() {
  renderizarPedidos();
  atualizarSelectPedidos();
}

function renderizarPedidos() {
  listaPedidos.innerHTML = "";

  if (pedidos.length === 0) {
    listaPedidos.innerHTML = `<p class="vazio">Nenhum pedido cadastrado ainda.</p>`;
    return;
  }

  pedidos.forEach((pedido) => {
    const card = document.createElement("div");
    card.className = "pedido";

    card.innerHTML = `
      <h3>Pedido #${pedido.id}</h3>
      <p><strong>Cliente:</strong> ${pedido.cliente}</p>
      <p><strong>Automovel:</strong> ${pedido.marca} ${pedido.modelo}</p>
      <p><strong>Matricula:</strong> ${pedido.matricula}</p>
      <p><strong>Placa:</strong> ${pedido.placa}</p>
      <p><strong>Ano:</strong> ${pedido.ano}</p>
      <p><strong>Proprietario:</strong> ${pedido.tipoProprietario}</p>
      <p><strong>Contrato de credito:</strong> ${pedido.temCredito}</p>
      <p><strong>Banco agente:</strong> ${pedido.bancoAgente}</p>
      <p><strong>Status:</strong> ${pedido.status}</p>
      <p><strong>Observacao:</strong> ${pedido.observacao}</p>
      <div class="acoes">
        <button class="btn-editar" onclick="editarPedido(${pedido.id})">Modificar</button>
        <button class="btn-cancelar" onclick="cancelarPedido(${pedido.id})">Cancelar</button>
      </div>
    `;

    listaPedidos.appendChild(card);
  });
}

function atualizarSelectPedidos() {
  pedidoSelecionado.innerHTML = `<option value="">Selecione um pedido</option>`;

  pedidos.forEach((pedido) => {
    const option = document.createElement("option");
    option.value = pedido.id;
    option.textContent = `Pedido #${pedido.id} - ${pedido.cliente}`;
    pedidoSelecionado.appendChild(option);
  });
}

function editarPedido(id) {
  const pedido = pedidos.find((p) => p.id === id);

  if (!pedido) {
    return;
  }

  document.getElementById("matricula").value = pedido.matricula;
  document.getElementById("ano").value = pedido.ano;
  document.getElementById("marca").value = pedido.marca;
  document.getElementById("modelo").value = pedido.modelo;
  document.getElementById("placa").value = pedido.placa;
  document.getElementById("tipoProprietario").value = pedido.tipoProprietario;
  document.getElementById("temCredito").value = pedido.temCredito;
  document.getElementById("bancoAgente").value =
    pedido.bancoAgente === "Nao se aplica" ? "" : pedido.bancoAgente;

  pedidoEmEdicao = id;
  mensagemPedido.style.color = "#fbbf24";
  mensagemPedido.textContent = `Editando o pedido #${id}. Faca as alteracoes e clique em "Registrar Pedido".`;
}

function cancelarPedido(id) {
  const pedido = pedidos.find((p) => p.id === id);

  if (!pedido) {
    return;
  }

  pedido.status = "Cancelado";
  pedido.observacao = "Pedido cancelado pelo cliente";

  atualizarPedidos();
}