package libSec; 
import org.springframework.context.annotation.*;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher; @EnableWebSecurity

@ComponentScan("com.w3spoint")
public class SecurityConfig extends WebSecurityConfigurerAdapter { @Bean

	public UserDetailsService userDetailsService() {
	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	manager.createUser(User.withDefaultPasswordEncoder()
	.username("jai").password("123456").roles("ADMIN").build());
return manager;
}
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().
	antMatchers("/index","/").permitAll()
	.antMatchers("/admin").authenticated()
	.and()
	.formLogin()
	.loginPage("/login")
	.and()
	.logout()
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}