package recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import recruitment.domain.Role;

import java.sql.*;
import java.math.*;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RecruiterRepository extends JpaRepository<Role, String> {

    @Query(value = "SELECT NAME FROM ROLE WHERE ROLE_ID = 1", nativeQuery = true)
      String findRoleById(int id);

    @Query(value = "SELECT NAME FROM PERSON WHERE PERSON_ID = ?", nativeQuery = true)
    String findNameById(int id);


    @Query(value = "SELECT NAME FROM COMPETENCE WHERE COMPETENCE_ID = 2", nativeQuery = true)
    String findCompetenceById(int id);

    @Query(value = "SELECT FROM_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID = 2", nativeQuery = true)
    Date findAvailabilityByPid(int pid);

    @Query(value = "SELECT YEARS_OF_EXPERIENCE FROM COMPETENCE_PROFILE WHERE COMPETENCE_PROFILE_ID = 1", nativeQuery = true)
    BigDecimal getExperienceByPid(int pid);

    @Query(value = "SELECT EXISTS(SELECT * FROM PERSON WHERE USERNAME = ?)", nativeQuery = true)
    boolean checkUsername(String username);

    @Query(value = "SELECT EXISTS(SELECT * FROM PERSON WHERE EMAIL = ?)", nativeQuery = true)
    boolean checkEmail(String email);

    @Query(value = "SELECT EXISTS(SELECT * FROM PERSON WHERE SSN = ?)", nativeQuery = true)
    boolean checkSsn(String ssn);

    @Modifying
    @Query(value = "INSERT INTO PERSON (NAME, SURNAME, SSN, EMAIL, PASSWORD, ROLE_ID, USERNAME) VALUES (?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
    void registerUser (String fname, String lname, String ssn, String email, String password, int roleId, String username);

    @Query(value = "SELECT ROLE_ID FROM PERSON WHERE USERNAME = ? AND PASSWORD = ?", nativeQuery = true)
    int authorize(String username, String password);
}
