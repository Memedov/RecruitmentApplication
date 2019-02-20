package recruitment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import recruitment.application.RecruiterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

/**
 * <p>Loads all security configurations for the recruitment application.</p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RecruiterService userDetailsService;

    /**
     * Spring utilizes this method to set the userDetailsService and password encoder to the
     * authentication manager builder to the database.
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Configuration of rules for different HTTP requests.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/register*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.usernameParameter("username").passwordParameter("password")
                //.loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/apply", false)
                /*.failureUrl("/login")
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())*/;
    }

    /**
     * Bean to encode passwords through BCrypt in the application.
     * @return The password encoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}