package com.usuario.service.modelos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Moto {
	
	private String marca;
	private String modelo;
	private int usuarioId;

}
