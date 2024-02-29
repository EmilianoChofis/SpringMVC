package mx.edu.utez.sda.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user1 = User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
        UserDetails recepcion = User.withUsername("recepcion").password(passwordEncoder().encode("recepcion")).roles("RECEP").build();
        UserDetails nino = User.withUsername("nino").password(passwordEncoder().encode("nino")).roles("NINO").build();
        UserDetails adulto = User.withUsername("adulto").password(passwordEncoder().encode("adulto")).roles("ADULTO").build();

        return new InMemoryUserDetailsManager(user1, admin, recepcion, nino, adulto);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeHttpRequests((requests)->{
            //defines las rutas publicas
           requests.requestMatchers("/","index").permitAll();
           //defines que si no es una de las anteriores necesita de autenticacion
           requests.anyRequest().authenticated();
        });
        httpSecurity.formLogin((login)->{
            login.loginPage("/login").permitAll();
        });

        httpSecurity.logout((logout)->{
            logout.permitAll();
        });

        return httpSecurity.build();
    }
}
