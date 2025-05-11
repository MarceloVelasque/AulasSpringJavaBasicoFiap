-- SEQUÊNCIAS
CREATE SEQUENCE COLETA_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE NOTIFICACAO_SEQ START WITH 1 INCREMENT BY 1;

-- TABELA COLETA
CREATE TABLE tbl_coleta (
                            id NUMBER PRIMARY KEY,
                            tipo_material VARCHAR2(50),
                            tratamento VARCHAR2(100),
                            data_coleta DATE
);

-- TABELA NOTIFICACAO
CREATE TABLE tbl_notificacao (
                                 id NUMBER PRIMARY KEY,
                                 tipo_notificacao VARCHAR2(50),
                                 descricao VARCHAR2(255),
                                 data_notificacao DATE
);
