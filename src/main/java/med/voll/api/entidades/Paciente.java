package med.voll.api.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.paciente.dto.PacienteAtualizarDTO;
import med.voll.api.paciente.dto.PacienteDTO;

@Table(name = "tb_paciente")
@Entity(name = "Paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	private String cpf;

	@Embedded
	private Endereco endereco;
	
	@Column(name = "st_ativo")
	private boolean ativo;

	public Paciente(PacienteDTO dto) {
		this.setAtivo(true);
		this.nome = dto.nome();
		this.email = dto.email();
		this.telefone = dto.telefone();
		this.cpf = dto.cpf();
		this.endereco = new Endereco(dto.endereco());
	}

	public void excluir() {
		this.setAtivo(false);
	}

	public void atualizar(@Valid PacienteAtualizarDTO dto) {
		
		if(dto.nome() !=null) {
			this.nome = dto.nome();
		}
		if(dto.telefone() !=null) {
			this.telefone = dto.telefone();
		}
		if(dto.endereco()!=null) {
			this.endereco.atualizarEndereco(dto.endereco());
		}
	}
}
