package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignclients.ICarroFeignClient;
import com.usuario.service.feignclients.IMotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repository.IUsuarioRepositorio;

@Service
public class UsuarioServicio {
	
	@Autowired
	ICarroFeignClient cfc;
	
	@Autowired
	IMotoFeignClient mfc;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	IUsuarioRepositorio ur;

	public List<Usuario> getAll(){
		return ur.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return ur.findById(id).orElse(null);
	}
	
	public Usuario guardar(Usuario u) {
		Usuario newUser = ur.save(u);
		return newUser;
	}
	
	/* -- Servicios con RestTemplate-- */
	
	@SuppressWarnings("unchecked")
	public List<Carro> getCarros(int usuarioId){
		List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carros/usuarios/"+ usuarioId, List.class);
		return carros;
	}
	
	@SuppressWarnings("unchecked")
	public List<Moto> getMotos(int usuarioId){
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/motos/usuarios/"+ usuarioId, List.class);
		return motos;
	}
	
	/* Servicios con FeignClient */
	
	public Carro saveCarro(int usuarioId, Carro c) {
		c.setUsuarioId(usuarioId);
		Carro newCar = cfc.save(c);
		return newCar;
	}
		
	public Moto saveMoto(int usuarioId, Moto m) {
		m.setUsuarioId(usuarioId);
		Moto newBike = mfc.save(m);
		return newBike;
	}
	
	public Map<String, Object> getUsuarioVehiculos(int usuarioId){
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = ur.findById(usuarioId).orElse(null);
		if(usuario==null) {
			resultado.put("Mensaje", "Usuario no existe");
		}else {
			resultado.put("Usuario", usuario);	
		}
		
		List<Carro> carros = cfc.getCarros(usuarioId);
		if(carros.isEmpty()) {
			resultado.put("Mensaje", "El Usuario no tiene carros");
		}else {
			resultado.put("Carros", carros);	
		}
		
		List<Moto> motos = mfc.getMotos(usuarioId);
		if(motos==null) {
			resultado.put("Mensaje", "El Usuario no tiene motos");
		}else {
			resultado.put("Motos", motos);	
		}
		return resultado;
		
	}
}

