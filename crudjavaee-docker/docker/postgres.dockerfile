FROM postgres:latest
MAINTAINER "carlosmedeiroslima@gmail.com"


# Adicionando os scripts SQL para serem executados na criação do banco
COPY ./db/ /docker-entrypoint-initdb.d/

