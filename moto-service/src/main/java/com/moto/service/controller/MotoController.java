package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.servicio.MotoService;

@RestController
@RequestMapping("/motos")
public class MotoController {
	
	@Autowired
	MotoService ms;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos(){
		List<Moto> motos = ms.getAll();
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> motoById(@PathVariable("id") int id){
		Moto bike = ms.getMotoById(id);
		if(bike==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bike);
	}
	
	@GetMapping("/usuarios/{usuarioId}")
	public ResponseEntity<List<Moto>> listarCarroByUsuario(@PathVariable("usuarioId") int usuarioId){
		List<Moto> lista = ms.byUsuarioId(usuarioId);
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	public ResponseEntity<Moto> guardaCarro(@RequestBody Moto b){
		Moto newBike = ms.guardar(b);
		return ResponseEntity.ok(newBike);
	}

}
