package br.com.pim.hotelaria.rest;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pim.hotelaria.api.repository.QuartoRepository;
import br.com.pim.hotelaria.model.Quarto;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("quartos")
public class QuartosController {
	
	@Autowired
	QuartoRepository quartoRepository;
	
	@GetMapping
	public Page<Quarto> lista( @PageableDefault(sort= "descricao", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Quarto> quartos = quartoRepository.findAll(paginacao);
		return quartos;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<Quarto> cadastrar(@RequestBody @Valid Quarto quarto, UriComponentsBuilder uriBuilder) {
		quartoRepository.save(quarto);
		URI uri = uriBuilder.path("/quartos/{id}").buildAndExpand(quarto.getId()).toUri();
		return ResponseEntity.created(uri).body(quarto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Quarto> datalhar(@PathVariable Long id) {
		Optional<Quarto> quarto = quartoRepository.findById(id);
		if (quarto.isPresent()) {
			return ResponseEntity.ok(quarto.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Quarto> atualizar(@PathVariable Long id, @RequestBody Quarto form, UriComponentsBuilder uriBuilder) {
		Optional<Quarto> quartoOptional = quartoRepository.findById(id);
		if (quartoOptional.isPresent()) {
			Quarto quarto = form.atualizar(id, quartoRepository);
			return ResponseEntity.ok(quarto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Quarto> quartoOptional = quartoRepository.findById(id);
		if (quartoOptional.isPresent()) {
			quartoRepository.delete(quartoOptional.get());
			return ResponseEntity.ok().build();			
		};
		return ResponseEntity.notFound().build();
	}
}
