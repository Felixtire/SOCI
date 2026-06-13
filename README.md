# 🌐 Documentação Backend - Soci API
## 📌 Visão Geral
**Soci** é uma plataforma de rede social para conectar usuários, permitindo criação de publicações, eventos, conexões entre usuários e interações através de curtidas e comentários.
### Stack Tecnológico
- **Linguagem:** Java 21
- **Framework:** Spring Boot 4.0.6
- **ORM:** Hibernate + JPA
- **Banco de Dados:** MySQL 8
- **Autenticação:** JWT (JSON Web Tokens)
- **Documentação API:** Swagger/OpenAPI (SpringDoc)
- **Build:** Maven 3.8+
- **Segurança:** Spring Security
---
## 📁 Estrutura do Projeto
\\\
src/main/java/com/projeto/soci/
├── controller/              # Endpoints REST
│   ├── Cadastro.java        # Gerenciamento de usuários
│   ├── LoginController.java # Autenticação
│   ├── ConexaoController.java # Conexões entre usuários
│   ├── PublicacaoController.java # Publicações
│   └── EventoController.java # Eventos
├── service/                 # Lógica de negócio
│   ├── CadastroService.java
│   ├── LoginService.java
│   ├── TokenService.java
│   ├── ConexaoService.java
│   ├── PublicacaoService.java
│   └── EventoService.java
├── repository/              # Acesso a dados (JPA)
│   ├── UsuarioRepository.java
│   ├── PublicacaoRepository.java
│   ├── ConexaoRepository.java
│   ├── EventoRepository.java
│   ├── ComentarioRepository.java
│   └── CurtidasRepository.java
├── model/                   # Entidades JPA
│   ├── Usuario.java
│   ├── Publicacao.java
│   ├── Conexao.java
│   ├── Evento.java
│   ├── Comentario.java
│   └── Curtidas.java
├── dto/                     # Data Transfer Objects
│   ├── DadosCadastroUsuario.java
│   ├── DadosLogin.java
│   ├── ConexaoResponseDto.java
│   ├── PublicacaoDto.java
│   ├── EventoResponseDto.java
│   └── ...
├── exception/               # Exceções customizadas
│   └── NotFoundException.java
├── enuns/                   # Enumerações
│   ├── TipoUsuario.java
│   └── StatusConexao.java
└── infra/
    └── configurations/
        └── SecurityFilter.java  # Filtro de segurança JWT
\\\
---
## 🏗️ Arquitetura
`
┌─────────────────────────────────────────────────────┐
│           Camada de Apresentação (HTTP)             │
│         Controllers com @RestController              │
└────────────────────┬────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────┐
│         Camada de Autenticação                       │
│    SecurityFilter (Validação JWT)                    │
└────────────────────┬────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────┐
│         Camada de Negócio (Services)                 │
│  - CadastroService, LoginService, etc.              │
│  - Lógica de validação e processamento              │
└────────────────────┬────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────┐
│         Camada de Persistência (Repositories)       │
│    Interfaces JpaRepository + Queries Custom        │
└────────────────────┬────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────┐
│         Banco de Dados (MySQL)                       │
│    Tabelas com Relacionamentos Bidirecionais        │
└─────────────────────────────────────────────────────┘
\\\
---
## 🚀 Setup Inicial
### Pré-requisitos
- **Java 21+** - [Download](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven 3.8+** - [Download](https://maven.apache.org/)
- **MySQL 8+** - [Download](https://dev.mysql.com/downloads/mysql/)
- **Git** - [Download](https://git-scm.com/)
### Variáveis de Ambiente
Criar arquivo \.env\ na raiz do projeto (ou configurar no IDE):
\\\properties
# Banco de Dados
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/soci?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
# JWT (IMPORTANTE: Alterar em produção!)
JWT_SECRET_KEY=seu-secret-key-seguro-aqui
JWT_EXPIRATION_HOURS=2
# Spring
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SERVER_PORT=8080
\\\
### Passo a Passo - Desenvolvimento Local
1. **Clonar repositório:**
\\\ash
git clone <url-do-repo>
cd soci
\\\
2. **Criar banco de dados MySQL:**
\\\sql
CREATE DATABASE soci;
USE soci;
-- Scripts de migração (opcional)
-- source src/main/resources/EXECUTAR_ESTE_SCRIPT_NO_MYSQL.sql;
\\\
3. **Compilar e build:**
\\\ash
mvn clean install
\\\
4. **Executar aplicação:**
\\\ash
mvn spring-boot:run
\\\
5. **Acessar API:**
- API Local: \http://localhost:8080\
- Swagger UI: \http://localhost:8080/swagger-ui.html\
- Documentação OpenAPI: \http://localhost:8080/v3/api-docs\
---
## 🔐 Autenticação e Segurança
### Fluxo de Login
1. Cliente envia credenciais (email + senha) para \POST /login\
2. LoginService valida credenciais no banco de dados
3. TokenService gera JWT token com validade de 2 horas
4. Cliente recebe: \{ idUsuario, token }\
5. Cliente inclui token em próximas requisições: \Authorization: Bearer <token>\
6. SecurityFilter valida token em cada requisição (exceto /login e /cadastrar)
### Endpoints Públicos (sem JWT)
- \POST /login\ - Fazer login
- \POST /cadastrar\ - Registrar novo usuário
- \GET /swagger-ui.html\ - Documentação
- \GET /v3/api-docs\ - OpenAPI JSON
### Endpoints Protegidos (requer JWT)
- Todos os demais endpoints
### Configuração de Segurança
\\\java
// No SecurityFilter.java:
// - Validação de token JWT
// - Extração de email (subject) do token
// - Busca do usuário no banco
// - Configuração de autenticação no contexto de segurança
\\\
---
## 📚 Documentação de Endpoints
### 1. Autenticação
#### Login
\\\
POST /login
Content-Type: application/json
Body:
{
  "email": "user@example.com",
  "senha": "password123"
}
Response 200 OK:
{
  "idUsuario": 1,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
Response 401 Unauthorized:
"Login ou senha inválidos"
\\\
---
### 2. Gerenciamento de Usuários (Cadastro)
#### Registrar Novo Usuário
\\\
POST /cadastrar
Content-Type: application/json
Body:
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "senha": "senha123",
  "dataNascimento": "2000-05-15",
  "curso": "Engenharia de Software",
  "fotoPerfil": "https://example.com/foto.jpg",
  "biografia": "Olá! Sou desenvolvedor.",
  "tipoUsuario": "ESTUDANTE",
  "rgm": "123456"
}
Response 201 Created:
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@example.com",
  "curso": "Engenharia de Software",
  ...
}
\\\
#### Listar Usuários (exceto o logado)
\\\
GET /cadastrar/{idUsuarioLogado}/usuarios
Authorization: Bearer <token>
Response 200 OK:
[
  {
    "id": 2,
    "nome": "Maria",
    "email": "maria@example.com",
    ...
  },
  ...
]
\\\
#### Buscar Usuário por ID
\\\
GET /cadastrar/{id}
Authorization: Bearer <token>
Response 200 OK:
{
  "id": 1,
  "nome": "João Silva",
  ...
}
Response 404 Not Found:
Usuário não encontrado
\\\
#### Atualizar Usuário
\\\
PUT /cadastrar/{id}
Authorization: Bearer <token>
Content-Type: application/json
Body: (mesmo formato do POST)
Response 200 OK: (dados atualizados)
Response 404 Not Found: Usuário não encontrado
\\\
#### Deletar Usuário
\\\
DELETE /cadastrar/{id}
Authorization: Bearer <token>
Response 204 No Content:
(sem resposta no body)
Response 404 Not Found:
\\\
#### Deletar Todos os Usuários
\\\
DELETE /cadastrar
Authorization: Bearer <token>
Response 204 No Content:
\\\
---
### 3. Conexões entre Usuários
#### Criar Conexão
\\\
POST /api/conexoes/{origemId}/{destinoId}
Authorization: Bearer <token>
Response 201 Created:
{
  "conexaoId": 1,
  "usuarioOrigemId": 1,
  "nomeUsuarioOrigem": "João",
  "usuarioDestinoId": 2,
  "nomeUsuarioDestino": "Maria",
  "fotoPerfilUsuarioOrigem": "...",
  "fotoPerfilUsuarioDestino": "..."
}
Erros possíveis:
- "Usuário de origem não encontrado" (500)
- "Usuário de destino não encontrado" (500)
- "Usuário de origem e destino não podem ser o mesmo" (500)
- "Conexão já existe entre os usuários" (500)
\\\
#### Listar Todas as Conexões
\\\
GET /api/conexoes
Authorization: Bearer <token>
Response 200 OK:
[
  { conexaoId, usuarioOrigemId, ... },
  ...
]
\\\
#### Listar Conexões de um Usuário
\\\
GET /api/conexoes/{usuarioId}
Authorization: Bearer <token>
Response 200 OK: (array de ConexaoResponseDto)
\\\
#### Deletar Conexão
\\\
DELETE /api/conexoes/{id}
Authorization: Bearer <token>
Response 204 No Content:
\\\
---
### 4. Publicações
#### Criar Publicação
\\\
POST /publicacoes
Authorization: Bearer <token>
Content-Type: application/json
Body:
{
  "conteudo": "Olá pessoal, meu primeiro post!",
  "imagemUrl": "https://example.com/image.jpg"
}
Validações:
- conteudo: obrigatório, máx 2000 caracteres
- imagemUrl: máx 1024 caracteres
Response 201 Created:
{
  "id": 1,
  "conteudo": "Olá pessoal, meu primeiro post!",
  "imagemUrl": "...",
  "usuarioId": 1,
  "usuarioNome": "João",
  ...
}
\\\
#### Listar Publicações
\\\
GET /publicacoes
Authorization: Bearer <token>
Response 200 OK: (array de PublicacaoDto)
\\\
#### Buscar Publicação por ID
\\\
GET /publicacoes/{id}
Authorization: Bearer <token>
Response 200 OK: (PublicacaoDto)
Response 404 Not Found: "Publicacao não encontrada"
\\\
#### Atualizar Publicação
\\\
PUT /publicacoes/{id}
Authorization: Bearer <token>
Content-Type: application/json
Body:
{
  "conteudo": "Conteúdo atualizado",
  "imagemUrl": "..."
}
Response 200 OK: (PublicacaoDto atualizada)
Response 404 Not Found:
\\\
#### Deletar Publicação
\\\
DELETE /publicacoes/{id}
Authorization: Bearer <token>
Response 204 No Content:
Response 404 Not Found:
\\\
---
### 5. Eventos
#### Criar Evento
\\\
POST /api/eventos/{usuarioId}
Authorization: Bearer <token>
Content-Type: application/json
Body:
{
  "titulo": "Meetup de Java",
  "descricao": "Encontro para discussão sobre Java",
  "dataEvento": "2026-07-15",
  "local": "São Paulo, SP"
}
Response 201 Created:
{
  "idEvento": 1,
  "titulo": "Meetup de Java",
  "descricao": "...",
  "dataEvento": "2026-07-15",
  "local": "São Paulo, SP",
  "usuarioId": 1,
  "nomeUsuario": "João"
}
Erros:
- "Usuário não encontrado" (500)
\\\
#### Listar Eventos
\\\
GET /api/eventos
Authorization: Bearer <token>
Response 200 OK: (array de EventoResponseDto)
\\\
#### Buscar Evento por ID
\\\
GET /api/eventos/{id}
Authorization: Bearer <token>
Response 200 OK: (EventoResponseDto)
Response 500 Internal Server Error: "Evento não encontrado"
\\\
#### Atualizar Evento
\\\
PUT /api/eventos/{id}
Authorization: Bearer <token>
Content-Type: application/json
Body: (mesmo formato do POST)
Response 200 OK: (EventoResponseDto atualizado)
Response 500 Internal Server Error: "Evento não encontrado"
\\\
#### Deletar Evento
\\\
DELETE /api/eventos/{id}
Authorization: Bearer <token>
Response 204 No Content:
\\\
---
## 📊 Modelo de Dados
### Tabela: Usuarios
\\\
id_usuario (PK)
nome (VARCHAR)
email (VARCHAR, UNIQUE)
data_nascimento (DATE)
senha (VARCHAR, encoded)
curso (VARCHAR)
foto_perfil (VARCHAR)
biografia (TEXT)
data_criacao (TIMESTAMP)
tipo_usuario (ENUM: ESTUDANTE, PROFESSOR, STAFF)
rgm (VARCHAR)
Relacionamentos:
- 1:N com Publicacao (CASCADE)
- 1:N com Comentario (CASCADE)
- 1:N com Curtidas (CASCADE)
- 1:N com Evento (CASCADE)
- 1:N com Conexao (como origem, CASCADE)
- 1:N com Conexao (como destino, CASCADE)
\\\
### Tabela: Publicacoes
\\\
id_publicacao (PK)
conteudo (TEXT)
imagemUrl (VARCHAR)
dataPublicacao (TIMESTAMP)
usuario_id (FK -> Usuarios)
Relacionamentos:
- N:1 com Usuario
- 1:N com Comentario (CASCADE)
- 1:N com Curtidas (CASCADE)
\\\
### Tabela: Conexoes
\\\
id_conexao (PK)
usuario_origem_id (FK -> Usuarios)
usuario_destino_id (FK -> Usuarios)
statusConexao (ENUM: PENDENTE, ACEITA, RECUSADA)
data_criacao (TIMESTAMP)
Relacionamentos:
- N:1 com Usuario (origem)
- N:1 com Usuario (destino)
\\\
### Tabela: Eventos
\\\
id_evento (PK)
titulo (VARCHAR)
descricao (TEXT)
data_evento (DATE)
local (VARCHAR)
usuario_id (FK -> Usuarios)
Relacionamentos:
- N:1 com Usuario
\\\
### Tabela: Comentarios
\\\
id_comentario (PK)
conteudo (TEXT)
dataComentario (TIMESTAMP)
usuario_id (FK -> Usuarios)
publicacao_id (FK -> Publicacoes)
Relacionamentos:
- N:1 com Usuario
- N:1 com Publicacao
\\\
### Tabela: Curtidas
\\\
id_curtida (PK)
usuario_id (FK -> Usuarios)
publicacao_id (FK -> Publicacoes)
dataCurtida (TIMESTAMP)
Relacionamentos:
- N:1 com Usuario
- N:1 com Publicacao
\\\
---
## 🐛 Troubleshooting
### Problema: Erro de conexão MySQL
\\\
Mensagem: com.mysql.cj.jdbc.exceptions.CommunicationsException
Solução:
1. Verificar se MySQL está rodando
2. Validar credenciais no application.properties
3. Verificar se banco 'soci' foi criado
4. Testar conexão: mysql -u root -p
\\\
### Problema: Token inválido ou expirado
\\\
Mensagem: "Token inválido ou expirado"
Solução:
1. Fazer novo login para obter novo token
2. Verificar se o token foi incluído corretamente no header Authorization
3. Verificar se passaram 2 horas desde a geração do token
\\\
### Problema: Erro ao criar usuário (email duplicado)
\\\
Mensagem: Erro de constraint SQL
Solução:
1. Usar email único
2. Verificar se o email não existe já no banco
3. Limpar dados de teste do banco se necessário
\\\
---
## 📝 Notas de Desenvolvimento
### Senhas
- Todas as senhas são **hasheadas** com bcrypt antes de armazenar
- Nunca retornar senha em endpoints de resposta
- Usar EncoderService.encode() para novo hash
- Usar EncoderService.matches() para validar senha
### JWT Token
- **Algoritmo:** HMAC256
- **Issuer:** Soci_api
- **Expiração:** 2 horas (configurável)
- **Secret Key:** Alterar em produção (atualmente hardcoded em TokenService.java)
### Transações
- Métodos de escrita (POST, PUT, DELETE) usam @Transactional
- Garantem atomicidade das operações
- Cascata de deletions configurada nos modelos
### Validação
- Usar \@Valid\ nos controllers
- DTOs com \@NotBlank\, \@Size\, etc.
- Mensagens customizadas de erro
---
**Versão da Documentação:** 1.0
**Data de Atualização:** 12 de Junho de 2026
**Mantido por:** Equipe de Desenvolvimento
