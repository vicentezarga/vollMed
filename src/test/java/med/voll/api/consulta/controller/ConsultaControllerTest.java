package med.voll.api.consulta.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.dto.ConsultaDetalhamentoDTO;
import med.voll.api.consulta.service.ConsultaService;
import med.voll.api.shared.enums.Especialidade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc // permite injetar o mockMvc
@AutoConfigureJsonTesters// para utilizar jacksonTester
public class ConsultaControllerTest {

	@Autowired // simula requisições
	private MockMvc mvc;

	@Autowired
	private JacksonTester<ConsultaDTO> dadosConsultaJson;

	@Autowired
	private JacksonTester<ConsultaDetalhamentoDTO> dadosDetalheConsultaJson;
	
	@MockBean
	private ConsultaService consultaService;
	
	@Test
	@DisplayName("Deve retornar código 400 quando informações inválidas!")
	@WithMockUser
	void agendar_cenario1() throws Exception {

		var response = mvc.perform(post("/consulta")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	@DisplayName("Deve retornar código 200 quando informações válidas.")
	@WithMockUser
	void agendar_censario2() throws IOException, Exception {
		
		var data = LocalDateTime.now().plusHours(1);
		var especialidade = Especialidade.CARDIOLOGIA;
		
		var dadosDetalhamento  = new ConsultaDetalhamentoDTO(null,1L,1L,especialidade,data);
		
		when(consultaService.agendar(any())).thenReturn(dadosDetalhamento);
		
		var response = mvc.perform(post("/consulta")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dadosConsultaJson.write(new ConsultaDTO(1L,1L,data,especialidade))
						.getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		var jsonEsperado = dadosDetalheConsultaJson.write(dadosDetalhamento
				).getJson();
		
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
		
	}
}
