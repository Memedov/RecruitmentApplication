package recruitment.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represent a person, or user, in the application.
 */
@Entity
@Table(name = "PERSON")
public class Person implements PersonDTO {
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "PERSON_SEQUENCE")
    @Column(name = "PERSON_ID", updatable = false)
    private int personId;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @Size(min = 11, max = 13, message = "Social security number needs to be exactly 13 characters.")
    @Column(name = "SSN")
    private String ssn;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "ROLE_ID")
    private int roleId;

    /**
     * Creates an instance of an person specified by given parameters.
     *
     * @param name First name of the person.
     * @param surname Last name of the person.
     * @param ssn The social security number.
     * @param email The email address.
     * @param username The username.
     * @param password The password.
     * @param roleId The role identifier.
     */
    public Person(String name, String surname, String ssn, String email,
                  String username, String password, int roleId) {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    protected Person() {
    }

    @Override
    public int getRole() {
        return roleId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

