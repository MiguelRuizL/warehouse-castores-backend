USE almacen_castores_mruiz;

CREATE TABLE roles(
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(50)
);

CREATE TABLE users(
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(100),
    email NVARCHAR(100),
    password NVARCHAR(MAX),
    id_role INT,
    status BIT,
    CONSTRAINT user_role_fk FOREIGN KEY (id_role) REFERENCES roles (id)
);

CREATE TABLE products (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(255),
    quantity INT,
    status BIT
);

CREATE TABLE logbook (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    movement_type VARCHAR(20),
    id_product BIGINT,
    id_user BIGINT,
    quantity INT,
    done_at DATETIME,
    CONSTRAINT product_logbook_fk FOREIGN KEY (id_product) REFERENCES products (id),
    CONSTRAINT user_logbook_fk FOREIGN KEY (id_user) REFERENCES users (id)
);