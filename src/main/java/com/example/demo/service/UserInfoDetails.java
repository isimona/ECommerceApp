//package com.example.demo.service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.example.demo.model.Role;
//import com.example.demo.model.User;
//
//public class UserInfoDetails implements UserDetails{
//	
//	 private static final long serialVersionUID = 1L;
//	private String username; // Changed from 'name' to 'email' for clarity
//	    private String password;
//	    private List<GrantedAuthority> authorities;
//
//	    public UserInfoDetails(User userInfo) {
//	        this.username = userInfo.getEmail(); // Use email as username
//	        this.password = userInfo.getPassword();
//	        List<GrantedAuthority> roles = new ArrayList<>();
//	        for(Role r: userInfo.getRoles()) {
//	        	SimpleGrantedAuthority a = new SimpleGrantedAuthority(r.getName());
//	        	roles.add(a);
//	        }
//	        this.authorities = roles;
//	    }
//
//	    @Override
//	    public Collection<? extends GrantedAuthority> getAuthorities() {
//	        return authorities;
//	    }
//
//	    @Override
//	    public String getUsername() {
//	        return username;
//	    }
//
//	    @Override
//	    public boolean isAccountNonExpired() {
//	        return true;
//	    }
//
//	    @Override
//	    public boolean isAccountNonLocked() {
//	        return true;
//	    }
//
//	    @Override
//	    public boolean isCredentialsNonExpired() {
//	        return true;
//	    }
//
//	    @Override
//	    public boolean isEnabled() {
//	        return true;
//	    }
//
//		@Override
//		public String getPassword() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//}
