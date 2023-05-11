package med.voll.api.usuario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.entidades.Usuario;
import med.voll.api.shared.infra.security.DadosTokenJWT;
import med.voll.api.shared.infra.security.TokenService;
import med.voll.api.usuario.RepositoryUsuario;

@RestController
@RequestMapping("/login")
public class UsuarioController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private RepositoryUsuario repositorio;
	
	@Autowired
	private TokenService tokeService;

	@PostMapping("/registrar")
	public ResponseEntity registrarUser(@RequestBody @Valid UsuarioDTO dadosUser) {

		var usuario = new Usuario(dadosUser);
		repositorio.save(usuario);

		return ResponseEntity.ok().build();

	}

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioDTO dadosUser) {

		var autheticationToken = new UsernamePasswordAuthenticationToken(dadosUser.login(), dadosUser.senha());
		var authetication = manager.authenticate(autheticationToken);

		var tokeJWT = tokeService.gerarToken((Usuario)authetication.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokeJWT));
	}

}
