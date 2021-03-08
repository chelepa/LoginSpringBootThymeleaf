CREATE TABLE tb_permissao_grupo
(
   id_permissao INT NOT NULL,
   id_grupo INT NOT NULL,
   CONSTRAINT pk_per_grup PRIMARY KEY
   (
      id_permissao,
      id_grupo
   ),
   CONSTRAINT fk_perm_1 FOREIGN KEY (id_permissao) REFERENCES tb_permissao (id_permissao),
   CONSTRAINT fk_grup_1 FOREIGN KEY (id_grupo) REFERENCES tb_grupo (id_grupo)
);