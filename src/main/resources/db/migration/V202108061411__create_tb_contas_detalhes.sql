CREATE TABLE IF NOT EXISTS tb_contas_detalhes (
  id_contas_detalhes INT NOT NULL,
  descricao VARCHAR(45) NULL DEFAULT NULL,
  valor DECIMAL(10,2) NULL DEFAULT NULL,
  pagemento TINYINT NULL DEFAULT NULL,
  id_contas INT NOT NULL,
  id_files VARCHAR(255),
  PRIMARY KEY (id_contas_detalhes),
  INDEX fk_tb_contas_detalhes_tb_contas_idx (id_contas ASC) VISIBLE,
  INDEX fk_tb_contas_detalhes_tb_files1_idx (id_files ASC) VISIBLE,
  CONSTRAINT fk_tb_contas_detalhes_tb_contas
    FOREIGN KEY (id_contas)
    REFERENCES tb_contas (id_contas),
  CONSTRAINT fk_tb_contas_detalhes_tb_files1
    FOREIGN KEY (id_files)
    REFERENCES tb_files (id_files)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)