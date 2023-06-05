package med.voll.api.shared.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.entidades.Usuario;

@Service
public class TokenService {
	
	@Value("{api.security.token.chave}")
	private String chave;
	
	public String gerarToken(Usuario usuario) {

		try {
		    var algorithm = Algorithm.HMAC256(chave);
		    return  JWT.create()
		    		.withIssuer("API Voll Med")
		    		.withSubject(usuario.getLogin()) //Identifica Usuário
		    		.withClaim("id", usuario.getId())// Informações complementares transação
		    		.withExpiresAt(dataExpiracao())
		    		.sign(algorithm);
		} catch (JWTCreationException ex){
		    throw new RuntimeException("Erro ao gerar Token", ex);
		}
		
	}
	
	public String getSubject(String tokenJWT) {
		
		try {
			var algorithm = Algorithm.HMAC256(chave);
		    return JWT.require(algorithm)
		        // specify an specific claim validations
		        .withIssuer("API Voll Med")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		        
		} catch (JWTVerificationException exception){
			throw new RuntimeException("Token JWT inválido ou expirado");
		}
		
	}

	private Instant dataExpiracao() {
		// TODO Auto-generated method stub
		return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
	}

}
