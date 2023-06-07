package med.voll.api.medico;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.consulta.dao.RepositoryConsulta;
import med.voll.api.entidades.Consulta;
import med.voll.api.entidades.Medico;
import med.voll.api.entidades.Paciente;
import med.voll.api.medico.dto.MedicoDTO;
import med.voll.api.paciente.dto.PacienteDTO;
import med.voll.api.shared.dto.EnderecoDTO;
import med.voll.api.shared.enums.Especialidade;

/**
 * 
 * @author vicente
 *
 *         Para utilizar o banco de dados em memória devemos incluir a
 *         dependência do banco de dados H2 e remover as
 *         anotações @AutoConfigureTestDatabase e @ActiveProfiles deixando
 *         apenas @DataJpaTest. E também apagar o arquivo
 *         application-test.properties na src/test/resources
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {

	@Autowired
	private RepositoryMedico medicoRepository;

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private RepositoryConsulta consultaRepositoy;

	private Medico medico;
	private Paciente paciente;
	private LocalDateTime proximaSegundaAsDez;
	
	@BeforeEach
	void setUp() {
		
		proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		EnderecoDTO enderecoMedico = new EnderecoDTO("Rua das Amigas", "Primas", "66666666", "Bauru", "SP", 666,"Na esquina de amaro!");
		EnderecoDTO enderecoPaciente = new EnderecoDTO("Rua das Flores", "Jardins", "66666689", "João Pessoa", "JB", 668,"Mercado das Flores!");
		
		medico = cadastrarMedico(new MedicoDTO("Ernesto de Paula", "ernesto.gfs@gmail.com", "81985135094", "123456",Especialidade.CARDIOLOGIA, enderecoMedico));
		paciente = cadastrarPaciente(new PacienteDTO("João Carlos", "jc@gmail.com", "81968512136", "01259482566", enderecoPaciente));
		cadastrarConsulta(paciente, medico, proximaSegundaAsDez);
	}

	@Test
	@DisplayName("Deve devolver null quando médico não está disponível nada data.")
	void escolherMidicoAleatorioLivreNaData_cenario1() {

		var medicoLivre = medicoRepository.escolherMidicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,proximaSegundaAsDez);

		assertThat(medicoLivre).isNull();

	}

	@Test
	@DisplayName("Deve devolver médico disponível nada data.")
	void escolherMidicoAleatorioLivreNaData_cenario2() {

		var proximaSegundaAsOnze = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11,0);
		var medicoLivre = medicoRepository.escolherMidicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,proximaSegundaAsOnze);

		assertThat(medicoLivre).isEqualTo(medico);
	}

	@Test
	@DisplayName("Deve retornar médico Ativo!")
	void findAtivoById() {
		
		var medicoAtivo = medicoRepository.findAtivoById(medico.getId());
		
		assertThat(medicoAtivo).isNotNull();
	}
	
	private Medico cadastrarMedico(MedicoDTO dto) {
		var medico = new Medico(dto);
		medico = em.persist(medico);

		return medico;
	}

	private Paciente cadastrarPaciente(PacienteDTO dto) {
		
		var paciente = new Paciente(dto);
		paciente = em.persist(paciente);
		
		return paciente;
		
	}
	public Consulta cadastrarConsulta(Paciente paciente, Medico medico,LocalDateTime dta) {
		
		Long codigo = consultaRepositoy.getProximoCodigo();
		var consulta = new Consulta(codigo+"CL",medico, paciente,dta,true);
		consulta = em.persist(consulta);
		
		return consulta;
	}
}
