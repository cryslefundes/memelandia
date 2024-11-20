CREATE TABLE usuarios (
     id UUID NOT NULL,
     nome VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     data_cadastro date NOT NULL,
     CONSTRAINT pk_usuarios PRIMARY KEY (id)
);