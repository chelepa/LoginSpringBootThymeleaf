CREATE TABLE tb_usuario(
   id_usuario INT NOT NULL AUTO_INCREMENT,
   ds_nome VARCHAR (60) NOT NULL,
   ds_login VARCHAR (60) NOT NULL,
   ds_senha VARCHAR (400) NOT NULL,
   fl_ativo NUMERIC(10) NOT NULL,
   PRIMARY KEY (id_usuario)
);