package med.voll.api.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.entidades.Medico;
import med.voll.api.shared.enums.Especialidade;

public interface RepositoryMedico extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);
	
	@Query("""
			Select m from Medico m 
			where 
				m.ativo = true 
				and 
				m.especialidade = :especialidade
				and m.id not in (
					Select c.medico.id from Consulta c 
					where 
					c.data = :data
				)
				order by rand()
				limit 1
			""")
	Medico escolherMidicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

	@Query("""
			Select m.ativo from Medico m
				Where m.id = :idMedico
			""")
	Boolean findAtivoById(Long idMedico);

}
