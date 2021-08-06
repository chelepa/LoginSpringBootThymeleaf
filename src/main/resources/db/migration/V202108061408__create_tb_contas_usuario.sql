CREATE TABLE IF NOT EXISTS tb_contas_usuario (
  id_contas INT NOT NULL,
  id_usuario INT NOT NULL,
  PRIMARY KEY (id_contas, id_usuario),
  INDEX fk_tb_contas_usuario_tb_usuario_idx (id_usuario) VISIBLE,
  INDEX fk_tb_contas_usuario_tb_contas_idx (id_contas) VISIBLE,
  CONSTRAINT fk_tb_contas_usuario_tb_contas FOREIGN KEY (id_contas) REFERENCES tb_contas (id_contas) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_tb_contas_usuario_tb_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
)