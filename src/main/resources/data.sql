DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id VARCHAR(250) PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    email VARCHAR(250) UNIQUE NOT NULL
);

INSERT INTO user (id, first_name, last_name, email) VALUES
(uuid(), 'John1', 'Smith', 'jonhsmith1@gmail.com'),
(uuid(), 'John2', 'Smith', 'jonhsmith2@gmail.com'),
(uuid(), 'John3', 'Smith', 'jonhsmith3@gmail.com'),
(uuid(), 'John4', 'Smith', 'jonhsmith4@gmail.com'),
(uuid(), 'John5', 'Smith', 'jonhsmith5@gmail.com'),
(uuid(), 'John6', 'Smith', 'jonhsmith6@gmail.com'),
(uuid(), 'John7', 'Smith', 'jonhsmith7@gmail.com'),
(uuid(), 'John8', 'Smith', 'jonhsmith8@gmail.com'),
(uuid(), 'John9', 'Smith', 'jonhsmith9@gmail.com'),
(uuid(), 'John10', 'Smith', 'jonhsmith10@gmail.com'),
(uuid(), 'John11', 'Smith', 'jonhsmith11@gmail.com'),
(uuid(), 'John12', 'Smith', 'jonhsmith12@gmail.com'),
(uuid(), 'John13', 'Smith', 'jonhsmith13@gmail.com'),
(uuid(), 'John14', 'Smith', 'jonhsmith14@gmail.com'),
(uuid(), 'John15', 'Smith', 'jonhsmith15@gmail.com');
