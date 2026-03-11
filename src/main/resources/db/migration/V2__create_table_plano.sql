CREATE TABLE  plano(
    id BIGINT NOT NULL AUTO_INCREMENT,
    status_plano VARCHAR(50)NOT NULL,
    valor_planos DECIMAL(10,2) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criacao_do_plano DATE NOT NULL,
    PRIMARY KEY (id)
);