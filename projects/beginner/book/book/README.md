# 📚 Book Management API

Este projeto é uma API RESTful desenvolvida com **Spring Boot** para gerenciamento de livros, autores e categorias. Ele permite criar, atualizar, buscar e deletar registros nas tabelas relacionadas, com suporte a validações e tratamento de exceções. A API também inclui documentação interativa utilizando **OpenAPI/Swagger**.

---

## 📂 Estrutura de Pastas

A estrutura do projeto segue boas práticas para organização de aplicações Spring Boot, distribuindo responsabilidades de forma clara e modularizada:

### **`config/`**
Configuração do Swagger para documentar a API de forma interativa:
- Define informações gerais sobre a API (título, descrição, versão).
- Configurações personalizadas para os endpoints.

---

### **`controllers/`**
Camada responsável por expor as rotas (endpoints) da aplicação. Contém:
- **`CategoryController`**: Gerencia as operações CRUD de categorias.
- **`AuthorController`**: Gerencia as operações CRUD de autores.
- **`BookController`**: Gerencia as operações CRUD de livros.
- **`exceptions/`**: Contém a classe `StandardError` para definir o modelo de erros padrão.
- **`handlers/`**: Implementa o `ResourceExceptionHandler`, responsável por capturar e personalizar mensagens de erro.

---

### **`repositories/`**
Camada de acesso ao banco de dados, implementada com **Spring Data JPA**:
- **`CategoryRepository`**: Acesso à tabela de categorias.
- **`AuthorRepository`**: Acesso à tabela de autores.
- **`BookRepository`**: Acesso à tabela de livros.

---

### **`services/`**
Camada de lógica de negócios que conecta os controllers aos repositórios:
- **`CategoryService`**: Contém as regras para CRUD de categorias e validações.
- **`AuthorService`**: Contém as regras para CRUD de autores e validações.
- **`BookService`**: Contém as regras para CRUD de livros, incluindo o relacionamento com categorias e autores.
- **`exceptions/`**: Implementa exceções personalizadas, como:
  - `ResourceNotFoundException`: Quando um recurso solicitado não é encontrado.
  - `DatabaseException`: Para violações de integridade do banco de dados.
  - `DuplicateResourceException`: Para evitar a criação de registros duplicados.

---

## 📘 Funcionalidades

- **Gerenciamento de Livros**:
  - Adicionar livros com autor e categorias.
  - Atualizar detalhes dos livros.
  - Listar todos os livros ou buscar por ID.
  - Excluir livros.

- **Gerenciamento de Autores**:
  - Adicionar novos autores com informações detalhadas.
  - Atualizar ou excluir registros.
  - Listar todos os autores ou buscar por ID.

- **Gerenciamento de Categorias**:
  - Criar e editar categorias.
  - Remover categorias que não estejam associadas a livros.
  - Buscar categorias por nome ou ID.

---

## 🚀 Tecnologias Utilizadas

- **Java** com **Spring Boot**
- **Spring Data JPA** para persistência de dados.
- **Postgresql** para o armazenamento dos dados.
- **Swagger/OpenAPI** para documentação da API.

---
