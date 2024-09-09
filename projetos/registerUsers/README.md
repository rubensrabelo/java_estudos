# Estrutura do Projeto `registerUsers`

A imagem mostra a estrutura de diretórios de um projeto Java Spring Boot. A seguir está a explicação de cada pacote:

- **src/main/java**
  - Contém o código-fonte principal do projeto. Todos os pacotes e classes Java estão dentro deste diretório.

### Pacotes do Projeto

- **com.registerUsers.registerUsers**
  - Pacote raiz do projeto. Contém as classes principais da aplicação Spring Boot.

- **com.registerUsers.registerUsers.config**
  - Pacote destinado a arquivos de configuração do projeto. Normalmente, contém classes que configuram componentes como segurança, banco de dados, e outros aspectos da aplicação.

- **com.registerUsers.registerUsers.handlers**
  - Pacote utilizado para lidar com exceções ou eventos específicos da aplicação. Pode conter manipuladores globais para exceções personalizadas ou outros eventos.

- **com.registerUsers.registerUsers.models**
  - Pacote que contém as classes de modelo (entidades) que representam as tabelas do banco de dados.

- **com.registerUsers.registerUsers.repositories**
  - Pacote dedicado a interfaces que estendem `JpaRepository` ou `CrudRepository`. Essas interfaces são responsáveis pela comunicação com o banco de dados.

- **com.registerUsers.registerUsers.resources**
  - Pacote que contém os controladores (controllers) da aplicação, onde são definidas as rotas e endpoints RESTful.

- **com.registerUsers.registerUsers.services**
  - Pacote que contém a lógica de negócios do projeto. As classes de serviço fazem a ponte entre os controladores e os repositórios, aplicando as regras de negócio necessárias.

- **com.registerUsers.registerUsers.services.exceptions**
  - Pacote onde são definidas exceções personalizadas para o projeto. Normalmente, estas exceções são utilizadas para tratar erros específicos de forma organizada e clara.

### src/main/resources

Esse diretório contém arquivos de configuração e recursos estáticos, como o arquivo `application.properties` ou `application.yml`, que são usados para definir configurações da aplicação (banco de dados, portas, etc.).

### Observação

- O nome dos pacotes segue o padrão de convenção para pacotes em Java, começando com o domínio invertido, seguido pelo nome do projeto e a organização interna baseada em camadas (configurações, manipuladores, modelos, repositórios, recursos e serviços).
