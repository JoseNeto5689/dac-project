CREATE TABLE event
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE event_date
(
    event_id UUID NOT NULL,
    date_id  UUID NOT NULL
);

CREATE TABLE event_info
(
    id          UUID NOT NULL,
    event_start TIMESTAMP WITHOUT TIME ZONE,
    event_end   TIMESTAMP WITHOUT TIME ZONE,
    address     VARCHAR(255),
    CONSTRAINT pk_eventinfo PRIMARY KEY (id)
);

CREATE TABLE event_organizers
(
    event_id      UUID NOT NULL,
    organizers_id UUID NOT NULL
);

CREATE TABLE field
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    type        VARCHAR(255),
    description VARCHAR(255),
    modality_id UUID,
    CONSTRAINT pk_field PRIMARY KEY (id)
);

CREATE TABLE field_response
(
    id       UUID NOT NULL,
    content  VARCHAR(255),
    field_id UUID,
    CONSTRAINT pk_fieldresponse PRIMARY KEY (id)
);

CREATE TABLE modality
(
    id       UUID NOT NULL,
    type     VARCHAR(255),
    event_id UUID,
    CONSTRAINT pk_modality PRIMARY KEY (id)
);

CREATE TABLE organizer
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_organizer PRIMARY KEY (id)
);

CREATE TABLE organizer_events
(
    organizer_id UUID NOT NULL,
    events_id    UUID NOT NULL
);

CREATE TABLE person
(
    id       UUID NOT NULL,
    name     VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    cpf      VARCHAR(255),
    birthday TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE TABLE promo_ticket
(
    id            UUID NOT NULL,
    modality_id   UUID,
    event_id      UUID,
    event_date_id UUID,
    CONSTRAINT pk_promoticket PRIMARY KEY (id)
);

CREATE TABLE ticket
(
    id            UUID NOT NULL,
    event_id      UUID,
    owner_id      UUID,
    event_date_id UUID,
    modality_id   UUID,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

CREATE TABLE ticket_package
(
    id   UUID NOT NULL,
    type VARCHAR(255),
    CONSTRAINT pk_ticketpackage PRIMARY KEY (id)
);

CREATE TABLE ticket_package_ticket_options
(
    ticket_package_id UUID NOT NULL,
    ticket_options_id UUID NOT NULL
);

CREATE TABLE ticket_response_list
(
    ticket_id        UUID NOT NULL,
    response_list_id UUID NOT NULL
);

ALTER TABLE event_date
    ADD CONSTRAINT uc_event_date_date UNIQUE (date_id);

ALTER TABLE ticket_package_ticket_options
    ADD CONSTRAINT uc_ticket_package_ticket_options_ticketoptions UNIQUE (ticket_options_id);

ALTER TABLE ticket_response_list
    ADD CONSTRAINT uc_ticket_response_list_responselist UNIQUE (response_list_id);

ALTER TABLE field_response
    ADD CONSTRAINT FK_FIELDRESPONSE_ON_FIELD FOREIGN KEY (field_id) REFERENCES field (id);

ALTER TABLE field
    ADD CONSTRAINT FK_FIELD_ON_MODALITY FOREIGN KEY (modality_id) REFERENCES modality (id);

ALTER TABLE modality
    ADD CONSTRAINT FK_MODALITY_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE promo_ticket
    ADD CONSTRAINT FK_PROMOTICKET_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE promo_ticket
    ADD CONSTRAINT FK_PROMOTICKET_ON_EVENTDATE FOREIGN KEY (event_date_id) REFERENCES event_info (id);

ALTER TABLE promo_ticket
    ADD CONSTRAINT FK_PROMOTICKET_ON_MODALITY FOREIGN KEY (modality_id) REFERENCES modality (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_EVENTDATE FOREIGN KEY (event_date_id) REFERENCES event_info (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_MODALITY FOREIGN KEY (modality_id) REFERENCES modality (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_OWNER FOREIGN KEY (owner_id) REFERENCES person (id);

ALTER TABLE event_date
    ADD CONSTRAINT fk_evedat_on_event FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE event_date
    ADD CONSTRAINT fk_evedat_on_event_info FOREIGN KEY (date_id) REFERENCES event_info (id);

ALTER TABLE event_organizers
    ADD CONSTRAINT fk_eveorg_on_event FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE event_organizers
    ADD CONSTRAINT fk_eveorg_on_organizer FOREIGN KEY (organizers_id) REFERENCES organizer (id);

ALTER TABLE organizer_events
    ADD CONSTRAINT fk_orgeve_on_event FOREIGN KEY (events_id) REFERENCES event (id);

ALTER TABLE organizer_events
    ADD CONSTRAINT fk_orgeve_on_organizer FOREIGN KEY (organizer_id) REFERENCES organizer (id);

ALTER TABLE ticket_package_ticket_options
    ADD CONSTRAINT fk_ticpacticopt_on_promo_ticket FOREIGN KEY (ticket_options_id) REFERENCES promo_ticket (id);

ALTER TABLE ticket_package_ticket_options
    ADD CONSTRAINT fk_ticpacticopt_on_ticket_package FOREIGN KEY (ticket_package_id) REFERENCES ticket_package (id);

ALTER TABLE ticket_response_list
    ADD CONSTRAINT fk_ticreslis_on_field_response FOREIGN KEY (response_list_id) REFERENCES field_response (id);

ALTER TABLE ticket_response_list
    ADD CONSTRAINT fk_ticreslis_on_ticket FOREIGN KEY (ticket_id) REFERENCES ticket (id);