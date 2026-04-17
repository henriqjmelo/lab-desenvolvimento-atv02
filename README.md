# Sistema de Aluguel de Carros (LAB02)

<p align="center">
  <img src="https://img.shields.io/badge/Linguagem-Java-orange" alt="Java">
  <img src="https://img.shields.io/badge/Framework-Spring%20Boot-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Arquitetura-MVC-blue" alt="MVC">
  <img src="https://img.shields.io/badge/Status-Sprint%2001-yellow" alt="Sprint 01">
</p>

## Descricao do Projeto

Este repositorio contem o desenvolvimento do **Sistema de Aluguel de Carros**, um projeto pratico da disciplina *Laboratorio de Desenvolvimento de Software* (LAB02) do curso de **Engenharia de Software**.

O objetivo e criar uma plataforma web que permita a gestao completa de alugueis de automoveis. O sistema atende a diferentes perfis de usuarios (Clientes, Empresas e Bancos), permitindo desde o cadastro e solicitacao de aluguel ate a analise financeira e formalizacao de contratos de credito.

O projeto e desenvolvido em **Java**, utilizando a arquitetura **MVC** e seguindo um cronograma de desenvolvimento agil dividido em 3 Sprints.

---

## Estrutura do Repositorio (Sprint 01)

Atualmente, o projeto encontra-se na fase de modelagem. Os artefatos disponiveis sao:

- **Diagrama de Casos de Uso**: Representacao das interacoes entre os atores (Clientes e Agentes) e o sistema.
- **Historias de Usuario**: Detalhamento das funcionalidades sob a otica do usuario final.
- **Diagrama de Classes**: Modelagem estrutural das entidades (Clientes, Automoveis, Agentes, Contratos).
- **Diagrama de Pacotes**: Visao logica da organizacao do sistema (Visao Logica/Arquitetural).

---

## Funcionalidades Planejadas

- **Cadastro de Usuarios**: Registro obrigatorio para utilizacao do sistema.
- **Gestao de Pedidos**: Clientes podem introduzir, modificar, consultar e cancelar pedidos de aluguel.
- **Analise Financeira**: Agentes (Bancos e Empresas) podem avaliar e modificar pedidos de aluguel.
- **Gestao de Contratos**: Formalizacao de contratos de aluguel e credito, com registro de propriedade dos veiculos.
- **Multiperfil**: Diferenciacao entre usuarios individuais (clientes) e agentes corporativos.

---

## Regras de Negocio e Dados

| Entidade | Dados Armazenados |
| :--- | :--- |
| **Contratantes** | RG, CPF, Nome, Endereco, Profissao, Entidades Empregadoras, Rendimentos (ate 3). |
| **Automoveis** | Matricula, Ano, Marca, Modelo, Placa. |
| **Agentes** | Empresas e Bancos responsaveis pela avaliacao e concessao de credito. |

---

## Processo de Desenvolvimento (Sprints)

O desenvolvimento segue o roteiro estabelecido para o Laboratorio 02:

| Sprint | Atividades | Status |
| :--- | :--- | :---: |
| **Lab02S01** | Modelagem (Casos de Uso, Historias, Classes, Pacotes). | Concluido |
| **Lab02S02** | Revisao, Diagrama de Componentes e CRUD de Cliente (Java/Web). | Pendente |
| **Lab02S03** | Diagrama de Implantacao e Prototipo Final (Gestao de Aluguel). | Pendente |

---

## Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Arquitetura:** MVC (Model-View-Controller)
- **Framework:** Spring Boot / Micronaut
- **Persistencia:** JPA / Hibernate
- **Modelagem:** UML (Diagramas de Casos de Uso, Classes, Componentes)

---

## Autores

- **Gabriel Afonso Infante Vieira**
- **Camila** 
- **Henrique** 

---

**Professor:** Joao Paulo Carneiro Aramuni  
**Instituicao:** PUC Minas
