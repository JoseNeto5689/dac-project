CREATE TABLE ticket
(
    ticket_id UUID NOT NULL,
    cpf       VARCHAR(255),
    valitated BOOLEAN,
    CONSTRAINT pk_ticket PRIMARY KEY (ticket_id)
);