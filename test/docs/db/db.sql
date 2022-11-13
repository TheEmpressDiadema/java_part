CREATE TABLE test (
	id integer PRIMARY KEY AUTOINCREMENT,
	name text,
	subject_id integer,
	is_active boolean,
	creation_date date,
	update_date date
);

CREATE TABLE result (
	id integer PRIMARY KEY AUTOINCREMENT,
	student_id integer,
	test_id integer,
	date date,
	mark float
);

CREATE TABLE role (
	id integer PRIMARY KEY AUTOINCREMENT,
	name text
);

CREATE TABLE user (
	id integer PRIMARY KEY AUTOINCREMENT,
	name text,
	second_name text,
	patronimyc text,
	role_id integer
);

CREATE TABLE subject (
	id integer PRIMARY KEY AUTOINCREMENT,
	name text
);






