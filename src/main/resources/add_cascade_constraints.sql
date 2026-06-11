-- Script SQL para adicionar ON DELETE CASCADE nas Foreign Keys do banco soci
-- Execute este script no MySQL Workbench ou via linha de comando

USE soci;

-- 1. CONEXOES - remover FK e recriar com ON DELETE CASCADE
ALTER TABLE conexoes DROP FOREIGN KEY FK3m81xvs4hapo3qq3o61mjbgha;
ALTER TABLE conexoes ADD CONSTRAINT FK3m81xvs4hapo3qq3o61mjbgha
  FOREIGN KEY (usuario_origem_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE;

ALTER TABLE conexoes DROP FOREIGN KEY FKtvepikwvfrlm3nujvm52vmspo;
ALTER TABLE conexoes ADD CONSTRAINT FKtvepikwvfrlm3nujvm52vmspo
  FOREIGN KEY (usuario_destino_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE;

-- 2. PUBLICACOES
-- Primeiro, encontre o nome exato da FK (varia conforme geração)
-- Executar: SHOW CREATE TABLE publicacoes;
-- Depois ajuste o nome da constraint abaixo e execute:
-- ALTER TABLE publicacoes DROP FOREIGN KEY <nome_FK>;
-- ALTER TABLE publicacoes ADD CONSTRAINT <nome_FK>
--   FOREIGN KEY (usuario_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE;

-- 3. COMENTARIOS
-- FK para publicacoes
-- ALTER TABLE comentarios DROP FOREIGN KEY <nome_FK>;
-- ALTER TABLE comentarios ADD CONSTRAINT <nome_FK>
--   FOREIGN KEY (id_publicacao) REFERENCES publicacoes (id_publicacao) ON DELETE CASCADE;

-- FK para usuarios
-- ALTER TABLE comentarios DROP FOREIGN KEY <nome_FK>;
-- ALTER TABLE comentarios ADD CONSTRAINT <nome_FK>
--   FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE;

-- 4. CURTIDAS
-- FK para publicacoes
-- ALTER TABLE curtidas DROP FOREIGN KEY <nome_FK>;
-- ALTER TABLE curtidas ADD CONSTRAINT <nome_FK>
--   FOREIGN KEY (publicacao_id) REFERENCES publicacoes (id_publicacao) ON DELETE CASCADE;

-- FK para usuarios
-- ALTER TABLE curtidas DROP FOREIGN KEY <nome_FK>;
-- ALTER TABLE curtidas ADD CONSTRAINT <nome_FK>
--   FOREIGN KEY (usuario_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE;

-- 5. EVENTOS
-- FK para usuarios
-- ALTER TABLE eventos DROP FOREIGN KEY <nome_FK>;
-- ALTER TABLE eventos ADD CONSTRAINT <nome_FK>
--   FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE;

-- Para descobrir os nomes das constraints, use:
SHOW CREATE TABLE publicacoes\G
SHOW CREATE TABLE comentarios\G
SHOW CREATE TABLE curtidas\G
SHOW CREATE TABLE eventos\G

