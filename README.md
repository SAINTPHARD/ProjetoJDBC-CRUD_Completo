# Projeto de JDBC (Java Database Connectivity) Completo

Projeto prático de estudo sobre **Java Database Connectivity (JDBC)**.
O sistema realiza o ciclo completo de **CRUD** (Create, Read, Update, Delete) e gerencia **Transações** bancárias com segurança.

## Tecnologias Utilizadas
- Java 21
- JDBC (Connection, Statement, PreparedStatement)
- MySQL 8.0
- Padrão de Projeto DAO (Data Access Object)

## Funcionalidades
O projeto está organizado em níveis de aprendizado:

1.  **Conexão:** Configuração e conexão manual com o banco de dados.
2.  **Select:** Recuperação de dados formatados.
3.  **Insert:** Inserção de dados simples e inserção com recuperação de ID (GeneratedKeys).
4.  **Update:** Atualização de dados com proteção de integridade.
5.  **Delete:** Remoção segura de dados.
6.  **Transações:** Controle de atomicidade (Commit e Rollback) para operações críticas.

##  Como Rodar o Projeto

1. **Banco de Dados:**
   - Utilize o arquivo 'database.sql' para criar o banco e as tabelas no seu MySQL Workbench.

2. **Configuração:**
   - Crie um arquivo `db.properties` dentro da pasta `src/db` com seus dados:
     ```properties
     user=seu_usuario
     password=sua_senha
     dburl=jdbc:mysql://localhost:3306/coursejdbc
     useSSL=false
     ```

3. **Execução:**
   - Execute os arquivos numerados na pasta `application` (ex: `Program01...`, `Program02...`) para testar cada funcionalidade.

---
*Projeto desenvolvido por Robedson SAINTPHARD para fins de portfólio e demonstração DE Habilidade e Conhecimentos técnicos.*