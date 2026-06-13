# 📋 Mensagens do Sistema Soci
Documentação de todas as mensagens exibidas pelo sistema, incluindo erros, sucessos, avisos e logs.
## 📌 Índice
- [Autenticação e Login](#autenticação-e-login)
- [Conexões](#conexões)
- [Eventos](#eventos)
- [Publicações](#publicações)
- [Usuários/Cadastro](#usuárioscadastro)
- [Tokens](#tokens)
- [Logs do Sistema](#logs-do-sistema)
---
## 🔐 Autenticação e Login
### ❌ Erro: Credenciais Inválidas
`
"Login ou senha inválidos"
`
- **Código HTTP:** 401 (UNAUTHORIZED)
- **Cenário:** Quando o email não existe ou a senha está incorreta
- **Endpoint:** POST /login
- **Arquivo:** LoginService.java
### ✅ Sucesso: Login Bem-sucedido
- **Resposta:** Token JWT + ID do usuário
- **Código HTTP:** 200 (OK)
- **Formato:** \{ "idUsuario": Long, "token": String }\
### ❌ Erro: Token Inválido ou Expirado
\\\
"Token inválido ou expirado"
\\\
- **Cenário:** Token JWT expirou ou é inválido
- **Arquivo:** \TokenService.java\
### ❌ Erro: Geração de Token Falhou
\\\
"Erro ao gerar o token"
\\\
- **Cenário:** Exceção durante criação do JWT
- **Arquivo:** \TokenService.java\
---
## 🤝 Conexões
### ❌ Erro: Usuário de Origem não Encontrado
\\\
"Usuário de origem não encontrado"
\\\
- **Cenário:** ID do usuário origem não existe no banco
- **Código HTTP:** 500
- **Arquivo:** \ConexaoService.java\
### ❌ Erro: Usuário de Destino não Encontrado
\\\
"Usuário de destino não encontrado"
\\\
- **Cenário:** ID do usuário destino não existe no banco
- **Código HTTP:** 500
- **Arquivo:** \ConexaoService.java\
### ❌ Erro: Mesma Pessoa
\\\
"Usuário de origem e destino não podem ser o mesmo"
\\\
- **Cenário:** Tentativa de conectar usuário consigo mesmo
- **Código HTTP:** 500
- **Endpoint:** POST \/api/conexoes/{origemId}/{destinoId}\
- **Arquivo:** \ConexaoService.java\
### ❌ Erro: Conexão Já Existe
\\\
"Conexão já existe entre os usuários"
\\\
- **Cenário:** Uma conexão (pendente ou aceita) já existe entre estes usuários
- **Código HTTP:** 500
- **Arquivo:** \ConexaoService.java\
### ✅ Sucesso: Conexão Criada
- **Código HTTP:** 201 (CREATED)
- **Status Inicial:** PENDENTE
- **Resposta:** \ConexaoResponseDto\
### ✅ Sucesso: Conexão Deletada
- **Código HTTP:** 204 (NO CONTENT)
- **Endpoint:** DELETE \/api/conexoes/{id}\
---
## 📅 Eventos
### ❌ Erro: Usuário não Encontrado (ao criar evento)
\\\
"Usuário não encontrado"
\\\
- **Cenário:** ID do usuário não existe ao criar evento
- **Código HTTP:** 500
- **Endpoint:** POST \/api/eventos/{usuarioId}\
- **Arquivo:** \EventoService.java\
### ❌ Erro: Evento não Encontrado
\\\
"Evento não encontrado"
\\\
- **Cenário:** ID do evento não existe
- **Código HTTP:** 500
- **Endpoints:** GET/PUT/DELETE \/api/eventos/{id}\
- **Arquivo:** \EventoService.java\
### ✅ Sucesso: Evento Criado
- **Código HTTP:** 201 (CREATED)
- **Resposta:** \EventoResponseDto\
### ✅ Sucesso: Evento Atualizado
- **Código HTTP:** 200 (OK)
- **Resposta:** \EventoResponseDto\
### ✅ Sucesso: Evento Deletado
- **Código HTTP:** 204 (NO CONTENT)
---
## 📝 Publicações
### ❌ Erro: Publicação não Encontrada
\\\
"Publicacao não encontrada"
\\\
- **Cenário:** ID da publicação não existe
- **Código HTTP:** 404 (NOT FOUND)
- **Endpoints:** GET/PUT/DELETE \/publicacoes/{id}\
- **Arquivo:** \PublicacaoService.java\
- **Tipo de Exceção:** \NotFoundException\
### ✅ Sucesso: Publicação Criada
- **Código HTTP:** 201 (CREATED)
- **Resposta:** \PublicacaoDto\
### ✅ Sucesso: Publicação Atualizada
- **Código HTTP:** 200 (OK)
- **Resposta:** \PublicacaoDto\
### ✅ Sucesso: Publicação Deletada
- **Código HTTP:** 204 (NO CONTENT)
### ⚠️ Validações em Publicações
- **Campo:** \conteudo\
  - Mensagem: \"Conteúdo é obrigatório"\
  - Máximo: 2000 caracteres
  - Mensagem: \"Conteúdo muito longo"\
- **Campo:** \imagemUrl\
  - Máximo: 1024 caracteres
  - Mensagem: \"URL da imagem muito longa"\
---
## 👤 Usuários/Cadastro
### ✅ Sucesso: Usuário Cadastrado
- **Código HTTP:** 201 (CREATED)
- **Resposta:** \DadosCadastroUsuarioSaida\
- **Endpoint:** POST \/cadastrar\
### ✅ Sucesso: Usuários Listados
- **Código HTTP:** 200 (OK)
- **Resposta:** Lista de \DadosCadastroUsuarioSaida\
- **Endpoint:** GET \/cadastrar/{id}/usuarios\
- **Nota:** Retorna todos os usuários exceto o logado
### ✅ Sucesso: Usuário Encontrado
- **Código HTTP:** 200 (OK)
- **Resposta:** \DadosCadastroUsuarioSaida\
- **Endpoint:** GET \/cadastrar/{id}\
### ❌ Erro: Usuário não Encontrado
- **Código HTTP:** 404 (NOT FOUND)
- **Endpoint:** GET \/cadastrar/{id}\
### ✅ Sucesso: Usuário Atualizado
- **Código HTTP:** 200 (OK)
- **Resposta:** \DadosCadastroUsuarioSaida\
- **Endpoint:** PUT \/cadastrar/{id}\
### ✅ Sucesso: Usuário Deletado
- **Código HTTP:** 204 (NO CONTENT)
- **Endpoint:** DELETE \/cadastrar/{id}\
### ✅ Sucesso: Todos os Usuários Deletados
- **Código HTTP:** 204 (NO CONTENT)
- **Endpoint:** DELETE \/cadastrar\
---
## 🔑 Tokens
### ⏱️ Expiração
- **Tempo de vida:** 2 horas
- **Fuso horário:** -03:00 (Brasília)
- **Issuer:** \Soci_api\
---
## 📊 Logs do Sistema
### ℹ️ Logs de Validação de Token (INFO)
\\\
📋 Validando token para: [METHOD] [URI]
✅ Token validado para usuário: [EMAIL]
✅ Autenticação configurada para: [EMAIL] | [METHOD] [URI]
\\\
### ⚠️ Avisos (FINE)
\\\
⚠️  Nenhum token fornecido em: [METHOD] [URI]
\\\
### ❌ Erros do Sistema (SEVERE)
\\\
❌ Usuário não encontrado no banco: [EMAIL]
❌ Erro ao processar token: [MENSAGEM_ERRO] | URI: [URI]
\\\
---
## 📊 Resumo de Códigos HTTP
| Código | Significado | Uso |
|--------|-------------|-----|
| 200 | OK | Requisição bem-sucedida (GET, PUT) |
| 201 | CREATED | Recurso criado com sucesso (POST) |
| 204 | NO CONTENT | Requisição bem-sucedida sem retorno (DELETE) |
| 401 | UNAUTHORIZED | Login ou senha inválidos |
| 404 | NOT FOUND | Recurso não encontrado |
| 500 | INTERNAL SERVER ERROR | Erro genérico do servidor |
---
## 🔗 Endpoints Principais
### Autenticação
- POST \/login\ - Fazer login
- POST \/cadastrar\ - Cadastrar novo usuário
### Usuários
- GET \/cadastrar/{id}\ - Buscar usuário
- GET \/cadastrar/{id}/usuarios\ - Listar usuários
- PUT \/cadastrar/{id}\ - Atualizar usuário
- DELETE \/cadastrar/{id}\ - Deletar usuário
### Conexões
- POST \/api/conexoes/{origemId}/{destinoId}\ - Criar conexão
- GET \/api/conexoes\ - Listar todas as conexões
- GET \/api/conexoes/{usuarioId}\ - Listar conexões de um usuário
- DELETE \/api/conexoes/{id}\ - Deletar conexão
### Eventos
- POST \/api/eventos/{usuarioId}\ - Criar evento
- GET \/api/eventos\ - Listar eventos
- GET \/api/eventos/{id}\ - Buscar evento
- PUT \/api/eventos/{id}\ - Atualizar evento
- DELETE \/api/eventos/{id}\ - Deletar evento
### Publicações
- POST \/publicacoes\ - Criar publicação
- GET \/publicacoes\ - Listar publicações
- GET \/publicacoes/{id}\ - Buscar publicação
- PUT \/publicacoes/{id}\ - Atualizar publicação
- DELETE \/publicacoes/{id}\ - Deletar publicação
---
**Última atualização:** 12 de Junho de 2026
**Versão do Sistema:** 1.0
