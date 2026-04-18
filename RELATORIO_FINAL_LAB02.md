# Relatorio Final - Sistema de Aluguel de Carros (LAB02)

## 1. Status do Projeto

Projeto academico com prototipo funcional em Java + Micronaut, cobrindo o fluxo principal de cadastro, pedidos de aluguel e analise financeira.

## 2. Objetivo

Construir um sistema web para gerenciamento de aluguel de carros, com perfis de cliente e agente, contemplando:

- cadastro e autenticacao
- criacao e manutencao de pedidos
- avaliacao financeira dos pedidos
- acompanhamento de status

## 3. Escopo Entregue

### 3.1 Funcionalidades implementadas

- CRUD de Cliente
- CRUD de Automovel
- CRUD de PedidoAluguel
- CRUD de Agente
- CRUD de Empregador
- Login/Cadastro de usuario
- Tela de analise financeira e avaliacao do pedido
- Tela de status do pedido

### 3.2 Funcionalidades modeladas (nao expostas como fluxo completo de UI)

- ContratoCredito (modelo, repositorio e servico)

## 4. Arquitetura e Tecnologias

### 4.1 Arquitetura

- MVC
- Separacao em Controller, Service, Repository e Model

### 4.2 Stack tecnica

- Java 11
- Micronaut 3.10.0
- Micronaut Data JPA + Hibernate
- Thymeleaf
- H2 Database
- Maven

## 5. Artefatos de Documentacao

Os artefatos principais estao no repositorio:

- README principal: ./README.md
- Historias de usuario: ./docs/historia-de-usuario.md
- Historias banco: ./docs/historia-banco
- Diagramas: ./docs/diagramas

## 6. Como Executar

### 6.1 Pre-requisitos

- Java 11+
- Maven

### 6.2 Build

```bash
mvn clean compile
mvn -q dependency:build-classpath -Dmdep.outputFile=target/classpath.txt
```

### 6.3 Run

PowerShell (Windows):

```powershell
$cp = 'target/classes;' + (Get-Content target/classpath.txt -Raw).Trim()
java -cp $cp com.example.carrental.CarrentalApplication
```

Depois acesse:

- http://localhost:8080/
- http://localhost:8080/clientes

## 7. Evidencias de Validacao

Durante a consolidacao final do projeto foram validados:

- compilacao sem erros
- carga das rotas principais (HTTP 200)
- fluxo CRUD completo das entidades principais
- fluxo de avaliacao financeira
- sincronizacao de templates para padrao Thymeleaf

## 8. Time

- Gabriel
- Camila
- Henrqieu

Professor: Joao Paulo Carneiro Aramuni  
Instituicao: PUC Minas
