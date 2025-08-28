//package config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.example.demo.filter.JwtAuthFilter;
//
//// Since I haven't worked with Spring Security much in the past especially with tokenization I found some example online and Tried to make it work 
//// in this case. as close as possible to the the given requirements, but I am having an issue with PasswordEncoder bean
//// it says I should add it for UserInfoService to work, but it is literally listed in this configuration class below and 
//// somehow it cannot be seen by Spring and used in other places. I tried to fix it, but with no success, so I commented out
//// all classes regarding security and I didnt get to test if it works at all.
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	
//	private final JwtAuthFilter jwtAuthFilter;
//    private final UserDetailsService userDetailsService;
//
//    // Constructor injection for required dependencies
//    public SecurityConfig(JwtAuthFilter jwtAuthFilter, 
//                     UserDetailsService userDetailsService) {
//    this.jwtAuthFilter = jwtAuthFilter;
//    this.userDetailsService = userDetailsService;
//}
//
//    
//    /* 
//     * Main security configuration
//     * Defines endpoint access rules and JWT filter setup
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // Disable CSRF (not needed for stateless JWT)
//            .csrf(csrf -> csrf.disable())
//
//            // Configure endpoint authorization
//            .authorizeHttpRequests(auth -> auth
//                // Public endpoints
//                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
//                
//                // Role-based endpoints
//                .requestMatchers("api/auth/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                
//                // All other endpoints require authentication
//                .anyRequest().authenticated()
//            )
//
//            // Stateless session (required for JWT)
//            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            
//            // Set custom authentication provider
//            .authenticationProvider(authenticationProvider())
//            
//            // Add JWT filter before Spring Security's default filter
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    /* 
//     * Password encoder bean (uses BCrypt hashing)
//     * Critical for secure password storage
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /* 
//     * Authentication provider configuration
//     * Links UserDetailsService and PasswordEncoder
//     */
//	@Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    /* 
//     * Authentication manager bean
//     * Required for programmatic authentication (e.g., in /generateToken)
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
//
