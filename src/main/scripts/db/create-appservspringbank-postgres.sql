DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS PERSON;
DROP TABLE IF EXISTS AVAILABILITY;
DROP TABLE IF EXISTS COMPETENCE;
DROP TABLE IF EXISTS COMPETENCE_PROFILE;

--
-- Create for table `ROLE
--
CREATE TABLE ROLE (
                    `ROLE_ID` BIGINT PRIMARY KEY,
                    `NAME` VARCHAR(255) NOT NULL
);


--
-- Create for table `PERSON`
--
CREATE TABLE PERSON (
                      person_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255) NOT NULL,
                      ssn VARCHAR(255),
                      email VARCHAR(255),
                      password VARCHAR(255) NOT NULL,
                      role_id BIGINT REFERENCES role NOT NULL,
                      username VARCHAR(255) NOT NULL UNIQUE
);

--
-- Create for table `AVAILABILITY`
--
CREATE TABLE AVAILABILITY (
                            availability_id BIGINT PRIMARY KEY,
                            person_id BIGINT REFERENCES person NOT NULL,
                            from_date DATE NOT NULL,
                            to_date DATE NOT NULL,
                            CONSTRAINT CheckToLaterThanFrom CHECK (to_date >= from_date)
);

-- --
-- -- Create for table `COMPETENCE`
-- --
CREATE TABLE COMPETENCE (
                          competence_id BIGINT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL UNIQUE
);
--
-- --
-- -- Create for table `COMPETENCE_PROFILE`
-- --
CREATE TABLE COMPETENCE_PROFILE (
                                  competence_profile_id BIGINT PRIMARY KEY,
                                  person_id BIGINT REFERENCES person NOT NULL,
                                  competence_id BIGINT REFERENCES competence NOT NULL,
                                  years_of_experience NUMERIC(4,2) NOT NULL
);

