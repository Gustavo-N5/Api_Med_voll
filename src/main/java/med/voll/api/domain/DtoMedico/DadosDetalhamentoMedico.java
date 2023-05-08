package med.voll.api.domain.DtoMedico;

import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String telefone, String crm,
		Especialidade especialidade, Endereco endereco) {

	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(),
				medico.getEspecialidade(), medico.getEndereco());
	}
}
