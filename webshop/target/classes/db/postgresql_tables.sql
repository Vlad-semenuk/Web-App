DROP TABLE users;
DROP TABLE products;
DROP TABLE categories;
DROP TABLE manufactorers;
CREATE TABLE users(
        login varchar(32) NOT NULL,
        first_name varchar(32) NOT NULL,
        last_name varchar(32) NOT NULL,
        email varchar(32) NOT NULL,
        password varchar(32) NOT NULL,
        spam boolean NOT NULL,
        avatar varchar(45) NOT NULL,
        role_id INTEGER,
        PRIMARY KEY (login)
      );

CREATE TABLE categories(
    category_id SERIAL PRIMARY KEY NOT NULL,
	category_name VARCHAR(32) NOT NULL
);
CREATE TABLE manufactorers(
    manufacturer_id SERIAL PRIMARY KEY NOT NULL,
	manufacturer_name VARCHAR(32) NOT NULL
);

CREATE TABLE products(
    product_id SERIAL PRIMARY KEY NOT NULL,
	product_name VARCHAR(32) NOT NULL,
	product_description VARCHAR(45) NOT NULL,
	product_image VARCHAR(32) NOT NULL,
	product_price DECIMAL NOT NULL,
	product_manufacturer INTEGER NOT NULL,
	product_category INTEGER NOT NULL,
	FOREIGN KEY (product_manufacturer) REFERENCES manufactorers(manufacturer_id)
       ON UPDATE NO ACTION ON DELETE CASCADE,
	FOREIGN KEY (product_category) REFERENCES categories(category_id)
       ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE statuses(
  status_id SERIAL PRIMARY KEY NOT NULL,
  status_name VARCHAR(32) NOT NULL
);

CREATE TABLE order_entry(
  entry_id SERIAL PRIMARY KEY NOT NULL,
  entry_product_id INTEGER NOT NULL,
  entry_order_id INTEGER NOT NULL,
  entry_product_quantity INTEGER NOT NULL,
  entry_total_price decimal NOT NULL,
  FOREIGN KEY (entry_product_id) REFERENCES products(product_id)
    ON UPDATE NO ACTION ON DELETE CASCADE,
  FOREIGN KEY (entry_order_id) REFERENCES orders(order_id)
);

CREATE TABLE orders(
 order_id SERIAL  PRIMARY KEY NOT NULL,
 order_date DATE NOT NULL,
 order_user_login varchar(32)  NOT NULL,
 order_user_address varchar(32)  NOT NULL,
 order_user_card varchar(19)  NOT NULL,
 order_status INTEGER NOT NULL,
 order_delivery_method INTEGER NOT NULL,
 order_payment_method INTEGER NOT NULL,
 FOREIGN KEY (order_user_login) REFERENCES users(login)
   ON UPDATE NO ACTION ON DELETE CASCADE,
 FOREIGN KEY (order_status) REFERENCES statuses(status_id)
   ON UPDATE NO ACTION ON DELETE CASCADE
);