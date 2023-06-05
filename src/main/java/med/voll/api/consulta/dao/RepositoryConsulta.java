package med.voll.api.consulta.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.entidades.Consulta;

public interface RepositoryConsulta extends JpaRepository<Consulta, Long> {

	@Query(value = "SELECT IFNULL(MAX(id),0) FROM Consulta")
	public Long getProximoCodigo();

	@Query(value = """
				Select case when count(c)> 0 then true else false end from 
					Consulta c 
					where 
					c.codigo= :codigoConsulta
					and
					c.ativo= true
			""")
	public boolean exists(String codigoConsulta);

	@Query(value = """
			Select c from 
				Consulta c 
				where 
				c.codigo= :codigoConsulta
				and
				c.ativo = true
		""")
	public Consulta findbyCodigo(String codigoConsulta);

	public boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

	public boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario,LocalDateTime ultimoHorario);

	@Query(value = """
			Select c.data 
			From Consulta c
			Where
			c.codigo = :codigoConsulta and c.ativo = true
			""")
	public LocalDateTime findDataConsultaByCodigo(String codigoConsulta);
	
}
