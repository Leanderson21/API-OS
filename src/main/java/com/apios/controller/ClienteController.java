package com.apios.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apios.model.Cliente;
import com.apios.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clrp; 	
	
	@GetMapping
	public List<Cliente> listar() {
		return clrp.findAll();
	}
	
	@GetMapping("/n")
	public ResponseEntity<List<Cliente>> buscanome(){
	
		List<Cliente> cliente =  clrp.findByNomeContaining("le");
			
			if (!cliente.isEmpty()) {
				return ResponseEntity.ok(cliente);
				
			}else {
				return ResponseEntity.notFound().build();
			}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> busca(@PathVariable Long id){
		Optional<Cliente> cliente = clrp.findById(id);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public Cliente cadastrar(@RequestBody Cliente cliente) {
		return clrp.save(cliente);
	}
	
	
	
}
