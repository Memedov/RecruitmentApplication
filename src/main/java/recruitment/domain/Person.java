package recruitment.domain;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
public class Person implements PersonDTO {
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "PERSON_SEQUENCE")

    @Column(name = "PERSON_ID")
    private int personId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE_ID")
    private int roleId;


    public Person(int personId, String name, String surname, String ssn, String email,
                  String username, String password, int roleId) {
        this.personId = personId;
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

