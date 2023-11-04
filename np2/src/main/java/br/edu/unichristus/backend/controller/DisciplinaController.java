package br.edu.unichristus.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.backend.data.dto.DisciplinaDTO;
import br.edu.unichristus.backend.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/disciplina")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService service;

	@Operation(summary = "Cadastra os dados de uma disciplina | role: [ADMIN]", tags = "Disciplina")
	@PostMapping
	public DisciplinaDTO create(@RequestBody DisciplinaDTO disciplina) {
		return service.save(disciplina);
	}
	
	@Operation(summary = "Edita os dados de uma disciplina | role: [ADMIN]", tags = "Disciplina")
	@PutMapping
	public DisciplinaDTO update(@RequestBody DisciplinaDTO disciplina) {
		return service.save(disciplina);
	}
	
	@Operation(summary = "Retorna os dados de uma disciplina a partir do ID | role: [ADMIN]", tags = "Disciplina")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200", description = "Disciplina retornada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Disciplina não encontrada"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor - unichristus.backend.service.disciplina.notfound.exception") })
	@GetMapping("/{id}")
	public DisciplinaDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@Operation(summary = "Deleta uma disciplina | role: [ADMIN]", tags = "Disciplina")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@Operation(summary = "Lista dados de todas as disciplina | role: [ADMIN]", tags = "Disciplina")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
}
