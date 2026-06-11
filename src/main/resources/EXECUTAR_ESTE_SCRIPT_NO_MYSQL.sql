-- ========================================
-- Script Completo para Adicionar ON DELETE CASCADE
-- Banco de Dados: soci (MySQL)
-- ========================================

USE soci;

-- ========================================
-- 1. TABELA: conexoes
-- ========================================
-- Alterar FK usuario_origem_id
ALTER TABLE conexoes DROP FOREIGN KEY FK3m81xvs4hapo3qq3o61mjbgha;
ALTER TABLE conexoes ADD CONSTRAINT FK3m81xvs4hapo3qq3o61mjbgha
  FOREIGN KEY (usuario_origem_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

-- Alterar FK usuario_destino_id
ALTER TABLE conexoes DROP FOREIGN KEY FKtvepikwvfrlm3nujvm52vmspo;
ALTER TABLE conexoes ADD CONSTRAINT FKtvepikwvfrlm3nujvm52vmspo
  FOREIGN KEY (usuario_destino_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

-- ========================================
-- 2. TABELA: publicacoes
-- ========================================
-- Primeiro, descubra o nome da FK executando:
-- SHOW CREATE TABLE publicacoes;
-- Procure por: CONSTRAINT `...` FOREIGN KEY (`usuario_id`)

-- Depois, substitua o nome abaixo e execute:
-- Assumindo nome genérico (ajuste conforme necessário):
-- ALTER TABLE publicacoes DROP FOREIGN KEY FKpublicacao_usuario;
-- ALTER TABLE publicacoes ADD CONSTRAINT FKpublicacao_usuario
--   FOREIGN KEY (usuario_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

-- ========================================
-- 3. TABELA: comentarios
-- ========================================
-- FK para publicacoes
-- Descubra o nome: SHOW CREATE TABLE comentarios;
-- ALTER TABLE comentarios DROP FOREIGN KEY FKcomentario_publicacao;
-- ALTER TABLE comentarios ADD CONSTRAINT FKcomentario_publicacao
--   FOREIGN KEY (id_publicacao) REFERENCES publicacoes (id_publicacao) ON DELETE CASCADE ON UPDATE CASCADE;

-- FK para usuarios
-- ALTER TABLE comentarios DROP FOREIGN KEY FKcomentario_usuario;
-- ALTER TABLE comentarios ADD CONSTRAINT FKcomentario_usuario
--   FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

-- ========================================
-- 4. TABELA: curtidas
-- ========================================
-- FK para publicacoes
-- ALTER TABLE curtidas DROP FOREIGN KEY FKcurtida_publicacao;
-- ALTER TABLE curtidas ADD CONSTRAINT FKcurtida_publicacao
--   FOREIGN KEY (publicacao_id) REFERENCES publicacoes (id_publicacao) ON DELETE CASCADE ON UPDATE CASCADE;

-- FK para usuarios
-- ALTER TABLE curtidas DROP FOREIGN KEY FKcurtida_usuario;
-- ALTER TABLE curtidas ADD CONSTRAINT FKcurtida_usuario
--   FOREIGN KEY (usuario_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

-- ========================================
-- 5. TABELA: eventos
-- ========================================
-- FK para usuarios
-- ALTER TABLE eventos DROP FOREIGN KEY FKevento_usuario;
-- ALTER TABLE eventos ADD CONSTRAINT FKevento_usuario
--   FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

-- ========================================
-- VERIFICAR CONSTRAINTS (Execute após as alterações)
-- ========================================
-- SELECT CONSTRAINT_NAME, TABLE_NAME, COLUMN_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME
-- FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
-- WHERE TABLE_SCHEMA = 'soci' AND COLUMN_NAME IN ('usuario_id', 'id_usuario', 'id_publicacao', 'publicacao_id')
-- ORDER BY TABLE_NAME;

-- ========================================
-- TESTAR (Após aplicar as alterações)
-- ========================================
-- DELETE FROM usuarios WHERE id_usuario = 52;
-- -- Deve deletar sem erro de constraint!

