package br.com.pim.hotelaria.config.validacao;

import lombok.Getter;

@Getter
public class ErroDeFormDto {
	private String campo;
	private String erro;

	public ErroDeFormDto(String erro, String campo) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
}
