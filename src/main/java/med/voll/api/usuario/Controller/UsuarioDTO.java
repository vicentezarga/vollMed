package med.voll.api.usuario.Controller;

import med.voll.api.entidades.Usuario;

public record UsuarioDTO(String login, String senha) {

	public UsuarioDTO(Usuario user) {
		this(user.getLogin(),user.getSenha());
	}
	
}
