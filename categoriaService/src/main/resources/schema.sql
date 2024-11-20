CREATE TABLE categorias (
    id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_cadastro date NOT NULL,
    usuario_id UUID,
    CONSTRAINT pk_categorias PRIMARY KEY (id)
);