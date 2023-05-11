package med.voll.api.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.shared.dto.EnderecoDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	private String cep;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "complemento")
	private String complemento;

	public Endereco(EnderecoDTO dto) {
		this.logradouro = dto.logradouro();
		this.bairro = dto.bairro();
		this.cep = dto.cep();
		this.cidade = dto.cidade();
		this.uf = dto.uf();
		this.numero = dto.numero();
		this.complemento = dto.complemento();
	}

	public void atualizarEndereco(EnderecoDTO dto) {

		if (dto.logradouro() != null) {
			this.logradouro = dto.logradouro();
		}
		if (dto.bairro() != null) {
			this.bairro = dto.bairro();
		}
		if (dto.cep() != null) {
			this.cep = dto.cep();
		}
		if (dto.cidade() != null) {
			this.cidade = dto.cidade();
		}
		if (dto.uf() != null) {
			this.uf = dto.uf();
		}
		if (dto.numero() != null) {
			this.numero = dto.numero();
		}
		if (dto.complemento() != null) {
			this.complemento = dto.complemento();
		}

	}
}
