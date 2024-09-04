package med.voll.api.mensageria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.mensageria.config.SQSConfiguration;

@Service
public class MessageService {

	@Autowired
	private SQSConfiguration sqsClient;

	public void sendMessage(String dados) {

		sqsClient.sqsTemplate().send(dados);
	}

}
