CREATE TABLE IF NOT EXISTS tb_contas (
  id_contas INT NOT NULL,
  nm_descricao VARCHAR(45) NULL,
  valor DECIMAL(10,2) NULL,
  PRIMARY KEY (id_contas)
)