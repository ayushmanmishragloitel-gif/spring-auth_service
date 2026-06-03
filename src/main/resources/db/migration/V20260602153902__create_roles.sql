-- ==========================================
-- Sequence : ROLES_SEQ
-- ==========================================

CREATE SEQUENCE IF NOT EXISTS ROLES_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    CACHE 1;


-- ==========================================
-- Table : ROLES
-- ==========================================

CREATE TABLE IF NOT EXISTS ROLES
(
    ROLE_ID         BIGINT NOT NULL DEFAULT nextval('ROLES_SEQ'),

    TENANT_ID       BIGINT NOT NULL,

    ROLE_CODE       VARCHAR(50) NOT NULL,
    ROLE_NAME       VARCHAR(100) NOT NULL,

    DESCRIPTION     VARCHAR(500),

    STATUS          VARCHAR(20),

    CREATED_BY      BIGINT,
    UPDATED_BY      BIGINT,

    CREATED_AT      TIMESTAMP,
    UPDATED_AT      TIMESTAMP,

    IS_DELETED      BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT PK_ROLES
    PRIMARY KEY (ROLE_ID),

    CONSTRAINT UK_ROLES_CODE
    UNIQUE (TENANT_ID, ROLE_CODE),

    CONSTRAINT FK_ROLES_TENANT
    FOREIGN KEY (TENANT_ID)
    REFERENCES MAS_TENANCY (TENANT_ID)
    );


-- ==========================================
-- Indexes
-- ==========================================

CREATE INDEX IF NOT EXISTS IDX_ROLES_TENANT
    ON ROLES (TENANT_ID);

CREATE INDEX IF NOT EXISTS IDX_ROLES_NAME
    ON ROLES (ROLE_NAME);

CREATE INDEX IF NOT EXISTS IDX_ROLES_STATUS
    ON ROLES (STATUS);

CREATE INDEX IF NOT EXISTS IDX_ROLES_DELETED
    ON ROLES (IS_DELETED);