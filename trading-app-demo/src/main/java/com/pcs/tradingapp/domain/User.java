package com.pcs.tradingapp.domain;

import java.io.Serializable;

import com.pcs.tradingapp.config.CustomUserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Domain entity representing an application user.
 * <p>
 * Contains user-specific data such as username, password, and associated {@link Role}, 
 * which populate {@link CustomUserDetails} instances for authentication and authorization. 
 * Implements {@link Serializable} interface to prevent serialization issues on authentication. 
 * </p>
 */
@Entity
@Table(name = "app_user")
public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	/**
	 * The username used for authentication.
	 */
    private String username;
    
    /**
     * The user's hashed password.
     */
    private String password;
    
    private String fullname;
        
    /**
     * The user's associated role for authorization.
     */
	@ManyToOne
	@JoinColumn(name="role", referencedColumnName = "id")
	private Role role;
	
	public User(){};
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
