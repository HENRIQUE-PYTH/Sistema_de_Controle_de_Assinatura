CREATE TABLE pagamentos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    assinatura_id BIGINT,
    valor_pago DECIMAL(10,2) NOT NULL,
    data_de_pagamento DATE,
    tipo_do_pagamento VARCHAR(50) NOT NULL,
    status_pagamento VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),

    CONSTRAINT fk_pagamento_assinatura
                       FOREIGN KEY (assinatura_id)
                       REFERENCES assinatura(id)
);