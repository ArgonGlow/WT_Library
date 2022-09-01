package main.WTLibraryApp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        	.authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
        .logout() .invalidateHttpSession(true) 
            .clearAuthentication(true) .permitAll();
   }
	
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   auth
	   	.jdbcAuthentication()
	   	.dataSource(dataSource)
        .usersByUsernameQuery("select email,password, account_non_locked "
        		+ "from users "
        		+ "where email = ?")
	   	.authoritiesByUsernameQuery("select admin_id,user_id "
		        + "from admins "
		        + "where admin_id = ?");
      	
         //.withUser("user").password("$2a$10$at2qzGSaEgBQuKXjIWiIVeGevA4TPbeRUJUAuEUtYbJrEoRr6miMO").roles("USER");
      /*
	  auth.jdbcAuthentication()
	  	.dataSource(DataSource)
	  	.users*/
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() { 
      return new BCryptPasswordEncoder(); 
   }
}