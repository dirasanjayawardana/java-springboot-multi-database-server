-- ---------------------------- Oracle ---------------------------------------
CREATE TABLE users
(
    username         VARCHAR2(100) NOT NULL,
    password         VARCHAR2(100) NOT NULL,
    name             VARCHAR2(100) NOT NULL,
    token            VARCHAR2(100),
    token_expired_at NUMBER(19), -- Menggunakan NUMBER untuk BIGINT
    PRIMARY KEY (username),
    UNIQUE (token)
);

CREATE TABLE contacts
(
    id         VARCHAR2(100) NOT NULL,
    username   VARCHAR2(100) NOT NULL,
    first_name VARCHAR2(100) NOT NULL,
    last_name  VARCHAR2(100),
    phone      VARCHAR2(100),
    email      VARCHAR2(100),
    PRIMARY KEY (id),
    CONSTRAINT fk_users_contacts FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE addresses
(
    id          VARCHAR2(100) NOT NULL,
    contact_id  VARCHAR2(100) NOT NULL,
    street      VARCHAR2(200),
    city        VARCHAR2(100),
    province    VARCHAR2(100),
    country     VARCHAR2(100) NOT NULL,
    postal_code VARCHAR2(10),
    PRIMARY KEY (id),
    CONSTRAINT fk_contacts_addresses FOREIGN KEY (contact_id) REFERENCES contacts (id)
);



-- ------------------------ MySql ---------------------------------------------------------------
CREATE TABLE users
(
    username         VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL,
    name             VARCHAR(100) NOT NULL,
    token            VARCHAR(100),
    token_expired_at BIGINT,
    PRIMARY KEY (username),
    UNIQUE (token)
) ENGINE InnoDB;


CREATE TABLE contacts
(
    id         VARCHAR(100) NOT NULL,
    username   VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100),
    phone      VARCHAR(100),
    email      VARCHAR(100),
    PRIMARY KEY (id),
    FOREIGN KEY fk_users_contacts (username) REFERENCES users (username)
) ENGINE InnoDB;


CREATE TABLE addresses
(
    id          VARCHAR(100) NOT NULL,
    contact_id  VARCHAR(100) NOT NULL,
    street      VARCHAR(200),
    city        VARCHAR(100),
    province    VARCHAR(100),
    country     VARCHAR(100) NOT NULL,
    postal_code VARCHAR(10),
    PRIMARY KEY (id),
    FOREIGN KEY fk_contacts_addresses (contact_id) REFERENCES contacts (id)
) ENGINE InnoDB;

-- ----------------------- MySql Book store ---------------------------------
CREATE TABLE book (
    id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_deleted TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
