CREATE TABLE test (
	id integer primary key autoincrement,
	name text not null,
	subject_id integer references subject(id) not null,
	status boolean not null,
	created datetime not null,
	updated datetime not null
);

CREATE TABLE result 
(
	id integer primary key autoincrement,
	student_id integer references user(id) not null,
	test_id integer references test(id) not null,
	date date not null,
	mark float not null,
	created datetime not null,
	updated datetime not null
);

CREATE TABLE role (
	id integer primary key autoincrement,
	name text not null,
	created datetime not null,
	updated datetime not null
);

CREATE TABLE user (
	id integer primary key autoincrement,
	name text not null,
	second_name text not null,
	patronimyc text not null,
	role_id integer references role(id) not null,
	created datetime not null,
	updated datetime not null
);

CREATE TABLE subject (
	id integer primary key autoincrement,
	name text not null
	created datetime not null,
	updated datetime not null
);






