-- ==========================================
-- Sequence : MAS_TENANCY_SEQ
-- ==========================================

CREATE SEQUENCE IF NOT EXISTS MAS_TENANCY_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    CACHE 1;


-- ==========================================
-- Table : MAS_TENANCY
-- ==========================================

CREATE TABLE IF NOT EXISTS MAS_TENANCY
(
    TENANT_ID      BIGINT NOT NULL DEFAULT nextval('MAS_TENANCY_SEQ'),
    TENANT_CODE    VARCHAR(50) NOT NULL,
    TENANT_NAME    VARCHAR(255) NOT NULL,

    CREATED_BY     BIGINT,
    UPDATED_BY     BIGINT,

    CREATED_AT     TIMESTAMP,
    UPDATED_AT     TIMESTAMP,

    IS_DELETED     BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT PK_MAS_TENANCY
    PRIMARY KEY (TENANT_ID),

    CONSTRAINT UK_MAS_TENANCY_CODE
    UNIQUE (TENANT_CODE)
    );


-- ==========================================
-- Indexes
-- ==========================================

CREATE INDEX IF NOT EXISTS IDX_MAS_TENANCY_NAME
    ON MAS_TENANCY (TENANT_NAME);

CREATE INDEX IF NOT EXISTS IDX_MAS_TENANCY_DELETED
    ON MAS_TENANCY (IS_DELETED);
