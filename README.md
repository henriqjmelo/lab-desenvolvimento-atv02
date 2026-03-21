# Sistema de Aluguel de Carros (LAB02)

<p align="center">
  <img src="https://img.shields.io/badge/Linguagem-Java-orange" alt="Java">
  <img src="https://img.shields.io/badge/Framework-Spring%20Boot-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Arquitetura-MVC-blue" alt="MVC">
  <img src="https://img.shields.io/badge/Status-Sprint%2001-yellow" alt="Sprint 01">
</p>

## 📖 Descrição do Projeto

Este repositório contém o desenvolvimento do **Sistema de Aluguel de Carros**, um projeto prático da disciplina *Laboratório de Desenvolvimento de Software* (LAB02) do curso de **Engenharia de Software**.

O objetivo é criar uma plataforma web que permita a gestão completa de aluguéis de automóveis. O sistema atende a diferentes perfis de usuários (Clientes, Empresas e Bancos), permitindo desde o cadastro e solicitação de aluguel até a análise financeira e formalização de contratos de crédito.

O projeto é desenvolvido em **Java**, utilizando a arquitetura **MVC** e seguindo um cronograma de desenvolvimento ágil dividido em 3 Sprints.

---

## 🗂️ Estrutura do Repositório (Sprint 01)

Atualmente, o projeto encontra-se na fase de modelagem. Os artefatos disponíveis são:

- **Diagrama de Casos de Uso**: Representação das interações entre os atores (Clientes e Agentes) e o sistema.
- **Histórias de Usuário**: Detalhamento das funcionalidades sob a ótica do usuário final.
- **Diagrama de Classes**: Modelagem estrutural das entidades (Clientes, Automóveis, Agentes, Contratos).
- **Diagrama de Pacotes**: Visão lógica da organização do sistema (Visão Lógica/Arquitetural).

---

## ✨ Funcionalidades Planejadas

- **Cadastro de Usuários**: Registro obrigatório para utilização do sistema.
- **Gestão de Pedidos**: Clientes podem introduzir, modificar, consultar e cancelar pedidos de aluguel.
- **Análise Financeira**: Agentes (Bancos e Empresas) podem avaliar e modificar pedidos de aluguel.
- **Gestão de Contratos**: Formalização de contratos de aluguel e crédito, com registro de propriedade dos veículos.
- **Multiperfil**: Diferenciação entre usuários individuais (clientes) e agentes corporativos.

---

## 🛠️ Regras de Negócio e Dados

| Entidade | Dados Armazenados |
| :--- | :--- |
| **Contratantes** | RG, CPF, Nome, Endereço, Profissão, Entidades Empregadoras, Rendimentos (até 3). |
| **Automóveis** | Matrícula, Ano, Marca, Modelo, Placa. |
| **Agentes** | Empresas e Bancos responsáveis pela avaliação e concessão de crédito. |

---

## 📈 Processo de Desenvolvimento (Sprints)

O desenvolvimento segue o roteiro estabelecido para o Laboratório 02:

| Sprint | Atividades | Status |
| :--- | :--- | :---: |
| **Lab02S01** | Modelagem (Casos de Uso, Histórias, Classes, Pacotes). | 🟢 Concluído |
| **Lab02S02** | Revisão, Diagrama de Componentes e CRUD de Cliente (Java/Web). | 🟡 Pendente |
| **Lab02S03** | Diagrama de Implantação e Protótipo Final (Gestão de Aluguel). | ⚪ Pendente |

---

## 🚀 Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Arquitetura:** MVC (Model-View-Controller)
- **Framework:** Spring Boot / Micronaut
- **Persistência:** JPA / Hibernate
- **Modelagem:** UML (Diagramas de Casos de Uso, Classes, Componentes)

---

## 👥 Autores

- **Gabriel Afonso Infante Vieira**
- **Camila** 
- **Henrique** 

---

**Professor:** João Paulo Carneiro Aramuni  
**Instituição:** PUC Minas
