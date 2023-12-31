package com.capgemini.projetospring.controllers.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.projetospring.dto.ItemDTO;
import com.capgemini.projetospring.dto.ItensPedidoDTO;
import com.capgemini.projetospring.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class ApiItemController {

	@Autowired
	private ItemService itemService;
	
	@CrossOrigin
	@PostMapping("/")
	public ResponseEntity<ItemDTO> incluirItemDTO(@RequestBody Map<String, String> dados){
		return new ResponseEntity<ItemDTO>(itemService.adicionarItemDTO(dados), HttpStatus.CREATED);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<String> incluirItem(@RequestBody Map<String, String> dados){
		return new ResponseEntity<String>(itemService.adicionarItem(dados), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping(path = {"/", "/{id}"})
	public ResponseEntity<List<ItensPedidoDTO>> listarItens(
			@PathVariable(name = "id", required = false) Integer id){
		
		try {
			if(id != null && id > 0) {
				return new ResponseEntity<List<ItensPedidoDTO>>(itemService.listarItens(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ItensPedidoDTO>>(itemService.listarItens(), HttpStatus.OK);
			}
		} catch (Exception e) {
			//throw new RuntimeException(e);
			return new ResponseEntity<List<ItensPedidoDTO>>(
				new ArrayList<ItensPedidoDTO>(), HttpStatus.NOT_FOUND);
		}
		
	}
}









