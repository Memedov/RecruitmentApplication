INSERT INTO ROLE (ROLE_ID, NAME) VALUES (1, 'recruiter');
INSERT INTO ROLE (ROLE_ID, NAME) VALUES (2, 'applicant');

INSERT INTO PERSON (name, surname, username, password, role_id)
VALUES ('Greta', 'Borg', 'borg', 'wl9nk23a', 1);
INSERT INTO PERSON (name, surname, ssn, email, username, password, role_id)
VALUES ('Per', 'Strand', '19671212-1211', 'per@strand.kth.se', 'strand', 'passstrand', 2);

INSERT INTO AVAILABILITY (availability_id, person_id, from_date, to_date)
VALUES (1, 2, '2014-02-23', '2014-05-25');
INSERT INTO AVAILABILITY (availability_id, person_id, from_date, to_date)
VALUES (2, 2, '2014-07-10', '2014-08-10');

INSERT INTO COMPETENCE (competence_id, name)
VALUES (1, 'Korvgrillning');
INSERT INTO COMPETENCE (competence_id, name)
VALUES (2, 'Karuselldrift');

INSERT INTO COMPETENCE_PROFILE (competence_profile_id, person_id,
                                  competence_id, years_of_experience)
VALUES (1, 2, 1, 3.5);
INSERT INTO COMPETENCE_PROFILE (competence_profile_id, person_id,
                                  competence_id, years_of_experience)
VALUES (2, 2, 2, 2.0);