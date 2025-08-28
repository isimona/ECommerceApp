//package com.example.demo.service;
//
//import java.util.Collection;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import com.example.demo.dto.UserDTO;
//import com.example.demo.model.Role;
//
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Service
//public class UserInfoService implements UserDetailsService{
//	
//	private  UserRepository userRepository;
//	private  PasswordEncoder encoder;
//	
//	 @Autowired
//	    public UserInfoService(UserRepository repository, PasswordEncoder encoder) {
//	        this.userRepository = repository;
//	        this.encoder = encoder;
//	    }
//    
//    @Autowired 
//    RoleRepository roleRepository;
//    
//    
//
////    @Autowired
////    public UserInfoService(UserRepository userRepository, PasswordEncoder encoder) {
////        this.userRepository = userRepository;
////        this.encoder = encoder;
////    }
//
//    // Method to load user details by username (email)
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Fetch user from the database by email (username)
//        Optional<com.example.demo.model.User> userInfo = userRepository.findByEmail(email);
//        
//        if (userInfo.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//        
//        // Convert UserInfo to UserDetails (UserInfoDetails)
//        com.example.demo.model.User user = userInfo.get();
//       return (UserDetails) new User(user.getEmail(), user.getPassword(), (Collection<? extends GrantedAuthority>) user.getRoles());
//    }
//
//    // Add any additional methods for registering or managing users
//    public String addUser(UserDTO userInfo) {
//        // Encrypt password before saving
//        userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
//        com.example.demo.model.User user = new com.example.demo.model.User();
//        user.setFirstname(userInfo.getFirstName());
//        user.setAddress(userInfo.getAddress());
//        user.setSurname(userInfo.getLastName());
//        user.setEmail(userInfo.getEmail());
//        user.setPassword(userInfo.getPassword());
//        String name = userInfo.getRole();
//        Optional<Role> role = roleRepository.findByName(name);
//        if(role.isPresent()) {
//        	role.get().getUsers().add(user);
//        	 user.getRoles().add(role.get());
//             roleRepository.save(role.get());
//        }else {
//        	Role userRole = new Role(2,"USER");
//        	userRole.getUsers().add(user);
//        	user.getRoles().add(userRole);
//        	roleRepository.save(userRole);
//        }
//        
//        userRepository.save(user);
//        return "User added successfully!";
//    }
//	
//
//}
