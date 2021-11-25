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
import br.com.pim.hotelaria.api.repository.ReservaRepository;
import br.com.pim.hotelaria.model.Reserva;
import br.com.pim.hotelaria.rest.form.ReservaForm;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("reservas")
public class ReservasController {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Autowired
	QuartoRepository quartoRepository;
	
	@GetMapping
	public Page<Reserva> lista( @PageableDefault(sort= "dataEntrada", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Reserva> reservas = reservaRepository.findAll(paginacao);
		return reservas;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<Reserva> cadastrar(@RequestBody @Valid ReservaForm reserva, UriComponentsBuilder uriBuilder) {
		Reserva reservaEntity = reserva.converter(quartoRepository);
		reservaRepository.save(reservaEntity);
		URI uri = uriBuilder.path("/reservas/{id}").buildAndExpand(reservaEntity.getId()).toUri();
		return ResponseEntity.created(uri).body(reservaEntity);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> datalhar(@PathVariable Long id) {
		Optional<Reserva> reserva = reservaRepository.findById(id);
		if (reserva.isPresent()) {
			return ResponseEntity.ok(reserva.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Reserva> atualizar(@PathVariable Long id, @RequestBody ReservaForm form, UriComponentsBuilder uriBuilder) {
		Optional<Reserva> reservaOptional = reservaRepository.findById(id);
		if (reservaOptional.isPresent()) {
			Reserva reserva = form.atualizar(id, reservaRepository, quartoRepository);
			return ResponseEntity.ok(reserva);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Reserva> reservaOptional = reservaRepository.findById(id);
		if (reservaOptional.isPresent()) {
			reservaRepository.delete(reservaOptional.get());
			return ResponseEntity.ok().build();			
		};
		return ResponseEntity.notFound().build();
	}
}
