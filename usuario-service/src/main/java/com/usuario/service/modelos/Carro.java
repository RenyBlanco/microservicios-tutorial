package com.usuario.service.modelos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Carro {
	
	private String marca;
	private String modelo;
	private int usuarioId;

}
