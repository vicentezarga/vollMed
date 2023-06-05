package med.voll.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.entidades.Paciente;

public interface RepositoryPaciente extends JpaRepository<Paciente, Long>{

	Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

	
	@Query("""
			Select p.ativo From Paciente p
			where p.id = :idPaciente
			""")
	boolean findAllByAtivoTrue(Long idPaciente);

}
