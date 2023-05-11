package med.voll.api.shared.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Informa ao Spring que se trata de uma classe de configuração
@EnableWebSecurity // Informa ao Spring que vamos definir as configurações de segurança
@EnableMethodSecurity(securedEnabled = true)//Habilitar Secured
public class SecurityConfiguration {

	@Autowired
	private SecurityFilter filtroVollMed;
	
	@Bean // Expor esse objeto para o spring para injetar se necessário
	public SecurityFilterChain definirConfiguracaoSeguranca(HttpSecurity http) throws Exception {

		return http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
				.and().addFilterBefore(filtroVollMed, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public AuthenticationManager autenticar(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();

	}

	@Bean
	public PasswordEncoder definirAlgoritmoEncriptacao() {

		return new BCryptPasswordEncoder();

	}

}
