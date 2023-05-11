package med.voll.api.medico.dto;

import med.voll.api.entidades.Endereco;
import med.voll.api.entidades.Medico;
import med.voll.api.shared.enums.Especialidade;

public record DadosDetalhadosMedicoDTO(Long id, String nome, String email, 
		String crn, String telefone, Especialidade especialidade, Endereco endereco) {

	
	public DadosDetalhadosMedicoDTO(Medico medico){
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
				medico.getEspecialidade(),medico.getEndereco());
	}
	
}
