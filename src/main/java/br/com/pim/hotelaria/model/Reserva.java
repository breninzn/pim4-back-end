package br.com.pim.hotelaria.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.pim.hotelaria.api.repository.QuartoRepository;
import br.com.pim.hotelaria.api.repository.ReservaRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Reserva {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    id;
	
	private String  nome;
	private String  celular;
	private String  email;
	private String  cpf;
	private LocalDate   dataEntrada;
	private LocalDate    dataSaida;
	@ManyToOne
	private Quarto  quarto;
	
	public Reserva(String nome, String celular, String email, String cpf, LocalDate dataEntrada, LocalDate dataSaida,
			Quarto quarto) {
		this.nome = nome;
		this.celular = celular;
		this.email = email;
		this.cpf = cpf;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.quarto = quarto;
	}
	
	public Reserva() {}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public Reserva atualizar(Long id, ReservaRepository reservaRepository, QuartoRepository quartoRepository) {
		Reserva reserva = reservaRepository.getOne(id);
		reserva.setNome(nome);
		reserva.setCelular(celular);
		reserva.setEmail(email);
		reserva.setCpf(cpf);
		reserva.setDataEntrada(dataEntrada);
		reserva.setDataSaida(dataSaida);
		reserva.setQuarto(quartoRepository.findById(quarto.getId()).get());
		return reserva;
	}
	
}
