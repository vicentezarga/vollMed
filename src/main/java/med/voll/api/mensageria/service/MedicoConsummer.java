package med.voll.api.mensageria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.awspring.cloud.sqs.annotation.SqsListener;
import med.voll.api.entidades.Medico;
import med.voll.api.medico.RepositoryMedico;
import med.voll.api.medico.dto.MedicoDTO;

@Component
public class MedicoConsummer {

	@Autowired
	private RepositoryMedico repositoryMedico;

	@SqsListener("${cloud.aws.sqs}")
	public void listenFila(MedicoDTO medicoDTO) {
		
		Medico medico = new Medico(medicoDTO); 
		repositoryMedico.save(medico);
	}
}
