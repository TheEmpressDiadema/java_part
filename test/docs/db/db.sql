CREATE TABLE test (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	subject_id INTEGER REFERENCES subject(id) NOT NULL,
	status BOOLEAN NOT NULL,
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);

CREATE TABLE result 
(
	id INTEGER PRIMARY KEY autoincrement,
	user_id INTEGER REFERENCES user(id) NOT NULL,
	test_id INTEGER REFERENCES test(id) NOT NULL,
	date DATETIME NOT NULL,
	mark FLOAT NOT NULL,
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);

CREATE TABLE role_object (
	id INTEGER PRIMARY KEY autoincrement,
	name TEXT NOT NULL,
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);

CREATE TABLE user (
	id INTEGER PRIMARY KEY autoincrement,
	name TEXT NOT NULL,
	second_name TEXT NOT NULL,
	patronimyc TEXT NOT NULL,
	role_id INTEGER REFERENCES role(id) NOT NULL,
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);

CREATE TABLE subject (
	id INTEGER PRIMARY KEY autoincrement,
	name TEXT NOT NULL
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);






