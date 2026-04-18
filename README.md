# Sistema de Aluguel de Carros - LAB02

<p align="center">
  <img src="https://img.shields.io/badge/Java-11-orange" alt="Java">
  <img src="https://img.shields.io/badge/Micronaut-3.10.0-blue" alt="Micronaut">
  <img src="https://img.shields.io/badge/Arquitetura-MVC-brightgreen" alt="MVC">
  <img src="https://img.shields.io/badge/Status-Prototipo%20Funcional-success" alt="Status">
</p>

---

## Indice

- [Links Uteis](#links-uteis)
- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades Principais](#funcionalidades-principais)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Instalacao e Execucao](#instalacao-e-execucao)
- [Estrutura de Pastas](#estrutura-de-pastas)
- [Demonstracao](#demonstracao)
- [Testes](#testes)
- [Documentacoes Utilizadas](#documentacoes-utilizadas)
- [Autores](#autores)
- [Contribuicao](#contribuicao)
- [Licenca](#licenca)

---

## Links Uteis

- Aplicacao local: http://localhost:8080/clientes
- Historias de usuario: ./docs/historia-de-usuario.md
- Historias banco: ./docs/historia-banco
- Diagramas: ./docs/diagramas
- Relatorio final: ./RELATORIO_FINAL_LAB02.md

---

## Sobre o Projeto

Este projeto implementa um sistema web para gestao de aluguel de carros no contexto do LAB02. O objetivo e permitir que clientes cadastrem e acompanhem pedidos de aluguel, enquanto agentes (bancos e empresas) realizam analise financeira e decisao de aprovacao.

O sistema resolve a necessidade de centralizar o fluxo de solicitacao de aluguel, validacao financeira e acompanhamento de status em um unico ambiente, com operacoes CRUD para as entidades principais.

---

## Funcionalidades Principais

- Autenticacao de usuario (login, cadastro e logout)
- CRUD de clientes
- CRUD de automoveis
- CRUD de pedidos de aluguel
- Consulta de status do pedido
- Analise financeira de pedidos por agentes
- CRUD de agentes (banco/empresa)
- CRUD de empregadores vinculados ao cliente

---

## Tecnologias Utilizadas

### Back-end

- Java 11
- Micronaut 3.10.0
- Micronaut Data + Hibernate JPA
- H2 Database
- Thymeleaf (views server-side)

### Arquitetura

- MVC
- Camadas de Controller, Service, Repository e Model

### Build

- Maven

---

## Arquitetura

O projeto segue arquitetura MVC com separacao clara de responsabilidades:

- Controller: recebe requisicoes HTTP e monta modelos para as views
- Service: concentra regras de negocio e orquestracao
- Repository: acesso aos dados com Micronaut Data JPA
- Model: entidades de dominio (Cliente, Automovel, PedidoAluguel, Agente, etc)
- Views: paginas HTML Thymeleaf em src/main/resources/views

Diagramas UML estao disponiveis em ./docs/diagramas.

---

## Instalacao e Execucao

### Pre-requisitos

- Java 11+
- Maven 3.8+

### Como executar

```bash
mvn clean compile
mvn -q dependency:build-classpath -Dmdep.outputFile=target/classpath.txt
```

Depois, execute a aplicacao:

```bash
java -cp "target/classes;$(cat target/classpath.txt)" com.example.carrental.CarrentalApplication
```

Em Windows PowerShell:

```powershell
$cp = 'target/classes;' + (Get-Content target/classpath.txt -Raw).Trim()
java -cp $cp com.example.carrental.CarrentalApplication
```

Acesse:

- http://localhost:8080/
- http://localhost:8080/clientes

---

## Estrutura de Pastas

```text
.
├── docs/
│   ├── diagramas/
│   ├── historia-de-usuario.md
│   └── historia-banco
├── src/main/java/com/example/carrental/
│   ├── controller/
│   ├── model/
│   ├── repository/
│   └── service/
├── src/main/resources/
│   ├── views/
│   └── templates/
├── RELATORIO_FINAL_LAB02.md
└── README.md
```

---

## Demonstracao

Fluxo principal validado:

1. Cadastro/Login
2. Cadastro de cliente
3. Cadastro de automovel
4. Criacao de pedido
5. Avaliacao financeira por agente
6. Consulta do status do pedido

---

## Testes

Compilacao e checagem rapida:

```bash
mvn clean compile
mvn test
```

Teste funcional manual recomendado:

- Abrir telas de clientes, automoveis, pedidos e agentes
- Executar create/edit/delete nas entidades principais
- Validar aprovacao/rejeicao em analise financeira

---

## Documentacoes Utilizadas

- Micronaut: https://docs.micronaut.io/latest/guide/
- Micronaut Data: https://micronaut-projects.github.io/micronaut-data/latest/guide/
- Thymeleaf: https://www.thymeleaf.org/documentation.html
- Maven: https://maven.apache.org/guides/

---

## Autores

- Gabriel Afonso Infante Vieira
- Camila
- Henrque Flores

Professor: Joao Paulo Carneiro Aramuni  
Instituicao: PUC Minas

---

## Contribuicao

1. Crie uma branch para sua alteracao
2. Faca commits claros
3. Abra um pull request com descricao objetiva

---

## Licenca

Projeto academico para fins didaticos no LAB02.
