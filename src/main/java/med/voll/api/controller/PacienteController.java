package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.DtoPaciente.DadosAtualizaPaciente;
import med.voll.api.domain.DtoPaciente.DadosDetalhadoPaciente;
import med.voll.api.domain.DtoPaciente.DadosListagemPaciente;
import med.voll.api.domain.DtoPaciente.DadosPaciente;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.domain.repositories.PacienteRepository;

@RestController
@RequestMapping("paciente")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosPaciente dados, UriComponentsBuilder uribuild) {
		var paciente = new Paciente(dados);
		pacienteRepository.save(paciente);

		var uri = uribuild.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhadoPaciente(paciente));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listar(
			@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosAtualizaPaciente dados) {
		var paciente = pacienteRepository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhadoPaciente(paciente));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		var paciente = pacienteRepository.getReferenceById(id);
		paciente.inativo();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity buscar(@PathVariable Long id) {
		var paciente = pacienteRepository.getReferenceById(id);		
		return ResponseEntity.ok(new DadosDetalhadoPaciente(paciente));
	}
}
