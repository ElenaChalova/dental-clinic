package org.top.dentalclinic.configutation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.top.dentalclinic.rdb.repository.UserRepository;
import org.top.dentalclinic.rdb.security.RdbUserDetailsService;

import javax.sql.DataSource;

// WebSecurityConfig - конфигурация Spring Security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public final UserRepository userRepository;

    private final DataSource dataSource;

    public WebSecurityConfig(UserRepository userRepository, DataSource dataSource) {
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Конфигурация реализуется через паттерн Строитель (Builder)
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/dev/user").permitAll()
                        .requestMatchers("/", "/department","/department/{id}","/feedbackForm/new","/user/register","webjars/**","css/style.css", "image/**").permitAll()
                        .requestMatchers(
                                "/department/new",
                                "/department/delete/*",
                                "/department/update",
                                "/category/update/*",
                                "/medicalServ/new",
                                "/medicalServ/new/*",
                                "/medicalServ/delete/*",
                                "/medicalServ/update",
                                "/medicalServ/update/*",
                                "/doctor/new",
                                "/doctor/new/*",
                                "/doctor/delete/*",
                                "/doctor/update",
                                "/doctor/update/*"
                                )
                        .hasAnyRole("ADMIN","SUPER_ADMIN","REGISTRAR")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout(customizer -> customizer.logoutSuccessUrl("/login"));
        http.csrf().disable(); // DEV ONLY
        return http.build();
    }

    // Зависимость кодировщика паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*-------------------------------------------------*/
    /* Конфигурация UserDetailsService для работы с БД */
    /*-------------------------------------------------*/

    @Bean
    public UserDetailsService userDetailsService() {
        return new RdbUserDetailsService(userRepository);
    }

    // зависимости, необходимые для работы Spring Security с нашими сервисами пользователя
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public UserDetailsManager users(HttpSecurity http) throws Exception {
        // TODO: remove deprecated calls
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }

}
