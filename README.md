
# Biblioteca Virtual

A API de Biblioteca Virtual é um sistema para gerenciar o acervo de livros, usuários, e os empréstimos/devoluções de livros. Ela foi desenvolvida com Java usando Spring Boot, JPA/Hibernate para persistência de dados, e um banco de dados relacional PostgreSQL.




## Funcionalidades Principais

#### Gerenciamento de Livros

 - Adicionar, atualizar, listar e remover livros do acervo.
 - Buscar livros por título, autor ou gênero.
 - Indicar se um livro está disponível ou emprestado.

#### Gerenciamento de Usuários

- Registrar novos usuários (alunos, professores, etc.).
- Listar e atualizar dados de usuários.
- Visualizar histórico de empréstimos de cada usuário.

#### Empréstimos e Devoluções
- Registrar empréstimos de livros.
- Registrar devoluções, atualizando a disponibilidade do livro.
- Aplicar multas para devoluções atrasadas (opcional).




## Fluxo de Funcionamento

#### 1 - Cadastro de Livros e Usuários
- O administrador insere livros no sistema e registra usuários.

#### 2 - Empréstimos
- Um usuário solicita um livro.
- O sistema verifica disponibilidade e registra o empréstimo.

#### 3 - Devoluções
- O usuário devolve o livro.
- O sistema atualiza o status do empréstimo e o livro fica disponível novamente.

#### 4 - Relatórios
- Administradores podem consultar relatórios de uso, livros mais emprestados, e usuários ativos.(Funcionalidade a ser implementada)


## Tecnologias e Recursos

**Spring Boot:** Para construir a API.

**JPA/Hibernate:** Para mapeamento e persistência de dados.

**Banco de Dados:** PostgreSQL 

**Swagger:** Para documentação dos endpoints.


## Pré-requisitos

 Certifique-se de que os seguintes programas estão instalados na sua máquina:

- Java 21
- Maven 3.3.1
- PostgreSql
- Git


## Rodando localmente

Clone o projeto

```bash
  git clone 
```

Configurar o Banco de Dados

- Crie um banco de dados no PostgreSql
- Atualize o arquivo application.properties com as credenciais do seu banco de dados

```bash
  CREATE DATABASE biblioteca;
```

Instale as dependências

```bash
  mvn clean install
```

Executar o Projeto

```bash
  mvn spring-boot:run
```


Documentação Interativa

Acesse a documentação interativa da API em:

```bash
  http://localhost:8080/swagger-ui.html
```


