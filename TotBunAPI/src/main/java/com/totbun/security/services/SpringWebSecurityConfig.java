package com.totbun.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.totbun.security.jwt.AuthJwtEntryPoint;
import com.totbun.security.jwt.JwtAuthFilter;
import com.totbun.security.jwt.RestAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringWebSecurityConfig {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthJwtEntryPoint authJwtEntryPoint;
	
	@Autowired
	private RestAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsServiceImpl);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception
	{
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authJwtEntryPoint).accessDeniedHandler(accessDeniedHandler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/totbun/admin/add-new-product", "/totbun/admin/see-individual-product-details/{productId}", 
				"/totbun/admin/see-all-products-details", "/totbun/admin/update-product-quantity/{productId}/{newProductQty}", 
				"/totbun/admin/view-all-customers", "/totbun/admin/view-customer-by-id/{customerId}", 
				"/totbun/admin/admin-logout", "/totbun/admin/delete-customer/{customerId}",
				"/totbun/order/see-all-orders-details", "/totbun/order/{orderId}/{newOrderStatus}", 
				"/totbun/order/search-order{customerId}", "/totbun/admin/sales-made-last-month", 
				"/totbun/admin/sales-made-today", "/totbun/admin/sales-made-last-week", 
				"/totbun/admin/sales-between-months/{fromMonth}/{toMonth}", "/totbun"
				).hasAuthority("ADMIN")
		
		.antMatchers("/totbun/cart/add/{productId}/{quantity}", "/totbun/cart/see-cart-details", 
				"/totbun/cart/delete/{productId}", "/totbun/customers/update", 
				"/totbun/customers/view-pesonal-details", "/totbun/customers/customer-logout", 
				"/totbun/order/place-order/{paymentType}", "/totbun/order/history/order-history"
				).hasAuthority("CUSTOMER")
		
		.antMatchers("/totbun/user/login-user", "/totbun/user/logout-user", "/totbun/admin/register",
				"/totbun/customers/register", "/totbun/products/search-by-name/{productName}", 
				"/totbun/products/search-by-category/{productCategory}", "/totbun/products/sort-by-price-low-to-high/{productCategory}", 
				"/totbun/products/sort-by-price-high-to-low/{productCategory}", "/totbun/products/sort-by-rating-low-to-high/{productCategory}", 
				"/totbun/products/sort-by-rating-high-to-low/{productCategory}", "/totbun/products/see-individual-product-Details/{productId}"
				).permitAll()

        .antMatchers(AUTH_WHITELIST).permitAll()
		
		.anyRequest().authenticated();
		
		http.authenticationProvider(daoAuthenticationProvider());
		
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

}
