# Relatório Final - Laboratório 02: Sistema de Aluguel de Carros

Este relatório apresenta o desenvolvimento completo do Sistema de Aluguel de Carros, conforme as especificações do Laboratório 02. O projeto foi dividido em três sprints, abrangendo desde a modelagem inicial até a implementação de um protótipo funcional em Java com Spring Boot e arquitetura MVC.

## Visão Geral do Projeto

O objetivo principal foi desenvolver um sistema para apoiar a gestão de aluguéis de automóveis, permitindo efetuar, cancelar e modificar pedidos através da Internet. O sistema foi projetado para ser utilizado por usuários individuais (clientes) e agentes (empresas e bancos), com funcionalidades específicas para cada perfil.

## Fases do Desenvolvimento

O desenvolvimento seguiu as três sprints definidas no documento do laboratório:

### Sprint 01: Modelagem do Sistema (Lab02S01)

Nesta fase, foram elaborados os modelos iniciais do sistema, incluindo:

*   **Diagrama de Casos de Uso**: Identifica as interações entre os atores (Cliente e Agente) e as funcionalidades do sistema.
*   **Histórias de Usuário**: Detalham as funcionalidades sob a perspectiva dos usuários.
*   **Diagrama de Classes**: Representa a estrutura estática do sistema, suas classes e relacionamentos.
*   **Diagrama de Pacotes (Visão Lógica)**: Organiza os elementos do sistema em grupos lógicos.

Para mais detalhes, consulte o documento: [Modelagem do Sistema - Lab02S01](./docs/modelagem_system/modelagem_lab02s01.md)

### Sprint 02: Arquitetura e Implementação Inicial (Lab02S02)

Esta fase focou na arquitetura e na implementação inicial do sistema, com a criação de:

*   **Diagrama de Componentes**: Ilustra a estrutura modular do sistema e a interação entre os componentes.
*   **Implementação do CRUD de Cliente**: Desenvolvimento das funcionalidades de Criação, Leitura, Atualização e Exclusão para a entidade `Cliente` utilizando Spring Boot, JPA e Thymeleaf.

Para mais detalhes, consulte o documento: [Arquitetura e Implementação Inicial - Lab02S02](./docs/modelagem_system/arquitetura_implementacao_lab02s02.md)

### Sprint 03: Protótipo Final (Lab02S03)

A fase final envolveu a conclusão do protótipo, incluindo:

*   **Diagrama de Implantação**: Mostra a configuração de hardware e software do sistema.
*   **Implementação do CRUD de Automóvel**: Desenvolvimento das funcionalidades de Criação, Leitura, Atualização e Exclusão para a entidade `Automovel`.
*   **Implementação do CRUD de Pedido de Aluguel**: Desenvolvimento das funcionalidades de Criação, Leitura, Atualização e Exclusão para a entidade `PedidoAluguel`, incluindo a capacidade de criar novos pedidos e visualizar seu status.

Para mais detalhes, consulte o documento: [Protótipo Final - Lab02S03](./docs/modelagem_system/prototipo_final_lab02s03.md)

### Instruções para Execução Local (Mac/iPhone)

Para executar o projeto localmente, siga os passos abaixo:

1.  **Pré-requisitos**: Certifique-se de ter o Java Development Kit (JDK) versão 11 ou superior e o Apache Maven instalados em sua máquina.
2.  **Download do Projeto**: Baixe o diretório `/home/ubuntu/lab02_car_rental_system` para sua máquina local.
3.  **Navegar até o Diretório**: Abra um terminal e navegue até o diretório raiz do projeto (onde o arquivo `pom.xml` está localizado).
    ```bash
    cd /caminho/para/lab02_car_rental_system
    ```
4.  **Compilar e Executar**: Utilize o Maven para compilar e iniciar a aplicação Spring Boot.
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
5.  **Acessar a Aplicação**: Abra seu navegador web e acesse `http://localhost:8080/clientes` para ver a aplicação em funcionamento.

Para a entrega final, por favor, faça o commit de todos os artefatos (documentos Markdown, diagramas PNG e código-fonte) em um repositório público no GitHub, conforme solicitado no documento do laboratório.
