CREATE TABLE memes (
    id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    data_cadastro date NOT NULL,
    categoria_id UUID,
    usuario_id UUID,
    CONSTRAINT pk_memes PRIMARY KEY (id)
);