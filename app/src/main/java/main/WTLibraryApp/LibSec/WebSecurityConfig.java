package main.WTLibraryApp.LibSec;

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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        	.authorizeRequests()
            .antMatchers("/home","/reservations","/reservations/*").permitAll()
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
    		.usersByUsernameQuery("select email,passphrase, active "
        		+ "from users "
        		+ "where email = ?")
    		.authoritiesByUsernameQuery("select email,user_id "
		        + "from admins "
		        + "where email = ?");
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() { 
      return new BCryptPasswordEncoder(); 
   }
}
