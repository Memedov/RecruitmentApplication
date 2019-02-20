package recruitment.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Creates a profile based a persons competences.
 */
@Entity
@Table(name = "COMPETENCE_PROFILE")
public class CompetenceProfile{
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "COMPETENCE_PROFILE_SEQUENCE")

    @Column(name = "COMPETENCE_PROFILE_ID")
    private int competenceProId;

    @Column(name = "PERSON_ID")
    private int pid;

    @Column(name = "COMPETENCE_ID")
    private int competenceId;

    @Column(name = "YEARS_OF_EXPERIENCE")
    private BigDecimal experience;

    /**
     * Creates an instance of a competence profile based on given parameters.
     *
     * @param competenceProId The identifier.
     * @param pid The person identifier.
     * @param competenceId The competence identifier.
     * @param experience Years of experience.
     */
    public CompetenceProfile(int competenceProId, int pid, int competenceId, BigDecimal experience) {
        this.competenceProId = competenceProId;
        this.pid = pid;
        this.competenceId = competenceId;
        this.experience = experience;
    }

    protected CompetenceProfile() {

    }
}


