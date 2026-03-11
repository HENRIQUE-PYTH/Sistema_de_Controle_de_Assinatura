CREATE TABLE assinatura(
    id BIGINT NOT NULL AUTO_INCREMENT,

    data_de_Inicio DATE,
    data_de_Expiracao DATE,
    proxima_Cobranca DATE,
    status_de_Assinatura VARCHAR(50),
    data_de_Cancelamento DATE,

    usuario_id BIGINT,
    plano_id BIGINT,

    PRIMARY KEY (id),

    CONSTRAINT fk_assinatura_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario(id),

    CONSTRAINT fk_assinatura_plano
        FOREIGN KEY (plano_id)
        REFERENCES plano(id)
);