package recruitment.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import recruitment.domain.Person;
import org.springframework.security.core.userdetails.User;
import recruitment.repository.RecruiterRepository;
import recruitment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruiterService implements UserDetailsService {
    @Autowired
    private RecruiterRepository recruiterRepo;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String findRole(int id) {
        return recruiterRepo.findRoleById(id);
    }

    public String findName(int id){ return recruiterRepo.findNameById(id); }

    public String findCompetence(int id){
        return recruiterRepo.findCompetenceById(id);
    }

    public String findAvailabilityByPid(int pid){
        return recruiterRepo.findAvailabilityByPid(pid).toString();
    }

    public BigDecimal getExperienceByPid(int pid){
        return recruiterRepo.getExperienceByPid(pid);
    }

    public boolean checkUsername(String username) { return recruiterRepo.checkUsername(username); }

    public boolean checkEmail(String email) { return recruiterRepo.checkEmail(email); }

    public boolean checkSsn(String ssn) {
        return recruiterRepo.checkSsn(ssn);
    }

    public void registerUser(String fname, String lname, String email, String ssn, String username, String password) {
        recruiterRepo.registerUser(fname, lname, ssn, email, passwordEncoder.encode(password), 2, username);
    }

    public int authorize(String username, String password) {
        return recruiterRepo.authorize(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findByUsername(username);
        String roleName = recruiterRepo.findRoleById(person.getRole());

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority(roleName));

        return new User(person.getName(),person.getPassword(),grantedAuthoritySet);
    }
}
