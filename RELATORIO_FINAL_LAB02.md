# Relatorio Final - Laboratorio 02: Sistema de Aluguel de Carros

Este relatorio apresenta o desenvolvimento completo do Sistema de Aluguel de Carros, conforme as especificacoes do Laboratorio 02. O projeto foi dividido em tres sprints, abrangendo desde a modelagem inicial ate a implementacao de um prototipo funcional em Java com Spring Boot e arquitetura MVC.

## Visao Geral do Projeto

O objetivo principal foi desenvolver um sistema para apoiar a gestao de alugueis de automoveis, permitindo efetuar, cancelar e modificar pedidos atraves da Internet. O sistema foi projetado para ser utilizado por usuarios individuais (clientes) e agentes (empresas e bancos), com funcionalidades especificas para cada perfil.

## Fases do Desenvolvimento

O desenvolvimento seguiu as tres sprints definidas no documento do laboratorio:

### Sprint 01: Modelagem do Sistema (Lab02S01)

Nesta fase, foram elaborados os modelos iniciais do sistema, incluindo:

*   **Diagrama de Casos de Uso**: Identifica as interacoes entre os atores (Cliente e Agente) e as funcionalidades do sistema.
*   **Historias de Usuario**: Detalham as funcionalidades sob a perspectiva dos usuarios.
*   **Diagrama de Classes**: Representa a estrutura estatica do sistema, suas classes e relacionamentos.
*   **Diagrama de Pacotes (Visao Logica)**: Organiza os elementos do sistema em grupos logicos.

Para mais detalhes, consulte o documento: [Modelagem do Sistema - Lab02S01](./docs/modelagem_system/modelagem_lab02s01.md)

### Sprint 02: Arquitetura e Implementacao Inicial (Lab02S02)

Esta fase focou na arquitetura e na implementacao inicial do sistema, com a criacao de:

*   **Diagrama de Componentes**: Ilustra a estrutura modular do sistema e a interacao entre os componentes.
*   **Implementacao do CRUD de Cliente**: Desenvolvimento das funcionalidades de Criacao, Leitura, Atualizacao e Exclusao para a entidade `Cliente` utilizando Spring Boot, JPA e Thymeleaf.

Para mais detalhes, consulte o documento: [Arquitetura e Implementacao Inicial - Lab02S02](./docs/modelagem_system/arquitetura_implementacao_lab02s02.md)

### Sprint 03: Prototipo Final (Lab02S03)

A fase final envolveu a conclusao do prototipo, incluindo:

*   **Diagrama de Implantacao**: Mostra a configuracao de hardware e software do sistema.
*   **Implementacao do CRUD de Automovel**: Desenvolvimento das funcionalidades de Criacao, Leitura, Atualizacao e Exclusao para a entidade `Automovel`.
*   **Implementacao do CRUD de Pedido de Aluguel**: Desenvolvimento das funcionalidades de Criacao, Leitura, Atualizacao e Exclusao para a entidade `PedidoAluguel`, incluindo a capacidade de criar novos pedidos e visualizar seu status.

Para mais detalhes, consulte o documento: [Prototipo Final - Lab02S03](./docs/modelagem_system/prototipo_final_lab02s03.md)

### Instrucoes para Execucao Local (Mac/iPhone)

Para executar o projeto localmente, siga os passos abaixo:

1.  **Pre-requisitos**: Certifique-se de ter o Java Development Kit (JDK) versao 11 ou superior e o Apache Maven instalados em sua maquina.
2.  **Download do Projeto**: Baixe o diretorio `/home/ubuntu/lab02_car_rental_system` para sua maquina local.
3.  **Navegar ate o Diretorio**: Abra um terminal e navegue ate o diretorio raiz do projeto (onde o arquivo `pom.xml` esta localizado).
    ```bash
    cd /caminho/para/lab02_car_rental_system
    ```
4.  **Compilar e Executar**: Utilize o Maven para compilar e iniciar a aplicacao Spring Boot.
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
5.  **Acessar a Aplicacao**: Abra seu navegador web e acesse `http://localhost:8080/clientes` para ver a aplicacao em funcionamento.

Para a entrega final, por favor, faca o commit de todos os artefatos (documentos Markdown, diagramas PNG e codigo-fonte) em um repositorio publico no GitHub, conforme solicitado no documento do laboratorio.
