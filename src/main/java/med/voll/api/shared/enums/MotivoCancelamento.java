package med.voll.api.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MotivoCancelamento {
	
	DESISTIU(1,"Desistência"),
	CANCELAMENTO(2,"Médico Cancelou consulta"),
	OUTROS(3,"Outros");
	
	private Integer codigo;
	private String descricao;

}
