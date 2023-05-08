package med.voll.api.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.DtoPaciente.DadosAtualizaPaciente;
import med.voll.api.domain.DtoPaciente.DadosPaciente;

@Entity(name = "paciente")
@Table(name = "pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String cpf;

	private String email;

	private String telefone;

	@Embedded
	private Endereco endereco;

	private boolean ativo = true;

	public Paciente(DadosPaciente dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.cpf = dados.cpf();
		this.endereco = new Endereco(dados.endereco());
	}

	public void atualizarInformacoes(DadosAtualizaPaciente dados) {
		if (dados.nome() != null)
			this.nome = dados.nome();

		if (dados.telefone() != null)
			this.telefone = dados.telefone();

		if (dados.endereco() != null)
			this.endereco.atualizarInformacoes(dados.endereco());
	}

	public void inativo() {
		this.ativo = false;
		
	}
}
