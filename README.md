# 🛒 CommerceMail API

_Seu e-commerce com pagamentos simplificados por e-mail._

---

## 📜 Descrição do Projeto

O **CommerceMail** é uma API RESTful robusta para e-commerce, construída com Spring Boot. A principal proposta do projeto é oferecer um fluxo de compra onde o pagamento e a finalização do pedido são realizados através do e-mail do cliente, simplificando o processo de checkout.

A aplicação gerencia todo o ciclo de vida de um e-commerce, desde o cadastro de usuários e produtos até o gerenciamento de carrinhos de compra e feedback de clientes.

---

## 🚀 Status do Projeto

![Status](https://img.shields.io/badge/status-em--desenvolvimento-yellow)

O projeto encontra-se em fase de desenvolvimento ativo. Novas funcionalidades e melhorias estão sendo implementadas continuamente.

---

## 🛠️ Tecnologias Utilizadas

Esta API foi construída utilizando as seguintes tecnologias e ferramentas:

### Principais
- **Java 17:** Versão da linguagem Java utilizada.
- **Spring Boot 3.5.3:** Framework principal para a construção da aplicação.
- **Spring Data JPA:** Para persistência de dados em banco relacional.
- **Spring Data MongoDB:** Para persistência de dados em banco não-relacional (carrinhos).
- **Spring Data Redis:** Para gerenciamento de cache.
- **Spring Security:** Para autenticação e autorização baseada em JWT.
- **PostgreSQL:** Banco de dados relacional principal.
- **MongoDB:** Banco de dados NoSQL para entidades flexíveis como o carrinho.
- **Redis:** Banco de dados em memória para cache.
- **Maven:** Gerenciador de dependências e build do projeto.
- **Docker & Docker Compose:** Para containerização da aplicação e dos serviços (banco de dados).
- **Lombok:** Para reduzir código boilerplate em classes Java.

### Outras Dependências
- **Flyway:** Para controle de versionamento do banco de dados relacional.
- **Java JWT (Auth0):** Biblioteca para geração e validação de tokens JWT.
- **Spring DotEnv:** Para gerenciamento de variáveis de ambiente a partir de um arquivo `.env`.

---

## ✨ Funcionalidades

- **👤 Gestão de Usuários e Autenticação:**
    - Cadastro e login de usuários.
    - Autenticação via JWT (JSON Web Token).
    - Atualização de perfil de usuário.
    - Sistema de redefinição de senha por e-mail.
    - Controle de acesso baseado em permissões (roles).

- **📦 Gestão de Produtos e Categorias:**
    - CRUD completo para produtos.
    - CRUD completo para categorias de produtos.
    - Busca de produtos por categoria.

- **🛒 Gestão de Carrinho de Compras:**
    - Criação e atualização de carrinhos de compra.
    - Busca de carrinhos abertos ou finalizados por usuário.
    - Finalização de compra (simulada, com envio de e-mail).

- **🖼️ Upload e Gerenciamento de Imagens:**
    - Upload de imagens de produtos.
    - Endpoint para servir as imagens de forma otimizada.

- **⭐ Sistema de Feedback:**
    - Usuários podem criar, ler, atualizar e deletar feedbacks sobre suas compras.

---

## 📋 Pré-requisitos

Antes de começar, garanta que você tenha as seguintes ferramentas instaladas em sua máquina:
- **Java JDK 17** ou superior.
- **Maven 3.8** ou superior.
- **Docker** e **Docker Compose**.

---

## ⚙️ Como Rodar o Projeto

Siga os passos abaixo para executar a aplicação localmente:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/QueijosRomanu.git
    ```

2.  **Navegue até o diretório raiz:**
    ```bash
    cd QueijosRomanu
    ```

3.  **Crie o arquivo de ambiente:**
    Crie um arquivo chamado `.env` na raiz do projeto, baseado no `docker-compose.yml`. Ele deve conter, no mínimo:
    ```properties
    JWT_SECRET=sua-chave-secreta-aqui
    MAIL_HOST=smtp.gmail.com
    MAIL_PORT=587
    MAIL_USERNAME=seu-email@gmail.com
    MAIL_PASSWORD=sua-senha-de-app
    ```

4.  **Suba os containers Docker:**
    O `docker-compose` irá iniciar a aplicação, o PostgreSQL, o MongoDB e o Redis.
    ```bash
    docker-compose up --build
    ```

5.  **Acesse a aplicação:**
    A API estará disponível em `http://localhost:8080`.

---

## Endpoints Principais da API

A base da URL para todos os endpoints é `/api/v1`.

### Autenticação e Usuários (`/user`)
- `POST /user/login`: Realiza a autenticação e retorna um token JWT.
- `POST /user/create`: Cria um novo usuário.
- `PUT /user/update/{id}`: Atualiza os dados de um usuário.
- `GET /user/{id}`: Busca um usuário por ID.
- `GET /user/all`: Lista todos os usuários (requer permissão de admin).
- `POST /user/reset-password`: Envia um e-mail para redefinição de senha.

### Produtos (`/product`)
- `POST /product/create`: Cria um novo produto (requer permissão de admin).
- `GET /product/all`: Lista todos os produtos.
- `GET /product/{id}`: Busca um produto por ID.
- `PUT /product/update/{id}`: Atualiza um produto (requer permissão de admin).
- `DELETE /product/{id}`: Deleta um produto (requer permissão de admin).

### Categorias (`/category`)
- `POST /category/create`: Cria uma nova categoria (requer permissão de admin).
- `GET /category/all`: Lista todas as categorias.
- `PUT /category/update/{id}`: Atualiza uma categoria (requer permissão de admin).
- `DELETE /category/delete/{id}`: Deleta uma categoria (requer permissão de admin).

### Carrinho (`/cart`)
- `POST /cart/create`: Cria um novo carrinho para um usuário.
- `GET /cart/cart-open/{userId}`: Busca o carrinho aberto de um usuário.
- `PUT /cart/update/{id}`: Adiciona/remove itens de um carrinho.
- `GET /cart/sold/{id}`: Marca um carrinho como "vendido" e dispara o fluxo de e-mail.

---

## 🤝 Como Contribuir

Contribuições são bem-vindas! Se você deseja contribuir com o projeto, siga os passos abaixo:

1.  Faça um **fork** do projeto.
2.  Crie uma nova branch para sua feature (`git checkout -b feature/sua-feature`).
3.  Faça o commit de suas alterações (`git commit -m 'feat: Adiciona nova feature'`).
4.  Faça o push para a branch (`git push origin feature/sua-feature`).
5.  Abra um **Pull Request**.

---

## 👨‍💻 Autor

**Leonardo Figorelli**

- **GitHub:** [@leofigorelli](https://github.com/leofigorelli)
- **LinkedIn:** [Leonardo Figorelli](https://www.linkedin.com/in/leonardo-figorelli-025a22238/)

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.



