INSERT INTO `LoginSpringBootThymeleaf`.`tb_usuario` (`ds_nome`, `ds_login`, `ds_senha`, `fl_ativo`) VALUES ('Claudimir Chelepa', 'Chelepa', '$2a$10$YYe9VtFGZoWvrNSZNV/AeuVSTOMQLxcGia4IQEl/yVaxrfAnPDcuO', '1');

INSERT INTO `LoginSpringBootThymeleaf`.`tb_grupo` (`ds_nome`, `ds_descricao`) VALUES ('ADMINISTRADORES', 'Adminitrador');
INSERT INTO `LoginSpringBootThymeleaf`.`tb_grupo` (`ds_nome`, `ds_descricao`) VALUES ('USUARIOS', 'Usuários Comum');
INSERT INTO `LoginSpringBootThymeleaf`.`tb_grupo` (`ds_nome`, `ds_descricao`) VALUES ('BACKOFFICE', 'Backoffice - Cadastros');

INSERT INTO `LoginSpringBootThymeleaf`.`tb_permissao` (`ds_permissao`, `ds_descricao`) VALUES ('ROLE_ADMIN', 'ADMINISTRAÇÂO COMPLETA');
INSERT INTO `LoginSpringBootThymeleaf`.`tb_permissao` (`ds_permissao`, `ds_descricao`) VALUES ('ROLE_CONSULTAUSUARIO', 'CONSULTA DE USUÁRIOS');
INSERT INTO `LoginSpringBootThymeleaf`.`tb_permissao` (`ds_permissao`, `ds_descricao`) VALUES ('ROLE_CADASTROUSUARIO', 'CADASTRO DE NOVOS USUÁRIOS');

INSERT INTO `LoginSpringBootThymeleaf`.`tb_permissao_grupo` (`id_permissao`, `id_grupo`) VALUES ('1', '1');
INSERT INTO `LoginSpringBootThymeleaf`.`tb_permissao_grupo` (`id_permissao`, `id_grupo`) VALUES ('2', '2');
INSERT INTO `LoginSpringBootThymeleaf`.`tb_permissao_grupo` (`id_permissao`, `id_grupo`) VALUES ('3', '3');

INSERT INTO `LoginSpringBootThymeleaf`.`tb_usuario_grupo` (`id_usuario`, `id_grupo`) VALUES ('1', '1');