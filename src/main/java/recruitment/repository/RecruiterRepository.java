package recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import recruitment.domain.Role;

import java.sql.*;
import java.math.*;

/**
 * Contains all database operations concerning users on the application.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RecruiterRepository extends JpaRepository<Role, String> {

    /**
     * Finds a role given a certain identifier.
     * @param id The identifier.
     * @return The name of the role.
     */
    @Query(value = "SELECT NAME FROM ROLE WHERE ROLE_ID = ?", nativeQuery = true)
    String getRoleById(int id);

    /**
     * Checks if a username exists in database.
     *
     * @param username The given username to check for.
     * @return true if username already exists, else false.
     */
    @Query(value = "SELECT EXISTS(SELECT * FROM PERSON WHERE USERNAME = ?)", nativeQuery = true)
    boolean checkUsername(String username);

    /**
     * Checks if an email exists in database.
     *
     * @param email The email to check for.
     * @return true if email already exists, otherwise false.
     */
    @Query(value = "SELECT EXISTS(SELECT * FROM PERSON WHERE EMAIL = ?)", nativeQuery = true)
    boolean checkEmail(String email);

    /**
     * Checks if social security number exists in database.
     *
     * @param ssn The ssn to check for.
     * @return true if ssn already exists, otherwise false.
     */
    @Query(value = "SELECT EXISTS(SELECT * FROM PERSON WHERE SSN = ?)", nativeQuery = true)
    boolean checkSsn(String ssn);

    /**
     * Registers a user, by creating an instance in the database.
     *
     * @param fname User's first name.
     * @param lname User's last name.
     * @param email User's email-address.
     * @param ssn User's social security number.
     * @param username User's username.
     * @param password User's password.
     */
    @Modifying
    @Query(value = "INSERT INTO PERSON (NAME, SURNAME, SSN, EMAIL, PASSWORD, ROLE_ID, USERNAME) VALUES (?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
    void registerUser (String fname, String lname, String ssn, String email, String password, int roleId, String username);

    /**
     * Authorizes a user at login.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return The role id of the specific user.
     */
    @Query(value = "SELECT ROLE_ID FROM PERSON WHERE USERNAME = ? AND PASSWORD = ?", nativeQuery = true)
    int authorize(String username, String password);
}
