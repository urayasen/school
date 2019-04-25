package oktenweb.school.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.security.AllPermission;

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
                .antMatchers("/home","/saveUser", "/SelectElements",  "/saveNewUser", "/chat", "/regaddelements/all", "/regaddelements/**").permitAll()
                .antMatchers(HttpMethod.POST,   "/saveElements").permitAll()
                .antMatchers("/admin/**").access("hasRole('TEACHER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .antMatchers("/saveStudents").access("hasRole('ADMIN')")
                .antMatchers("/news").hasRole("STUDENT")
                .antMatchers("/marks").access("hasAnyRole('DEPUTI','PARENT', 'ADMIN', 'TEACHER','CLASSTEACHER','ADMIN')")
                .antMatchers("/account").access("hasAnyRole('DEPUTI','STUDENT','PARENT','TEACHER','CLASSTEACHER','ADMIN')")
                .antMatchers("/news").access("hasAnyRole('DEPUTI','STUDENT','PARENT','TEACHER','CLASSTEACHER','ADMIN')")
//                .antMatchers("/chat").access("hasAnyRole('CLASSTHEACHER','PARENT','ADMIN')")
                .antMatchers("/homework").access("hasAnyRole('DEPUTI','STUDENT','PARENT','TEACHER','CLASSTEACHER','ADMIN')")
                .antMatchers("/lesson").access("hasAnyRole('DEPUTI','STUDENT','PARENT','TEACHER','CLASSTEACHER','ADMIN')")
                .antMatchers("/visited").access("hasAnyRole('DEPUTI','STUDENT','PARENT','TEACHER','CLASSTEACHER','ADMIN')")
//                .antMatchers("/saveNewUser").hasAuthority("ADMIN")
//                .antMatchers("/saveNewUser").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/successURL")//handle with post mapping in controller
                .failureUrl("/login?error").permitAll()
                .permitAll()
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
    }


}
