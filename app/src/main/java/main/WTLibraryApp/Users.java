package main.WTLibraryApp;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
*/
@Entity
public class Users implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;

	private String first_name;

	private String last_name;

	private String email;
	
	private String password; 
	
	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override 
   public Collection<? extends GrantedAuthority> getAuthorities() { 
      return List.of(() -> "read"); 
   }
   @Override
   public String getPassword() {    
      return password; 
   } 
   public void setPassword(String password) { 
      this.password = password; 
   } 
   @Override 
   public String getUsername() { 
      return email; 
   } 
   public void setUsername(String email) { 
      this.email = email; 
   } 
   @Override 
   public boolean isAccountNonExpired() { 
      return true; 
   } 
   @Override
   public boolean isAccountNonLocked() { 
      return accountNonLocked; 
   } 
   @Override public boolean isCredentialsNonExpired() { 
      return true; 
   } 
   @Override public boolean isEnabled() { 
   return true; 
   } 
   
   public void setAccountNonLocked(Boolean accountNonLocked) { 
      this.accountNonLocked = accountNonLocked; 
   } 
   public boolean getAccountNonLocked() { 
      return accountNonLocked; 
   }

}
