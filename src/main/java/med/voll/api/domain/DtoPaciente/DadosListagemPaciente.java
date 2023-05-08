package med.voll.api.domain.DtoPaciente;

import med.voll.api.domain.entity.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf, String telefone) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
    }
}
