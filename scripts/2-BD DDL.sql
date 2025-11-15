USE almacen_castores_mruiz;

CREATE TABLE roles(
    id INT PRIMARY KEY,
    name NVARCHAR(50)
);

CREATE TABLE users(
    id BIGINT PRIMARY KEY,
    username NVARCHAR(100),
    email NVARCHAR(100),
    password NVARCHAR(MAX),
    id_role INT,
    status BIT,
    CONSTRAINT user_role_fk FOREIGN KEY (id_role) REFERENCES roles (id)
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    product NVARCHAR(255),
    quantity INT,
    status BIT
);

CREATE TABLE logbook (
    id BIGINT PRIMARY KEY,
    movement_type VARCHAR(20),
    id_product BIGINT,
    id_user BIGINT,
    quantity INT,
    done_at DATETIME,
    CONSTRAINT product_logbook_fk FOREIGN KEY (id_product) REFERENCES product (id),
    CONSTRAINT user_logbook_fk FOREIGN KEY (id_user) REFERENCES users (id)
);