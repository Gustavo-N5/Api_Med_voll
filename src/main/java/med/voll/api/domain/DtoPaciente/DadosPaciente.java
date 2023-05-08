package med.voll.api.domain.DtoPaciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.DtoMedico.DadosEndereco;

public record DadosPaciente(
		
		@NotBlank 
		String nome, 
		
		@NotBlank @Email 
		String email, 
		
		@NotBlank 
		String telefone,
		
		@NotBlank 
		@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") 
		String cpf,
		
		@NotNull 
		@Valid 
		DadosEndereco endereco

) {

}
