package recruitment.domain;

/**
 * <p>Defines all operations that can be performed on a person
 * outside the application and domain layers. </p>
 */
public interface PersonDTO {
    /**
     * Returns an integer specifying a role.
     */
    int getRole();

    /**
     * Returns the first name of a person.
     */
    String getName();

    /**
     * Returns the password of a user.
     */
    String getPassword();
}
