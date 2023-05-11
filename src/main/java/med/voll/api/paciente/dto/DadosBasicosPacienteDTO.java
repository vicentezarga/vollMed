package med.voll.api.paciente.dto;

import med.voll.api.entidades.Endereco;
import med.voll.api.entidades.Paciente;

public record DadosBasicosPacienteDTO(Long id,String nome, String email, String cpf, Endereco endereco) {

	public DadosBasicosPacienteDTO(Paciente paciente) {
		this(paciente.getId(),paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
	}
	
}
