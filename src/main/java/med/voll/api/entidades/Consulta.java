package med.voll.api.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.shared.enums.MotivoCancelamento;

@Entity(name = "Consulta")
@Table(name = "tb_consulta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cd_consulta")
	private String codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@Column(name = "dt_consulta")
	private LocalDateTime data;

	@Column(name = "st_ativo")
	private Boolean ativo;

	@Enumerated(EnumType.STRING)
	@Column(name = "ds_cancelamento")
	private MotivoCancelamento motivoCancelamento;

	public Consulta(String codigo, Medico medico, Paciente paciente, LocalDateTime data, Boolean ativo) {

		this.codigo = codigo;
		this.medico = medico;
		this.paciente = paciente;
		this.data = data;
		this.ativo = ativo;

	}
	
	public void cancelarConsulta(MotivoCancelamento motivo) {
		this.ativo = false;
		this.motivoCancelamento = motivo;
	}

	public Consulta(String codigo,Medico medico, Paciente paciente, LocalDateTime data, boolean ativo) {
		super();
		this.codigo = codigo;
		this.medico = medico;
		this.paciente = paciente;
		this.data = data;
		this.ativo = ativo;
	}
}
