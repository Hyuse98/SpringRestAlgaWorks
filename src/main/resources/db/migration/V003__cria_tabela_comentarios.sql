CREATE TABLE comentario (
    id bigint not null AUTO_INCREMENT,
    ordem_servico_id bigint not null,
    descricao text not null,
    data_envio datetime not null,
    
    PRIMARY KEY (id)
    );
    
    ALTER TABLE comentario ADD CONSTRAINT fk_comentario_ordem_servico
    FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico (id);