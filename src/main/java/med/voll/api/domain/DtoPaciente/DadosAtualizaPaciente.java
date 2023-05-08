package med.voll.api.domain.DtoPaciente;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.DtoMedico.DadosEndereco;


public record DadosAtualizaPaciente(
		
		@NotNull
		Long id,
		String nome, 
		String telefone, 
		@Valid DadosEndereco endereco  ) {
}
