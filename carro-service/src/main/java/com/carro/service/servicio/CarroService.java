package com.carro.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.service.entity.Carro;
import com.carro.service.repository.ICarroRepository;

@Service
public class CarroService {

	@Autowired
	ICarroRepository cr;
	
	public List<Carro> getAll(){
		return cr.findAll();
	}
	
	public Carro getCarroById(int id) {
		return cr.findById(id).orElse(null);
	}
	
	public List<Carro> byUsuarioId(int usuarioId){
		return cr.findByUsuarioId(usuarioId);
	}
	
	public Carro guardar(Carro c) {
		Carro newCar = cr.save(c);
		return newCar;
	}
}
