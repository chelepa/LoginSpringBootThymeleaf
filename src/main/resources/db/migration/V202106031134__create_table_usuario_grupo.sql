CREATE TABLE tb_usuario_grupo(
  id_usuario INT NOT NULL,  
  id_grupo   INT NOT NULL,
  CONSTRAINT pk_usu_grup   PRIMARY KEY(id_usuario,id_grupo),
  FOREIGN KEY(id_usuario) REFERENCES tb_usuario(id_usuario), 
  FOREIGN KEY(id_grupo)  REFERENCES tb_grupo(id_grupo)
);