create table Students ( 
	s_id int8 not null PRIMARY KEY,
	firstName varchar(30) not null,
	lastName varchar(30) not null,
	created timestamp not null
);

create sequence STUDENT_PK_SEQ;