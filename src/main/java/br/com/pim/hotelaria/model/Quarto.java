package br.com.pim.hotelaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.pim.hotelaria.api.repository.QuartoRepository;
import br.com.pim.hotelaria.api.repository.ReservaRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Quarto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private double preco;
	private int numero;
	private int quantidade_lugares;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + numero;
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantidade_lugares;
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
		Quarto other = (Quarto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero != other.numero)
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (quantidade_lugares != other.quantidade_lugares)
			return false;
		return true;
	}
	public Quarto atualizar(Long id, QuartoRepository quartoRepository) {
		Quarto quarto = quartoRepository.getOne(id);
		quarto.setDescricao(descricao);
		quarto.setPreco(preco);
		quarto.setNumero(numero);
		quarto.setQuantidade_lugares(quantidade_lugares);
		return quarto;
	}
	
}
