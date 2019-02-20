package recruitment.application;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import recruitment.domain.Person;
import org.springframework.security.core.userdetails.User;
import recruitment.domain.PersonDTO;
import recruitment.repository.RecruiterRepository;
import recruitment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>This is the recruitment application class, which defines tasks that can be
 * performed by the domain layer.</p>
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruiterService implements UserDetailsService {
    @Autowired
    private RecruiterRepository recruiterRepo;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Checks if a username exists in database.
     *
     * @param username The given username to check for.
     * @return true if username already exists, else false.
     */
    public boolean checkUsername(String username) { return recruiterRepo.checkUsername(username); }

    /**
     * Checks if an email exists in database.
     *
     * @param email The email to check for.
     * @return true if email already exists, otherwise false.
     */
    public boolean checkEmail(String email) { return recruiterRepo.checkEmail(email); }

    /**
     * Checks if social security number exists in database.
     *
     * @param ssn The ssn to check for.
     * @return true if ssn already exists, otherwise false.
     */
    public boolean checkSsn(String ssn) {
        return recruiterRepo.checkSsn(ssn);
    }

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
    public void registerUser(String fname, String lname, String email, String ssn, String username, String password) {
        recruiterRepo.registerUser(fname, lname, ssn, email, passwordEncoder.encode(password), 2, username);
    }

    /**
     * Authorizes a user at login.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return The role id of the specific user.
     */
    public int authorize(String username, String password) {
        return recruiterRepo.authorize(username, password);
    }

    /**
     * Finds a user entity based on a given username.
     *
     * @param username The specified username.
     * @return The user entity, with related data.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findByUsername(username);
        String roleName = recruiterRepo.findRoleById(person.getRole());

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority(roleName));

        return new User(person.getName(),person.getPassword(),grantedAuthoritySet);
    }

    /**
     * Finds username of authenticated user.
     *
     * @return The instance of person.
     */
    public PersonDTO getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return personRepo.findByUsername(authentication.getName());
        }
        return null;
    }
}
