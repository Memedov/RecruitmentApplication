package recruitment.domain;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role{
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "Role_SEQUENCE")

    @Column(name = "ROLE_ID")
    private int roleId;

    @Column(name = "NAME")
    private String name;

    public Role(int roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    protected Role() {

    }
}

