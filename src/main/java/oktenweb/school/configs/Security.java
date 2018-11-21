package oktenweb.school.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {


    @Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userDetailsService);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home","/saveUser", "/saveNewUser").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .antMatchers("/saveNewUser").hasAuthority("ADMIN")
//                .antMatchers("/saveNewUser").hasRole("ADMIN")
//                .antMatchers("/news").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/admin")
                .successForwardUrl("/successURL")//handle with post mapping in controller
                .failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
                logoutSuccessUrl("/login")
                .permitAll();
    }

     private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryConfigurer() {
             return new InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>();
         }

         @Autowired
         public void configureGlobal(AuthenticationManagerBuilder auth,
                                     AuthenticationProvider provider) throws Exception {
             inMemoryConfigurer()
                     .withUser("admin")
                     .password("{noop}admin")
                     .authorities("ADMIN")
                     .and()
                     .configure(auth);
             auth.authenticationProvider(provider);

         }


}
