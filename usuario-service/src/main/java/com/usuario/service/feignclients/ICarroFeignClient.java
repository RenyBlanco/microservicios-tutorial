package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;

@FeignClient(name="carro-service", url= "http://localhost:8002")
public interface ICarroFeignClient {
	
	@GetMapping("/carros/usuarios/{usuarioId}")
	public List<Carro> getCarros(@PathVariable("usuarioId") int usuarioId);
	
	@PostMapping("/carros")
	public Carro save(@RequestBody Carro c);
}
