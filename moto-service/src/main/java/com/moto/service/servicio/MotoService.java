package com.moto.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.IMotoRepository;

@Service
public class MotoService {
	
	@Autowired
	IMotoRepository mr;
	
	public List<Moto> getAll(){
		return mr.findAll();
	}
	
	public Moto getMotoById(int id) {
		return mr.findById(id).orElse(null);
	}
	
	public List<Moto> byUsuarioId(int usuarioId){
		return mr.findByUsuarioId(usuarioId);
	}
	
	public Moto guardar(Moto m) {
		Moto newBike = mr.save(m);
		return newBike;
	}

}
