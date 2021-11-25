package br.com.pim.hotelaria.rest.form;

import java.time.LocalDate;

import br.com.pim.hotelaria.api.repository.QuartoRepository;
import br.com.pim.hotelaria.api.repository.ReservaRepository;
import br.com.pim.hotelaria.model.Reserva;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaForm {

	private Long id;
	private String nome;
	private String celular;
	private String email;
	private String cpf;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private Long quarto;
	
	public Reserva converter(QuartoRepository quartoRepository) {
		return new Reserva(nome, celular, email, cpf, dataEntrada, dataSaida, 
				quartoRepository.findById(quarto).get());
	}
	
	public Reserva atualizar(Long id, ReservaRepository reservaRepository, QuartoRepository quartoRepository) {
		Reserva reserva = reservaRepository.getOne(id);
		reserva.setNome(nome);
		reserva.setCelular(celular);
		reserva.setEmail(email);
		reserva.setCpf(cpf);
		reserva.setDataEntrada(dataEntrada);
		reserva.setDataSaida(dataSaida);
		reserva.setQuarto(quartoRepository.findById(quarto).get());
		return reserva;
	}
}
