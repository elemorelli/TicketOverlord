package es.ujaen.dae.ticketoverlord.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    private final static String REALM = "TICKET_OVERLORD";
    private final static String USERS_QUERY = "SELECT username, password, enabled FROM users WHERE username=?";
    private final static String ROLES_QUERY = "SELECT username, role FROM users WHERE username=?";

    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    public DigestAuthenticationFilter authFilter() throws Exception {
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setUserDetailsService(userDetailsServiceBean()); // ref="jdbcDaoImpl" ?
        filter.setAuthenticationEntryPoint(digestEntryPoint());
        return filter;
    }

    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
        entryPoint.setRealmName(REALM);
        entryPoint.setKey("uniqueAndSecret");
        return entryPoint;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(digestEntryPoint())
                .and()
                .addFilter(authFilter())
                //.httpBasic()
                .csrf().disable() // TODO: Es necesario deshabilitarlo? Sino tira error de token faltante
        ;
    }
}
