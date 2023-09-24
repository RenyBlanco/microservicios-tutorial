package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entity.Carro;
import com.carro.service.servicio.CarroService;

@RestController
@RequestMapping("/carros")
public class CarroController {
	
	@Autowired
	CarroService cs;
	
	@GetMapping
	public ResponseEntity<List<Carro>> listarCarros(){
		List<Carro> carros = cs.getAll();
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro> carroById(@PathVariable("id") int id){
		Carro car = cs.getCarroById(id);
		if(car==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}
	
	@GetMapping("/usuarios/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarroByUsuario(@PathVariable("usuarioId") int usuarioId){
		List<Carro> lista = cs.byUsuarioId(usuarioId);
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	public ResponseEntity<Carro> guardaCarro(@RequestBody Carro c){
		Carro newCar = cs.guardar(c);
		return ResponseEntity.ok(newCar);
	}
}
