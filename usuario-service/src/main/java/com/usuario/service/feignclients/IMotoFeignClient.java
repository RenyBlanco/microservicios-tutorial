package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Moto;

@FeignClient(name="moto-service", url= "http://localhost:8003")
public interface IMotoFeignClient {

	@GetMapping("motos/usuarios/{usuarioId}")
	public List<Moto> getMotos(@PathVariable("usuarioId") int usuarioId);
	
	@PostMapping("/motos")
	public Moto save(@RequestBody Moto m);
}
