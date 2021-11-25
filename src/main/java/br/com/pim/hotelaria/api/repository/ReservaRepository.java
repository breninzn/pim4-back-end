package br.com.pim.hotelaria.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pim.hotelaria.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
