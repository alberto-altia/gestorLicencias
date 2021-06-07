package proyectoFCT.gestorLicencias.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class WebConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    	http.cors().disable()
//        .csrf().disable()
//        .authorizeRequests().antMatchers("/**").permitAll();
    	http.cors()
	    	.and()
	        .csrf()
        	.disable()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests(configurer ->
	      		configurer
	            .antMatchers(
	                      "/login",
	                      "/nuevoDeportista",
	                      "/swagger-resources/**",
	                      "/v2/api-docs/**",
	                      "/swagger-ui.html/**",
	                      "/webjars/**"
	            			)
	            .permitAll()
				.anyRequest()
				.authenticated()
			)
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
    
}
