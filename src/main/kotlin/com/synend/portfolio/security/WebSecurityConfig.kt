package com.synend.portfolio.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        private val dataSource: DataSource,
        private val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {


    @Bean
    override fun userDetailsServiceBean(): UserDetailsService {
        return super.userDetailsServiceBean()
    }

    @Bean
    override fun authenticationManagerBean() : AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = Arrays.asList("http://localhost:8080", "http://localhost:3000")
        configuration.allowedMethods = Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        //configuration.allowedHeaders = Arrays.asList("SESSION", "Content-Type","Set-Cookie", "Accept")
        configuration.addAllowedHeader("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    override fun configure(http: HttpSecurity) {

        http.cors().and().httpBasic()
                .and()
                .logout()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Projects
                .antMatchers(HttpMethod.GET, "/api/projects").permitAll()
                .antMatchers(HttpMethod.POST, "/api/projects").hasRole("ADMIN")

                // Experiences
                .antMatchers(HttpMethod.GET, "/api/experiences").permitAll()
                .antMatchers(HttpMethod.POST, "/api/experiences").hasRole("ADMIN")
                .antMatchers("/api/auth/user").authenticated()
                .antMatchers("/api/auth/logout").authenticated()
                .antMatchers("/api/auth/signup").permitAll()
                .antMatchers("/api/auth/login").permitAll()

                // Swagger
                .antMatchers("/swagger-resources/**").hasRole("ADMIN")
                .antMatchers("/swagger-ui.html").hasRole("ADMIN")
                .antMatchers("/v2/api-docs").hasRole("ADMIN")
                .antMatchers("/webjars/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
    }


    override fun configure(auth: AuthenticationManagerBuilder) {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("""
                     SELECT username, password, enabled
                     FROM users
                     WHERE username=?
                     """)
                .authoritiesByUsernameQuery("""
                     SELECT x.username, y.roles
                     FROM users x, user_entity_roles y
                     WHERE x.username=? and y.user_entity_username=x.username
                     """)
                .passwordEncoder(passwordEncoder)
    }
}