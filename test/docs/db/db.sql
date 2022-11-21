CREATE TABLE test (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	subject_id INTEGER NOT NULL REFERENCES subject(id),
	status BOOLEAN NOT NULL,
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);

CREATE TABLE result 
(
	id INTEGER PRIMARY KEY autoincrement,
	user_id INTEGER NOT NULL REFERENCES user(id),
	test_id INTEGER NOT NULL REFERENCES test(id),
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
	role_id INTEGER NOT NULL REFERENCES role_object(id),
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);

CREATE TABLE subject (
	id INTEGER PRIMARY KEY autoincrement,
	name TEXT NOT NULL,
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL
);






