-- Assumes the database already exists, preferably PostgreSQL.
-- Create such a database before running this script.
-- Connect to PostgreSQL by logging in to your A2 Hosting account using SSH.
-- At the command line type:
-- 'psql DBNAME USERNAME' (Where you replace DBNAME and USERNAME with your credentials).
-- At the Password prompt, type the database user's password.
-- When you type the correct password, the psql prompt appears.
-- Drop all tables if any exists, before running script.
-- You can run the script with this command:
-- 'psql -f create_db.sql DBNAME' (Where DBNAME is the name of your database)
-- If ownership of sequences and tables need to be changed, a group of commands are found
-- at the bottom of this file.


create sequence availability_sequence
	increment by 1;

create sequence competence_profile_sequence
	increment by 1;

create sequence competence_sequence
	increment by 1;

create sequence person_sequence
	increment by 1;

create sequence role_sequence
	increment by 1;

create table competence
(
  competence_id integer not null
		constraint competence_pkey
			primary key,
	name varchar(255) not null
		constraint uk_pm4ejnjatkdrkhvoqspp5ovn
			unique
);

create table role
(
	role_id integer not null
		constraint role_pkey
			primary key,
	name varchar(255) not null
		constraint uk_lqaytvswxwacb7s84gcw7tk7l
			unique
);

create table person
(
	person_id integer not null
		constraint person_pkey
			primary key,
	email varchar(255)
		constraint uk_p6uxi4q2mortxh2o3lil5slpl
			unique,
	name varchar(255) not null,
	password varchar(255) not null,
	ssn varchar(13)
		constraint uk_dvt4dq1urgmoct9cnxxdsxfao
			unique,
	surname varchar(255) not null,
	username varchar(255) not null
		constraint uk_81chp0afbt6fexv12agu0gx25
			unique,
	fk_roleid integer not null
		constraint fk4xmnlbffhwko8jl9johcaqlh4
			references role
);

create table availability
(
	availability_id integer not null
		constraint availability_pkey
			primary key,
	from_date date not null,
	person_id integer not null
		constraint fkq3tmt9rj2oe484x0s960o6cfe
			references person,
	to_date date not null
);

create table competence_profile
(
	competence_profile_id integer not null
		constraint competence_profile_pkey
			primary key,
	competence_id integer not null
		constraint fk8ws3592hqrtfu29c1kf71c9ia
			references competence,
	years_of_experience numeric(19,2) not null,
	person_id integer not null
		constraint fkb2oluh6jnx00huobxrpi45uxf
			references person
);

create table competence_competenceprofiles
(
	competence_competence_id integer not null
		constraint fkfooknwdirsvsi31qmcpofkivw
			references competence,
	competenceprofiles_competence_profile_id integer not null
		constraint uk_m70d1y0yn4xxfkra4rcu9opmn
			unique
		constraint fkgmwsi32vylr18eaoyf3ick370
			references competence_profile,
	constraint competence_competenceprofiles_pkey
		primary key (competence_competence_id, competenceprofiles_competence_profile_id)
);

create table person_availabilities
(
	person_person_id integer not null
		constraint fk7xf1qefgpl33f93ihm49mvchc
			references person,
	availabilities_availability_id integer not null
		constraint uk_86f1wohma9yfkukabrdqpbd02
			unique
		constraint fkjaachhd1ywb7klucm2jgvym0n
			references availability,
	constraint person_availabilities_pkey
		primary key (person_person_id, availabilities_availability_id)
);

create table person_competenceprofiles
(
	person_person_id integer not null
		constraint fk3jo4ki963v42vudkkbljy21q1
			references person,
	competenceprofiles_competence_profile_id integer not null
		constraint uk_i5st6oi9c4wqlccaxisng7qgk
			unique
		constraint fkf4m57r5kdbx37wghcyxdnfkuy
			references competence_profile,
	constraint person_competenceprofiles_pkey
		primary key (person_person_id, competenceprofiles_competence_profile_id)
);

/*
alter sequence availability_sequence owner to USERNAME;
alter sequence competence_profile_sequence owner to USERNAME;
alter sequence competence_sequence owner to USERNAME;
alter sequence person_sequence owner to USERNAME;
alter sequence role_sequence owner to USERNAME;
alter table competence owner to USERNAME;
alter table role owner to USERNAME;
alter table person owner to USERNAME;
alter table availability owner to USERNAME;
alter table competence_profile owner to USERNAME;
alter table competence_competenceprofiles owner to USERNAME;
alter table person_availabilities owner to USERNAME;
alter table person_competenceprofiles owner to USERNAME;
*/