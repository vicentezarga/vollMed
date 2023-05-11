package med.voll.api.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import med.voll.api.medico.dto.MedicoAtualizarDTO;
import med.voll.api.medico.dto.MedicoDTO;
import med.voll.api.shared.enums.Especialidade;

@Table(name = "tb_medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "crm")
	private String crm;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

	@Column(name = "st_ativo")
	private boolean ativo;

	public Medico(MedicoDTO dto) {
		this.setAtivo(true);
		this.nome = dto.nome();
		this.email = dto.email();
		this.telefone = dto.telefone();
		this.crm = dto.crm();
		this.especialidade = dto.especialidade();
		this.endereco = new Endereco(dto.endereco());
	}

	public void atualizarMedico(@Valid MedicoAtualizarDTO dto) {

		if (dto.nome() != null) {
			this.nome = dto.nome();
		}
		if (dto.telefone() != null) {
			this.telefone = dto.telefone();
		}
		if (dto.endereco() != null) {
			this.endereco.atualizarEndereco(dto.endereco());
		}

	}
	public void excluir() {
		this.ativo = false;
	}
}
