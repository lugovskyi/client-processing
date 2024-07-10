CREATE SCHEMA IF NOT EXISTS creator;

CREATE TABLE IF NOT EXISTS creator.client
(
    is_fop            boolean,
    is_fraud_assigned boolean,
    id                bigint NOT NULL,
    address           varchar(255),
    client_id         varchar(255),
    passport          varchar(255),
    tax_number        varchar(255),
    PRIMARY KEY (id)
)