-- Script para adicionar ON DELETE CASCADE nas constraints de foreign key
-- Execute estes comandos no seu banco de dados MySQL para permitir deletar usuários em cascata

-- 1. Remover e recriar constraint em conexoes (usuario_origem_id)
ALTER TABLE `conexoes` DROP FOREIGN KEY `FK3m81xvs4hapo3qq3o61mjbgha`;
ALTER TABLE `conexoes` ADD CONSTRAINT `FK3m81xvs4hapo3qq3o61mjbgha`
  FOREIGN KEY (`usuario_origem_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

-- 2. Remover e recriar constraint em conexoes (usuario_destino_id)
ALTER TABLE `conexoes` DROP FOREIGN KEY `FKtvepikwvfrlm3nujvm52vmspo`;
ALTER TABLE `conexoes` ADD CONSTRAINT `FKtvepikwvfrlm3nujvm52vmspo`
  FOREIGN KEY (`usuario_destino_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

-- 3. Adicionar ON DELETE CASCADE em publicacoes (usuario_id)
-- Primeiro, obter o nome da constraint
SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'publicacoes' AND COLUMN_NAME = 'usuario_id' AND REFERENCED_TABLE_NAME = 'usuarios';
-- Depois executar:
-- ALTER TABLE `publicacoes` DROP FOREIGN KEY `<nome_constraint>`;
-- ALTER TABLE `publicacoes` ADD CONSTRAINT `<nome_constraint>`
--   FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

-- 4. Adicionar ON DELETE CASCADE em comentarios (id_publicacao e id_usuario)
-- Para publicacao_id:
SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'comentarios' AND COLUMN_NAME = 'id_publicacao' AND REFERENCED_TABLE_NAME = 'publicacoes';
-- Para usuario_id:
SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'comentarios' AND COLUMN_NAME = 'id_usuario' AND REFERENCED_TABLE_NAME = 'usuarios';

-- 5. Adicionar ON DELETE CASCADE em curtidas (publicacao_id e usuario_id)
-- Para publicacao_id:
SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'curtidas' AND COLUMN_NAME = 'publicacao_id' AND REFERENCED_TABLE_NAME = 'publicacoes';
-- Para usuario_id:
SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'curtidas' AND COLUMN_NAME = 'usuario_id' AND REFERENCED_TABLE_NAME = 'usuarios';

-- 6. Adicionar ON DELETE CASCADE em eventos (id_usuario)
SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'eventos' AND COLUMN_NAME = 'id_usuario' AND REFERENCED_TABLE_NAME = 'usuarios';

