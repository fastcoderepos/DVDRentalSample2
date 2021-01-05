DROP SCHEMA IF EXISTS dvdrental CASCADE;
CREATE SCHEMA IF NOT EXISTS dvdrental;

CREATE SEQUENCE dvdrental.actor_seq increment 1 start 1;

CREATE TABLE dvdrental.actor (
    actor_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.actor_seq') PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL
);

CREATE SEQUENCE dvdrental.category_seq increment 1 start 1;

CREATE TABLE dvdrental.category (
    category_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.category_seq') PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL
);

CREATE SEQUENCE dvdrental.country_seq increment 1 start 1;

CREATE TABLE dvdrental.country (
    country_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.country_seq') PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL
);

CREATE SEQUENCE dvdrental.city_seq increment 1 start 1;

CREATE TABLE dvdrental.city (
    city_id integer NOT NULL DEFAULT NEXTVAL ('dvdrental.city_seq') PRIMARY KEY,
    city VARCHAR(50) NOT NULL,
    country_id smallint NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (country_id) REFERENCES dvdrental.country(country_id)
);

CREATE SEQUENCE dvdrental.address_seq increment 1 start 1;

CREATE TABLE dvdrental.address (
    address_id integer DEFAULT NEXTVAL ('dvdrental.address_seq') PRIMARY KEY,
    address VARCHAR(50) NOT NULL,
    address2 VARCHAR(50),
    district VARCHAR(20) NOT NULL,
    city_id smallint NOT NULL,
    postal_code VARCHAR(10),
    phone VARCHAR(20) NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (city_id) REFERENCES dvdrental.city(city_id)
);

CREATE SEQUENCE dvdrental.customer_seq increment 1 start 1;

CREATE TABLE dvdrental.customer (
    customer_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.customer_seq') PRIMARY KEY,
    store_id smallint NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    address_id smallint NOT NULL,
    activebool boolean DEFAULT true NOT NULL,
    create_date DATE DEFAULT now() NOT NULL,
    last_update TIMESTAMP DEFAULT now(),
    active integer,
    FOREIGN KEY (address_id) REFERENCES dvdrental.address(address_id)
 
);

CREATE SEQUENCE dvdrental.language_seq increment 1 start 1;

CREATE TABLE dvdrental.language (
    language_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.language_seq') PRIMARY KEY,
    name character(20) NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL
);

CREATE SEQUENCE dvdrental.film_seq increment 1 start 1;

CREATE TABLE dvdrental.film (
    film_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.film_seq') PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description text,
    release_year integer,
    language_id smallint NOT NULL,
    rental_duration smallint DEFAULT 3 NOT NULL,
    rental_rate numeric(4,2) DEFAULT 4.99 NOT NULL,
    length smallint,
    replacement_cost numeric(5,2) DEFAULT 19.99 NOT NULL,
    rating VARCHAR(255),
    last_update TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (language_id) REFERENCES dvdrental.language(language_id)
 
);

CREATE TABLE dvdrental.film_actor (
    actor_id smallint NOT NULL,
    film_id smallint NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL,
   CONSTRAINT pk_dvdrental_filmactor PRIMARY KEY (actor_id, film_id),
    FOREIGN KEY (actor_id) REFERENCES dvdrental.actor(actor_id),
    FOREIGN KEY (film_id) REFERENCES dvdrental.film(film_id)
 
);

CREATE TABLE dvdrental.film_category (
    film_id smallint NOT NULL,
    category_id smallint NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL,
   CONSTRAINT pk_dvdrental_filmcategory PRIMARY KEY (category_id, film_id),
    FOREIGN KEY (film_id) REFERENCES dvdrental.film(film_id),
    FOREIGN KEY (category_id) REFERENCES dvdrental.category(category_id)
);

CREATE SEQUENCE dvdrental.inventory_seq increment 1 start 1;

CREATE TABLE dvdrental.inventory (
    inventory_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.inventory_seq') PRIMARY KEY,
    film_id smallint NOT NULL,
    store_id smallint NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (film_id) REFERENCES dvdrental.film(film_id)
);

CREATE SEQUENCE dvdrental.staff_seq increment 1 start 1;

CREATE TABLE dvdrental.staff (
    staff_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.staff_seq') PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    address_id smallint NOT NULL,
    email VARCHAR(50),
    store_id smallint NOT NULL,
    active boolean DEFAULT true NOT NULL,
    username VARCHAR(16) NOT NULL,
    password VARCHAR(40),
    last_update TIMESTAMP DEFAULT now() NOT NULL,
    picture bytea,
    FOREIGN KEY (address_id) REFERENCES dvdrental.address(address_id)
 
);

CREATE SEQUENCE dvdrental.store_seq increment 1 start 1;

CREATE TABLE dvdrental.store (
    store_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.store_seq') PRIMARY KEY,
    manager_staff_id smallint NOT NULL,
    address_id smallint NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (address_id) REFERENCES dvdrental.address(address_id),
    FOREIGN KEY (manager_staff_id) REFERENCES dvdrental.staff(staff_id)
);

CREATE SEQUENCE dvdrental.rental_seq increment 1 start 1;

CREATE TABLE dvdrental.rental (
    rental_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.rental_seq') PRIMARY KEY,
    rental_date TIMESTAMP NOT NULL,
    inventory_id integer NOT NULL,
    customer_id smallint NOT NULL,
    return_date TIMESTAMP,
    staff_id smallint NOT NULL,
    last_update TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES dvdrental.customer(customer_id),
    FOREIGN KEY (staff_id) REFERENCES dvdrental.staff(staff_id),
    FOREIGN KEY (inventory_id) REFERENCES dvdrental.inventory(inventory_id)
);
CREATE SEQUENCE dvdrental.payment_seq increment 1 start 1;

CREATE TABLE dvdrental.payment (
    payment_id INT NOT NULL DEFAULT NEXTVAL ('dvdrental.payment_seq') PRIMARY KEY,
    customer_id smallint NOT NULL,
    staff_id smallint NOT NULL,
    rental_id integer NOT NULL,
    amount numeric(5,2) NOT NULL,
    payment_date TIMESTAMP NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES dvdrental.staff(staff_id),
    FOREIGN KEY (customer_id) REFERENCES dvdrental.customer(customer_id),
    FOREIGN KEY (rental_id) REFERENCES dvdrental.rental(rental_id)
);






