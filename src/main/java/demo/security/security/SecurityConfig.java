package demo.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;
	
	/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	
*/	
	@Autowired
	public UserDetailsService userDetailsService;
//	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public PasswordEncoder passwordEncoder;




	/**
	 * nos permite configurar la autenticasion 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*
		auth.inMemoryAuthentication()
        .withUser("leonardo")
        .password(passwordEncoder.encode("123"))
       // .password("{noop}123")
        .roles("ADMIN");		
		*/
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
		
		/*
        auth.inMemoryAuthentication()
        .withUser("leonardo")
        .password(passwordEncoder.encode("123"))
        .authorities("ADMIN");
        */
        
	}

	/**
	 *  configura el control de acceso 
	 *  configuramos la autorizacion
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
			.authenticationEntryPoint(customBasicAuthenticationEntryPoint)
			.and()
			.authorizeRequests()
//				.antMatchers(HttpMethod.GET, "/producto/**", "/lote/**").hasRole("ADMIN")
//			.antMatchers("/", "/home", "/guest").permitAll()
				.antMatchers(HttpMethod.GET, "/api/", "/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			.and()
				.csrf().disable();	
		
		
		/*
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/vup/v1/api/purposing")
                .permitAll().anyRequest().authenticated().and().csrf().disable()
                .headers().xssProtection();		
		
		*/
		
	}
	


//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().anyRequest();
//	}
	
	
	
	

}
