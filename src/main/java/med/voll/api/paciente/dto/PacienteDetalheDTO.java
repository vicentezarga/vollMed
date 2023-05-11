package med.voll.api.paciente.dto;

import med.voll.api.entidades.Endereco;
import med.voll.api.entidades.Paciente;

public record PacienteDetalheDTO(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

	
	public PacienteDetalheDTO(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
	}
	
}
