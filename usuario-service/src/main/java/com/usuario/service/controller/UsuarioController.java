package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioServicio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioServicio us;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = us.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = us.getUsuarioById(id);
		if(usuario==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> guardaUsuario(@RequestBody Usuario u){
		Usuario nuevo = us.guardar(u);
		return ResponseEntity.ok(nuevo);
	}
	
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> listaCarros(@PathVariable("usuarioId") int usuarioId){
		Usuario usuario = us.getUsuarioById(usuarioId);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = us.getCarros(usuarioId);
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listaMotos(@PathVariable("usuarioId") int usuarioId){
		Usuario usuario = us.getUsuarioById(usuarioId);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = us.getMotos(usuarioId);
		return ResponseEntity.ok(motos);
	}
	
	@PostMapping("/carros/{usuarioId}")
	public ResponseEntity<Carro> guardaCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro c){
		Carro nuevoCar = us.saveCarro(usuarioId, c);
		return ResponseEntity.ok(nuevoCar);
	}
	
	@PostMapping("/motos/{usuarioId}")
	public ResponseEntity<Moto> guardaMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto m){
		Moto nuevoBike = us.saveMoto(usuarioId, m);
		return ResponseEntity.ok(nuevoBike);
	}

	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarVehiculos(@PathVariable("usuarioId") int usuarioId){
		Map<String, Object> resultado = us.getUsuarioVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}
}
