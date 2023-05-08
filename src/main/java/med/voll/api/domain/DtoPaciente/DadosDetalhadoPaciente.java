package med.voll.api.domain.DtoPaciente;

import med.voll.api.domain.entity.Paciente;

public record DadosDetalhadoPaciente(Long id, String nome, String email, String cpf, String telefone) {
	public DadosDetalhadoPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
	}
}
