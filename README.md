# 🚀 SistemaCadastroFuncionarios



## 📦 Pré-requisitos

- Java JDK 8+ (recomendado JDK 11 ou 17)
- NetBeans IDE (versão 12 ou superior)
- PostgreSQL 12+ 
- Git (para clonar o repositório)

## 🛠️ Clonar o Repositório

#### Abra o terminal (Git Bash, CMD ou PowerShell) e execute:
- git clone https://github.com/seu-usuario/nome-do-repositorio.git

#### Ou pelo NetBeans:
- File → New Project → Git → Project from Git → Clone
- Cole a URL do repositório e siga as instruções.

## 🐘 Configurar o Banco de Dados (PostgreSQL)

- Crie um banco de dados:

    CREATE DATABASE sistema_funcionarios;

- Execute os scripts SQL (encontrados em /sql/ no projeto):

    CREATE TABLE usuarios (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        email VARCHAR(100) UNIQUE NOT NULL,
        senha VARCHAR(64) NOT NULL
    );

    CREATE TABLE funcionarios (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        data_admissao DATE NOT NULL,
        salario DECIMAL(10,2) NOT NULL,
        status BOOLEAN DEFAULT TRUE
    );

## 📌 Configurar o Projeto no NetBeans
1. Abra o NetBeans.

2. File → Open Project → Selecione a pasta clonada.

3. Configure o PostgreSQL no projeto:

4. Verifique o arquivo util/DatabaseConnection.java e atualize:
-    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_funcionarios";
-    private static final String USER = "postgres"; // Seu usuário
-    private static final String PASSWORD = "sua_senha"; // Sua senha

##### Adicione o driver JDBC:
-    Botão direito no projeto → Properties → Libraries → Add JAR/Folder → Selecione postgresql-XX.X.jar.

## ▶️ Executar a Aplicação

1. Clique com o botão direito no projeto → Run.
2. A tela de login aparecerá:
-  Cadastre um novo usuário ou use um existente.
-  Após o login, a tela de cadastro de funcionários será exibida.

## 🧪 Possíveis Problemas e Soluções

### Problema Solução
-  Problema: Erro de conexão com PostgreSQL Solução: Verifique se o PostgreSQL está rodando (sudo service postgresql start no Linux ou via pgAdmin no Windows).
-  Problema: Driver JDBC não encontrado Solução: Baixe o driver e adicione ao projeto.
-  Problema: Tabelas não criadas Solução: Execute manualmente os scripts SQL ou verifique DatabaseConnection.java.
-  Problema: Erro ao logar Solução: Verifique se o usuário está cadastrado e a senha está correta.

### Observação
- Não foi segmentado a tela de cadastro de funcionários por perfil ex:usuario admin por não fazer parte do escopo do enunciado, de modo que, todo usuário que esteja gravado no banco quando acessar a tela de cadastro de funcionários e gravar dados visualizará todos os registros já cadastrados.

## 🗂 Estrutura do Projeto

```plaintext
📂 src/
├── main/
├── controller/
├── dao/
├── model/
├── util/
├── view/
📂 sql/ (scripts SQL)
📄 README.md
📄 pom.xml (se for Maven)






