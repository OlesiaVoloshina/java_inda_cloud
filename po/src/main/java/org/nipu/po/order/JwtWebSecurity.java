package org.nipu.po.order;


import org.nipu.po.order.auth.JwtAuthenticationEntryPoint;
import org.nipu.po.order.auth.TokenAuthenticationFilter;
import org.nipu.po.order.auth.TokenAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class JwtWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    TokenAuthenticationManager tokenAuthenticationManager;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean(name = "restTokenAuthenticationFilter")
    public TokenAuthenticationFilter restTokenAuthenticationFilter() {
        TokenAuthenticationFilter restTokenAuthenticationFilter = new TokenAuthenticationFilter();
        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return restTokenAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // registration for new user is available without login
        web.ignoring().antMatchers("/register**");
    }
}
