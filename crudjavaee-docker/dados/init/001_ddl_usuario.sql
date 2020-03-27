/*Use o schema Criado */
create schema "sistemaexemplods"

CREATE SEQUENCE sistemaexemplods.usuario_seq;

CREATE TABLE sistemaexemplods.tb_usuario(
  id int not null default nextval('sistemaexemplods.usuario_seq'),
  nome varchar(100) not null,
  usuario varchar(100) not null,
  senha varchar(20) not null,
  ativo boolean not null default true,
  PRIMARY KEY ( ID )
);